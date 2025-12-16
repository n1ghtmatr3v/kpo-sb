package com.example.gateway.dto;

import java.time.OffsetDateTime;

public class ReportResponse {
    private Long id;
    private Long submissionId;
    private String assignmentName;
    private String studentName;
    private boolean plagiarismDetected;
    private Long conflictingSubmissionId;
    private String status;
    private String details;
    private OffsetDateTime createdAt;
    private String wordCloudUrl;

    public Long getId() {
        return id;
    }

    public Long getSubmissionId() {
        return submissionId;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public String getStudentName() {
        return studentName;
    }

    public boolean isPlagiarismDetected() {
        return plagiarismDetected;
    }

    public Long getConflictingSubmissionId() {
        return conflictingSubmissionId;
    }

    public String getStatus() {
        return status;
    }

    public String getDetails() {
        return details;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public String getWordCloudUrl() {
        return wordCloudUrl;
    }
}
