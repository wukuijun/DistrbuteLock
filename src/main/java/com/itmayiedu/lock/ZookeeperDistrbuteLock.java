package com.itmayiedu.lock;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;

import java.util.List;

/**
 * Created by wukuijun on 2018/10/19
 */
public class ZookeeperDistrbuteLock extends ZookeeperAbstractLock{
    protected void waitLock() {
        if(zkClient.exists(lockPath)){
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        IZkDataListener iZkDataListener = new IZkDataListener() {
            public void handleDataChange(String s, Object o) throws Exception {

            }

            public void handleDataDeleted(String s) throws Exception {
                if(countDownLatch != null){
                    countDownLatch.countDown();
                }
            }
        };
        //使用zk监听事件
        zkClient.subscribeDataChanges(lockPath,iZkDataListener);//监听的代码会单独开一个线程，不会影响主流程的执行
        //监听完毕之后，移除事件通知
        zkClient.unsubscribeDataChanges(lockPath,iZkDataListener);
    }

    protected boolean tryLock() {
        try {
            zkClient.createEphemeral(lockPath);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }
}
