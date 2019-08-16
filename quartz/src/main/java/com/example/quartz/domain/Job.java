package com.example.quartz.domain;


import com.example.quartz.domain.enumeration.ScheduleStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "vn_job")
@Entity
public class Job implements Serializable {

    private static final long serialVersionUID = 400066840871805700L;


    /**
     * 任务调度参数key
     */
    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @ExportConfig(value = "任务ID")
    private Long jobId;

    @Column(name = "BEAN_NAME")
//    @ExportConfig(value = "Bean名称")
    private String beanName;

    @Column(name = "METHOD_NAME")
//    @ExportConfig(value = "方法名称")
    private String methodName;

    @Column(name = "PARAMS")
//    @ExportConfig(value = "参数")
    private String params;

    @Column(name = "CRON_EXPRESSION")
//    @ExportConfig(value = "cron表达式")
    private String cronExpression;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
//    @ExportConfig(value = "状态", convert = "s:0=正常,1=暂停")
    private ScheduleStatus status;

    @Column(name = "REMARK")
//    @ExportConfig(value = "备注")
    private String remark;

    @Column(name = "CREATE_TIME")
//    @ExportConfig(value = "创建时间", convert = "c:cc.mrbird.common.utils.poi.convert.TimeConvert")
    private Date createTime;

    /**
     * @return JOB_ID
     */
    public Long getJobId() {
        return jobId;
    }

    /**
     * @param jobId
     */
    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    /**
     * @return BEAN_NAME
     */
    public String getBeanName() {
        return beanName;
    }

    /**
     * @param beanName
     */
    public void setBeanName(String beanName) {
        this.beanName = beanName == null ? null : beanName.trim();
    }

    /**
     * @return METHOD_NAME
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * @param methodName
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName == null ? null : methodName.trim();
    }

    /**
     * @return PARAMS
     */
    public String getParams() {
        return params;
    }

    /**
     * @param params
     */
    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    /**
     * @return CRON_EXPRESSION
     */
    public String getCronExpression() {
        return cronExpression;
    }

    /**
     * @param cronExpression
     */
    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression == null ? null : cronExpression.trim();
    }

    /**
     * @return STATUS
     */
    public ScheduleStatus getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(ScheduleStatus status) {
        this.status = status;
    }

    /**
     * @return REMARK
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * @return CREATE_TIME
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobId=" + jobId +
                ", beanName='" + beanName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", params='" + params + '\'' +
                ", cronExpression='" + cronExpression + '\'' +
                ", status='" + status + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}