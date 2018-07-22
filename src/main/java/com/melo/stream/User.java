package com.melo.stream;

import java.io.Serializable;

/**
 *
 * @author 76009
 * @date 2018/7/22
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1675776039765550549L;

    private String name;

    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
