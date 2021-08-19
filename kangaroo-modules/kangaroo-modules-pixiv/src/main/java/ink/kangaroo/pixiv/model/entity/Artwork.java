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
@Table(name = "artworks")
@ToString
@EqualsAndHashCode(callSuper = true)
public class Artwork extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "ink.kangaroo.pixiv.model.support.CustomIdGenerator")
    private Integer id;

    /**
     * Attachment name.
     */
    @Column(name = "data_id", nullable = false)
    private String dataId;
    /**
     * Attachment name.
     */
    @Column(name = "name")
    private String name;

    /**
     * Attachment access path.
     */
    @Column(name = "path", length = 1023)
    private String path;

    /**
     * File key: oss file key or local file key (Just for deleting)
     */
    @Column(name = "image_count", length = 2047)
    private Integer imageCount;


    @Override
    public void prePersist() {
        super.prePersist();
    }
}
