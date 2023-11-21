package org.geekhub.hw5;

import org.geekhub.hw5.exception.FileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class FileUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);

    private FileUtils() {
    }

    public static List<String> readAllLines(String file) {
        try {
            return Files.readAllLines(Path.of(file));
        } catch (IOException e) {
            throw new FileException("Error happened while reading lines from file", e);
        }
    }

    public static void createDirectories(Path path) {
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            throw new FileException("Error happened while creation directories", e);
        }
    }

    public static void writeToFile(Path path, byte[] content) {
        try {
            createFileIfNotExists(path);

            Files.write(path, content, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            LOGGER.error("Error happened while writing content to file", e);
        }
    }

    public static void copyToFile(InputStream inputStream, Path path) {
        try {
            createFileIfNotExists(path);

            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            LOGGER.error("Error happened while copying content to file", e);
        }
    }

    public static void createFileIfNotExists(Path path) {
        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            LOGGER.error("Error happened while creating content to file", e);
        }
    }

    public static void deleteDirectories(String directory) {
        try (Stream<Path> pathStream = Files.walk(Path.of(directory))) {
            pathStream
                .sorted(Comparator.reverseOrder())
                .forEach(FileUtils::deleteIfExists);
        } catch (IOException e) {
            throw new FileException("Error happened while deleting directories", e);
        }
    }

    public static void deleteIfExists(Path path) {
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new FileException("Error happened while deleting path", e);
        }
    }
}
