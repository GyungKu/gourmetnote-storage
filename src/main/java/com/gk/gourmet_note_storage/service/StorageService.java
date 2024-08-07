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

    @Value("${upload.image.url}")
    private String url;

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
                    .url(url + saveFileName)
                    .build());
        }

        return responseFiles;
    }

    public void deleteFilesFromSaveNames(List<String> saveNames) {
        saveNames.forEach(name -> {
            File file = new File(dir + name);
            if (file.exists()) file.delete();
        });
    }

    private String getSaveFileName(String originName) {
        String uuid = UUID.randomUUID().toString();
        return uuid + originName.substring(originName.lastIndexOf("."));
    }

}
