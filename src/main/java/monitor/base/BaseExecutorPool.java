package monitor.base;

import monitor.framework.JobPool;
import monitor.impl.ExecutorPoolImpl;

/**
 * 基础的处理器池类BaseExecuterPool：基本的封装处理器队列
 *
 * Created by qigao212074 on 2016/9/2.
 */
public class BaseExecutorPool extends ExecutorPoolImpl {

    /**
     * 构造函数
     *
     * @param maxExecutorNum
     *            能容纳的最大处理器数目
     * @param jobPool
     *            要处理的Job池
     */
    public BaseExecutorPool(int maxExecutorNum, JobPool jobPool) {
        super(maxExecutorNum, jobPool);
    }

}
