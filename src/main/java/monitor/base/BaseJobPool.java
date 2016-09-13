package monitor.base;


import monitor.impl.JobPoolImpl;

import java.rmi.RemoteException;

/**
 * 基础的Job池BaseJobPool类：提供最基本Job池的方法
 *
 * Created by qigao212074 on 2016/9/2.
 */
public abstract class BaseJobPool extends JobPoolImpl {

    /**
     * 默认构造函数
     */
    public BaseJobPool() throws RemoteException
    {
        this(MAX_JOB_NUM);
    }

    /**
     * 构造函数，初始化相关参数
     *
     * @param maxJobNum
     *            实际Job池中所能存储的最大Job数目
     */
    public BaseJobPool(int maxJobNum) throws RemoteException
    {
        this.maxJobNum = maxJobNum;
    }
}
