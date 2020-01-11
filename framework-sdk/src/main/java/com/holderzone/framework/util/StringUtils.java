package com.holderzone.framework.util;

import java.util.regex.Pattern;

public class StringUtils {
    private static final String FOLDER_SEPARATOR = "/";
    private static final Pattern INT_PATTERN = Pattern.compile("^\\d+$");

    public StringUtils() {
    }

    public static boolean isEmpty(Object str) {
        return str == null || "".equals(str);
    }

    public static boolean hasLength(CharSequence str) {
        return str != null && str.length() > 0;
    }

    public static boolean hasText(CharSequence str) {
        if (!hasLength(str)) {
            return false;
        } else {
            int strLen = str.length();

            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return true;
                }
            }

            return false;
        }
    }

    public static boolean containsWhitespace(CharSequence str) {
        if (!hasLength(str)) {
            return false;
        } else {
            int strLen = str.length();

            for (int i = 0; i < strLen; ++i) {
                if (Character.isWhitespace(str.charAt(i))) {
                    return true;
                }
            }

            return false;
        }
    }

    public static String trimPreTailWhitespace(String str) {
        if (!hasLength(str)) {
            return str;
        } else {
            StringBuilder sb = new StringBuilder(str);

            while (sb.length() > 0 && Character.isWhitespace(sb.charAt(0))) {
                sb.deleteCharAt(0);
            }

            while (sb.length() > 0 && Character.isWhitespace(sb.charAt(sb.length() - 1))) {
                sb.deleteCharAt(sb.length() - 1);
            }

            return sb.toString();
        }
    }

    public static String trimAllWhitespace(String str) {
        if (!hasLength(str)) {
            return str;
        } else {
            int len = str.length();
            StringBuilder sb = new StringBuilder(str.length());

            for (int i = 0; i < len; ++i) {
                char c = str.charAt(i);
                if (!Character.isWhitespace(c)) {
                    sb.append(c);
                }
            }

            return sb.toString();
        }
    }

    public static String quote(String str) {
        if (str == null) {
            return str;
        } else {
            StringBuilder builder = new StringBuilder("'");
            builder.append(str).append("'");
            return builder.toString();
        }
    }

    public static String getFilename(String path) {
        if (path == null) {
            return null;
        } else {
            int separatorIndex = path.lastIndexOf("/");
            return separatorIndex != -1 ? path.substring(separatorIndex + 1) : path;
        }
    }

    public static boolean isNumber(String str) {
        return isEmpty(str) ? false : INT_PATTERN.matcher(str).matches();
    }

    public static String getStr(String regex, Object... parms) {
        StringBuilder format = new StringBuilder("%s");

        for (int i = 0; i < parms.length - 1; ++i) {
            format.append(regex).append("%s");
        }

        return String.format(format.toString(), parms);
    }

    public static void main(String[] args) {
    }

    public static String capitalize(String str) {
        int strLen;
        if (str != null && (strLen = str.length()) != 0) {
            int firstCodepoint = str.codePointAt(0);
            int newCodePoint = Character.toTitleCase(firstCodepoint);
            if (firstCodepoint == newCodePoint) {
                return str;
            } else {
                int[] newCodePoints = new int[strLen];
                int outOffset = 0;
                outOffset = outOffset + 1;
                newCodePoints[outOffset] = newCodePoint;

                int codepoint;
                for (int inOffset = Character.charCount(firstCodepoint); inOffset < strLen; inOffset += Character.charCount(codepoint)) {
                    codepoint = str.codePointAt(inOffset);
                    newCodePoints[outOffset++] = codepoint;
                }

                return new String(newCodePoints, 0, outOffset);
            }
        } else {
            return str;
        }
    }
}

