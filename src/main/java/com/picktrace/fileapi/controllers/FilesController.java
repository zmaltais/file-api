package com.picktrace.fileapi.controllers;

import com.picktrace.fileapi.models.FileModel;
import com.picktrace.fileapi.models.FolderModel;
import com.picktrace.fileapi.services.IOService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/files")
public class FilesController {

    public final IOService ioService;

    public FilesController(IOService ioService) {
        this.ioService = ioService;
    }

    @PostMapping("/")
    void addFile(@RequestBody FileModel file){
        ioService.addFile(file.getFileName(), file.getFolder().getFolderPath());
    }

    @PutMapping("/move")
    void moveFile(@RequestBody FileModel file){
        ioService.moveFile(file.getFileName(), file.getFolder().getFromFolderPath(), file.getFolder().getToFolderPath());
    }

    @PutMapping("/rename")
    void renameFile(@RequestBody FileModel file){
        ioService.renameFile(file.getFileName(), file.getNewFileName(),file.getFolder().getFolderPath());
    }

    @DeleteMapping("/")
    void deleteFile(@RequestBody FileModel file){
        ioService.deleteFile(file.getFileName(), file.getFolder().getFolderPath());
    }
}
