package com.example.java_io;

import java.io.File;
import java.io.IOException;
/**
 * Created by colin on 16-1-15.
 */
public class FileUtilsTest1 {
    public static void main(String[] args) throws IOException {
        FileUtils.listDirectory(new File("./app"));
    }
}
