package ink.kangaroo.pixiv.model.entity.artwords;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author OysterQAQ
 * @version 1.0
 * @date 2019/09/19 21:13
 * @description ArtistPreView
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ArtistPreView {
    protected Long id;
    protected String name;
    protected String account;
    protected String avatar;
}
