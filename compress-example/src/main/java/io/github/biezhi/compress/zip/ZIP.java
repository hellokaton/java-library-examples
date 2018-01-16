package io.github.biezhi.compress.zip;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ZIP 操作
 *
 * @author biezhi
 * @date 2018/1/16
 */
public class ZIP {

    public static void compress(String name, File... files) throws IOException {
        try (ZipArchiveOutputStream out = new ZipArchiveOutputStream(new FileOutputStream(name))) {
            for (File file : files) {
                addToArchiveCompression(out, file, ".");
            }
        }
    }

    public static void decompress(String in, File destination) throws IOException {
        try (ZipArchiveInputStream jin = new ZipArchiveInputStream(new FileInputStream(in))) {
            ZipArchiveEntry entry;
            while ((entry = jin.getNextZipEntry()) != null) {
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

    private static void addToArchiveCompression(ZipArchiveOutputStream out, File file, String dir) throws IOException {
        String name = dir + File.separator + file.getName();
        if (file.isFile()) {
            ZipArchiveEntry entry = new ZipArchiveEntry(name);
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
