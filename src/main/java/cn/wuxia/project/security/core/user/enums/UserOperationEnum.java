package cn.wuxia.project.security.core.user.enums;

public enum UserOperationEnum {
    //
    ZHUCE("医生注册"), DENGLU("登录系统"),
    //
    TUICHU("退出系统"), XIUGAIMIMA("修改密码"), ZILIAOXIUGAI("修改资料");

    private String displayValue;

    private UserOperationEnum(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }

}
