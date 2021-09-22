package ink.kangaroo.pixiv.repository.pixiv;

import ink.kangaroo.pixiv.model.entity.PixivRank;
import ink.kangaroo.pixiv.repository.base.BaseRepository;

import java.util.List;

public interface PixivRankRepository extends BaseRepository<PixivRank, Long> {
    List<PixivRank> findAllByDate(String date);

    PixivRank findPixivRankByModeAndDate(String mode, String date);

    PixivRank findPixivRankByModeAndDateAndContent(String mode, String date, String content);
}
