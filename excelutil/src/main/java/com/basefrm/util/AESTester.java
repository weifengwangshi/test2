package com.basefrm.util;

//AESTester.java
public class AESTester {

    static String key;

    static {
        try {
            key = AESUtils.getSecretKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //url:jdbc:oracle:thin:@192.168.0.5:1521:orcl11g
    //username=szxj_admin
    //password=xjjhpt2014
    //driver=oracle.jdbc.driver.OracleDriver
    public static void main(String[] args) throws Exception {

        String source = "excel_admin";
        System.err.println("加密前:" + source);
        byte[] inputData = source.getBytes();
        inputData = AESUtils.encrypt(inputData, AESUtils.KEY);
        System.err.println("加密后:" + Base64Utils.encode(inputData));
        byte[] outputData = AESUtils.decrypt(inputData, AESUtils.KEY);
        String outputStr = new String(outputData);
        System.err.println("解密后:" + outputStr);
        String val= new String(AESUtils.decrypt(Base64Utils.decode("YJfBwvY9ZEmxzrHFwPxPbA=="), AESUtils.KEY));
        System.out.println(val);
    }

    static void encryptFile() throws Exception {
        String sourceFilePath = "D:/demo.mp4";
        String destFilePath = "D:/demo_encrypted.mp4";
        AESUtils.encryptFile(key, sourceFilePath, destFilePath);
    }

    static void decryptFile() throws Exception {
        String sourceFilePath = "D:/demo_encrypted.mp4";
        String destFilePath = "D:/demo_decrypted.mp4";
        AESUtils.decryptFile(key, sourceFilePath, destFilePath);
    }

    static void test() throws Exception {
        String source = " p+MQFmt3deXYwz+KGZ+GvA==";
        System.err.println("加密前:" + source);
        byte[] inputData = source.getBytes();
        inputData = AESUtils.encrypt(inputData, AESUtils.KEY);
        System.err.println("加密后:" + Base64Utils.encode(inputData));
        byte[] outputData = AESUtils.decrypt(inputData, AESUtils.KEY);
        String outputStr = new String(outputData);
        System.err.println("解密后:" + outputStr);
    }

}
