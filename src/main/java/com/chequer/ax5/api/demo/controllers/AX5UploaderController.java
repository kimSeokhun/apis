package com.chequer.ax5.api.demo.controllers;

import com.chequer.ax5.api.demo.entity.file.AX5File;
import com.chequer.ax5.api.demo.entity.file.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/ax5uploader")
public class AX5UploaderController extends BaseController {

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping
    public AX5File upload(@RequestParam MultipartFile file) throws IOException {
        return fileUploadService.upload(file);
    }

    @GetMapping
    public List<AX5File> files() {
        return fileUploadService.files();
    }
}
