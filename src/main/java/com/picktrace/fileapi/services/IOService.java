package com.picktrace.fileapi.services;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class IOService {
    //Temp directory used for local testing.
    private static final File TEMP_DIRECTORY = new File(System.getProperty("java.io.tmpdir"));
    public void addFolder(String folderPath){
        File newDirectory = new File(TEMP_DIRECTORY, folderPath);
        System.out.println("Creating new directory " + newDirectory);
        if(!newDirectory.isDirectory()){
            newDirectory.mkdir();
        } else {
            System.out.println(folderPath + " is not unique, appending unique folder value.");
            //Create a unique folder
            try {
                getUniquePath(folderPath).mkdir();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    public void moveFolder(String fromFolderPath, String toFolderPath) {
        File fromPath = new File(TEMP_DIRECTORY, fromFolderPath);
        File toPath = null;
        try {
            toPath = getUniquePath(toFolderPath);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try {
            System.out.println("Moving directory and contents from: " + fromPath.getPath() + " to: " + toPath.getPath());
            FileUtils.moveDirectory(fromPath, toPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteFolder(String folderPath){
        File folder = new File(TEMP_DIRECTORY, folderPath);
        try {
            System.out.println("Deleting directory: " + folder.getPath());
            FileUtils.deleteDirectory(folder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void renameFolder(String fromFolderPath, String toFolderPath) {
        File fromPath = new File(TEMP_DIRECTORY, fromFolderPath);
        File toPath = null;
        try {
            toPath = getUniquePath(toFolderPath);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("Renaming directory: " + fromPath.getPath() + " to: " + toPath.getPath());
        fromPath.renameTo(toPath);
    }
    private File getUniquePath(String folderPath) throws IOException {
        File folder = new File(TEMP_DIRECTORY, folderPath);
        if(!folder.isDirectory()){
            return folder;
        } else {
            Boolean findUniquePath = true;
            Integer appendValue = 1;
            //This is gross but leaving for time, there are better ways to give up on renaming
            while(findUniquePath || appendValue < 100){
                File uniqueDirectory = new File(TEMP_DIRECTORY, folderPath + "(" + appendValue + ")");
                if(!uniqueDirectory.isDirectory()){
                    return uniqueDirectory;
                } else {
                    appendValue++;
                }
            }
        }
        throw new IOException("Cannot create unique folder");
    }

    public void addFile(String fileName, String folderPath) {
        try {
            File file = getUniqueFileAndPath(fileName, folderPath);
            System.out.println("Adding file: " + file.getPath());
            FileUtils.touch(file);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    public void moveFile(String fileName, String fromFolderPath, String toFolderPath) {
        File initialLocation = new File(TEMP_DIRECTORY, fromFolderPath + "/" + fileName);
        File newLocation = null;
        try {
            newLocation = getUniqueFileAndPath(fileName, toFolderPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileUtils.moveFile(initialLocation, newLocation);
            System.out.println("Moving file: " + initialLocation.getPath() + " to: " + newLocation.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void renameFile(String fileName, String newFileName, String folderPath) {
        File file = new File(TEMP_DIRECTORY, folderPath + "/" + fileName);
        try {
            File newFile = getUniqueFileAndPath(newFileName, folderPath);
            file.renameTo(newFile);
            System.out.println("Renaming file: " + file.getPath() + " to: " + newFile.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteFile(String fileName, String folderPath) {
        File file = new File(TEMP_DIRECTORY, folderPath + "/" + fileName);
        System.out.println("Deleting file: " + file.getPath());
        FileUtils.deleteQuietly(file);
    }

    public File getUniqueFileAndPath(String fileName, String folderPath) throws IOException {
        File file = new File(TEMP_DIRECTORY, folderPath + "/" + fileName);
        if(!file.isFile()){
            return file;
        } else {
            Boolean findUniquePath = true;
            Integer appendValue = 1;
            //Same as above.
            while(findUniquePath || appendValue < 100){
                File uniqueDirectory = new File(TEMP_DIRECTORY, folderPath + "/" +
                        FilenameUtils.removeExtension(fileName) + "(" + appendValue + ")." +
                        FilenameUtils.getExtension(fileName));
                if(!uniqueDirectory.isDirectory()){
                    return uniqueDirectory;
                } else {
                    appendValue++;
                }
            }
        }
        throw new IOException("Cannot generate unique file");
    }

    //Did not get this working correctly. Having issues with the filter.
    public ResponseEntity findFile(String fileName) {
        File[] matches = TEMP_DIRECTORY.listFiles(new FilenameFilter()
        {
            public boolean accept(File dir, String fileName)
            {
                return fileName.startsWith(FilenameUtils.removeExtension(fileName));
            }
        });
        return new ResponseEntity<List<File>>(Arrays.asList(matches), HttpStatus.OK);
    }
}
