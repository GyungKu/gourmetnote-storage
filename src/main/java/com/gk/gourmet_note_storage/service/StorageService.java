package com.gk.gourmet_note_storage.service;

import com.gk.gourmet_note_storage.vo.ResponseFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class StorageService {

    @Value("${upload.image.dir}")
    private String dir;

    public List<ResponseFile> saveFiles(List<MultipartFile> files) throws IOException {
        if (files == null) return new ArrayList<>();

        List<ResponseFile> responseFiles = new ArrayList<>();
        for (MultipartFile file : files) {
            String originName = file.getOriginalFilename();
            String saveFileName = getSaveFileName(originName);
            file.transferTo(new File(dir + saveFileName));

            responseFiles.add(ResponseFile.builder()
                    .originName(originName)
                    .saveName(saveFileName)
                    .build());
        }

        return responseFiles;
    }

    private String getSaveFileName(String originName) {
        String uuid = UUID.randomUUID().toString();
        return uuid + originName.substring(originName.lastIndexOf("."));
    }

}
