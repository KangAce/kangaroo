package ink.kangaroo.pixiv.model.entity;

import ink.kangaroo.common.core.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author OysterQAQ
 * @version 1.0
 * @date 2019/08/11 1:04
 * @description Tag
 */
@Data
@Entity
@Table(name = "pixiv_tag")
@ToString(exclude = "pixivArtwords")
@EqualsAndHashCode(callSuper = true,exclude = "pixivArtwords")
@NoArgsConstructor
public class PixivTag extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "ink.kangaroo.pixiv.model.support.CustomIdGenerator")
    private Long id;
    protected String name;
    protected String translatedName;
    @ManyToMany(mappedBy = "tags", cascade = {CascadeType.MERGE})
    private List<PixivArtword> pixivArtwords;

    public PixivTag(String name, String translatedName) {
        this.name = name;
        this.translatedName = translatedName;
    }

    public PixivTag(String e) {
        this.name = e;
    }

    public static List<PixivTag> creatByList(List<String> tags) {
        return tags.stream().map(PixivTag::new).collect(Collectors.toList());
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public String getTranslatedName() {
        return translatedName == null ? "" : translatedName;
    }
}