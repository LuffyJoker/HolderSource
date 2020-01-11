package com.holderzone.holder.saas.store.dto.dto.kds.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceQueryReqDTO implements Serializable {

    private static final long serialVersionUID = -2773042054434843563L;

    @NotEmpty(message = "门店Guid不得为空")
    @ApiModelProperty(value = "门店Guid")
    private String storeGuid;

    @NotEmpty(message = "设备ID不得为空")
    @ApiModelProperty(value = "设备ID")
    private String deviceId;

    public DeviceQueryReqDTO(String deviceId) {
        this.deviceId = deviceId;
    }
}
