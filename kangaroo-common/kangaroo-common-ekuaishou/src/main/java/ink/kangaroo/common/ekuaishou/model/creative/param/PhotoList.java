package ink.kangaroo.common.ekuaishou.model.creative.param;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 除非接口变更否则请勿修改
 *
 * @author kbw
 * @version 1.0
 * @date 2021/9/13 18:08
 */
@Data
public class PhotoList {

    /**
     * 新版必填	视频 ID
     * <p>
     * photo_id
     */
    @NotNull
    @JSONField(name = "photo_id")
    private Long photo_id;
    /**
     * 新版必填	封面图片 token	通过上传图片接口获得，不传值则直接使用视频的首帧作为封面图片，自定义封面的图片宽高要与视频宽高一致，使用智能抽帧时不需要传
     * <p>
     * image_token
     */
    @NotNull
    @JSONField(name = "image_token")
    private String image_token;
    /**
     * 新版必填	素材类型	1：竖版视频 2：横版视频
     * <p>
     * creative_material_type
     */
    @NotNull
    @JSONField(name = "creative_material_type")
    private Integer creative_material_type;

}