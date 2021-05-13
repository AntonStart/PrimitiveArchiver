package com.javarush.task.task31.task3110.command;

import com.javarush.task.task31.task3110.ConsoleHelper;
import com.javarush.task.task31.task3110.FileProperties;
import com.javarush.task.task31.task3110.ZipFileManager;

import java.util.List;
import java.util.Properties;

//класс команды просмотра содержимого архива
public class ZipContentCommand extends ZipCommand{
    @Override
    public void execute() throws Exception {
        ConsoleHelper.writeMessage("Просмотр содержимого архива.");
        ZipFileManager zipFileManager = getZipFileManager();
        ConsoleHelper.writeMessage("Содержимое архива:");
        List<FileProperties> list = zipFileManager.getFilesList();
        for (FileProperties fileProperties : list) {
            System.out.println(fileProperties.toString());
        }
        ConsoleHelper.writeMessage("Содержимое архива прочитано.");
    }
}
