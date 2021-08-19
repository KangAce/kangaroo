package ink.kangaroo.pixiv.model.entity;

import ink.kangaroo.common.core.enums.Sex;
import ink.kangaroo.common.core.web.domain.BaseEntity;
import ink.kangaroo.pixiv.model.result.PixivRankContentResult;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.*;
import java.util.Date;
import java.util.List;

/**
 * @author OysterQAQ
 * @version 1.0
 * @date 2019/07/31 9:29
 * @description Artist
 */
@Data
@Entity
@Table(name = "pixiv_artists")
@ToString(exclude = "pixivArtword")
@EqualsAndHashCode(callSuper = true,exclude = "pixivArtword")
public class PixivArtist extends BaseEntity implements Cloneable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "ink.kangaroo.pixiv.model.support.CustomIdGenerator")
    protected Long id;
    @Column(name = "name")
    protected String name;
    @Column(name = "account",unique = true)
    protected String account;
    @Column(name = "avatar")
    protected String avatar;
    /**
     * 头像的附件id
     */
    @Column(name = "attachment_id")
    protected Long attachmentId;
    @Column(name = "comment")
    protected String comment;
    @Column(name = "gender")
    @ColumnDefault("-1")
    protected Sex gender;
    @OneToMany(mappedBy = "artist")
    private List<PixivArtword> pixivArtword;
    /**
     * 生日
     */
    @Column(name = "birthday")
    private Date birthDay;
    @Column(name = "region")
    protected String region;
    @Column(name = "web_page")
    protected String webPage;
    @Column(name = "twitter_account")
    protected String twitterAccount;
    @Column(name = "twitter_url")
    protected String twitterUrl;
    @Column(name = "total_follow_users")
    protected String totalFollowUsers;
    @Column(name = "total_illust_bookmarks_public")
    protected String totalIllustBookmarksPublic;


    public PixivArtist() {

    }

    public PixivArtist(PixivRankContentResult e) {
        this.account = e.getUserId();
        this.avatar = e.getProfileImg();
        this.name = e.getUserName();
    }
    @Override
    public PixivArtist clone(){
        PixivArtist pixivArtist = null;
                try{
                        pixivArtist = (PixivArtist)super.clone();   //浅复制
                     }catch(CloneNotSupportedException e) {
                       e.printStackTrace();
                    }
        try {
            pixivArtword =deepCopy(pixivArtword);   //深度复制
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return pixivArtist;
    }
    public static <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        @SuppressWarnings("unchecked")
        List<T> dest = (List<T>) in.readObject();
        return dest;
    }
}
