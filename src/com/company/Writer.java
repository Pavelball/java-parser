package com.company;

import java.io.*;
import java.util.PriorityQueue;

public class Writer {
    private String path;

    public Writer(String path) {
        this.path = path;
    }

    public void writer(PriorityQueue<String> list) throws IOException {
        BufferedWriter str = new BufferedWriter
                (new OutputStreamWriter(new FileOutputStream(path), "windows-1251"));
        String line;

        while((line = list.poll()) != null) {
            str.append(line + '\n');
        }
        str.close();
    }
}
