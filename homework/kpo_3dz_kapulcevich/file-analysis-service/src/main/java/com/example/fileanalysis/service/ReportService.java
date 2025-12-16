package com.example.fileanalysis.service;

import com.example.fileanalysis.dto.ReportResponse;
import com.example.fileanalysis.dto.SubmissionDto;
import com.example.fileanalysis.model.Report;
import com.example.fileanalysis.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private final ReportRepository repository;
    private final RestTemplate restTemplate;
    private final WordCloudService wordCloudService;
    private final String storageBaseUrl;

    public ReportService(ReportRepository repository,
                         RestTemplate restTemplate,
                         WordCloudService wordCloudService,
                         @Value("${services.storage.url:http://localhost:8081}") String storageBaseUrl) {
        this.repository = repository;
        this.restTemplate = restTemplate;
        this.wordCloudService = wordCloudService;
        this.storageBaseUrl = storageBaseUrl;
    }

    @Transactional
    public ReportResponse analyze(Long submissionId) {
        SubmissionDto target = fetchSubmission(submissionId);
        List<SubmissionDto> submissions = fetchSubmissionsForAssignment(target.getAssignmentName());

        Optional<SubmissionDto> conflict = submissions.stream()
                .filter(item -> !item.getId().equals(submissionId))
                .filter(item -> !item.getStudentName().equalsIgnoreCase(target.getStudentName()))
                .filter(item -> item.getContentHash().equals(target.getContentHash()))
                .sorted((a, b) -> a.getCreatedAt().compareTo(b.getCreatedAt()))
                .findFirst();

        Report report = new Report();
        report.setSubmissionId(submissionId);
        report.setAssignmentName(target.getAssignmentName());
        report.setStudentName(target.getStudentName());
        report.setCreatedAt(OffsetDateTime.now());
        report.setStatus("FINISHED");

        if (conflict.isPresent()) {
            SubmissionDto matching = conflict.get();
            report.setPlagiarismDetected(true);
            report.setConflictingSubmissionId(matching.getId());
            report.setDetails("Работа совпадает с ранее загруженной №" + matching.getId());
        } else {
            report.setPlagiarismDetected(false);
            report.setDetails("Совпадений не обнаружено");
        }

        String text = downloadContent(submissionId);
        report.setDetails(report.getDetails());

        Report saved = repository.save(report);
        String wordCloudUrl = wordCloudService.buildWordCloudUrl(text);
        return map(saved, wordCloudUrl);
    }

    public List<ReportResponse> getReportsByAssignment(String assignment) {
        return repository.findByAssignmentNameIgnoreCase(assignment).stream()
                .map(report -> map(report, null))
                .collect(Collectors.toList());
    }

    public List<ReportResponse> getReportsBySubmission(Long submissionId) {
        return repository.findBySubmissionId(submissionId).stream()
                .map(report -> map(report, null))
                .collect(Collectors.toList());
    }

    private SubmissionDto fetchSubmission(Long id) {
        ResponseEntity<SubmissionDto> entity = restTemplate.getForEntity(
                storageBaseUrl + "/files/" + id + "/metadata", SubmissionDto.class);
        return entity.getBody();
    }

    private List<SubmissionDto> fetchSubmissionsForAssignment(String assignment) {
        ResponseEntity<SubmissionDto[]> entity = restTemplate.getForEntity(
                storageBaseUrl + "/files/assignments/" + assignment, SubmissionDto[].class);
        return Arrays.asList(entity.getBody());
    }

    private String downloadContent(Long submissionId) {
        byte[] bytes = restTemplate.getForObject(storageBaseUrl + "/files/" + submissionId + "/content", byte[].class);
        return bytes == null ? "" : new String(bytes);
    }

    private ReportResponse map(Report report, String wordCloudUrl) {
        return new ReportResponse(
                report.getId(),
                report.getSubmissionId(),
                report.getAssignmentName(),
                report.getStudentName(),
                report.isPlagiarismDetected(),
                report.getConflictingSubmissionId(),
                report.getStatus(),
                report.getDetails(),
                report.getCreatedAt(),
                wordCloudUrl
        );
    }
}
