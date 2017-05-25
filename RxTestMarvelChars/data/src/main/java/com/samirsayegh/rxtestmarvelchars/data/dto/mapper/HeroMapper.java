package com.samirsayegh.rxtestmarvelchars.data.dto.mapper;

import com.samirsayegh.rxtestmarvelchars.data.dto.HeroDTO;
import com.samirsayegh.rxtestmarvelchars.data.dto.base.BaseDTO;
import com.samirsayegh.rxtestmarvelchars.data.dto.base.DataDTO;
import com.samirsayegh.rxtestmarvelchars.data.dto.base.ThumbnailDTO;
import com.samirsayegh.rxtestmarvelchars.domain.entities.Hero;
import com.samirsayegh.rxtestmarvelchars.domain.entities.OffsetList;
import com.samirsayegh.rxtestmarvelchars.domain.entities.Thumbnail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yormirsamir.sayegh on 26/04/2017.
 */

public class HeroMapper {

    public static Hero toHero(HeroDTO heroDTO) {
        Hero hero = new Hero();
        if (heroDTO != null) {
            hero.setId(heroDTO.getId());
            hero.setName(heroDTO.getName() != null ? heroDTO.getName() : "");
            hero.setDescription(heroDTO.getDescription() != null ? heroDTO.getDescription() : "");
            Thumbnail thumbnail = new Thumbnail();
            if (heroDTO.getThumbnail() != null) {
                ThumbnailDTO thumbnailDTO = heroDTO.getThumbnail();
                thumbnail.setPath(thumbnailDTO.getPath() != null ? thumbnailDTO.getPath() : "");
                thumbnail.setExtension(thumbnailDTO.getExtension() != null ? thumbnailDTO.getExtension() : "");
            }
            hero.setThumbnail(thumbnail);
        }
        return hero;
    }

    public static OffsetList<Hero> toHeroList(BaseDTO<List<HeroDTO>> heroDTOList) {
        OffsetList<Hero> offsetList = new OffsetList<>();
        List<Hero> heroList = new ArrayList<>();
        offsetList.setList(heroList);
        DataDTO<List<HeroDTO>> dataDTO = heroDTOList.getData();
        if (dataDTO != null) {
            offsetList.setOffset(dataDTO.getOffset());
            offsetList.setTotal(dataDTO.getTotal());
            for (HeroDTO heroDTO : dataDTO.getResults()) {
                if (heroDTO != null)
                    heroList.add(toHero(heroDTO));
            }
        }
        return offsetList;
    }
}
