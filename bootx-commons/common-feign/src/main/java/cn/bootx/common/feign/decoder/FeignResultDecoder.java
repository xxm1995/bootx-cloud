package cn.bootx.common.feign.decoder;

import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.support.SpringDecoder;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * feign解码器
 * @author xxm
 * @date 2021/4/7
 */
@RequiredArgsConstructor
public class FeignResultDecoder implements Decoder {

    private final SpringDecoder springDecoder;
    private final ResponseInterceptor responseInterceptor;

    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {

        if (response.status() == 404 || response.status() == 204) {
            return Util.emptyValueOf(type);
        }
        if (response.body() == null) {
            return null;
        }
        if (byte[].class.equals(type)) {
            return Util.toByteArray(response.body().asInputStream());
        }

        Object o = springDecoder.decode(response, type);
        return responseInterceptor.apply(o);
    }
}
