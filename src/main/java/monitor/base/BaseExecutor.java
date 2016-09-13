package monitor.base;

import monitor.framework.JobPool;
import monitor.impl.ExecutorImpl;

/**
 * 基础的处理器BaseExecuter类：提供最基本处理Job的方法
 *
 * Created by qigao212074 on 2016/9/2.
 */
public class BaseExecutor extends ExecutorImpl {

    /**
     * 构造函数
     *
     * @param executorId
     *            处理器ID
     * @param jobPool
     *            所要处理的Job池
     */
    public BaseExecutor(int executorId, JobPool jobPool)
    {
        super(executorId, jobPool);
    }

}
