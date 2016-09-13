package monitor.framework;

import monitor.framework.Job;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *  既然生成了Job，那么这些Job就需要进行存放。
 *  JobPool作为Job作业存储的载体，最为关注的是Job的存入put()，和Job的取出get()。
 *
 * Created by qigao212074 on 2016/9/2.
 */
public interface JobPool extends Remote, Serializable
{
    /**
     * 获取一个Job
     *
     * @return 一个Job
     */
    public Job get() throws RemoteException;

    /**
     * 存放一个Job
     *
     * @param job
     *            要存放的Job
     * @return 是否成功
     */
    public boolean put(Job job) throws RemoteException;

    /**
     * 批量存放Job
     *
     * @param jobs
     *            要存放的批量Job
     * @return 是否成功
     */
    public boolean putBatch(Job[] jobs) throws RemoteException;

    /**
     * 获取Job池中Job的数目
     *
     * @return Job池中Job数目
     */
    public int getNum() throws RemoteException;

    /**
     * 判断Job池是否为空
     *
     * @return Job池是否为空
     */
    public boolean isEmpty() throws RemoteException;

    /**
     * 判断Job池是否满载
     *
     * @return Job池是否满载
     */
    public boolean isFull() throws RemoteException;

    /**
     * 清空Job池
     *
     * @return 是否清空Job池
     */
    public boolean clear() throws RemoteException;
}
