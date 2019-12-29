package com.holderzone.holdersaasstoreitem.entity.domain;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Mr.Q
 * @date 2019/12/29 23:30
 * desc：
 */
@Data
public class BasePushDO implements Serializable {
    /**
     * 门店GUID
     */
    protected String storeGuid;

    /**
     * 父实体GUID：如果是自己创建的内容，则此字段为空，如果是被推送过来的实体，则该字段为品牌库对应的实体GUID。
     */
    protected String parentGuid;

    /**
     * 名称
     */
    @TableField(strategy = FieldStrategy.NOT_NULL)
    protected String name;
}

