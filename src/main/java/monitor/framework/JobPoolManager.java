package monitor.framework;

import java.rmi.RemoteException;

/**
 * 为了使计算资源也作业本身对接，
 * 我们创建一个作业池管理类JobPoolManager封装JobPool与ExecuterPool，用于取出Job并执行。
 *
 * Created by qigao212074 on 2016/9/2.
 */
public interface JobPoolManager {

    /**
     * 启动工作线程，开始处理任务
     */
    public void startup();

    /**
     * 向Job池中放入新Job，托管交由Job池处理
     *
     * @param newJob
     *            新Job
     * @throws java.rmi.RemoteException
     */
    public void put(Job newJob) throws RemoteException;

    /**
     * 批量增加新Job，托管交由Job池处理
     *
     * @param jobs
     *            批量Job
     * @throws java.rmi.RemoteException
     */
    public void putBatch(Job[] jobs) throws RemoteException;

    /**
     * 获知Job池的相关信息
     *
     * @return Job池的相关信息
     * @throws java.rmi.RemoteException
     */
    public String getJobPoolInfo() throws RemoteException;

    /**
     * 停止工作线程，工作线程不一定立即停止，只有在线程处于运行状态时会立即停止
     *
     * @throws java.rmi.RemoteException
     */
    public void shutdown() throws RemoteException;

    /**
     * 获取所要管理的Job池
     *
     * @return Job池
     */
    public JobPool getJobPool();

    /**
     * 设置要管理的Job池
     *
     * @param jobPool
     *            所要管理的Job池
     */
    public void setJobPool(JobPool jobPool);

    /**
     * 设置所要管理的处理器池
     *
     * @return 所要管理的处理器池
     */
    public ExecutorPool getExecutors();

    /**
     * 设置所要管理的处理器池
     *
     * @param executers
     *            所要管理的处理器池
     */
    public void setExecutors(ExecutorPool executers);

}
