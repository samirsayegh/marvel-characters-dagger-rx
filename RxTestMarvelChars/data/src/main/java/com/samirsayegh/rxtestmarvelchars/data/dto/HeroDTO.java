package com.samirsayegh.rxtestmarvelchars.data.dto;

import com.samirsayegh.rxtestmarvelchars.data.dto.base.BaseInformationDTO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by yormirsamir.sayegh on 04/05/2017.
 */

public class HeroDTO extends BaseInformationDTO {

    @SerializedName("name")
    @Expose
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
