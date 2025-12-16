package com.example.fileanalysis.dto;

import java.time.OffsetDateTime;

public class SubmissionDto {
    private Long id;
    private String studentName;
    private String assignmentName;
    private String originalFilename;
    private String storedFilename;
    private String storedPath;
    private String contentHash;
    private OffsetDateTime createdAt;

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
