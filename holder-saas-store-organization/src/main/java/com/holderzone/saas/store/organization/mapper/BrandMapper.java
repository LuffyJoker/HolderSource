package com.holderzone.saas.store.organization.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.holderzone.saas.store.organization.domain.BrandDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface BrandMapper extends BaseMapper<BrandDO> {

}
