package io.github.biezhi.compress.sevenz;

import java.io.File;
import java.io.IOException;

public class SevenZProgram {

    private static final String OUTPUT_DIRECTORY = new File("tmp").mkdir() ? "tmp" : "";
    private static final String TAR_GZIP_SUFFIX = ".7z";

    private static final String MULTIPLE_RESOURCES = "/example-multiple-resources";
    private static final String RECURSIVE_DIRECTORY = "/example-recursive-directory";

    private static final String MULTIPLE_RESOURCES_PATH = OUTPUT_DIRECTORY + MULTIPLE_RESOURCES + TAR_GZIP_SUFFIX;
    private static final String RECURSIVE_DIRECTORY_PATH = OUTPUT_DIRECTORY + RECURSIVE_DIRECTORY + TAR_GZIP_SUFFIX;


    public static void main(String... args) throws IOException {

        // class for resource classloading
        Class clazz = SevenZProgram.class;

        // get multiple resources files to compress
        File resource1 = new File(clazz.getResource("/in/resource1.txt").getFile());
        File resource2 = new File(clazz.getResource("/in/resource2.txt").getFile());
        File resource3 = new File(clazz.getResource("/in/resource3.txt").getFile());

        // compress multiple resources
        SevenZ.compress(MULTIPLE_RESOURCES_PATH, resource1, resource2, resource3);

        // decompress multiple resources
        SevenZ.decompress(MULTIPLE_RESOURCES_PATH, new File(OUTPUT_DIRECTORY + MULTIPLE_RESOURCES));



        // get directory file to compress
        File directory = new File(clazz.getResource("/in/dir").getFile());

        // compress recursive directory
        SevenZ.compress(RECURSIVE_DIRECTORY_PATH, directory);

        // decompress recursive directory
        SevenZ.decompress(RECURSIVE_DIRECTORY_PATH, new File(OUTPUT_DIRECTORY + RECURSIVE_DIRECTORY));
    }
}