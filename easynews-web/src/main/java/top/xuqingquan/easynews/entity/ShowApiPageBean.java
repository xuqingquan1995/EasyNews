package top.xuqingquan.easynews.entity;

import net.sf.json.JSONArray;

/**
 * 分页数据
 */
public class ShowApiPageBean {

    //所有记录数
    private int allNum;
    //所有页数
    private int allPages;
    //当前页
    private int currentPage;
    //每页最大记录数
    private int maxResult;
    //数据条目列表
    private JSONArray contentlist;

    public ShowApiPageBean() {
    }

    public ShowApiPageBean(int allNum, int allPages, int currentPage, int maxResult, JSONArray contentlist) {
        this.allNum = allNum;
        this.allPages = allPages;
        this.currentPage = currentPage;
        this.maxResult = maxResult;
        this.contentlist = contentlist;
    }

    public int getAllNum() {
        return allNum;
    }

    public void setAllNum(int allNum) {
        this.allNum = allNum;
    }

    public int getAllPages() {
        return allPages;
    }

    public void setAllPages(int allPages) {
        this.allPages = allPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getMaxResult() {
        return maxResult;
    }

    public void setMaxResult(int maxResult) {
        this.maxResult = maxResult;
    }

    public JSONArray getContentlist() {
        return contentlist;
    }

    public void setContentlist(JSONArray contentlist) {
        this.contentlist = contentlist;
    }

    @Override
    public String toString() {
        return "ShowApiPageBean{" +
                "allNum=" + allNum +
                ", allPages=" + allPages +
                ", currentPage=" + currentPage +
                ", maxResult=" + maxResult +
                ", contentlist=" + contentlist +
                '}';
    }
}
