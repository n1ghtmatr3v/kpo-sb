package com.example.gateway.dto;

public class SubmissionReportResponse {
    private SubmissionResponse submission;
    private ReportResponse report;

    public SubmissionReportResponse() {
    }

    public SubmissionReportResponse(SubmissionResponse submission, ReportResponse report) {
        this.submission = submission;
        this.report = report;
    }

    public SubmissionResponse getSubmission() {
        return submission;
    }

    public ReportResponse getReport() {
        return report;
    }
}
