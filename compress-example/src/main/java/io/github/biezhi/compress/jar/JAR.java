package io.github.biezhi.compress.jar;

import org.apache.commons.compress.archivers.jar.JarArchiveEntry;
import org.apache.commons.compress.archivers.jar.JarArchiveInputStream;
import org.apache.commons.compress.archivers.jar.JarArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * JAR 操作
 *
 * @author biezhi
 * @date 2018/1/16
 */
public class JAR {

    private JAR() {}

    public static void compress(String name, File... files) throws IOException {
        try (JarArchiveOutputStream out = new JarArchiveOutputStream(new FileOutputStream(name))) {
            for (File file : files) {
                addToArchiveCompression(out, file, ".");
            }
        }
    }

    public static void decompress(String in, File destination) throws IOException {
        try (JarArchiveInputStream jin = new JarArchiveInputStream(new FileInputStream(in))) {
            JarArchiveEntry entry;
            while ((entry = jin.getNextJarEntry()) != null) {
                if (entry.isDirectory()) {
                    continue;
                }
                File curfile = new File(destination, entry.getName());
                File parent  = curfile.getParentFile();
                if (!parent.exists()) {
                    if (!parent.mkdirs()) {
                        throw new RuntimeException("could not create directory: " + parent.getPath());
                    }
                }
                IOUtils.copy(jin, new FileOutputStream(curfile));
            }
        }
    }

    private static void addToArchiveCompression(JarArchiveOutputStream out, File file, String dir) throws IOException {
        String name = dir + File.separator + file.getName();
        if (file.isFile()) {
            JarArchiveEntry entry = new JarArchiveEntry(name);
            out.putArchiveEntry(entry);
            entry.setSize(file.length());
            IOUtils.copy(new FileInputStream(file), out);
            out.closeArchiveEntry();
        } else if (file.isDirectory()) {
            File[] children = file.listFiles();
            if (children != null) {
                for (File child : children) {
                    addToArchiveCompression(out, child, name);
                }
            }
        } else {
            System.out.println(file.getName() + " is not supported");
        }
    }
}