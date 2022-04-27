package ink.kangaroo.system.api.domain;

import ink.kangaroo.common.core.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/5 14:27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysFile extends BaseEntity {
    /**
     * 文件（夹）主键
     */
    private String id;
    /**
     * 文件（夹）名称
     */
    private String name;
    /**
     * 文件后缀（eg. png 全部转小写保存）
     */
    private String suffix;

    /**
     * 文件（夹）地址
     */
    private String url;

    /**
     * 文件夹id (如果是顶节点，那么文件夹id是0)
     */
    private Long fileFolderId;

    /**
     * 文件夹路径（父文件夹路径，如果是顶节点，那么父文件夹路径是 “/”）
     */
    private Long fileFolderUrl;

    /**
     * 是否为文件夹
     */
    private Boolean isFolder;

    /**
     * 文件大小
     */
    private Long fileSize;

    private String mediaType;

    /*宽*/
    private Long width;
    /*高度*/
    private Long height;
    /*类型*/
    private Integer type;
    /**
     * 2021年9月29日更新参数
     * @return
     */
    /**
     * 时长
     */
    private Long duration;
    /**
     * 视频码率
     */
    private Integer bitRate;
    /**
     * 视频帧率
     */
    private Float frameRate;
    /**
     * 音频码率
     */
    private Integer audioBitRate;
    /**
     * 宽高比 9/16
     */
    private Double aspectRatio;
    /**
     * md5
     */
    private String md5;
}
