package monitor.framework;

/**
 * 计算资源从JobPool里出取一个Job，并执行Job.run()
 * 计算资源抽象为Executer，即处理器。
 * Executer可以是一个进程或者是一个线程，可以是本地的计算资源，也可以考虑是远程的计算资源，
 * 这样我们可以灵活地将单机环境的系统扩展为一个分布式计算系统。
 * 为了简单处理，我们Executer的设计先考虑本地线程的情况。
 * Created by qigao212074 on 2016/9/2.
 */
public interface Executor {

    /**
     * 处理器执行Job的接口
     */
    public abstract void run();

    /**
     * 处理器开始执行
     */
    public abstract void start();

    /**
     * 处理器关闭接口
     */
    public abstract void shutdown();

    /**
     * 获取处理器ID
     *
     * @return 处理器ID
     */
    public int getExecutorId();

    /**
     * 设置处理器ID
     *
     * @param executerId
     *            处理器ID
     */
    public void setExecutorId(int executerId);

    /**
     * 获取该处理器作用的Job池
     *
     * @return Job池
     */
    public JobPool getJobPool();

    /**
     * 为该处理器设置对应的Job池
     *
     * @param jobPool
     *            Job池
     */
    public void setJobPool(JobPool jobPool);

    /**
     * 处理器是否正在执行
     *
     * @return 处理器是否正在执行
     */
    public boolean isRunning();

    /**
     * 设置处理器的执行状态
     *
     * @param isRunning
     *            处理器的执行状态
     */
    public void setRunning(boolean isRunning);

    /**
     * 处理器是否已经关闭
     *
     * @return 处理器是否已经关闭
     */
    public boolean isShutdown();

    /**
     * 设置处理器的关闭状态
     *
     * @param isShutdown
     *            处理器的关闭状态
     */
    public void setShutdown(boolean isShutdown);
}
