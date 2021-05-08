package com.javarush.task.task31.task3110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//вспомогательный класс для работы с консолью
public class ConsoleHelper {
    //Вывести сообщение в консоль
    public static void writeMessage(String message) {
        System.out.println(message);
    }
    //Прочитать строку с консоли
    public static String readString() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        reader.close();
        return s;
    }
    //Прочитать число с консоли
    public static int readInt() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        reader.close();
        return n;
    }
}
