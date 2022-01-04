package ink.kangaroo.pixiv.service.impl;

import ink.kangaroo.common.jpa.repository.base.BaseRepository;
import ink.kangaroo.common.jpa.service.AbstractCrudService;
import ink.kangaroo.pixiv.model.entity.PixivArtist;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PixivArtistService extends AbstractCrudService<PixivArtist, Long> {

    protected PixivArtistService(@Qualifier("pixivArtistRepository") BaseRepository<PixivArtist, Long> repository) {
        super(repository);
    }

//    public void putArtistInfo(List<String> artistIds) {
//        for (String artistId : artistIds) {
//            HashMap artistInfo1By = (HashMap) getArtistInfo1By(artistId);
//            System.out.println("");
//
//        }
//    }
//
//    public Object getArtistInfo0By(String artistId) {
//
////        PixivArtistResult
//        String url = "https://www.pixiv.net/ajax/user/" + artistId + "?full=0&lang=zh";
//        url = "https://www.pixiv.net/ajax/user/" + artistId + "/profile/all?lang=zh";
//        HashMap jsonSync = (HashMap) requestUtil.getJsonSync(url, HashMap.class);
//        System.out.println(jsonSync);
//        return jsonSync;
//    }
//
//    public Object getArtistInfo1By(String artistId) {
////        PixivArtistResult
//        String url = "https://www.pixiv.net/ajax/user/" + artistId + "?full=1&lang=zh";
//        HashMap jsonSync = (HashMap) requestUtil.getJsonSync(url, HashMap.class);
//        System.out.println(jsonSync);
//        return jsonSync;
//    }
//
//    public Object getArtistProfileBy(String artistId) {
////        PixivArtistResult
//        String url = "https://www.pixiv.net/ajax/user/" + artistId + "?full=0&lang=zh";
//        url = "https://www.pixiv.net/ajax/user/2993192/profile/all?lang=zh";
//        HashMap jsonSync = (HashMap) requestUtil.getJsonSync(url, HashMap.class);
//        System.out.println(jsonSync);
//        return jsonSync;
//    }

}
