package ink.kangaroo.pixiv.model.entity;

import ink.kangaroo.common.core.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * @author OysterQAQ
 * @version 1.0
 * @date 2019/08/17 17:41
 * @description Rank
 */
@Data
@Entity(name = "pixiv_rank_artword")
@ToString
@EqualsAndHashCode(callSuper = true)
public class PixivRankArtword extends BaseEntity {
    @EmbeddedId
    PixivRankArtwordKey pixivRankArtwordKey;
    @Column(name = "rank_number")
    private Integer rankNumber;
    @Column(name = "last_ranking")
    private Integer lastRanking;

    @Transient
    public PixivRank getPixivRank() {
        return pixivRankArtwordKey.getRank();
    }

    @Transient
    public PixivArtword getPixivArtword() {
        return pixivRankArtwordKey.getArtword();
    }
}
