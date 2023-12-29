package org.tku.web.entity;

import lombok.Getter;

@Getter
public class SignUpForm {
    private String account;
    private String newPassword;
    private String checkPassword;

    // 省略构造函数、getter 和 setter 方法...

    public void setAccount(String account) {
        this.account = account;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setCheckPassword(String checkPassword) {
        this.checkPassword = checkPassword;
    }
}
