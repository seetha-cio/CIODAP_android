package com.connectedio.ciodav001.model;

/**
 * Created by seetha on 23/8/16.
 */
public class Result {
    String key,value;

    public Result(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
