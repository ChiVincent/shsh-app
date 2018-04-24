package com.gameball.school;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/* compiled from: example */
class Example {
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("retnObject")
    @Expose
    private RetnObject retnObject;

    Example() {
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RetnObject getRetnObject() {
        return this.retnObject;
    }

    public void setRetnObject(RetnObject retnObject) {
        this.retnObject = retnObject;
    }
}
