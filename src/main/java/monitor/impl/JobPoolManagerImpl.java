package monitor.impl;


import monitor.base.BaseExecutorPool;
import monitor.base.impl.ListJobPool;
import monitor.framework.*;

import java.rmi.RemoteException;

/**
 * 管理作业池抽象类JobPoolManagerImpl：用于管理Job池及处理器
 *
 * Created by qigao212074 on 2016/9/2.
 */
public class JobPoolManagerImpl implements JobPoolManager {


    /* Job池的存储载体 */
    protected JobPool jobPool = null;

    /* 处理器队列 */
    protected ExecutorPool executors = null;

    /* 开辟存储处理线程的个数 */
    protected static final int MAX_EXECUTOR_NUM = 3;

    public JobPoolManagerImpl() throws RemoteException {
          /* 生成Job池，并将Job批量放入Job池中 */
        JobPool jobPool = new ListJobPool();

        /* 初始化处理器池 */
        ExecutorPoolImpl executors = new BaseExecutorPool(MAX_EXECUTOR_NUM, jobPool);

        this.jobPool = jobPool;
        this.executors = executors;

        /* 启动处理器 */
        this.startup();

    }

    /**
     * 启动工作线程，开始处理任务
     */
    public void startup() {

        /* 启动处理器工作 */
        for (Executor executor : executors.getExecutors()) {
            executor.start();
        }
    }

    /**
     * 向Job池中放入新Job，托管交由Job池处理
     *
     * @param newJob
     *            新Job
     * @throws java.rmi.RemoteException
     */
    public void put(Job newJob) throws RemoteException {
        jobPool.put(newJob);
    }

    /**
     * 批量增加新Job，托管交由Job池处理
     *
     * @param jobs
     *            批量Job
     * @throws java.rmi.RemoteException
     */
    public void putBatch(Job[] jobs) throws RemoteException {
        jobPool.putBatch(jobs);
    }

    /**
     * 获知Job池的相关信息
     *
     * @return Job池的相关信息
     * @throws java.rmi.RemoteException
     */
    public String getJobPoolInfo() throws RemoteException {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("Job池中尚未处理的Job数目：" + jobPool.getNum() + "/n");

        for (Executor executor : executors.getExecutors()) {
            stringBuffer.append("处理器【" + executor.getExecutorId() + "】正在"
                    + ((executor.isRunning()) ? "运行..." : "等待.../n"));
        }

        return stringBuffer.toString();
    }

    /**
     * 停止工作线程，工作线程不一定立即停止，只有在线程处于运行状态时会立即停止
     *
     * @throws java.rmi.RemoteException
     */
    public synchronized void shutdown() throws RemoteException {

        /* 关闭处理器 */
        for (Executor executor : executors.getExecutors()) {
            executor.shutdown();
            executor = null;
        }

        /* 清空Job池 */
        jobPool.clear();

        System.out.println("处理器已经全部停止，Job池已经清空...");
    }

    /**
     * 获取所要管理的Job池
     *
     * @return Job池
     */
    public JobPool getJobPool()
    {
        return jobPool;
    }

    /**
     * 设置要管理的Job池
     *
     * @param jobPool
     *            所要管理的Job池
     */
    public void setJobPool(JobPool jobPool)
    {
        this.jobPool = jobPool;
    }

    /**
     * 设置所要管理的处理器池
     *
     * @return 所要管理的处理器池
     */
    public ExecutorPool getExecutors()
    {
        return executors;
    }

    /**
     * 设置所要管理的处理器池
     *
     * @param executors
     *            所要管理的处理器池
     */
    public void setExecutors(ExecutorPool executors)
    {
        this.executors = executors;
    }

}
