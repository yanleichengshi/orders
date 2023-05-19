package com.celiaKey.orders.listener;

import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 在应用启动后开始监听目标文件夹
 * 由于监听器在独立的线程中执行，一旦异常发生将导致线程退出，
 * 所以如果希望监听线程不中断，应在线程中捕获所有异常。
 */
@Component
public class FileListenerRunner  implements CommandLineRunner {
    // 注入监听工厂
    @Autowired
    private FileListenerFactory fileListenerFactory;

    @Override
    public void run(String... args) throws Exception {
        // 创建监听者
        FileAlterationMonitor monitor = fileListenerFactory.getMonitor();
        try {
            // don't stop this thread
            monitor.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
