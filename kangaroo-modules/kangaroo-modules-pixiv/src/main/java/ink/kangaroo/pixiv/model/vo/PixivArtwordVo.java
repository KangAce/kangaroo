package ink.kangaroo.pixiv.model.vo;

import ink.kangaroo.pixiv.model.entity.PixivArtword;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PixivArtwordVo {
    private Long id;
    private String artworkId;
    private Integer width;
    private Integer height;
    private String title;
    private String imgUrl;
    private String username;
    private String userId;
    private String avatar;

    public PixivArtwordVo(PixivArtword e){
        this.id = e.getId();
        this.title = e.getTitle();
//        this.imgUrl = e.getThumbnailLocal();
        this.username = e.getArtist().getName();
        this.artworkId = e.getIllustId();
        this.width = e.getWidth();
        this.height = e.getHeight();
        this.userId = e.getArtist().getAccount();
        this.avatar = e.getArtist().getAvatar();
    }

}
