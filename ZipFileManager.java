package com.javarush.task.task31.task3110;

import java.nio.file.Path;

public class ZipFileManager {
    //В этой переменной мы будем хранить полный путь к архиву, с которым будем работать
    private Path zipFile;

    public ZipFileManager(Path zipFile) {
        this.zipFile = zipFile;
    }

    public void createZip(Path source) throws Exception {
    }
}
