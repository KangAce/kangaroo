package ink.kangaroo.pixiv.model.entity;

import ink.kangaroo.common.core.web.domain.BaseEntity;
import ink.kangaroo.common.jpa.model.support.StringListConverter;
import ink.kangaroo.common.pixiv.model.rank.result.PixivRankContentResult;
import ink.kangaroo.pixiv.model.entity.artwords.ImageUrl;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * @author Kangaroo
 * @version 1.0
 * @date 2019/07/28 9:19
 * @description illustration
 */
@Data
@Entity
@Table(name = "pixiv_artworks")
@ToString(exclude = "pixivRanks")
@EqualsAndHashCode(callSuper = true, exclude = "pixivRanks")
public class PixivArtword extends BaseEntity implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "ink.kangaroo.pixiv.model.support.CustomIdGenerator")
    protected Long id;
    @OneToMany(mappedBy = "pixivRankArtwordKey.artword", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<PixivRankArtword> pixivRanks;
    @ManyToOne(targetEntity = PixivArtist.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "artist_id")
    protected PixivArtist artist;
    @Column(name = "illust_id", length = 20)
    protected String illustId;
    @Column(name = "title")
    protected String title;
    @Column(name = "type")
    protected Integer type;
    @Column(name = "caption")
    protected String caption;
    //    @JsonSetter("artist")
//    @JsonAlias({"artist", "artistPreView"})
//    protected ArtistPreView artistPreView;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "pixiv_tag_artword",
            joinColumns = @JoinColumn(name = "artword_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    protected List<PixivTag> tags;

//    @Column(name = "image_urls")
//    @Convert(converter = StringListConverter.class)
//    protected List<ImageUrl> imageUrls;

    @Column(name = "image_urls", length = 2000)
    @Convert(converter = StringListConverter.class)
    protected List<ImageUrl> imageUrls;

    @Column(name = "tools")
    @Convert(converter = StringListConverter.class)
    protected List<String> tools;
    //    @JsonSetter("create_date")
//    @JsonAlias({"create_date", "createDate"})
//    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
//    protected Date createDate;
    @Column(name = "page_count")
    protected Integer pageCount;
    @Column(name = "width")
    protected Integer width;
    @Column(name = "height")
    protected Integer height;

    @Column(name = "thumbnail")
    protected String thumbnail;
    @Column(name = "thumbnail_local")
    protected Long thumbnailLocal;

    @Column(name = "sanity_level", nullable = false)
    @ColumnDefault("-1")
    protected Integer sanityLevel;
    @Column(name = "limit_num")
    protected Integer limitNum;

    @Column(name = "x_restrict")
    protected Integer xRestrict;

    @Column(name = "total_view")
    protected Integer totalView;

    @Column(name = "total_bookmarks")
    protected Integer totalBookmarks;

    public PixivArtword() {

    }

    public PixivArtword(PixivRankContentResult e) {

//        this.tags = PixivTag.creatByList(e.getTags());
//    TODO    ImageUrl.createByList(e.get)
        illustId = e.getIllustId();
        title = e.getTitle();
        width = e.getWidth();
        height = e.getHeight();
        totalView = e.getViewCount();
        type = e.getIllustType();
        sanityLevel = -1;
        thumbnail = e.getUrl();
        pageCount = e.getIllustPageCount();
    }

//    public void setArtistPreView(Long id, String name, String account, String avatar) {
//        this.artistPreView = new ArtistPreView(id, name, account, avatar);
//    }

}


