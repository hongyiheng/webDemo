package com.hyh.dang.entity;

import java.io.Serializable;

public class Jijin implements Serializable {
    private String num;

    private String name;

    private String createdt;

    private String raise;

    private static final long serialVersionUID = 1L;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCreatedt() {
        return createdt;
    }

    public void setCreatedt(String createdt) {
        this.createdt = createdt == null ? null : createdt.trim();
    }

    public String getRaise() {
        return raise;
    }

    public void setRaise(String raise) {
        this.raise = raise == null ? null : raise.trim();
    }
}