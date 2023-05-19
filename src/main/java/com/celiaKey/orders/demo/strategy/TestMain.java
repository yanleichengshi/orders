package com.celiaKey.orders.demo.strategy;

public class TestMain {
    public static void main(String[] args) {
        UploadFactory factory = new UploadFactory();
        factory.getUpload("localUpload").uploadFile();
    }
}
