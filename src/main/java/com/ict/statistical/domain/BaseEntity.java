package com.ict.statistical.domain;

import lombok.Data;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/08/16/15:18
 */
@Data
public class BaseEntity {
    private String name;

    private Double value;

    public BaseEntity() {
    }

    public BaseEntity(final String name, final Double value) {
        this.name = name;
        this.value = value;
    }
}
