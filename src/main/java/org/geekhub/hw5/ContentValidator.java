package org.geekhub.hw5;

import org.geekhub.hw5.exception.ContentLengthNotKnownException;
import org.geekhub.hw5.exception.FileExistException;
import org.geekhub.hw5.exception.LimitSizeException;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;

public class ContentValidator {

    private static final int CONTENT_LENGTH_IS_NOT_KNOWN = -1;

    private final int maxFileSize;

    public ContentValidator(int maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    public boolean isValid(URL url, String pathToFile, String filename)
        throws FileExistException, LimitSizeException, ContentLengthNotKnownException, IOException {
        URLConnection urlConnection = url.openConnection();
        int contentLength = urlConnection.getContentLength();

        hasContent(contentLength, url);
        validateContentLength(contentLength, url);
        isExistFile(pathToFile, filename, url);

        return true;
    }

    private void hasContent(int contentLength, URL url) throws ContentLengthNotKnownException {
        if (contentLength == CONTENT_LENGTH_IS_NOT_KNOWN) {
            throw new ContentLengthNotKnownException(String.format("Cannot download file from url: %s%n", url));
        }
    }

    private void validateContentLength(int contentLength, URL url) throws LimitSizeException {
        if (contentLength > maxFileSize) {
            throw new LimitSizeException(String.format("Failed to download from url: %s over %s%n", url, maxFileSize));
        }
    }

    private void isExistFile(String pathToFile, String filename, URL url) throws FileExistException {
        if (Files.exists(Path.of(pathToFile, filename))) {
            throw new FileExistException(String.format("File downloaded again: %s by url: %s%n", filename, url));
        }
    }
}
