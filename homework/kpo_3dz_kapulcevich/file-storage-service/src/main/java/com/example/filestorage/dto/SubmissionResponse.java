package com.example.filestorage.dto;

import java.time.OffsetDateTime;

public class SubmissionResponse {
    private Long id;
    private String studentName;
    private String assignmentName;
    private String originalFilename;
    private String storedFilename;
    private String storedPath;
    private String contentHash;
    private OffsetDateTime createdAt;

    public SubmissionResponse() {
    }

    public SubmissionResponse(Long id, String studentName, String assignmentName, String originalFilename,
                              String storedFilename, String storedPath, String contentHash, OffsetDateTime createdAt) {
        this.id = id;
        this.studentName = studentName;
        this.assignmentName = assignmentName;
        this.originalFilename = originalFilename;
        this.storedFilename = storedFilename;
        this.storedPath = storedPath;
        this.contentHash = contentHash;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public String getStoredFilename() {
        return storedFilename;
    }

    public String getStoredPath() {
        return storedPath;
    }

    public String getContentHash() {
        return contentHash;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}
