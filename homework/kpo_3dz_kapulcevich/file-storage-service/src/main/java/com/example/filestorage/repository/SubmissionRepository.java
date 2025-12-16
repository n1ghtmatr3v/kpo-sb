package com.example.filestorage.repository;

import com.example.filestorage.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> findByAssignmentNameIgnoreCase(String assignmentName);
}
