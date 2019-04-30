package cn.wuxia.project.security.core.user.enums;

import cn.wuxia.common.util.StringUtil;

/**
 * 类型
 */
public enum UserTypeEnum {

	NORMAL("普通用户"), ADMIN("内部人员");
	//

	private String displayName;

	private UserTypeEnum(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public static UserTypeEnum getByType(String type) {
		for (UserTypeEnum e : UserTypeEnum.values()) {
			if (StringUtil.equals(type, e.name())) {
				return e;
			}
		}
		return null;

	}

}
