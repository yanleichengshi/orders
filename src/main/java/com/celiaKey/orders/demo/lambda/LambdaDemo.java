package com.celiaKey.orders.demo.lambda;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

/**
 * lambda参考
 */
public class LambdaDemo {
    public static void main(String[] args) {
        /* -----  1. lambda实现Runnable   -    无参  ----- */
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Before Java 8, too much code for too little to do");
            }
        }).start();
        new Thread(() -> System.out.println("In Java8, so cool")).start();

        /* -----  2. lambda实现Runnable   -    有参  ----- */
        JButton jButton = new JButton("show");
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("before Java8");
            }
        });
        jButton.addActionListener((e) -> System.out.println("after java8"));

        /* -----  3、使用lambda表达式对列表进行迭代  ----- */
        List<String> list = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        for (String str : list) {
            System.out.println(str);
        }
        list.forEach(s -> System.out.println(s));
    }
}
