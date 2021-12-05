package com.picktrace.fileapi.models;

public class FolderModel {
    private String folderPath;
    private String fromFolderPath;
    private String toFolderPath;

    public FolderModel(String folderPath, String fromFolderPath, String toFolderPath) {
        this.folderPath = folderPath;
        this.fromFolderPath = fromFolderPath;
        this.toFolderPath = toFolderPath;
    }


    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    public String getFromFolderPath() {
        return fromFolderPath;
    }

    public void setFromFolderPath(String fromFolderPath) {
        this.fromFolderPath = fromFolderPath;
    }

    public String getToFolderPath() {
        return toFolderPath;
    }

    public void setToFolderPath(String toFolderPath) {
        this.toFolderPath = toFolderPath;
    }
}
