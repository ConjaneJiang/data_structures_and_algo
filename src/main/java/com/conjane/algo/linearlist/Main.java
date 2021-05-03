package com.conjane.algo.linearlist;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        DynamicArray<Integer> dynamicArray = new DynamicArray<>();
        dynamicArray.add(10);
        dynamicArray.add(20);
        dynamicArray.add(30);
        dynamicArray.add(40);
        dynamicArray.add(50);
        System.out.println(dynamicArray.toString());
        System.out.println(dynamicArray.size());
        dynamicArray.add(1,11);
        System.out.println(dynamicArray.toString());
        System.out.println(dynamicArray.size());
        dynamicArray.remove(3);
        System.out.println(dynamicArray.toString());
        System.out.println(dynamicArray.size());
    }
}
