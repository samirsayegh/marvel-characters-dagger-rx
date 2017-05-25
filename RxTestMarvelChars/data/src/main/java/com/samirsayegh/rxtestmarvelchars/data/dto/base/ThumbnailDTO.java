package com.samirsayegh.rxtestmarvelchars.data.dto.base;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by yormirsamir.sayegh on 04/05/2017.
 */

public class ThumbnailDTO {

    @SerializedName("path")
    @Expose
    private String path;

    @SerializedName("extension")
    @Expose
    private String extension;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
