package com.zut.AddressReadAndWriter;

import javafx.util.Pair;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;

/**
 * @author 王朝的宇宙
 * @version V1.0
 * @Package com.zut.AddressReadAndWriter
 * @date 2020/11/16 17:30
 */
/*最佳适应算法*/
public class mean_c  extends Thread{


    public char[] mem; //定义的一个char来表示地址位置

    // k 代表分区的大小， start 代表开始起点
    public static LinkedList<Pair<Integer,Integer>> size_start = new LinkedList<Pair<Integer, Integer>>(); // 空闲分区链表
    public static LinkedList<Character> withKey = new LinkedList<>();
    public static LinkedList<Integer> withSize= new LinkedList<>();
    // 初始化地址的大小为size
    public mean_c(int size) {
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
//                System.out.println(Collections.nCopies(100, "\n")
//                        .stream()
//                        .reduce((a, b) -> a + b)
//                        .get());
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

    // 分配的尺寸的大小为size
    // 返回值为Boolean
    // 且true 表示已经分区
    public boolean allocate(char key,  int size) {
        boolean flag = false;
        System.out.println("size_start.size = "+ size_start.size());
        for (Pair<Integer,Integer> entry: size_start){
            System.out.println("entry.getKey 代表未连续分配的size  "+entry.getKey());
            System.out.println("entry.getValue 代表未连续分配的开始的id  "+entry.getValue());
            if (entry.getKey() >=size) { // 代表可以分区
                // 进行分区操作
                for (int i = 0; i < size; i++) {
                    mem[entry.getValue() + i] = key;
                }
                // 分完去区后对空闲区进行更新
                updateList();
                flag = true; // 代表的可以退出了
            }
        }
        // 没有分区的进入等待循环等待队列
        if(!flag){
            withKey.push(key);
            withSize.push(size);
        }
        return flag;
    }
    // 对空闲分区进行更新操作
    private void updateList() {
        size_start.clear();
        // 空闲的地址数目
        int pointCount = 0;

        for (int i = 0; i < mem.length; i++) {
            if(mem[i] == '_'){
                pointCount++;
            }else{
                // pointCount代表空闲的数目
                if(pointCount >0 ){
                    size_start.add(new Pair<>(pointCount, (i - pointCount)%mem.length));
                    pointCount = 0;
                }
            }
        }
        // 对于尾部分区时
        if(pointCount > 0){
            size_start.add(new Pair<>(pointCount, (mem.length - pointCount)%mem.length));
            pointCount = 0;
        }
        // 根据分区排序
        Collections.sort(size_start, new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                return o1.getKey() - o2.getKey();
            }
        });

    }



    // 释放字符为key的地址
    public void free(char key) {
        int start = -1;
        int count = 0;
        for (int i = 0; i < mem.length; i++) {
            if (mem[i] == key) {
                mem[i] = '_';
                count++;
                if(start == -1) start = i;
            }
        }

        size_start.add(new Pair<>(count,start));
        int waitNum = withKey.size();
        for (int i = 0; i < waitNum; i++) {
            allocate(withKey.poll(), withSize.poll());
        }

    }
    public static void main(String[] args) throws Exception {
        mean_c m = new mean_c(100);
        size_start.add(new Pair<>(100,0));
        m.start();
        Thread.sleep(2000);
        m.allocate('a',30);
        Thread.sleep(2000);
        m.allocate('b', 40);
        Thread.sleep(2000);
        m.allocate('c', 20);
        Thread.sleep(2000);
        m.allocate('d',20);
        Thread.sleep(2000);
        m.free('a');
        Thread.sleep(2000);
        m.free('d');
    }

}
