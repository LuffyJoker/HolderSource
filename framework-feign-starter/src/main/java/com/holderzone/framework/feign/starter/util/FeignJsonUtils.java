package com.holderzone.framework.feign.starter.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.TimeZone;

/**
 * @author Mr.Q
 * @date 2019/12/25 20:31
 * desc：
 */
public class FeignJsonUtils {

    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();

        //不包含null值的属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // 设置时区
        objectMapper.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));

        // 忽略空bean转json的错误
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        //设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        objectMapper.registerModule(new JavaTimeModule());
    }

    /**
     * java对象转为字符串，null对象即为"null"
     *
     * @param obj
     * @return
     * @throws JsonProcessingException
     */
    public static String writeValueAsString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("JSON 转化异常！");
        }
    }

    /**
     * 是否是一个有效的Json字符串
     *
     * @param json
     * @return
     */
    public static boolean isValidJSON(final String json) {
        boolean valid = true;
        try {
            objectMapper.readTree(json);
        } catch (IOException e) {
            valid = false;
        }
        return valid;
    }

    /**
     * 将JSON字符串转换为对象
     *
     * @param clazz
     * @param json
     * @return T
     * @throws
     * @Title: toObject
     * @Description: TODO
     */
    public static <T> T toObject(Class<T> clazz, String json) {
        try {
            if (StringUtils.isEmpty(json)) {
                return null;
            }
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("json字符串转化错误！！");
        }
    }
}
