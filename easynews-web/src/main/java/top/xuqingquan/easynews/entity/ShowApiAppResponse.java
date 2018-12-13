package top.xuqingquan.easynews.entity;

/**
 * 应用级参数（系统级输出参数showapi_res_body字段中的json数据结构）
 */
public class ShowApiAppResponse {

    //返回的分页结构
    private ShowApiPageBean pagebean;
    //成功为0，其他失败
    private String ret_code;

    public ShowApiAppResponse() {
    }

    public ShowApiAppResponse(ShowApiPageBean pagebean, String ret_code) {
        this.pagebean = pagebean;
        this.ret_code = ret_code;
    }

    public ShowApiPageBean getPagebean() {
        return pagebean;
    }

    public void setPagebean(ShowApiPageBean pagebean) {
        this.pagebean = pagebean;
    }

    public String getRet_code() {
        return ret_code;
    }

    public void setRet_code(String ret_code) {
        this.ret_code = ret_code;
    }

    @Override
    public String toString() {
        return "ShowApiAppResponse{" +
                "pagebean=" + pagebean +
                ", ret_code='" + ret_code + '\'' +
                '}';
    }
}
