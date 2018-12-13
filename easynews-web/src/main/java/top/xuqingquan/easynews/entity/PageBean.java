package top.xuqingquan.easynews.entity;

import java.util.List;

/**
 * 返回给客户端的分页封装
 */
public class PageBean<T> {

    //第一个的id
    private int before;
    //最后一个的id
    private int after;
    //数据
    private List<T> data;

    public PageBean() {
    }

    public PageBean(int before, int after, List<T> data) {
        this.before = before;
        this.after = after;
        this.data = data;
    }

    public int getBefore() {
        return before;
    }

    public void setBefore(int before) {
        this.before = before;
    }

    public int getAfter() {
        return after;
    }

    public void setAfter(int after) {
        this.after = after;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                " before=" + before +
                ", after=" + after +
                ", data=" + data +
                '}';
    }
}
