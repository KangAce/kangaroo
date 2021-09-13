package ink.kangaroo.pixiv;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Objects;

/**
 * 腾讯返回创建广告错误信息
 *
 * @author zjw
 * @since 2020-07-20
 */
@Getter
@Setter
@ToString
public class TencentErrorMsg implements Serializable {
    private static final long serialVersionUID = 7688887263512237321L;
    /**
     * 错误码
     */
    @Field("error_Code")
    private Long errorCode;
    /**
     * 错误信息
     */
    @Field("message")
    private String message;
    /**
     * 错误信息中文描述
     */
    @Field("message_cn")
    private String messageCn;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TencentErrorMsg that = (TencentErrorMsg) o;
        return errorCode.equals(that.errorCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorCode);
    }
}
