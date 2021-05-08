package com.javarush.task.task31.task3110;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFileManager {
    //В этой переменной мы будем хранить полный путь к архиву, с которым будем работать
    private Path zipFile;

    public ZipFileManager(Path zipFile) {
        this.zipFile = zipFile;
    }
    // метод в котором мы будем архивировать файл, заданный переменной source
    public void createZip(Path source) throws Exception {
        //В Java есть специальный класс ZipOutputStream из пакета java.util.zip,
        // который сжимает (архивирует) переданные в него данные.
        //Чтобы несколько файлов, сжимаемые в один архив, не слиплись вместе,
        // для каждого из них создается специальная сущность - элемент архива ZipEntry.
        //Т.е. в ZipOutputStream мы сначала кладем ZipEntry, а затем уже записываем содержимое файла.
        //При записи файл автоматически сжимается, а при чтении - автоматически восстанавливается.
        //ZipEntry может быть не только файлом, но и папкой.
        ZipEntry zipEntry = new ZipEntry(source.getFileName().toString());
        int i;
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFile));
             InputStream inputStream = Files.newInputStream(source))
        {
            zipOutputStream.putNextEntry(zipEntry);
            while ((i = inputStream.read()) > 0) {
                zipOutputStream.write(i);
            }
        }
    }
}
