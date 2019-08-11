package com.woowacourse.tecobrary.domain.model.core;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
public abstract class BasicEntity {

    @Column(name = "is_active")
    private boolean isActive = true;

    public void softDelete() {
        this.isActive = false;
    }
}
