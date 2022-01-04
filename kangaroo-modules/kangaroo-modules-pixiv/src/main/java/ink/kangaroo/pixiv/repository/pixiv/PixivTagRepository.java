package ink.kangaroo.pixiv.repository.pixiv;

import ink.kangaroo.pixiv.model.entity.PixivTag;
import ink.kangaroo.common.jpa.repository.base.BaseRepository;

import java.util.Collection;
import java.util.List;

public interface PixivTagRepository extends BaseRepository<PixivTag, Long> {
    PixivTag findByNameAndTranslatedName(String name, String translatedName);

    List<PixivTag> findByNameIn(Collection<String> name);

    PixivTag findByName(String name);
}
