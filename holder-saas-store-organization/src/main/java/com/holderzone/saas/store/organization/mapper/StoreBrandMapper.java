package com.holderzone.saas.store.organization.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.holderzone.saas.store.organization.domain.StoreBrandDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author Mr.Q
 * @date 2019/12/24 22:25
 * desc：
 */
@Mapper
@Component
public interface StoreBrandMapper extends BaseMapper<StoreBrandDO> {

}
