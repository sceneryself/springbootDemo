package com.self.model.enums;

public enum IntentResult {
    YES(1, "确认"),
    NO(0, "否认")
    ;

    private int code;
    private String value;

    private IntentResult(int code, String value){
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
