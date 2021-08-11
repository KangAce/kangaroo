package ink.kangaroo.pixiv.entity;

import ink.kangaroo.common.core.web.domain.BaseEntity;
import lombok.Data;

import javax.persistence.*;

/**
 * @author OysterQAQ
 * @version 1.0
 * @date 2019/08/17 17:41
 * @description Rank
 */
@Data
@Entity
@Table(name = "pixiv_rank", schema = "kangaroo-cloud", catalog = "")
public class PixivRank extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String mode;
    private String date;
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PixivRank() {

    }
}
