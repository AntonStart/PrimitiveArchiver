package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.command.ExitCommand;
import com.javarush.task.task31.task3110.exception.WrongZipFileException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

//главный класс!!!
public class Archiver {

    public static Operation askOperation() throws IOException{
        ConsoleHelper.writeMessage("Выберите операцию:");
        ConsoleHelper.writeMessage(Operation.CREATE.ordinal() + " - упаковать файлы в архив");
        ConsoleHelper.writeMessage(Operation.ADD.ordinal() + " - добавить файл в архив");
        ConsoleHelper.writeMessage(Operation.REMOVE.ordinal() + " - удалить файл из архива");
        ConsoleHelper.writeMessage(Operation.EXTRACT.ordinal() + " - распаковать архив");
        ConsoleHelper.writeMessage(Operation.CONTENT.ordinal() + " - просмотреть содержимое архива");
        ConsoleHelper.writeMessage(Operation.EXIT.ordinal() + " - выход");
        return Operation.values()[ConsoleHelper.readInt()];
    }

    public static void main(String[] args) throws Exception {

        Operation operation = null;

         do {
             try {
                 operation = askOperation();
                 CommandExecutor.execute(operation);
             }
             catch (WrongZipFileException e)
             {
                 System.out.println("Вы не выбрали файл архива или выбрали неверный файл.");
                 continue;
             }
             catch (Exception e)
             {
                 System.out.println("Произошла ошибка. Проверьте введенные данные.");
                 continue;
             }
        } while (operation != Operation.EXIT);

        // открываем поток ввода
        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //System.out.println("Введите полный путь архива включая имя будующего архива вместе с расширением .zip:");
        //Path fullPathToArchive = Paths.get(reader.readLine());
        //ZipFileManager zipFileManager = new ZipFileManager(fullPathToArchive);
        //System.out.println("Введите полный путь к файлу, который будем архивировать вместе с расширением:");
        //zipFileManager.createZip(Paths.get(reader.readLine()));
        //ExitCommand exitCommand = new ExitCommand();
        //exitCommand.execute();
    }
}
