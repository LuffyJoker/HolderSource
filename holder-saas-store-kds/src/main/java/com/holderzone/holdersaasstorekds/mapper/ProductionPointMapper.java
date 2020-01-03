package com.holderzone.holdersaasstorekds.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.holderzone.holdersaasstorekds.entity.domain.ProductionPointDO;
import com.holderzone.holdersaasstorekds.entity.read.PointItemReadDO;

import java.util.List;

public interface ProductionPointMapper extends BaseMapper<ProductionPointDO> {

    List<PointItemReadDO> queryPrdPointWithItemCount(ProductionPointDO productionPointDO);

}
