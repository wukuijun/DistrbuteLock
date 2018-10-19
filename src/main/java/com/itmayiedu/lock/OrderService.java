package com.itmayiedu.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wukuijun on 2018/10/19
 */
public class OrderService implements Runnable{

    private OrderNumGenerator orderNumGenerator = new OrderNumGenerator();

    private ZookeeperDistrbuteLock zookeeperDistrbuteLock = new ZookeeperDistrbuteLock();

    private Lock lock = new ReentrantLock();
    public void run() {
        getNumber();
    }

    public  void getNumber(){
        try {
            zookeeperDistrbuteLock.getLock();
            String orderNumber = orderNumGenerator.getNumber();
            System.out.println("线程:"+Thread.currentThread().getName()+",生产订单号为:"+orderNumber);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            zookeeperDistrbuteLock.unLock();
        }
    }

    public static void main(String[] args) {
//        OrderService orderService = new OrderService();
        for (int i = 0; i < 100; i++) {
            new Thread( new OrderService()).start();

        }
    }
}
