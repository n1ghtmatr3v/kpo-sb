package com.example.gateway.controller;

import com.example.gateway.dto.ReportResponse;
import com.example.gateway.dto.SubmissionReportResponse;
import com.example.gateway.service.GatewayService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GatewayController {

    private final GatewayService gatewayService;

    public GatewayController(GatewayService gatewayService) {
        this.gatewayService = gatewayService;
    }

    @PostMapping(value = "/submissions", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public SubmissionReportResponse submit(@RequestPart("file") MultipartFile file,
                                           @RequestPart("studentName") @NotBlank String studentName,
                                           @RequestPart("assignmentName") @NotBlank String assignmentName) {
        return gatewayService.uploadAndAnalyze(file, studentName, assignmentName);
    }

    @GetMapping("/assignments/{assignment}/reports")
    public List<ReportResponse> reports(@PathVariable String assignment) {
        return gatewayService.reportsForAssignment(assignment);
    }
}
