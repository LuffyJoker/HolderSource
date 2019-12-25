package com.holderzone.framework.feign.starter.util;

import org.springframework.util.StringUtils;

/**
 * @author Mr.Q
 * @date 2019/12/25 20:31
 * desc：
 */
public final class FeignMsgUtils {
    private FeignMsgUtils() {
    }

    public static String parseFeignMsg(String message) {
        if (StringUtils.isEmpty(message)) {
            return null;
        } else {
            String concat = "; content:\n";
            int concatLength = concat.length();
            int index = message.indexOf(concat);
            if (index > 0 && index <= message.length() - concatLength) {
                message = message.substring(index + concatLength);
            }

            return StringUtils.isEmpty(message) ? null : message;
        }
    }

    public static String parseFeignMsgDetailed(String message) {
        if (StringUtils.isEmpty(message)) {
            return null;
        } else {
            String reading = "reading";
            int readingLength = reading.length();
            int readingIndex = message.indexOf(reading);
            String content = "; content:\n";
            int contentLength = content.length();
            int contentIndex = message.indexOf(content);
            StringBuilder builder = new StringBuilder();
            builder.append("方法: ");
            if (readingIndex > 0 && readingIndex <= contentIndex - readingLength) {
                builder.append(message.substring(readingIndex + readingLength, contentIndex));
                builder.append(", 发生错误：");
                if (contentIndex > 0 && contentIndex <= message.length() - contentLength) {
                    builder.append(message.substring(contentIndex + contentLength));
                    String parsedMsg = builder.toString();
                    return StringUtils.isEmpty(parsedMsg) ? null : parsedMsg;
                } else {
                    return message;
                }
            } else {
                return message;
            }
        }
    }
}

