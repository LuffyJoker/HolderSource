package com.holderzone.saas.store.organization.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Mr.Q
 * @date 2019/12/24 22:26
 * desc：
 */
@Data
@Accessors(chain = true)
@TableName(value = "hso_r_store_brand")
public class StoreBrandDO implements Serializable {

    private static final long serialVersionUID = 8508038392422519052L;

    /**
     * 主键id
     */
    @TableId
    private Long id;

    /**
     * guid
     */
    private String guid;

    /**
     * 门店guid
     */
    private String storeGuid;

    /**
     * 品牌guid
     */
    private String brandGuid;

    /**
     * 创建人guid
     */
    private String createUserGuid;

    /**
     * 更新人guid
     */
    private String modifiedUserGuid;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 更新时间
     */
    private LocalDateTime gmtModified;
}

