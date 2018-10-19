package com.itmayiedu.lock;

/**
 * Created by wukuijun on 2018/10/19
 */
public interface ExtLock {

    //获取锁
    public void getLock();
    //释放锁
    public void unLock();
}
