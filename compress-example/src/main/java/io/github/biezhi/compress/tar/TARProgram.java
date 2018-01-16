package io.github.biezhi.compress.tar;

import java.io.File;
import java.io.IOException;

public class TARProgram {

    private static final String OUTPUT_DIRECTORY = new File("tmp").mkdir() ? "tmp" : "";
    private static final String JAR_SUFFIX = ".tar";

    private static final String MULTIPLE_RESOURCES = "/example-multiple-resources";
    private static final String RECURSIVE_DIRECTORY = "/example-recursive-directory";

    private static final String MULTIPLE_RESOURCES_PATH = OUTPUT_DIRECTORY + MULTIPLE_RESOURCES + JAR_SUFFIX;
    private static final String RECURSIVE_DIRECTORY_PATH = OUTPUT_DIRECTORY + RECURSIVE_DIRECTORY + JAR_SUFFIX;


    public static void main(String... args) throws IOException {

        // class for resource classloading
        Class clazz = TARProgram.class;

        // get multiple resources files to compress
        File resource1 = new File(clazz.getResource("/resource1.txt").getFile());
        File resource2 = new File(clazz.getResource("/resource2.txt").getFile());
        File resource3 = new File(clazz.getResource("/resource3.txt").getFile());

        // compress multiple resources
        TAR.compress(MULTIPLE_RESOURCES_PATH, resource1, resource2, resource3);

        // decompress multiple resources
        TAR.decompress(MULTIPLE_RESOURCES_PATH, new File(OUTPUT_DIRECTORY + MULTIPLE_RESOURCES));



        // get directory file to compress
        File directory = new File(clazz.getResource("/dir").getFile());

        // compress recursive directory
        TAR.compress(RECURSIVE_DIRECTORY_PATH, directory);

        // decompress recursive directory
        TAR.decompress(RECURSIVE_DIRECTORY_PATH, new File(OUTPUT_DIRECTORY + RECURSIVE_DIRECTORY));
    }
}