package com.example.fileanalysis.controller;

import com.example.fileanalysis.dto.ReportRequest;
import com.example.fileanalysis.dto.ReportResponse;
import com.example.fileanalysis.service.ReportService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/analyze")
    public ReportResponse analyze(@Valid @RequestBody ReportRequest request) {
        return reportService.analyze(request.getSubmissionId());
    }

    @GetMapping("/assignment/{assignment}")
    public List<ReportResponse> byAssignment(@PathVariable String assignment) {
        return reportService.getReportsByAssignment(assignment);
    }

    @GetMapping("/submission/{submissionId}")
    public List<ReportResponse> bySubmission(@PathVariable Long submissionId) {
        return reportService.getReportsBySubmission(submissionId);
    }
}
