package org.geekhub.hw5;

import static org.geekhub.hw5.FileUtils.deleteDirectories;

public class MusicDownloader {

    public static void main(String[] args) {
        String pathToPlaylist =  ClassLoader.getSystemResource("playlist.txt").getPath();
        String pathToLogFile = "log.txt";
        String mainDirectory = "library";
        int maxFileSize = 10485760;

        ContentValidator contentValidator = new ContentValidator(maxFileSize);
        Downloader downloader = new Downloader(contentValidator, mainDirectory, pathToLogFile);

        downloader.download(pathToPlaylist);

        deleteDirectories(mainDirectory);
    }
}
