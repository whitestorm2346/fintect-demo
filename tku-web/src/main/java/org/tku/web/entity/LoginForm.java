package org.tku.web.entity;

import lombok.Getter;

@Getter
public class LoginForm {
    private String account;
    private String password;

    // 省略构造函数、getter 和 setter 方法...

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
