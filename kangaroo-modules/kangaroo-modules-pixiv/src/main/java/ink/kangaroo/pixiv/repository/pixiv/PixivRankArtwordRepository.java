package ink.kangaroo.pixiv.repository.pixiv;

import ink.kangaroo.pixiv.model.entity.PixivRank;
import ink.kangaroo.pixiv.model.entity.PixivRankArtword;
import ink.kangaroo.pixiv.model.entity.PixivRankArtwordKey;
import ink.kangaroo.common.jpa.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PixivRankArtwordRepository extends BaseRepository<PixivRankArtword, PixivRankArtwordKey> {
    List<PixivRankArtword> findByPixivRankArtwordKey_RankIn(List<PixivRank> pixivRank);
    List<PixivRankArtword> findByPixivRankArtwordKey_RankInAndPixivRankArtwordKey_Artword_SanityLevelLessThan(List<PixivRank> pixivRank,Integer stanityLevel);
    Page<PixivRankArtword> findByPixivRankArtwordKey_Rank(PixivRank pixivRank, Pageable pageable);
    Page<PixivRankArtword> findByPixivRankArtwordKey_RankAndPixivRankArtwordKey_Artword_PageCountLessThan(PixivRank pixivRank, Integer pageCount, Pageable pageable);
    Page<PixivRankArtword> findByPixivRankArtwordKey_RankAndPixivRankArtwordKey_Artword_PageCountLessThanAndPixivRankArtwordKey_Artword_SanityLevelLessThan(PixivRank pixivRank, Integer pageCount,Integer sanityLevel, Pageable pageable);
    Page<PixivRankArtword> findByPixivRankArtwordKey_RankAndPixivRankArtwordKey_Artword_PageCountLessThanAndPixivRankArtwordKey_Artword_IllustIdNotIn(PixivRank pixivRank, Integer pageCount, List<String> artworkIds, Pageable pageable);
    Page<PixivRankArtword> findByPixivRankArtwordKey_RankAndPixivRankArtwordKey_Artword_PageCountLessThanAndPixivRankArtwordKey_Artword_IllustIdNotInAndPixivRankArtwordKey_Artword_SanityLevelLessThan(PixivRank pixivRank, Integer pageCount, List<String> artworkIds,Integer sanityLevel, Pageable pageable);
}
