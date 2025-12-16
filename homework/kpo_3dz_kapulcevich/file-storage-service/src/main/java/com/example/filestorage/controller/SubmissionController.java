package com.example.filestorage.controller;

import com.example.filestorage.dto.SubmissionResponse;
import com.example.filestorage.service.FileStorageService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/files")
public class SubmissionController {

    private final FileStorageService service;

    public SubmissionController(FileStorageService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public SubmissionResponse upload(@RequestPart("file") MultipartFile file,
                                     @RequestPart("studentName") @NotBlank String studentName,
                                     @RequestPart("assignmentName") @NotBlank String assignmentName) throws IOException {
        return service.save(file, studentName, assignmentName);
    }

    @GetMapping("/{id}/metadata")
    public SubmissionResponse metadata(@PathVariable Long id) {
        return service.findOne(id);
    }

    @GetMapping("/{id}/content")
    public ResponseEntity<Resource> download(@PathVariable Long id) {
        Resource resource = service.loadFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + resource.getFilename())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @GetMapping("/assignments/{assignmentName}")
    public List<SubmissionResponse> byAssignment(@PathVariable String assignmentName) {
        return service.findByAssignment(assignmentName);
    }
}
