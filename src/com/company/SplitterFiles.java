package com.company;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.company.Constants.COUNT_FILES;

public class SplitterFiles {

    private final String pathToLogFile;
    private final String pathToSplitFiles;

    public SplitterFiles(String pathToLogFile, String pathToSplitFiles) {
        this.pathToLogFile = pathToLogFile;
        this.pathToSplitFiles = pathToSplitFiles;
    }

    public void splitFiles() throws IOException {
        PriorityQueue<String> arr_lines = new PriorityQueue<>();
        String[] lst = pathToSplitFiles.split("\\\\");
        String splitPathFile = "";

        for (int i = 0; i < lst.length-1; i++) {
            splitPathFile += lst[i] + '\\';
        }

        new File(splitPathFile).mkdirs();

        ArrayList<String> list = Reader.readLog(pathToLogFile);

        int numFile = 1, num_lines = 0, lenList = list.size()/COUNT_FILES;

        for (int i = 0; i < COUNT_FILES; i++) {
            while (num_lines < lenList) {
                arr_lines.offer(list.get(num_lines));
                num_lines++;
            }

            new Writer(generateFileName(numFile)).writer(arr_lines);

            num_lines = 0;
            numFile++;
        }
    }

    private String generateFileName(int num) {
        return pathToSplitFiles + num + ".txt";
    }
}
