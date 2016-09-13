package monitor.impl;

import monitor.framework.Job;

import java.util.Date;

/**
 * 抽象工作类BaseJob：提供基本Job操作
 *
 * Created by qigao212074 on 2016/9/2.
 */
public abstract class JobImpl implements Job {

    protected long id;

    // Job创建时间
    protected Date createTime = null;

    // Job提交执行时间
    protected Date submitTime = null;

    // Job开始执行时间
    protected Date beginTime = null;

    // 完成时间
    protected Date finishTime = null;

    /**
     * 默认构造函数初始化Job的创建时间
     */
    public JobImpl()
    {
        this.createTime = new Date();
    }

    /**
     * Job执行入口，填充必要的业务逻辑，执行过程中可能产生新的任务
     *
     */
    public abstract void run();

    /**
     * 获知该Job相关的信息
     *
     * @return Job相关信息
     */
    public String getJobInfo()
    {
        return "Job:【ID】=" + this.id + "/t【生成时间】:" + this.createTime
                + "/t【开始执行时间】:" + this.beginTime + "/t【执行完成时间】:"
                + this.finishTime;
    }

    /**
     * 获取Job的ID
     *
     * @return
     */
    public long getId()
    {
        return id;
    }

    /**
     * 设置Job的ID
     *
     * @param id Job的ID
     */
    public void setId(long id)
    {
        this.id = id;
    }

    /**
     * 获取Job的创建时间
     *
     * @return Job的创建时间
     */
    public Date getCreateTime()
    {
        return createTime;
    }

    /**
     * 设置Job的创建时间
     *
     * @param createTime Job的创建时间
     */
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    /**
     * 获取Job的提交时间
     *
     * @return Job的提交时间
     */
    public Date getSubmitTime()
    {
        return submitTime;
    }

    /**
     * 设置Job的提交时间
     *
     * @param submitTime
     *            Job的提交时间
     */
    public void setSubmitTime(Date submitTime)
    {
        this.submitTime = submitTime;
    }

    /**
     * 获取Job开始执行的时间
     *
     * @return Job开始执行的时间
     */
    public Date getBeginTime()
    {
        return beginTime;
    }

    /**
     * 设置Job开始执行的时间
     *
     * @param beginTime
     *            Job开始执行的时间
     */
    public void setBeginTime(Date beginTime)
    {
        this.beginTime = beginTime;
    }

    /**
     * 获取Job执行结束的时间
     *
     * @return Job执行结束的时间
     */
    public Date getFinishTime()
    {
        return finishTime;
    }

    /**
     * 设置Job执行结束的时间
     *
     * @param finishTime
     *            Job执行结束的时间
     */
    public void setFinishTime(Date finishTime)
    {
        this.finishTime = finishTime;
    }
}
