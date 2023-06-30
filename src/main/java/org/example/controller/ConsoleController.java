package org.example.controller;

import org.example.dto.Product;
import java.io.*;
import java.util.List;

public class ConsoleController {
    public static String getFileNameFromConsole(String fileType) throws IOException {
        System.out.println("Please enter file name with " + fileType + " extension");

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        String input = reader.readLine();

        if (!input.contains(fileType)) {
            System.out.println("File name must contain ["+ fileType +"] file extension. Please try again!");
            return getFileNameFromConsole(fileType);
        }
        return input;
    }
}
