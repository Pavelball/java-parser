package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        try {
            if (args.length == 2)
                new SplitterFiles(args[0], args[1]).splitFiles();
            else if (args[0].contains( "--help"))
                new Help().help();
            else if (args[0].length() > 1)
                new FindWord(args[0], args[1], args[2]).findWord();
            else
                new FileMatcher(args[0], args[1], args[2]).regularExctracrot();
        } catch (Exception e) {
            System.out.println(e);
        }
        finally {
            if (!args[0].contains( "--help"))
                System.out.println("I`m finally!");
        }
    }
}
