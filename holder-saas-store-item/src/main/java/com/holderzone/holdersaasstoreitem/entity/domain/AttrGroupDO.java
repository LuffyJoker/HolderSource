package com.holderzone.holdersaasstoreitem.entity.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author Mr.Q
 * @date 2019/12/29 23:32
 * desc：
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("hsi_attr_group")
public class AttrGroupDO extends BasePushDO {

    private static final long serialVersionUID = 1L;

    private Long id;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    @TableLogic
    private Integer isDelete;

    @TableId(value = "guid", type = IdType.INPUT)
    private String guid;

    /**
     * 所属品牌GUID
     */
    private String brandGuid;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 描述
     */
    @TableField(strategy = FieldStrategy.NOT_NULL)
    private String description;

    /**
     * 是否启用（0：否，1：是）
     */
    private Integer isEnable;

    /**
     * 图标路径
     */
    @TableField(strategy = FieldStrategy.NOT_NULL)
    private String iconUrl;

    /**
     * 是否必选:0 否 1 是
     */
    private Integer isRequired;

    /**
     * 是否支持多选:0 否 1 是
     */
    private Integer isMultiChoice;

    /**
     * 是否有默认选项：0：否1：是
     */
    private Integer withDefault;

    /**
     * 是否因重复而改名：0：否，1：是
     */
    private Integer nameChange;

    /**
     * 属性组来源（0：门店自己创建的属性组，1：品牌自己创建的属性组,2:被推送过来的属性组）
     */
    private Integer attrGroupFrom;

}

