package ink.kangaroo.pixiv.repository.pixiv;

import ink.kangaroo.common.jpa.repository.base.BaseRepository;
import ink.kangaroo.pixiv.model.entity.PixivArtword;
import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.List;

public interface PixivArtwordRepository extends BaseRepository<PixivArtword, Long> {
    List<PixivArtword> findByIllustIdIn(Collection<String> illustids);

    PixivArtword findByIllustId(String illustids);

    /**
     * Finds all domain by id list.
     *
     * @param ids id list of domain must not be null
     * @return a list of domains
     */
    @NonNull
    List<PixivArtword> findAllByIdIn(@NonNull Collection<Long> ids);
}
