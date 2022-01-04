package ink.kangaroo.pixiv.repository.pixiv;

import ink.kangaroo.pixiv.model.entity.Artwork;
import ink.kangaroo.common.jpa.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Artwork repository
 *
 * @author johnniang
 * @author ryanwang
 * @date 2019-04-03
 */
public interface ImageRepository extends BaseRepository<Artwork, Long>, JpaSpecificationExecutor<Artwork> {

}
