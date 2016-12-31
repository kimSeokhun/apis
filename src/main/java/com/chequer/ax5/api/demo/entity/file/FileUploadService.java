package com.chequer.ax5.api.demo.entity.file;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileUploadService {

    @Autowired
    private FilePersistService filePersistService;

    public AX5File upload(MultipartFile multipartFile) throws IOException {
        AX5File file = AX5File.of(multipartFile);
        filePersistService.persist(file);

        return file;
    }

    public List<AX5File> files() {
        return filePersistService.listFiles();
    }
}
