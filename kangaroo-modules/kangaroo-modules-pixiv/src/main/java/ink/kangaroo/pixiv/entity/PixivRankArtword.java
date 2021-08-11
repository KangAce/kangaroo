package ink.kangaroo.pixiv.entity;

import ink.kangaroo.common.core.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * @author Kangaroo
 * @version 1.0
 * @date 2019/08/17 17:41
 * @description Rank
 */
public class PixivRankArtword extends BaseEntity {
    /**
     * 排行榜ID
     */
    private Long rankId;
    /**
     * 插画ID
     */

    /**
     * 在本次排行榜中排名
     */
    private Integer rankNumber;
    /**
     * 上次排行榜名次
     */
    private Integer lastRanking;

    private Long artwordId;

    public Long getRankId() {
        return rankId;
    }

    public void setRankId(Long rankId) {
        this.rankId = rankId;
    }

    public Long getArtwordId() {
        return artwordId;
    }

    public void setArtwordId(Long artwordId) {
        this.artwordId = artwordId;
    }

    public Integer getRankNumber() {
        return rankNumber;
    }

    public void setRankNumber(Integer rankNumber) {
        this.rankNumber = rankNumber;
    }

    public Integer getLastRanking() {
        return lastRanking;
    }

    public void setLastRanking(Integer lastRanking) {
        this.lastRanking = lastRanking;
    }
}
