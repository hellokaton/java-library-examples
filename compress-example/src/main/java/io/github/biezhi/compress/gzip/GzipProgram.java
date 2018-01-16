package io.github.biezhi.compress.gzip;

import java.io.File;
import java.io.IOException;

public class GzipProgram {

    public static void main(String... args) throws IOException {

        // class for resource classloading
        Class clazz = GzipApache.class;

        // create files to read and write
        File example = new File(clazz.getResource("/example.xml").getFile());
        File output = new File("tmp/example.xml.gz");
        File decompressed = new File("tmp/decompressed.xml");


        // Java GZIP example compression decompression
        GzipJava.compressGZIP(example, output);
        GzipJava.decompressGzip(output, decompressed);

        // Apache GZIP example compression decompression
        GzipApache.compressGZIP(example, output);
        GzipApache.decompressGZIP(output, decompressed);

    }
}