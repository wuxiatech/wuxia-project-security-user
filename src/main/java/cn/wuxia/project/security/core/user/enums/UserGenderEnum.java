package cn.wuxia.project.security.core.user.enums;

/**
 * 性别
 */
public enum UserGenderEnum {

	// 未知
	UNKNOW("未知", "未知"),
	// 男性
	MAN("先生", "男"),
	// 女性
	WOMEN("女士", "女");

	private String displayName;

	private String displayName2;

	private UserGenderEnum(String displayName, String displayName2) {
		this.displayName = displayName;
		this.displayName2 = displayName2;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getDisplayName2() {
		return displayName2;
	}
}
