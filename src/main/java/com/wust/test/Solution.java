package com.wust.test;


import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.net.*;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;

class DigestThread extends Thread {
    private String fileName;
    public DigestThread(String name){
        this.fileName = name;
    }

    @Override
    public void run() {
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            DigestInputStream digestInputStream = new DigestInputStream(fileInputStream, instance);
            while((digestInputStream.read()) != -1);
            digestInputStream.close();
            byte[] digest = instance.digest();
            StringBuilder sb = new StringBuilder(fileName);
            sb.append(": ");
            sb.append(DatatypeConverter.printHexBinary(digest));
            System.out.println(sb.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
//        DigestThread thread = new DigestThread("F:/1.txt");
//        thread.start();
        URL url = new URL("https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png");
        Object content = url.getContent();
        System.out.println(content.getClass().getName());
    }
}
