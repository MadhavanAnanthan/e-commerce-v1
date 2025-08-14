package com.e_commerce.monolith;

import java.util.ArrayList;
import java.util.List;

public class Learning {

    public static void start() {
        System.out.println("static block");
    }

    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new ArrayList<>();

        while (true) {
            for (int i = 0; i < 90_000; i++) {

                list.add(i);
            }
            System.gc();
            System.out.println("list size - " + list.size());
            Thread.sleep(2000);

        }
    }
}
