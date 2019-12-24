package com.holderzone.saas.store.organization.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@TableName(value = "hso_brand")
public class BrandDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id，自增
     */
    @TableId
    private Long id;

    /**
     * 品牌guid
     */
    private String guid;

    /**
     * 品牌名称
     */
    private String name;

    /**
     * 品牌介绍
     */
    private String description;

    /**
     * 品牌logo（oss下载地址）
     */
    private String logoUrl;

    /**
     * 是否启用（默认为1）
     */
    private Boolean isEnable;

    /**
     * 是否删除（默认为0）
     */
    @TableLogic
    private Boolean isDeleted;

    /**
     * 创建人guid
     */
    private String createUserGuid;

    /**
     * 修改人guid
     */
    private String modifiedUserGuid;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;
}

