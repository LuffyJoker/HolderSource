package com.holderzone.saas.store.organization.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.holderzone.holdersaasstoredto.dto.organization.BrandDTO;
import com.holderzone.holdersaasstoredto.dto.organization.QueryBrandDTO;
import com.holderzone.saas.store.organization.domain.BrandDO;

import java.util.List;

public interface BrandService extends IService<BrandDO> {

    BrandDTO createBrand(BrandDTO brandDTO);

    boolean updateBrand(BrandDTO brandDTO);

    boolean deleteBrand(String brandGuid);

    BrandDTO queryBrandByGuid(String brandGuid);

    List<BrandDTO> queryBrandByIdList(List<String> guidList);

    List<BrandDTO> queryAllList(QueryBrandDTO queryBrandDTO);

    boolean queryExistStoreAccount(String brandGuid);

    List<String> queryStoreGuidListByBrandGuid(String brandGuid);

    BrandDTO createDefaultBrand();

    String queryDefaultBrand();
}

