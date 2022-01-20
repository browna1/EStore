package com.zzh.estore.util;

import java.io.Serializable;

/**
 * @author ：zzh
 * @description ：Json格式的数据进行相应
 * @date ：Created in 2022/1/8 11:15
 */
public class JsonResult<E> implements Serializable {
    // 状态码
    private Integer status;
    // 描述信息
    private String message;
    // 用泛型表示类  因为类型不确定
    private E data;

    public JsonResult() {
    }

    public JsonResult(Integer status) {
        this.status = status;
    }

    public JsonResult(Throwable e) {
        this.message = e.getMessage();
    }
    public JsonResult(Integer status, E data) {
        this.status = status;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
