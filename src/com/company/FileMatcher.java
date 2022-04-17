package com.company;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.company.Constants.PATTERN;

public class FileMatcher {
    private String delimiter;
    private String pathToLogFile;
    private String pathToFileMatcher;
    private Pattern pattern;

    public FileMatcher(String delimiter, String pathToLogFile, String pathToFileMatcher) {
        this.delimiter = delimiter;
        this.pathToLogFile = pathToLogFile;
        this.pathToFileMatcher = pathToFileMatcher;
    }

    public void regularExctracrot() throws IOException {
        PriorityQueue<String> find_lines = new PriorityQueue<>();
        ArrayList<String> list;
        pattern = Pattern.compile(PATTERN);
        Matcher matcher;
        File f = new File(pathToLogFile);

        FileFilter filter = f1 -> f1.getName().endsWith("txt");

        for (File myFile : Objects.requireNonNull(f.listFiles(filter))) {
            list = Reader.readLog(String.valueOf(myFile));
            for (String line : list) {
                matcher = pattern.matcher(line);
                if (matcher.find()) {
                    find_lines.offer(matcher.group(1) + delimiter
                            + matcher.group(2) + delimiter
                            + matcher.group(3) + delimiter
                            + matcher.group(4) + delimiter
                            + matcher.group(5));
                }
            }
        }

        String newFinalPath = pathToLogFile+"\\resultMatcher";
        new File(newFinalPath).mkdirs();

        if (!pathToFileMatcher.contains("csv"))
            pathToFileMatcher += ".csv";

        new Writer(newFinalPath + '\\' + pathToFileMatcher).writer(find_lines);
    }
}
