package com.example.fileanalysis.model;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long submissionId;

    @Column(nullable = false)
    private String assignmentName;

    @Column(nullable = false)
    private String studentName;

    @Column(nullable = false)
    private boolean plagiarismDetected;

    private Long conflictingSubmissionId;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private OffsetDateTime createdAt;

    @Column(length = 2000)
    private String details;

    public Long getId() {
        return id;
    }

    public Long getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Long submissionId) {
        this.submissionId = submissionId;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public boolean isPlagiarismDetected() {
        return plagiarismDetected;
    }

    public void setPlagiarismDetected(boolean plagiarismDetected) {
        this.plagiarismDetected = plagiarismDetected;
    }

    public Long getConflictingSubmissionId() {
        return conflictingSubmissionId;
    }

    public void setConflictingSubmissionId(Long conflictingSubmissionId) {
        this.conflictingSubmissionId = conflictingSubmissionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
