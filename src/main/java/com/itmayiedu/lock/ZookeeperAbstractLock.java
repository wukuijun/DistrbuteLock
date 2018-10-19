package com.itmayiedu.lock;

import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.CountDownLatch;

/**
 * Created by wukuijun on 2018/10/19
 */
public abstract class ZookeeperAbstractLock implements ExtLock{

    private String CONNECTION = "127.0.0.1:2181";
    public ZkClient zkClient = new ZkClient(CONNECTION);

    //路径
    protected String lockPath = "/path";
    protected CountDownLatch countDownLatch = new CountDownLatch(1);


    public void getLock() {
        if(tryLock()){
            System.out.println("----获取锁成功-----");
        }else{
            waitLock();
            getLock();
        }

    }

    protected abstract void waitLock();

    protected abstract boolean tryLock();

    public void unLock() {
        if(zkClient != null){
            zkClient.close();
            System.out.println("-----释放锁-----");
        }
    }
}
