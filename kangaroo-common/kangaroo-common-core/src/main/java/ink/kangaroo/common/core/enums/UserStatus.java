package ink.kangaroo.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {

	OK("0", "正常"), DISABLE("1", "禁用"), DELETED("2", "已删除"),;

	private String code;

	private String info;

}
