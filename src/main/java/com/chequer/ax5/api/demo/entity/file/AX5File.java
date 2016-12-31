package com.chequer.ax5.api.demo.entity.file;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AX5File {

    private String id;

    private String fileName;

    private String ext;

    private long fileSize;

    private String createdAt;

    @JsonIgnore
    private MultipartFile multipartFile;

    public static AX5File of(MultipartFile multipartFile) {
        AX5File ax5File = new AX5File();

        ax5File.setId(UUID.randomUUID().toString());
        ax5File.setFileName(FilenameUtils.getName(multipartFile.getOriginalFilename()));
        ax5File.setExt(FilenameUtils.getExtension(multipartFile.getOriginalFilename()));
        ax5File.setFileSize(multipartFile.getSize());
        ax5File.setCreatedAt(LocalDateTime.now().toString());
        ax5File.setMultipartFile(multipartFile);

        return ax5File;
    }

    public String getSaveName() {
        return String.format("%s.%s", id, ext);
    }

    public String getJsonName() {
        return String.format("%s.json", id, ext);
    }
}
