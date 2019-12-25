package com.holderzone.framework.feign.starter.decoder;

import com.holderzone.framework.feign.starter.core.*;
import com.holderzone.framework.feign.starter.util.FeignJsonUtils;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;

import java.io.IOException;

/**
 * @author Mr.Q
 * @date 2019/12/25 20:28
 * desc：
 */
public class ErrorDecoderWithoutHystrix extends ErrorDecoder.Default {

    @Override
    public Exception decode(String methodKey, Response response) {
        int status = response.status();
        String body = BizValidator.UNKNOWN_REASON;
        try {
            body = Util.toString(response.body().asReader());
        } catch (IOException ignore) {
        }
        if (BizValidator.isBizError(status)) {
            if (FeignJsonUtils.isValidJSON(body)) {
                SpecificMessage message = FeignJsonUtils.toObject(SpecificMessage.class, body);
                if (message != null) {
                    if (message.getDetailMessage() == null) {
                        return new RawJsonException(body);
                    }
                    return SpecificException.remote(
                            message.getErrorCode(),
                            message.getDetailMessage()
                    );
                }
            }
            return GenericException.local(body);
        }
        return new OpenFeignException(status, body);
    }
}
