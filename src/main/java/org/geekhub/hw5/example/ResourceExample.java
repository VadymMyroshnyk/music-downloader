package org.geekhub.hw5.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ResourceExample {

    public static void main(String[] args) throws IOException {
        String classLoader = ClassLoader.getSystemResource("playlist.txt").getPath();

        System.out.println("classLoader");
        System.out.println(classLoader);
        System.out.println(Files.readAllLines(Path.of(classLoader)).size());

        // ----------------------------------------------------
        String pathFromSrc = "src/main/resources/playlist.txt";

        System.out.println("pathFromSrc");
        System.out.println(pathFromSrc);
        System.out.println(Files.readAllLines(Path.of(pathFromSrc)).size());
    }
}
