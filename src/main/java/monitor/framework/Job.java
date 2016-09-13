package monitor.framework;

import java.io.Serializable;

/**
 *   Job即是对处理某个任务/作业执行流程的一种抽象，
 *   Job接口提供一个最重要的方法：run()，我们可以定义具体的Job类，实现run()方法，
 *   用于填充必要的业务逻辑，在run()执行过程中可能会产生新的Job。
 *
 * Created by qigao212074 on 2016/9/2.
 */
public interface Job extends Runnable, Serializable
{
    /**
     * Job执行入口，填充必要的业务逻辑，执行过程中可能产生新的任务
     *
     */
    public void run();

    /**
     * 获知该Job相关的信息
     *
     * @return Job相关信息
     */
    public String getJobInfo();
}
