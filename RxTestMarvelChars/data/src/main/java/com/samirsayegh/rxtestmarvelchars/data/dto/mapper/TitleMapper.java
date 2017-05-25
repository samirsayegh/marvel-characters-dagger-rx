package com.samirsayegh.rxtestmarvelchars.data.dto.mapper;

import com.samirsayegh.rxtestmarvelchars.data.dto.TitleDTO;
import com.samirsayegh.rxtestmarvelchars.data.dto.base.BaseDTO;
import com.samirsayegh.rxtestmarvelchars.data.dto.base.DataDTO;
import com.samirsayegh.rxtestmarvelchars.data.dto.base.ThumbnailDTO;
import com.samirsayegh.rxtestmarvelchars.domain.entities.BaseContent;
import com.samirsayegh.rxtestmarvelchars.domain.entities.OffsetList;
import com.samirsayegh.rxtestmarvelchars.domain.entities.Thumbnail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yormirsamir.sayegh on 25/05/2017.
 */

public class TitleMapper {

    public static OffsetList<BaseContent> toOffsetList(BaseDTO<List<TitleDTO>> baseDTO) {
        OffsetList<BaseContent> offsetList = new OffsetList<>();
        List<BaseContent> baseContents = new ArrayList<>();
        offsetList.setList(baseContents);
        DataDTO<List<TitleDTO>> dataDTO = baseDTO.getData();
        if (dataDTO != null) {
            offsetList.setOffset(dataDTO.getOffset());
            offsetList.setTotal(dataDTO.getTotal());
            for (TitleDTO titleDTO : dataDTO.getResults()) {
                if (titleDTO != null)
                    baseContents.add(toBaseContent(titleDTO));
            }
        }
        return offsetList;
    }

    private static BaseContent toBaseContent(TitleDTO titleDTO) {
        BaseContent baseContent = new BaseContent();
        if (titleDTO != null) {
            baseContent.setName(titleDTO.getTitle() != null ? titleDTO.getTitle() : "");
            baseContent.setDescription(titleDTO.getDescription() != null ? titleDTO.getDescription() : "");
            Thumbnail thumbnail = new Thumbnail();
            if (titleDTO.getThumbnail() != null) {
                ThumbnailDTO thumbnailDTO = titleDTO.getThumbnail();
                thumbnail.setPath(thumbnailDTO.getPath() != null ? thumbnailDTO.getPath() : "");
                thumbnail.setExtension(thumbnailDTO.getExtension() != null ? thumbnailDTO.getExtension() : "");
            }
            baseContent.setThumbnail(thumbnail);
        }
        return baseContent;
    }

}
