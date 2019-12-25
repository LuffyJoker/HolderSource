package com.holderzone.framework.feign.starter.decoder;

import com.holderzone.framework.feign.starter.core.*;
import com.holderzone.framework.feign.starter.util.FeignJsonUtils;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;

import java.io.IOException;

/**
 * @author Mr.Q
 * @date 2019/12/25 20:28
 * desc：
 */
public class ErrorDecoderWithHystrix extends ErrorDecoder.Default {

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
                        return new HystrixBadRequestException(body, new RawJsonException(body));
                    }
                    SpecificException cause = SpecificException.remote(
                            message.getErrorCode(),
                            message.getDetailMessage()
                    );
                    return new HystrixBadRequestException(message.getDetailMessage(), cause);
                }
            }
            return new HystrixBadRequestException(body, GenericException.remote(body));
        }
        return new OpenFeignException(status, body);
    }
}
