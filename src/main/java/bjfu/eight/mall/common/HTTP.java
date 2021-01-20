package bjfu.eight.mall.common;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HTTP {
    private int status = 0;
    private String msg = "";
    private Object data = null;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static HTTP SUCCESS(){
        HTTP response = new HTTP();
        return response;
    }

    public static HTTP SUCCESS(String msg){
        HTTP response = new HTTP();
        response.setMsg(msg);
        return response;
    }

    public static HTTP SUCCESS(Object data){
        HTTP response = new HTTP();
        response.setData(data);
        return response;
    }

    public static HTTP SUCCESS(String msg, Object data){
        HTTP response = new HTTP();
        response.setMsg(msg);
        response.setData(data);
        return response;
    }

    public static HTTP ERROR(String msg){
        HTTP response = new HTTP();
        response.setStatus(1);
        response.setMsg(msg);
        return response;
    }
}

