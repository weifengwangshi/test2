package com.basefrm.excel.config;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class FieldTitleValue extends FieldValue {
    private KeyUpValue keyUpValue;
    /**cell z中的内容需要截取是可配置此项,格式为“(1,20)”*/
    private String keyValue;
    public FieldTitleValue() {
    }
    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public KeyUpValue getKeyUpValue() {
        return keyUpValue;
    }

    public void setKeyUpValue(KeyUpValue keyUpValue) {
        this.keyUpValue = keyUpValue;
    }
}
