package com.example.downlaod;

import org.zeroturnaround.zip.ZipUtil;

import java.io.*;
import java.util.zip.*;

public class ZipFilesExample {

    public static void main(String[] args) {
        // Directory containing files to be zipped
        String sourceDir = "C:\\Vinayak\\dates\\";

        // Zip file name
        String zipFileName = "C:\\Vinayak\\dates\\output.zip";

        try {

            ZipUtil.pack(new File("C:\\Vinayak\\dates\\"), new File("C:\\Vinayak\\dates.zip"));

            // Create output stream to write to the zip file
//            FileOutputStream fos = new FileOutputStream(zipFileName);
//            ZipOutputStream zos = new ZipOutputStream(fos);
//
//            // Call method to add files to the zip file
//            zipDirectory(sourceDir, sourceDir, zos);
//
//            // Close the ZipOutputStream
//            zos.close();
//
//            System.out.println("Zip file created successfully: " + zipFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void zipDirectory(String basePath, String sourceDir, ZipOutputStream zos) throws IOException {
        // Get list of files and directories in the source directory
        File dir = new File(sourceDir);
        File[] files = dir.listFiles();

        // Iterate through each file/directory
        for (File file : files) {
            if (file.isDirectory()) {
                // If it's a directory, recursively call the method
                zipDirectory(basePath, file.getAbsolutePath(), zos);
            } else {
                // If it's a file, add it to the zip file
                String entryName = file.getAbsolutePath().substring(basePath.length() + 1);
                addFileToZip(file, entryName, zos);
            }
        }
    }

    private static void addFileToZip(File file, String entryName, ZipOutputStream zos) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        ZipEntry zipEntry = new ZipEntry(entryName);
        zos.putNextEntry(zipEntry);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = fis.read(buffer)) > 0) {
            zos.write(buffer, 0, length);
        }

        zos.closeEntry();
        fis.close();
    }
}
