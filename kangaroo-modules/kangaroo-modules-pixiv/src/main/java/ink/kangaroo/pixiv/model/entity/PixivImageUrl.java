package ink.kangaroo.pixiv.model.entity;

import ink.kangaroo.common.core.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "pixiv_image")
@ToString
@EqualsAndHashCode(callSuper = true)
public class PixivImageUrl extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "fun.imore.tribe.model.entity.support.CustomIdGenerator")
    private Long id;
    @Column(name = "artwork_id")
    private Long artworkId;
    //每次都要查看对应附件是否获取，腾讯图床10天之后删除附件，设置定时任务9天删除附件表中内容，获取时显示空则重新上传。上传只需要
    @Column(name = "attachment_id")
    private Long attachmentId;
    //TODO 暂时的打算每次下载请求的时候 获取一次流，所以现在这个字段备用
    @Column(name = "original_id")
    private Long originalId;
    /**
     * 排序
     */
    @Column(name = "order_num")
    private Integer orderNum;
    @Column(name = "thumb_mini")
    private String thumbMini;
    @Column(name = "small")
    private String small;
    @Column(name = "regular")
    private String regular;
    @Column(name = "original")
    private String original;
}
