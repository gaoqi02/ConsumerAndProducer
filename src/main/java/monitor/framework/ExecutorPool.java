package monitor.framework;

import monitor.framework.JobPool;

import java.util.concurrent.Executor;

/**
 * 利用ExecutorPool对Executor进行管理。
 *
 * Created by qigao212074 on 2016/9/2.
 */
public interface ExecutorPool {
    /**
     * 从处理器池中获得处理器数组
     *
     * @return 处理器数组
     */
    public monitor.framework.Executor[] getExecutors();

    /**
     * 获取处理器池所对应的Job池
     *
     * @return 对应的Job池
     */
    public JobPool getJobPool();

    /**
     * 为处理器池设置对应的Job池
     *
     * @param jobPool
     *            对应的Job池
     */
    public void setJobPool(JobPool jobPool);
}
