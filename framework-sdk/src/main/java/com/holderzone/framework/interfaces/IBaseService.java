package com.holderzone.framework.interfaces;

import java.io.Serializable;

/**
 * @author Mr.Q
 * @date 2019/12/24 21:56
 * desc：
 */
public interface IBaseService<T extends Serializable> {
    T findByGuid(String var1);
}
