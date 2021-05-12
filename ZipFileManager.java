package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.exception.PathIsNotFoundException;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFileManager {
    //В этой переменной мы будем хранить полный путь к архиву, с которым будем работать
    private Path zipFile;
    //вспомогательный метод для архивации и применения в createZip(Path source)
    private void addNewZipEntry(ZipOutputStream zipOutputStream, Path filePath, Path fileName) throws Exception {
        //В Java есть специальный класс ZipOutputStream из пакета java.util.zip,
        // который сжимает (архивирует) переданные в него данные.
        //Чтобы несколько файлов, сжимаемые в один архив, не слиплись вместе,
        // для каждого из них создается специальная сущность - элемент архива ZipEntry.
        //Т.е. в ZipOutputStream мы сначала кладем ZipEntry, а затем уже записываем содержимое файла.
        //При записи файл автоматически сжимается, а при чтении - автоматически восстанавливается.
        //ZipEntry может быть не только файлом, но и папкой.
        Path fullPath = filePath.resolve(fileName);
        try (InputStream inputStream = Files.newInputStream(fullPath)){
            //Создаём` новый элемент архива ZipEntry, в качестве имени используем fileName
            ZipEntry zipEntry = new ZipEntry(fileName.toString());
            //Добавить в переданный zipOutputStream новый элемент архива ZipEntry
            zipOutputStream.putNextEntry(zipEntry);
            //Копируем данные из InputStream в переданный zipOutputStream
            copyData(inputStream, zipOutputStream);
            //Закрываем в zipOutputStream элемент архива ZipEntry
            zipOutputStream.closeEntry();
        }
    }
    //вспомогательный метод читает данные из in и записывает в out
    private void copyData(InputStream in, OutputStream out) throws Exception {
        int i;
        while ((i = in.read()) > -1) {
            out.write(i);
        }
    }
    // просто конструктор с инициализацией пути
    public ZipFileManager(Path zipFile) {
        this.zipFile = zipFile;
    }
    // метод в котором мы будем архивировать файл, заданный переменной source
    public void createZip(Path source) throws Exception {
        Path path = zipFile.getParent();
        if (Files.notExists(path)) {
            Files.createDirectories(path);
        }
        ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFile));
        if (Files.isRegularFile(source)) addNewZipEntry(zipOutputStream, source.getParent(), source.getFileName());
        else if (Files.isDirectory(source)) {
            FileManager fileManager = new FileManager(source);
            for (Path fileName : fileManager.getFileList()) {
                addNewZipEntry(zipOutputStream, source, fileName);
            }
        }
        else throw new PathIsNotFoundException();
    }
}
