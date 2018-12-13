package top.xuqingquan.easynews.entity;

/**
 * 系统级参数（所有接入点都会返回的参数）
 */
public class ShowApiResponse {

    //消息体的JSON封装，所有应用级的返回参数将嵌入此对象 。
    private ShowApiAppResponse showapi_res_body;
    /**
     * 易源返回标志,0为成功，其他为失败。
     * 0成功
     * -1，系统调用错误
     * -2，可调用次数或金额为0
     * -3，读取超时
     * -4，服务端返回数据解析错误
     * -5，后端服务器DNS解析错误
     * -6，服务不存在或未上线
     * -1000，系统维护
     * -1002，showapi_appid字段必传
     * -1003，showapi_sign字段必传
     * -1004，签名sign验证有误
     * -1005，showapi_timestamp无效
     * -1006，app无权限调用接口
     * -1007，没有订购套餐
     * -1008，服务商关闭对您的调用权限
     * -1009，调用频率受限
     * -1010，找不到您的应用
     * -1011，子授权app_child_id无效
     * -1012，子授权已过期或失效
     * -1013，子授权ip受限
     */
    private int showapi_res_code;
    //错误信息的展示
    private String showapi_res_error;
    //本次请求id
    private String showapi_res_id;

    public ShowApiResponse() {
    }

    public ShowApiResponse(ShowApiAppResponse showapi_res_body, int showapi_res_code, String showapi_res_error, String showapi_res_id) {
        this.showapi_res_body = showapi_res_body;
        this.showapi_res_code = showapi_res_code;
        this.showapi_res_error = showapi_res_error;
        this.showapi_res_id = showapi_res_id;
    }

    public ShowApiAppResponse getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowApiAppResponse showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public String getShowapi_res_id() {
        return showapi_res_id;
    }

    public void setShowapi_res_id(String showapi_res_id) {
        this.showapi_res_id = showapi_res_id;
    }

    @Override
    public String toString() {
        return "ShowApiResponse{" +
                "showapi_res_body='" + showapi_res_body + '\'' +
                ", showapi_res_code=" + showapi_res_code +
                ", showapi_res_error='" + showapi_res_error + '\'' +
                ", showapi_res_id='" + showapi_res_id + '\'' +
                '}';
    }
}
