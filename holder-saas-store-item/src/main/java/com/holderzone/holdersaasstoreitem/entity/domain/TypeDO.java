package com.holderzone.holdersaasstoreitem.entity.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author Mr.Q
 * @date 2019/12/29 23:28
 * desc：
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("hsi_type")
@NoArgsConstructor
@AllArgsConstructor
public class TypeDO extends BasePushDO {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;

    /**
     * 是否删除 0：false,1:true
     */
    @TableLogic
    private Integer isDelete;

    @TableId(value = "guid", type = IdType.INPUT)
    private String guid;

    /**
     * 品牌GUID
     */
    private String brandGuid;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 是否启用（0：否，1：是）
     */
    private Integer isEnable;

    /**
     * 分类描述
     */
    @TableField(strategy = FieldStrategy.NOT_NULL)
    private String description;

    /**
     * 图标
     */
    @TableField(strategy = FieldStrategy.NOT_NULL)
    private String iconUrl;

    /**
     * 关联该分类的商品数
     */
    private Integer itemNum;

    /**
     * 是否因重复而改名：0：否，1：是
     */
    private Integer nameChange;

    /**
     * 分类来源（0：门店自己创建的分类，1：品牌自己创建的分类,2:被推送过来的分类）
     */
    private Integer typeFrom;

    // 仅新建推送的分类实体时才能用
    public TypeDO(TypeDO typeDO) {
        this.storeGuid = typeDO.getStoreGuid();
        this.name = typeDO.getName();
        this.sort = typeDO.getSort();
        this.isEnable = typeDO.getIsEnable();
        this.description = typeDO.getDescription();
        this.iconUrl = typeDO.getIconUrl();
        this.itemNum = 0;
        this.parentGuid = typeDO.getGuid();
        this.nameChange = typeDO.getNameChange();
        this.typeFrom = 2;
    }

    public static TypeDO initGroupMealDO(){
        TypeDO typeDO = new TypeDO();
        typeDO.setIsEnable(0);
        typeDO.setSort(999);
        typeDO.setItemNum(0);
        typeDO.setTypeFrom(0);
        typeDO.setName("宴会套餐");
        return typeDO;
    }
}

