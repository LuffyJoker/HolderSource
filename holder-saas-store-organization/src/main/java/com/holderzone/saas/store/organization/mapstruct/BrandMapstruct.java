package com.holderzone.saas.store.organization.mapstruct;

import com.holderzone.holder.saas.store.dto.dto.organization.BrandDTO;
import com.holderzone.saas.store.organization.domain.BrandDO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Mr.Q
 * @date 2019/12/24 22:54
 * desc：
 */
@Component
@Mapper(componentModel = "spring")
public interface BrandMapstruct {
    BrandDO brandDTO2DO(BrandDTO brandDO);

    BrandDTO brandDO2DTO(BrandDO brandDO);

    List<BrandDTO> brandDOList2DTOList(List<BrandDO> brandDOList);
}
