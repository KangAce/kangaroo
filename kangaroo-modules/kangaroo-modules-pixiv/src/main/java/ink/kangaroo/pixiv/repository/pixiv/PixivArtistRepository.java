package ink.kangaroo.pixiv.repository.pixiv;


import ink.kangaroo.pixiv.model.entity.PixivArtist;
import ink.kangaroo.pixiv.repository.base.BaseRepository;

import java.util.List;

public interface PixivArtistRepository extends BaseRepository<PixivArtist, Long> {
    List<PixivArtist> findAllByIdIn(List<Long> id);

    List<PixivArtist> findAllByAccountIn(List<String> accounts);

    PixivArtist findByAccount(String accounts);

}
