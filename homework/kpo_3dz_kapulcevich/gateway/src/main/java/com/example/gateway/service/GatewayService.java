package com.example.gateway.service;

import com.example.gateway.dto.ReportResponse;
import com.example.gateway.dto.SubmissionReportResponse;
import com.example.gateway.dto.SubmissionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Service
public class GatewayService {

    private final RestTemplate restTemplate;
    private final String storageUrl;
    private final String analysisUrl;

    public GatewayService(RestTemplate restTemplate,
                          @Value("${services.storage.url:http://localhost:8081}") String storageUrl,
                          @Value("${services.analysis.url:http://localhost:8082}") String analysisUrl) {
        this.restTemplate = restTemplate;
        this.storageUrl = storageUrl;
        this.analysisUrl = analysisUrl;
    }

    public SubmissionReportResponse uploadAndAnalyze(MultipartFile file, String studentName, String assignmentName) {
        SubmissionResponse submission = upload(file, studentName, assignmentName);
        ReportResponse report = analyze(submission.getId());
        return new SubmissionReportResponse(submission, report);
    }

    public List<ReportResponse> reportsForAssignment(String assignment) {
        ResponseEntity<ReportResponse[]> entity = restTemplate.getForEntity(
                analysisUrl + "/reports/assignment/" + assignment, ReportResponse[].class);
        return Arrays.asList(entity.getBody());
    }

    private SubmissionResponse upload(MultipartFile file, String studentName, String assignmentName) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new MultipartInputStreamFileResource(file));
        body.add("studentName", studentName);
        body.add("assignmentName", assignmentName);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<SubmissionResponse> response = restTemplate.postForEntity(
                storageUrl + "/files", requestEntity, SubmissionResponse.class);
        return response.getBody();
    }

    private ReportResponse analyze(Long submissionId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>("{\"submissionId\": " + submissionId + "}", headers);
        ResponseEntity<ReportResponse> response = restTemplate.postForEntity(
                analysisUrl + "/reports/analyze", entity, ReportResponse.class);
        return response.getBody();
    }

    private static class MultipartInputStreamFileResource extends ByteArrayResource {
        MultipartInputStreamFileResource(MultipartFile file) {
            super(extractBytes(file));
            this.filename = file.getOriginalFilename();
        }

        private final String filename;

        @Override
        public String getFilename() {
            return this.filename;
        }
    }

    private static byte[] extractBytes(MultipartFile file) {
        try {
            return file.getBytes();
        } catch (Exception e) {
            throw new IllegalStateException("Cannot read multipart file", e);
        }
    }
}
