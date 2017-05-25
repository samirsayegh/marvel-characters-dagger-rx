package com.samirsayegh.rxtestmarvelchars.data.dto;

import com.samirsayegh.rxtestmarvelchars.data.dto.base.BaseInformationDTO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by yormirsamir.sayegh on 04/05/2017.
 */

public class TitleDTO extends BaseInformationDTO {

    @SerializedName("title")
    @Expose
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
