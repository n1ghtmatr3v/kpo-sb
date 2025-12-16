package com.example.fileanalysis.dto;

import jakarta.validation.constraints.NotNull;

public class ReportRequest {
    @NotNull
    private Long submissionId;

    public Long getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Long submissionId) {
        this.submissionId = submissionId;
    }
}
