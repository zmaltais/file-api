package com.picktrace.fileapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FileModel {

    @JsonProperty("folder")
    private FolderModel folder;
    private String fileName;
    private String newFileName;

    public FileModel(FolderModel folder, String fileName, String newFileName) {
        this.folder = folder;
        this.fileName = fileName;
        this.newFileName = newFileName;
    }

    public FolderModel getFolder() {
        return folder;
    }

    public void setFolder(FolderModel folder) {
        this.folder = folder;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getNewFileName() {
        return newFileName;
    }

    public void setNewFileName(String newFileName) {
        this.newFileName = newFileName;
    }
}
