package monitor.impl;


import monitor.framework.Job;
import monitor.framework.JobPool;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 抽象Job池BaseJobPool类：提供最基本Job池的方法
 *
 * Created by qigao212074 on 2016/9/2.
 */
public abstract class JobPoolImpl extends UnicastRemoteObject implements
        JobPool, Serializable {

    /* 默认Job池中所能存储的最大Job数目 */
    protected static final int MAX_JOB_NUM = 100;

    /* 实际Job池中所能存储的最大Job数目 */
    protected int maxJobNum = -1;

    /**
     * 默认构造函数
     */
    public JobPoolImpl() throws RemoteException {
        this(MAX_JOB_NUM);
    }

    /**
     * 构造函数，初始化相关参数
     *
     * @param maxJobNum
     *            实际Job池中所能存储的最大Job数目
     */
    public JobPoolImpl(int maxJobNum) throws RemoteException {
        this.maxJobNum = maxJobNum;
    }

    /**
     * 批量存放Job
     *
     * @param jobs
     *            要存放的批量Job
     * @return 是否成功
     */
    public boolean putBatch(Job[] jobs) throws RemoteException {
        return false;
    }

    /**
     * 获取Job池中Job的数目
     *
     * @return Job池中Job数目
     */
    public int getNum() throws RemoteException {
        return -1;
    }

    /**
     * 同步地获取最大能容纳的Job数目
     *
     * @return 最大能容纳的Job数目
     */
    public synchronized int getMaxJobNum() throws RemoteException {
        return maxJobNum;
    }

    /**
     * 设置最大能容纳的Job数目
     *
     * @param maxJobNum
     *            最大能容纳的Job数目
     */
    public void setMaxJobNum(int maxJobNum) throws RemoteException {
        this.maxJobNum = maxJobNum;
    }

    /**
     * 获取默认的最大能容纳的Job数目MAX_JOB_NUM
     *
     * @return 默认的最大能容纳的Job数目MAX_JOB_NUM
     */
    public static int getMAX_JOB_NUM() throws RemoteException {
        return MAX_JOB_NUM;
    }
}
