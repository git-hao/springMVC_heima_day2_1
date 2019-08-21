package com.hao.domain;

import java.io.Serializable;

/**
 * @Describe com.hao.domain
 * @Auther wenhao chen
 * @CreateDate 2019/8/20
 * @Version 1.0
 */
public class User implements Serializable {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
