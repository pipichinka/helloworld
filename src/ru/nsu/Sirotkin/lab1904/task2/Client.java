package ru.nsu.Sirotkin.lab1904.task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        if (args.length != 2){
            System.out.println("not enough arguments");
            return;
        }

        try (Socket s = new Socket(args[0], 8080); PrintWriter w = new PrintWriter(s.getOutputStream(), true);
             BufferedReader r = new BufferedReader(new InputStreamReader(s.getInputStream()))){
            w.println("GET " + args[1] + " HTTP/1.0\n");
            String firstLine = r.readLine();
            System.out.println(firstLine);
            String resultLine;
            switch (firstLine) {
                case "HTTP/1.0 200 OK" -> {
                    while ((resultLine = r.readLine()) != null && !resultLine.equals("")) {

                    }
                    while ((resultLine = r.readLine()) != null) {
                        System.out.println(resultLine);
                    }
                }
                case "HTTP/1.0 404 Not Found" -> {
                    System.out.println("ERROR");
                    while ((resultLine = r.readLine()) != null && !resultLine.equals("")) {
                        //System.out.println(resultLine);
                    }
                    while ((resultLine = r.readLine()) != null) {
                        System.out.println(resultLine);
                    }
                }
                case "HTTP/1.1 301 Moved Permanently" -> {
                    System.out.println("ERROR");
                    while ((resultLine = r.readLine()) != null && !resultLine.equals("")) {
                        String[] resultLineSplit = resultLine.split(":");
                        if (resultLineSplit[0].equals("Location")) {
                            System.out.println(resultLine.substring(resultLineSplit[0].length() + 2));
                        }
                    }
                }
                default -> System.out.println("ERROR1");
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }



    }



}
