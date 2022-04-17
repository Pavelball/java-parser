package com.company;
import java.io.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.PriorityQueue;

public class FindWord {
    private String findWord;
    private String pathToLogFile;
    private String pathToFileFindWord;

    public FindWord(String findWord, String pathToLogFile, String pathToFileFindWord) {
        this.findWord = findWord;
        this.pathToLogFile = pathToLogFile;
        this.pathToFileFindWord = pathToFileFindWord;
    }

    public void findWord() throws IOException {
        PriorityQueue<String> find_lines = new PriorityQueue<>();
        ArrayList<String> list;

        File f = new File(pathToLogFile);

        FileFilter filter = f1 -> f1.getName().endsWith("txt");

        for (File myFile : Objects.requireNonNull(f.listFiles(filter))) {
            list = Reader.readLog(String.valueOf(myFile));
            for (String line : list) {
                if (line.contains(findWord)) {
                    find_lines.offer(line);
                }
            }
        }

        String newFinalPath = pathToLogFile+"\\resultFindWord";
        new File(newFinalPath).mkdirs();

        if (!pathToFileFindWord.contains("txt"))
            pathToFileFindWord += ".txt";

        new Writer(newFinalPath + '\\' + pathToFileFindWord).writer(find_lines);
    }
}
