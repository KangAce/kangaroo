package ink.kangaroo.pixiv.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author OysterQAQ
 * @version 1.0
 * @date 2019/08/17 17:41
 * @description Rank
 */
@Embeddable
@Data

public class PixivRankArtwordKey implements Serializable {
    /**
     * Category id.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "rank_id")
    private PixivRank rank;

    /**
     * Post id.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "artword_id")
    private PixivArtword artword;

    public PixivRankArtwordKey() {

    }
}
