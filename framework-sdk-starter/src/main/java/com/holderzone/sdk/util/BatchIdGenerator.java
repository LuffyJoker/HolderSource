package com.holderzone.sdk.util;

import com.google.common.collect.Lists;
import com.holderzone.framework.exception.ParamException;
import com.holderzone.framework.exception.runtime.ParameterException;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr.Q
 * @date 2019/12/24 22:08
 * desc：
 */
public class BatchIdGenerator {
    public BatchIdGenerator() {
    }

    public static List<Long> batchGetGuids(RedisTemplate redisTemplate, String tab, long count) {
        if (count <= 10240L && count > 0L) {
            List<Long> guids = new ArrayList();
            long luaNum = 1L;
            if (count > 1024L) {
                if (count % 1024L != 0L) {
                    luaNum = count / 1024L + 1L;
                } else {
                    luaNum = count / 1024L;
                }
            }

            if (count <= 1024L) {
                guids.addAll(getGuids(redisTemplate, tab, count));
            } else {
                for(int i = 0; (long)i < luaNum; ++i) {
                    if (count - 1024L > 0L) {
                        guids.addAll(getGuids(redisTemplate, tab, 1024L));
                        count -= 1024L;
                    } else {
                        guids.addAll(getGuids(redisTemplate, tab, count));
                    }
                }
            }

            return guids;
        } else {
            throw new ParameterException("批量guid生成区间为1-10240");
        }
    }

    private static List<Long> getGuids(RedisTemplate redisTemplate, String tab, long count) {
        DefaultRedisScript<Object> redisScript = new DefaultRedisScript();
        String lua = "redis.replicate_commands();\nredis.call('select', 0);\nlocal prefix = 'batchgenerator_';\nlocal tag = KEYS[1];\nlocal step = tonumber(KEYS[2]);\nlocal startStep = 0;\nlocal now = redis.call('TIME');\nlocal miliSecondKey = prefix .. tag .. '_' .. now[1] .. '_' .. math.floor(now[2]/1000);\nlocal count;\nrepeat\n  count = tonumber(redis.call('INCRBY', miliSecondKey, step));\n  if count > (1024 - step) then\n      now = redis.call('TIME');\n      miliSecondKey = prefix .. tag .. '_' .. now[1] .. '_' .. math.floor(now[2]/1000);\n  end;\nuntil count <= 1024\n\nif count == step then\n  redis.call('PEXPIRE', miliSecondKey, 5);\nend;\n\nreturn {tonumber(now[1]), tonumber(now[2]), count - 1}";
        redisScript.setScriptText(lua);
        String sha1 = redisScript.getSha1();

        List result;
        try {
            result = (List)redisTemplate.execute((RedisCallback) (redisConnection) -> {
                return (List)redisConnection.scriptingCommands().evalSha(sha1, ReturnType.fromJavaType(Object.class), 2, new byte[][]{tab.getBytes(), String.valueOf(count).getBytes()});
            });
        } catch (Exception var9) {
            if (!exceptionContainsNoScriptError(var9)) {
                throw (RuntimeException)var9;
            }

            result = (List)redisTemplate.execute((RedisCallback) (redisConnection) -> {
                return (List)redisConnection.scriptingCommands().eval(lua.getBytes(), ReturnType.fromJavaType(Object.class), 2, new byte[][]{tab.getBytes(), String.valueOf(count).getBytes()});
            });
        }

        return buildGuids((Long)result.get(0), (Long)result.get(1), (Long)result.get(2), count);
    }

    public static long getGuid(RedisTemplate redisTemplate, String tab) throws IOException {
        return (Long)batchGetGuids(redisTemplate, tab, 1L).get(0);
    }

    public static List<Long> parseGuid(long id) {
        long miliSecond = id >>> 22;
        long shardId = (id & 4193280L) >> 10;
        long seq = id & 1023L;
        List<Long> re = new ArrayList(4);
        re.add(miliSecond);
        re.add(shardId);
        re.add(seq);
        return re;
    }

    private static List<Long> buildGuids(long second, long microSecond, long seq, long count) {
        List<Long> guids = Lists.newArrayList();
        long miliSecond = second * 1000L + microSecond / 1000L;
        long l = (miliSecond << 22) + seq;

        for(int i = 0; (long)i < count; ++i) {
            guids.add(l - (long)i);
        }

        return guids;
    }

    private static boolean exceptionContainsNoScriptError(Exception e) {
        if (!(e instanceof NonTransientDataAccessException)) {
            return false;
        } else {
            for(Object current = e; current != null; current = ((Throwable)current).getCause()) {
                String exMessage = ((Throwable)current).getMessage();
                if (exMessage != null && exMessage.contains("NOSCRIPT")) {
                    return true;
                }
            }

            return false;
        }
    }

    public static void main(String[] args) {
        long count = 10239L;
        if (count <= 10240L && count > 0L) {
            List<Long> guids = new ArrayList();
            long luaNum = 1L;
            if (count > 1024L) {
                if (count % 1024L != 0L) {
                    luaNum = count / 1024L + 1L;
                } else {
                    luaNum = count / 1024L;
                }
            }

            if (count <= 1024L) {
                List<Long> guid = new ArrayList();

                for(int j = 0; (long)j < count; ++j) {
                    guid.add((long)j);
                }

                guids.addAll(guid);
            } else {
                for(int i = 0; (long)i < luaNum; ++i) {
                    int j;
                    ArrayList guid;
                    if (count - 1024L > 0L) {
                        guid = new ArrayList();

                        for(j = 0; j < 1024; ++j) {
                            guid.add((long)j);
                        }

                        guids.addAll(guid);
                        count -= 1024L;
                    } else {
                        guid = new ArrayList();

                        for(j = 0; (long)j < count; ++j) {
                            guid.add((long)j);
                        }

                        guids.addAll(guid);
                    }
                }
            }

            System.out.println(guids);
        } else {
            throw new ParameterException("批量guid生成区间为1-10240");
        }
    }
}
