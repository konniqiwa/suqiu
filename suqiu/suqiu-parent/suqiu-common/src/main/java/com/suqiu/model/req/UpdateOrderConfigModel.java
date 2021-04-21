package com.suqiu.model.req;

import java.io.Serializable;

/**
 * @author suqiu
 */
public class UpdateOrderConfigModel implements Serializable {

    private Integer id;

    /**
     * 五星好评时间
     */
    private Integer commentOvertime;

    /**
     * 未收货自动关闭订单时间
     */
    private Integer confirmOvertime;

    /**
     * 不能申请售后时间
     */
    private Integer finishOvertime;


    /**
     * 秒杀订单超时时间
     */
    private Integer flashOrderOvertime;

    /**
     *正常订单超时时间
     */
    private Integer normalOrderOvertime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommentOvertime() {
        return commentOvertime;
    }

    public void setCommentOvertime(Integer commentOvertime) {
        this.commentOvertime = commentOvertime;
    }

    public Integer getConfirmOvertime() {
        return confirmOvertime;
    }

    public void setConfirmOvertime(Integer confirmOvertime) {
        this.confirmOvertime = confirmOvertime;
    }

    public Integer getFinishOvertime() {
        return finishOvertime;
    }

    public void setFinishOvertime(Integer finishOvertime) {
        this.finishOvertime = finishOvertime;
    }

    public Integer getFlashOrderOvertime() {
        return flashOrderOvertime;
    }

    public void setFlashOrderOvertime(Integer flashOrderOvertime) {
        this.flashOrderOvertime = flashOrderOvertime;
    }

    public Integer getNormalOrderOvertime() {
        return normalOrderOvertime;
    }

    public void setNormalOrderOvertime(Integer normalOrderOvertime) {
        this.normalOrderOvertime = normalOrderOvertime;
    }
}
