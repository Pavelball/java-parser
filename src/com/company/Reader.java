package com.company;

import java.io.*;
import java.util.ArrayList;

public class Reader {
    public static ArrayList<String> readLog(String path) throws IOException {
        BufferedReader str = new BufferedReader
                (new InputStreamReader(new FileInputStream(path), "windows-1251"));

        ArrayList<String> log = new ArrayList<>();
        String line;
        while ((line = str.readLine()) != null)
            log.add((line));

        str.close();
        return log;
    }
}
