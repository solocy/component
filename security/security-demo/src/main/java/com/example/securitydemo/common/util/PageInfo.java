package com.example.securitydemo.common.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import cn.hutool.core.util.PageUtil;

import java.io.Serializable;
import java.util.Objects;

public class PageInfo implements Serializable {
    public static final int DEFAULT_PAGE_SIZE = 20;

    /** 页码，0表示第一页 */
    private int pageNumber;
    /** 每页结果数 */
    private int pageSize;
    /** 排序 */
    private Order order;

    // ---------------------------------------------------------- Constructor start
    /**
     * 构造，默认第0页，每页{@value #DEFAULT_PAGE_SIZE} 条
     *
     * @since 4.5.16
     */
    public PageInfo() {
        this(0, DEFAULT_PAGE_SIZE);
    }

    /**
     * 构造
     *
     * @param pageNumber 页码，0表示第一页
     * @param pageSize 每页结果数
     */
    public PageInfo(int pageNumber, int pageSize) {
        this.pageNumber = Math.max(pageNumber, 0);
        this.pageSize = pageSize <= 0 ? DEFAULT_PAGE_SIZE : pageSize;
    }

    /**
     * 构造
     *
     * @param pageNumber 页码
     * @param numPerPage 每页结果数
     * @param order 排序对象
     */
    public PageInfo(int pageNumber, int numPerPage, Order order) {
        this(pageNumber, numPerPage);
        this.order =order;
    }
    // ---------------------------------------------------------- Constructor start

    // ---------------------------------------------------------- Getters and Setters start
    /**
     * @return 页码
     */
    public int getPageNumber() {
        return pageNumber;
    }

    /**
     * 设置页码，0表示第一页
     *
     * @param pageNumber 页码
     */
    public void setPageNumber(int pageNumber) {
        this.pageNumber = Math.max(pageNumber, 0);
    }

    /**
     * @return 每页结果数
     * @deprecated 使用 {@link #getPageSize()} 代替
     */
    @Deprecated
    public int getNumPerPage() {
        return getPageSize();
    }

    /**
     * 设置每页结果数
     *
     * @param pageSize 每页结果数
     * @deprecated 使用 {@link #setPageSize(int)} 代替
     */
    @Deprecated
    public void setNumPerPage(int pageSize) {
        setPageSize(pageSize);
    }

    /**
     * @return 每页结果数
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置每页结果数
     *
     * @param pageSize 每页结果数
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize <= 0 ? DEFAULT_PAGE_SIZE : pageSize;
    }

    /**
     * @return 排序
     */
    public Order getOrder() {
        return this.order;
    }

    /**
     * 设置排序
     *
     * @param orders 排序
     */
    public void setOrder(Order orders) {
        this.order = orders;
    }

    /**
     * 设置排序
     *
     * @param order 排序
     */
    public void addOrder(Order order) {

        this.order =order;
    }
    // ---------------------------------------------------------- Getters and Setters end

    /**
     * @return 开始位置
     */
    public int getStartPosition() {
        return getStartEnd()[0];
    }

    /**
     * @return 结束位置
     */
    public int getEndPosition() {
        return getStartEnd()[1];
    }

    /**
     * 开始位置和结束位置<br>
     * 例如：<br>
     * 页码：1，每页10 =》 [0, 10]<br>
     * 页码：2，每页10 =》 [10, 20]<br>
     * 。。。<br>
     *
     * @return 第一个数为开始位置，第二个数为结束位置
     */
    public int[] getStartEnd() {
        return PageUtil.transToStartEnd(pageNumber, pageSize);
    }

    @Override
    public String toString() {
        return "PageInfo [page=" + pageNumber + ", pageSize=" + pageSize + ", order=" + order + "]";
    }

    public static Pageable toPageable(PageInfo pageable) {
        Sort sort;
        if (Objects.isNull(pageable.getOrder())) {
            return new PageRequest(pageable.getPageNumber(),pageable.getPageSize());
        }
        if ("DESC".equals(pageable.getOrder().getDirection().toString())) {
            sort =new Sort(Sort.Direction.DESC,pageable.getOrder().getField());
        } else {
            sort = new Sort(pageable.getOrder().getField());
        }
        return new PageRequest(pageable.getPageNumber(),pageable.getPageSize(),sort);
    }
}
