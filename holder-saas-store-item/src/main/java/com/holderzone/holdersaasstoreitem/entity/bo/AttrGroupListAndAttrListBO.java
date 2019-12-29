package com.holderzone.holdersaasstoreitem.entity.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Mr.Q
 * @date 2019/12/29 23:20
 * desc：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttrGroupListAndAttrListBO implements Serializable {
    /**
     * 商品与属性组关联实体集合
     */
    private List<RItemAttrGroupDO> itemAttrGroupDOList;
    /**
     * 属性与商品和属性组关联实体的关联实体
     */
    private List<RAttrItemAttrGroupDO> attrItemAttrGroupDOList;

    /**
     * 所属的商品GUID
     */
    private String itemGuid;

    /**
     * 是否有属性组
     * @return
     */
    public boolean hasAttrGroup(){
        return !CollectionUtils.isEmpty(itemAttrGroupDOList);
    }
}

