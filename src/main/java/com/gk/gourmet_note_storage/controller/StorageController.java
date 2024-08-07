package com.gk.gourmet_note_storage.controller;

import com.gk.gourmet_note_storage.service.StorageService;
import com.gk.gourmet_note_storage.vo.ResponseFile;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/storage")
public class StorageController {

    private final StorageService storageService;

    @PostMapping
    public ResponseEntity<List<ResponseFile>> saveFiles(@RequestPart List<MultipartFile> files) throws IOException {
        List<ResponseFile> responseFiles = storageService.saveFiles(files);
        return ResponseEntity.ok(responseFiles);
    }
}
