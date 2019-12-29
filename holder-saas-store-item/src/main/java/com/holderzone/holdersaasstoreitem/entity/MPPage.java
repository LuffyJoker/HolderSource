package com.holderzone.holdersaasstoreitem.entity;

import lombok.Data;

/**
 * @author Mr.Q
 * @date 2019/12/29 23:19
 * desc：
 */
@Data
public class MPPage<T> extends Page<T> {
    public MPPage() {
        super(1, 10);
    }

    public MPPage(long current, long size) {
        super(current, size);
    }

    public MPPage(long current, long size, long total) {
        super(current, size, total);
    }

}
