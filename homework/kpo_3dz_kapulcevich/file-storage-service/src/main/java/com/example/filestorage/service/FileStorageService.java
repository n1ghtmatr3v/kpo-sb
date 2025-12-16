package com.example.filestorage.service;

import com.example.filestorage.dto.SubmissionResponse;
import com.example.filestorage.model.Submission;
import com.example.filestorage.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FileStorageService {

    private final SubmissionRepository repository;
    private final HashService hashService;
    private final Path storageDirectory;

    public FileStorageService(SubmissionRepository repository,
                              HashService hashService,
                              @Value("${storage.location:data/files}") String storageLocation) throws IOException {
        this.repository = repository;
        this.hashService = hashService;
        this.storageDirectory = Paths.get(storageLocation).toAbsolutePath();
        Files.createDirectories(this.storageDirectory);
    }

    @Transactional
    public SubmissionResponse save(MultipartFile file, String studentName, String assignmentName) throws IOException {
        String storedFilename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path target = storageDirectory.resolve(storedFilename);
        byte[] bytes = file.getBytes();
        Files.write(target, bytes);

        Submission submission = new Submission();
        submission.setStudentName(studentName);
        submission.setAssignmentName(assignmentName);
        submission.setOriginalFilename(file.getOriginalFilename());
        submission.setStoredFilename(storedFilename);
        submission.setStoredPath(target.toString());
        submission.setContentHash(hashService.sha256(bytes));
        submission.setCreatedAt(OffsetDateTime.now());

        Submission saved = repository.save(submission);
        return map(saved);
    }

    public Resource loadFile(Long id) {
        Submission submission = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Submission not found: " + id));
        return new FileSystemResource(submission.getStoredPath());
    }

    public SubmissionResponse findOne(Long id) {
        return repository.findById(id).map(this::map)
                .orElseThrow(() -> new IllegalArgumentException("Submission not found: " + id));
    }

    public List<SubmissionResponse> findByAssignment(String assignmentName) {
        return repository.findByAssignmentNameIgnoreCase(assignmentName).stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    private SubmissionResponse map(Submission submission) {
        return new SubmissionResponse(
                submission.getId(),
                submission.getStudentName(),
                submission.getAssignmentName(),
                submission.getOriginalFilename(),
                submission.getStoredFilename(),
                submission.getStoredPath(),
                submission.getContentHash(),
                submission.getCreatedAt()
        );
    }
}
