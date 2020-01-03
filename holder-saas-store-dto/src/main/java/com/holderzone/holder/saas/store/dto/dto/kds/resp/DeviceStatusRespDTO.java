package com.holderzone.holder.saas.store.dto.dto.kds.resp;

import com.holderzone.holder.saas.store.dto.dto.kds.DeviceDstConfDTO;
import com.holderzone.holder.saas.store.dto.dto.kds.DevicePrdConfDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class DeviceStatusRespDTO implements Serializable {

    private static final long serialVersionUID = -1158994112223699540L;

    @ApiModelProperty(value = "门店Guid")
    private String storeGuid;

    @ApiModelProperty(value = "设备ID")
    private String deviceId;

    @ApiModelProperty(value = "设备名称")
    private String name;

    @ApiModelProperty(value = "堂口模式不得为空")
    private Integer pointMode;

    @ApiModelProperty(value = "显示模式" +
            "制作点位显示模式：0=堂口模式，1=菜品汇总模式，2=订单模式，3=订单1*6" +
            "出堂点位显示模式：0=单菜品模式，1=菜品汇总模式，2=订单模式，3=订单1*6")
    private Integer displayMode;

    @ApiModelProperty(value = "是否已完成初始化")
    private Boolean isInitialized;

    @ApiModelProperty(value = "制作点高级配置")
    private DevicePrdConfDTO devicePrdConfDTO;

    @ApiModelProperty(value = "出堂口高级配置")
    private DeviceDstConfDTO deviceDstConfDTO;
}

