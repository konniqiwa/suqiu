package entity;

import java.util.HashMap;
import java.util.Map;
import javax.management.RuntimeErrorException;

public class JsonDTO {
    private int status;
    private String msg;
    private String errorMsg;
    private String errorCode;
    private Object data;
    private static ThreadLocal<JsonDTO> jsonDTO = new ThreadLocal();
    private static ThreadLocal<Map<String, Object>> dataMap = new ThreadLocal();
    public static final Integer SUCCESS = 1;
    public static final Integer FAIL = 0;

    public JsonDTO() {
    }

    public static JsonDTO createInstance() {
        jsonDTO.set(new JsonDTO());
        dataMap.set(new HashMap());
        return (JsonDTO)jsonDTO.get();
    }

    public int getStatus() {
        return this.status;
    }

    public JsonDTO setStatus(int status) {
        this.status = status;
        return (JsonDTO)jsonDTO.get();
    }

    public String getMsg() {
        return this.msg;
    }

    public JsonDTO setMsg(String msg) {
        this.msg = msg;
        return (JsonDTO)jsonDTO.get();
    }

    public JsonDTO put(String key, Object value) {
        if (this.data != null && !(this.data instanceof Map)) {
            throw new RuntimeErrorException(new Error(), "JsonDTO.put()调用失败：setData和put方法不可同时调用，朕断定你已经调用过setData方法！");
        } else {
            ((Map)dataMap.get()).put(key, value);
            this.setData(dataMap.get());
            return (JsonDTO)jsonDTO.get();
        }
    }

    public Object getData() {
        return this.data;
    }

    public JsonDTO setData(Object data) {
        if (this.data != null && !(data instanceof Map)) {
            throw new RuntimeErrorException(new Error(), "JsonDTO.setData()调用失败：setData和put方法不可同时调用，朕断定你已经调用过put方法！");
        } else {
            this.data = data;
            return (JsonDTO)jsonDTO.get();
        }
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public JsonDTO setErrorCode(String errorCode) {
        this.errorCode = errorCode;
        return (JsonDTO)jsonDTO.get();
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public JsonDTO setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return (JsonDTO)jsonDTO.get();
    }
}
