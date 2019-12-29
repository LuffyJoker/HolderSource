package com.holderzone.holdersaasstoreitem.entity.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Mr.Q
 * @date 2019/12/29 23:32
 * desc：
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("hsi_attr")
public class AttrDO extends BasePushDO {

    private static final long serialVersionUID = 1L;

    private Long id;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    @TableLogic
    private Integer isDelete;

    @TableId(value = "guid", type = IdType.INPUT)
    private String guid;
    /**
     * 品牌GUID
     */
    private String brandGuid;

    private String attrGroupGuid;

    /**
     * 加价
     */
    private BigDecimal price;

    /**
     * 是否是默认选中项（0：否，1：是）
     */
    private Integer isDefault;

    /**
     * 属性值来源
     * 0/门店自建，1/品牌自建，2被推送
     */
    private Integer attrFrom;
}

