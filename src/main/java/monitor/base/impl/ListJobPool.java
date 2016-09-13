package monitor.base.impl;


import monitor.base.BaseJob;
import monitor.base.BaseJobPool;
import monitor.framework.Job;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * List形式的Job池类ListJobPool：以List形式为载体的Job池实现
 *
 * Created by qigao212074 on 2016/9/2.
 */
public class ListJobPool extends BaseJobPool {

    private static final long serialVersionUID = -6236080833979961860L;

    // 用于标识Job的ID
    private long jobCounter = 0;

    //由于使用的比较小，所以不适用blockQueue作为存储的载体，减少开支
    private List<Job> jobList = Collections
            .synchronizedList(new LinkedList<Job>());

    /**
     * 默认构造函数
     */
    public ListJobPool() throws RemoteException {
        super();
    }

    /**
     * 从Job池中取出一个Job
     *
     */
    public Job get() throws RemoteException {
        synchronized (this) {
            return jobList.remove(0);
        }
    }

    /**
     * 向Job池中放入新Job
     *
     * @param newJob 新Job
     */
    public boolean put(Job newJob) throws RemoteException {
        synchronized(this) {
            /* 强制使用BaseJob类型，设置Job的相关属性 */
            ((BaseJob) newJob).setId(++jobCounter);
            ((BaseJob) newJob).setSubmitTime(new Date());

            /* 放入Job池 */
            jobList.add(newJob);

            /* 唤醒Job池，开始执行Job */
            this.notifyAll();
            return true;
        }
    }

    /**
     * 批量增加新Job
     *
     * @param jobs 批量Job
     */
    public boolean putBatch(Job[] jobs) throws RemoteException {
        /* 作业队列为空则返回 */
        if (null == jobs || jobs.length == 0) {
            return false;
        }

        /* 同步对象为封装了Job队列的ListJobPool */
        synchronized (this) {
            /* 遍历将Job依次放入Job池中 */
            for (int i = 0; i < jobs.length; ++i) {
                if (null == jobs[i])
                {
                    continue;
                }

                /* 设置Job相关属性信息 */
                ((BaseJob) jobs[i]).setId(++jobCounter);
                ((BaseJob) jobs[i]).setSubmitTime(new Date());

                /* 将该Job放入Job池中 */
                jobList.add(jobs[i]);
            }

            /* 唤醒Job池，开始执行 */
            this.notifyAll();

            return true;
        }
    }

    /**
     * 获取Job池中Job的数目
     *
     * @return Job池中Job数目
     */
    public int getNum() throws RemoteException {
        synchronized (this) {
            return jobList.size();
        }
    }

    /**
     * Job池是否为空
     *
     * @return Job池是否为空
     */
    public boolean isEmpty() throws RemoteException {
        synchronized (this){
            return jobList.isEmpty();
        }
    }

    /**
     * Job池是否已满
     *
     * @return Job池是否已满
     */
    public boolean isFull() throws RemoteException {
        synchronized (this) {
            return jobList.size() >= this.getMaxJobNum();
        }
    }

    /**
     * Job池是已清空
     *
     * @return Job池是已清空
     */
    public boolean clear() throws RemoteException {
        synchronized (this)
        {
            jobList.clear();

            return true;
        }
    }
}
