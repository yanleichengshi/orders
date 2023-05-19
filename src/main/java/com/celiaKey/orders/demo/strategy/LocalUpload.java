package com.celiaKey.orders.demo.strategy;

/**
 * 策略模式--实现类1
 */
public class LocalUpload implements BaseUpload{
    @Override
    public void uploadFile() {
        System.out.println("LocalUpload.uploadFile");
    }
}
