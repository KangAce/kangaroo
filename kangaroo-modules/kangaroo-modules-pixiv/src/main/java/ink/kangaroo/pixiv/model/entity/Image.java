package ink.kangaroo.pixiv.model.entity;

import ink.kangaroo.common.core.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Attachment entity
 *
 * @author kang
 * @date 2019-03-12
 */
@Data
@Entity
@Table(name = "image")
@ToString
@EqualsAndHashCode(callSuper = true)
public class Image extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "fun.imore.tribe.model.entity.support.CustomIdGenerator")
    private Integer id;

    /**
     * Attachment name.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Attachment access path.
     */
    @Column(name = "path", length = 1023, nullable = false)
    private String path;

    /**
     * File key: oss file key or local file key (Just for deleting)
     */
    @Column(name = "image_count", length = 2047)
    private String imageCount;


    @Override
    public void prePersist() {
        super.prePersist();
    }
}
