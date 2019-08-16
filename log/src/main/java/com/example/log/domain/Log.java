package com.example.log.domain;

import com.example.log.domain.enumeration.LogType;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "vn_log")
public class Log implements Serializable {

    @Autowired
    public Log() {
    }

    public Log(LogType type, Long time) {
        this.type = type;
        this.time = time;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vn_account")
    private String account;

    @Column(name = "vn_url")
    private String url;

    @Column(name = "vn_method_name")
    private String methodName;

    @Column(name = "vn_address")
    private String address;

    @Column(name = "vn_desc")
    private String desc;

    @Column(name = "vn_params")
    private String params;

    @Column(name = "vn_type")
    @Enumerated(EnumType.STRING)
    private LogType type;

    @Column(name = "vn_time")
    private Long time;

    @Column(name = "vn_request_type")
    private String requestType;

    @Column(name = "vn_create_time")
    private Instant createTime;

    @Column(name = "vn_browser")
    private String browser;

    @Column(name = "vn_device")
    private String device;

    /**
     * 异常详细
     */
    @Column(name = "vn_exception_detail", columnDefinition = "text")
    private String exceptionDetail;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Log log = (Log) o;

        return id != null ? id.equals(log.id) : log.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", url='" + url + '\'' +
                ", methodName='" + methodName + '\'' +
                ", address='" + address + '\'' +
                ", desc='" + desc + '\'' +
                ", params='" + params + '\'' +
                ", type=" + type +
                '}';
    }


    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public LogType getType() {
        return type;
    }

    public void setType(LogType type) {
        this.type = type;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getExceptionDetail() {
        return exceptionDetail;
    }

    public void setExceptionDetail(String exceptionDetail) {
        this.exceptionDetail = exceptionDetail;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
}
