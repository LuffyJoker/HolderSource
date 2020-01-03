package com.holderzone.holdersaasstoredto.dto.kds.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class DeviceCreateReqDTO implements Serializable {

    private static final long serialVersionUID = -2773042054434843563L;

    @NotEmpty(message = "门店Guid不得为空")
    @ApiModelProperty(value = "门店Guid")
    private String storeGuid;

    @NotEmpty(message = "设备ID不得为空")
    @ApiModelProperty(value = "设备ID")
    private String deviceId;

    @NotNull(message = "设备名称不得为空")
    @Size(max = 10, message = "设备名称不得超过10个字符")
    @ApiModelProperty(value = "设备名称")
    private String name;

    @NotNull(message = "堂口模式不得为空")
    @Min(value = 0, message = "堂口模式：0=制作点，1=出堂口")
    @Max(value = 1, message = "堂口模式：0=制作点，1=出堂口")
    @ApiModelProperty(value = "堂口模式：0=制作点，1=出堂口")
    private Integer pointMode;
}
