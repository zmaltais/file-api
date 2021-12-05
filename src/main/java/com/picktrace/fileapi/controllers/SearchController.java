package com.picktrace.fileapi.controllers;

import com.picktrace.fileapi.services.IOService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {

    public final IOService ioService;

    public SearchController(IOService ioService) {
        this.ioService = ioService;
    }


    @GetMapping("/{fileName}")
    public ResponseEntity findFile(@PathVariable String fileName){
        return ioService.findFile(fileName);
    }
}
