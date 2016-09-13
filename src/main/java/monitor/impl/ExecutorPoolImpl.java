package monitor.impl;

import monitor.framework.Executor;
import monitor.framework.ExecutorPool;
import monitor.framework.JobPool;

/**
 * Created by qigao212074 on 2016/9/2.
 */
public class ExecutorPoolImpl implements ExecutorPool {

    /* 实际处理器池中所能存储的最大处理器数目 */
    protected int maxExecutorNum = 0;

    /* 处理器所处理的Job池 */
    protected JobPool jobPool = null;

    /* 处理器队列 */
    protected Executor[] executors = null;

    /**
     * 构造函数，初始化相关参数
     *
     * @param maxExecutorNum
     *            实际Job池中所能存储的最大Job数目
     * @param jobPool
     *            所要处理的Job池
     */
    public ExecutorPoolImpl(int maxExecutorNum, JobPool jobPool) {
        this.maxExecutorNum = maxExecutorNum;
        this.jobPool = jobPool;

        this.init();
    }

    /**
     * 初始化处理器
     */
    public void init() {
        /* 必须先为数组申请存储空间，否则会出现空指针异常 */
        executors = new Executor[maxExecutorNum];

        /* 遍历初始化处理器 */
        for (int index = 0; index < maxExecutorNum; ++index) {
            this.executors[index] = new ExecutorImpl(index + 1, jobPool);
        }
    }

    /**
     * 从处理器池中获得处理器数组
     *
     * @return 处理器数组
     */
    public Executor[] getExecutors() {
        /* 如果处理器队列为空，则调用init()函数初始化处理器队列 */
        if (null == executors) {
            this.init();
        }

        return this.executors;
    }

    /**
     * 获取处理器池中最大能容纳的处理器数目
     *
     * @return 最大能容纳的处理器数目
     */
    public int getMaxExecutorNum()
    {
        return maxExecutorNum;
    }

    /**
     * 设置处理器池最大能容纳的处理器数目
     *
     * @param maxExecutorNum
     *            最大能容纳的处理器数目
     */
    public void setMaxExecutorNum(int maxExecutorNum)
    {
        this.maxExecutorNum = maxExecutorNum;
    }

    /**
     * 获取处理器池所对应的Job池
     *
     * @return 对应的Job池
     */
    public JobPool getJobPool()
    {
        return jobPool;
    }

    /**
     * 为处理器池设置对应的Job池
     *
     * @param jobPool
     *            对应的Job池
     */
    public void setJobPool(JobPool jobPool) {
        this.jobPool = jobPool;
    }

    /**
     * 设置处理器数组
     *
     * @param executors
     *            处理器数组
     */
    public void setExecutors(Executor[] executors)
    {
        this.executors = executors;
    }

}
