package com.picktrace.fileapi.controllers;

import com.picktrace.fileapi.models.FolderModel;
import com.picktrace.fileapi.services.IOService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/folders")
public class FoldersController {

    public final IOService ioService;

    public FoldersController(IOService ioService) {
        this.ioService = ioService;
    }

    @PostMapping("/")
    void addFolder(@RequestBody FolderModel folder){
        ioService.addFolder(folder.getFolderPath());
    }

    @PutMapping("/move")
    void moveFolder(@RequestBody FolderModel folder){
        ioService.moveFolder(folder.getFromFolderPath(), folder.getToFolderPath());
    }

    @PutMapping("/rename")
    void renameFolder(@RequestBody FolderModel folder){
        ioService.renameFolder(folder.getFromFolderPath(), folder.getToFolderPath());
    }

    @DeleteMapping("/")
    void deleteFolder(@RequestBody FolderModel folder){
        ioService.deleteFolder(folder.getFolderPath());
    }

    @GetMapping("/")
    ResponseEntity getFolderStructure(){
        return new ResponseEntity(HttpStatus.OK);
    }
}
