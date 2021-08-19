package ink.kangaroo.pixiv.model.entity;

import ink.kangaroo.common.core.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author OysterQAQ
 * @version 1.0
 * @date 2019/08/17 17:41
 * @description Rank
 */
@Data
@Entity
@Table(name = "pixiv_rank")
@ToString(callSuper = true,exclude = "pixivArtword")
@EqualsAndHashCode(callSuper = true,exclude = "pixivArtword")
public class PixivRank extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "ink.kangaroo.pixiv.model.support.CustomIdGenerator")
    protected Long id;
    @Column(name = "mode")
    private String mode;
    @Column(name = "date")
    private String date;
    @Column(name = "content")
            private String content;
    @OneToMany(mappedBy = "pixivRankArtwordKey.rank", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<PixivRankArtword> pixivArtword;

    public PixivRank(ArrayList<PixivRankArtword> pixivArtword, String mode, String date, String content) {
        this.content = content;
        this.mode = mode;
        this.date = date;
        this.pixivArtword = pixivArtword;
    }

    public PixivRank(String mode, String date, String content) {
        this.mode = mode;
        this.date = date;
        this.content = content;
    }

    public PixivRank() {

    }
}
