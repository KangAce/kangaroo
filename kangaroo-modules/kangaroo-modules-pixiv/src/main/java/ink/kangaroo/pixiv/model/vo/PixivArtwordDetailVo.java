package ink.kangaroo.pixiv.model.vo;

import ink.kangaroo.pixiv.model.entity.PixivArtword;
import lombok.Data;

import java.util.List;

@Data
public class PixivArtwordDetailVo extends PixivArtwordVo {
    private List<PixivTagVo> tags;
    private List<PixivImageUrlVo> imgList;
    private String illustId;
    private String date;
    private Integer view;
    private Integer mark;
    private Boolean liked;
    private String summary;
    private PixivArtistVo artist;
    public PixivArtwordDetailVo(PixivArtword entity){
        super(entity);
        this.view = entity.getTotalView();
        this.illustId = entity.getIllustId();
//        this.date = entity.getti();
        this.mark = entity.getTotalBookmarks();
    }

}
