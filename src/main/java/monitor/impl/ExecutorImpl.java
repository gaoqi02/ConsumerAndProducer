package monitor.impl;

import monitor.framework.Executor;
import monitor.framework.JobPool;

import java.rmi.RemoteException;
import java.util.Date;

/**
 * 处理器抽象类ExecutorImpl：以线程作为处理器的载体，并实现了处理器Executor抽象接口
 *
 * Created by qigao212074 on 2016/9/2.
 */
public class ExecutorImpl extends Thread implements Executor {


    /* 处理器的ID */
    protected int executorId;

    /* 所作用的作业池 */
    protected JobPool jobPool = null;

    /* 该工作线程是否正在执行 */
    protected boolean isRunning = false;

    /* 该工作线程是否已经关闭 */
    protected boolean isShutdown = false;

    /**
     * 设置处理器的ID和所要处理的Job池
     *
     * @param executorId
     *            处理器ID
     * @param jobPool
     *            所要处理的Job池
     */
    public ExecutorImpl(int executorId, JobPool jobPool)
    {
        this.executorId = executorId;
        this.jobPool = jobPool;
    }

    /**
     * 关键代码：处理Job池中的Job
     */
    public void run() {
        while (!this.isShutdown) {
            JobImpl job = null;

            /* 同步对象为JobPool */
            synchronized (jobPool) {
                try {
                    while (jobPool.isEmpty()) {
                        try {
                            /* Job池为空则进入等待状态 */
                            jobPool.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    /* 从Job池中取出一个Job */
                    job = (JobImpl) jobPool.get();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            if (null != job) {
                /* 设置忙碌状态 */
                this.isRunning = true;

                /* 设置Job开始执行时间 */
                job.setBeginTime(new Date());


                /* 执行Job */
                new Thread(job).start();

                /* 设置Job执行完毕的时间 */
                job.setFinishTime(new Date());

                /* 处理器重新处于等待状态 */
                this.isRunning = false;

                /* 清除处理后的Job */
                job = null;
            }
        }
    }

    /**
     * 设置处理器为关闭状态
     */
    public void shutdown()
    {
        this.setShutdown(true);
    }

    /**
     * 获取处理器ID
     *
     * @return 处理器ID
     */
    public int getExecutorId()
    {
        return executorId;
    }

    /**
     * 设置处理器ID
     *
     * @param executorId
     *            处理器ID
     */
    public void setExecutorId(int executorId) {
        this.executorId = executorId;
    }

    /**
     * 获取该处理器作用的Job池
     *
     * @return Job池
     */
    public JobPool getJobPool() {
        return jobPool;
    }

    /**
     * 为该处理器设置对应的Job池
     *
     * @param jobPool
     *            Job池
     */
    public void setJobPool(JobPool jobPool) {
        this.jobPool = jobPool;
    }

    /**
     * 处理器是否正在执行
     *
     * @return 处理器是否正在执行
     */
    public boolean isRunning()
    {
        return isRunning;
    }

    /**
     * 设置处理器的执行状态
     *
     * @param isRunning
     *            处理器的执行状态
     */
    public void setRunning(boolean isRunning)
    {
        this.isRunning = isRunning;
    }

    /**
     * 处理器是否已经关闭
     *
     * @return 处理器是否已经关闭
     */
    public boolean isShutdown()
    {
        return isShutdown;
    }

    /**
     * 设置处理器的关闭状态
     *
     * @param isShutdown
     *            处理器的关闭状态
     */
    public void setShutdown(boolean isShutdown)
    {
        this.isShutdown = isShutdown;
    }

}
