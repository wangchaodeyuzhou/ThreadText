package com.zut.AddressReadAndWriter;

import java.util.Collections;
import java.util.Date;

/**
 * @author 王朝的宇宙
 * @version V1.0
 * @Package com.zut.AddressReadAndWriter
 * @date 2020/11/16 10:25
 */

/**
 * 原始内存，按地址分配
 */
public class mean_a extends Thread {

    public char[] mem; //定义的一个char来表示地址位置
    // 初始化地址的大小为size
    public mean_a(int size) {
        mem = new char[size];
        for (int i = 0; i < size; i++) {
            mem[i] = '_';
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                // 清屏展示操作
                System.out.println(Collections.nCopies(100, "\n")
                        .stream()
                        .reduce((a, b) -> a + b)
                        .get());
                // 分配
                StringBuffer s1 = new StringBuffer("[");
                for (int i = 0; i < mem.length; i++) {
                    s1.append(mem[i]);
                }
                s1.append("]");

                StringBuffer s2 = new StringBuffer("[1");
                for (int i = 1; i < mem.length - 1; i++) {
                    s2.append("_");
                    if (mem[i] != mem[i + 1] && i != 0) {
                        s2.append(i + 1);
                        i += (i + "").length();
                    }
                }
                s2.append(mem.length + "]");

                System.out.println(new Date());
                System.out.println(s1);
                System.out.println(s2);
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    // 分配[start, start+length]一共分配的长度为length
    public void allocate(char key, int start, int length) {
        for (int i = start; i < start + length; i++) {
            mem[i] = key;
        }
    }
    // 释放字符为key的地址
    public void free(char key) {
        for (int i = 0; i < mem.length; i++) {
            if (mem[i] == key) {
                mem[i] = '_';
            }
        }
    }

    public static void main(String[] args) throws Exception {
        mean_a m = new mean_a(100);
        m.start();
        Thread.sleep(2000);
        m.allocate('a', 15, 30);
        Thread.sleep(2000);
        m.allocate('b', 50, 10);
        Thread.sleep(4000);
        m.free('a');
    }

}
