package com.guifa.okhttp.bean;

import java.io.File;
import java.io.Serializable;

public class FileInput implements Serializable {

    private String key;
    private String filename;
    private File file;

    public FileInput() {
    }

    public FileInput(String name, String filename, File file) {
        this.key = name;
        this.filename = filename;
        this.file = file;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "FileInput{" +
                "key='" + key + '\'' +
                ", filename='" + filename + '\'' +
                ", file=" + file +
                '}';
    }
}