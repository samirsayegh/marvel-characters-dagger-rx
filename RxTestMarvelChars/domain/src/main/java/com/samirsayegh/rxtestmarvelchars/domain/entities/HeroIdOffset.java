package com.samirsayegh.rxtestmarvelchars.domain.entities;

/**
 * Created by yormirsamir.sayegh on 25/05/2017.
 */

public class HeroIdOffset {

    private final int heroId;
    private final int offset;
    private final boolean isComic;

    private HeroIdOffset(HeroIdOffsetBuilder builder) {
        this.heroId = builder.heroId;
        this.offset = builder.offset;
        this.isComic = builder.isComic;
    }

    public int getHeroId() {
        return heroId;
    }

    public int getOffset() {
        return offset;
    }

    public boolean isComic() {
        return isComic;
    }

    public static class HeroIdOffsetBuilder {
        private int heroId;
        private int offset;
        private boolean isComic;

        public HeroIdOffsetBuilder heroId(int heroId) {
            this.heroId = heroId;
            return this;
        }

        public HeroIdOffsetBuilder offset(int offset) {
            this.offset = offset;
            return this;
        }

        public HeroIdOffsetBuilder isComic(boolean isComic) {
            this.isComic = isComic;
            return this;
        }

        public HeroIdOffset build() {
            return new HeroIdOffset(this);
        }
    }
}


