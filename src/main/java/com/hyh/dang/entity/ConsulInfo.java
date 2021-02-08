package com.hyh.dang.entity;

import java.io.Serializable;
import java.util.Date;

public class ConsulInfo extends ConsulInfoKey implements Serializable {
    private Byte alive;

    private Date createddt;

    private Date updateddt;

    private static final long serialVersionUID = 1L;

    public Byte getAlive() {
        return alive;
    }

    public void setAlive(Byte alive) {
        this.alive = alive;
    }

    public Date getCreateddt() {
        return createddt;
    }

    public void setCreateddt(Date createddt) {
        this.createddt = createddt;
    }

    public Date getUpdateddt() {
        return updateddt;
    }

    public void setUpdateddt(Date updateddt) {
        this.updateddt = updateddt;
    }
}