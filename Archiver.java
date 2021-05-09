package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.command.ExitCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

//главный класс!!!
public class Archiver {
    public static void main(String[] args) throws Exception {
        // открываем поток ввода
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите полный путь архива включая имя будующего архива вместе с расширением .zip:");
        Path fullPathToArchive = Paths.get(reader.readLine());
        ZipFileManager zipFileManager = new ZipFileManager(fullPathToArchive);
        System.out.println("Введите полный путь к файлу, который будем архивировать вместе с расширением:");
        zipFileManager.createZip(Paths.get(reader.readLine()));
        ExitCommand exitCommand = new ExitCommand();
        exitCommand.execute();
    }
}
