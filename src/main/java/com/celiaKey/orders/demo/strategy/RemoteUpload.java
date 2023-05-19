package com.celiaKey.orders.demo.strategy;

/**
 * 策略模式--实现类2
 */
public class RemoteUpload implements BaseUpload {
    @Override
    public void uploadFile() {
        System.out.println("RemoteUpload.uploadFile");
    }
}
