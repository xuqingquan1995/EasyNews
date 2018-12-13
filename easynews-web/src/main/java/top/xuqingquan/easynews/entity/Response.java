package top.xuqingquan.easynews.entity;

/**
 * 返回客户端的结果
 * @param <T>
 */
public class Response<T> {

    private int code;
    private T data;
    private String error;

    public Response() {
    }

    public Response(T data) {
        this.code = 0;
        this.data = data;
    }

    public Response(int code, String error) {
        this.code = code;
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", data=" + data +
                ", error='" + error + '\'' +
                '}';
    }
}
