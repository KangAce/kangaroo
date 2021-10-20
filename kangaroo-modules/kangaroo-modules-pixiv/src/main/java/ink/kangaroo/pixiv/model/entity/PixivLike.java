package ink.kangaroo.pixiv.model.entity;

import ink.kangaroo.common.core.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "pixiv_like")
@ToString
@EqualsAndHashCode(callSuper = true)
public class PixivLike extends BaseEntity {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "ink.kangaroo.pixiv.model.support.CustomIdGenerator")
    private Long id;
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "artwork_id", nullable = false)
    private Long artworkId;

}
