package com.gameball.school;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/* compiled from: example */
class RetnObject {
    @SerializedName("regToken")
    @Expose
    private String regToken;

    RetnObject() {
    }

    public String getRegToken() {
        return this.regToken;
    }

    public void setRegToken(String regToken) {
        this.regToken = regToken;
    }
}
