/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch.service;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.UUID;

/**
 *
 * @author krismorte
 */
public class TwitterStream {

    public static String download(String urlString, String toPath) {
        try {
            File dir = createDirectoryIfNotExists(toPath);

            URL url = new URL(urlString);

            InputStream in = new BufferedInputStream(url.openStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int n = 0;
            while (-1 != (n = in.read(buf))) {
                out.write(buf, 0, n);
            }
            out.close();
            in.close();
            byte[] response = out.toByteArray();
            String filePath = dir.getAbsolutePath() + "\\" + UUID.randomUUID().toString() + ".jpg";
            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write(response);
            fos.close();
            return filePath;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    private static File createDirectoryIfNotExists(String directory) {
        File file = new File(directory);
        if (file.exists()) {
            return file;
        } else {
            file.mkdir();
            return file;
        }
    }

    private String getExtension(String type) {
        if (type.equals("photo")) {
            return "jpg";
        } else if (type.equals("video")) {
            return "mp4";
        } else if (type.equals("animated_gif")) {
            return "gif";
        } else {
            return "err";
        }
    }

}
