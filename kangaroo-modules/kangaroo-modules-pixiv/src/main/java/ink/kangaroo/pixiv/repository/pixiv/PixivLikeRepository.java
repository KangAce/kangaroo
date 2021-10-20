package ink.kangaroo.pixiv.repository.pixiv;

import ink.kangaroo.pixiv.model.entity.PixivLike;
import ink.kangaroo.pixiv.repository.base.BaseRepository;

import java.util.List;

/**
 *
 */
public interface PixivLikeRepository extends BaseRepository<PixivLike, Long> {
    List<PixivLike> findAllByUserId(Long id);

    PixivLike findAllByUserIdAndArtworkId(Long id, Long artworkId);
}
