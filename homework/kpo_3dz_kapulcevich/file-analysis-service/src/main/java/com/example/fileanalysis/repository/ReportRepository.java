package com.example.fileanalysis.repository;

import com.example.fileanalysis.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByAssignmentNameIgnoreCase(String assignmentName);
    List<Report> findBySubmissionId(Long submissionId);
}
