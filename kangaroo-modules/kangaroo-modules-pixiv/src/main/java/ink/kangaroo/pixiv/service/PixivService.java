//package ink.kangaroo.pixiv.service;
//
//import ink.kangaroo.pixiv.model.vo.PixivArtwordDetailVo;
//import ink.kangaroo.pixiv.model.vo.PixivArtwordVo;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//
//import java.util.Set;
//
//public interface PixivService {
//
//    Page<PixivArtwordVo> list(String date, String mode , Integer flag, Pageable pageable);
//
//    PixivArtwordDetailVo detail(Long userId, Long id);
//    boolean like(Long userId,Long id);
//
//    Page<PixivArtwordVo> listByLikeUserId(Long userId,Pageable pageable);
//}
