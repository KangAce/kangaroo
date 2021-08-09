package ink.kangaroo.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {
	/**
	 * 正常状态
	 */
	OK("0", "正常"), DISABLE("1", "禁用"), DELETED("2", "已删除"),;

	private final String code;

	private final String info;

}
