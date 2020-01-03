package com.holderzone.holdersaasstorekds.entity.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BaseDO implements Serializable {

    private static final long serialVersionUID = 8424514095125620853L;

    /**
     * 主键ID
     */
    @TableId
    private Long id;

    /**
     * 唯一GUID
     */
    private String guid;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;
}
