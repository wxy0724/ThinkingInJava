package com.lovetogether.work;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class DownloadPic {

    public void downloadPic(HttpServletRequest request, HttpServletResponse response, String urlImg) {

        URLConnection connection = null;
        URL url = null;
        InputStream is = null;
        ServletOutputStream outputStream = null;
        try {
           url = new URL(urlImg);
           connection = url.openConnection();
           connection.setReadTimeout(2000);
           if (connection != null) {
               is = connection.getInputStream();
               outputStream = response.getOutputStream();
               String fileName = urlImg.substring(urlImg.lastIndexOf("/"), urlImg.length());
               fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
               response.setContentType("multipart/form-data");
               response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
                byte[] data = new byte[2048];
                int count = is.read(data, 0, data.length);
                while (count > 0) {
                    outputStream.write(data, 0, count);
                    count = is.read(data, 0, data.length);
                }
           }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
