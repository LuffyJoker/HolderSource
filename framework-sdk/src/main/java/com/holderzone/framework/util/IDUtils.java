package com.holderzone.framework.util;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

public class IDUtils {
    private static final int TEN = 10;
    private static final int HUNDRED = 100;
    private static long lastTimeMill = -1L;
    private static long sequence = 0L;
    private static final int MAX_SEQUENCE = 999;

    public IDUtils() {
    }

    public static String nextId() {
        Class var5 = IDUtils.class;
        long currentTimeMill;
        long currentSequence;
        synchronized(IDUtils.class) {
            currentTimeMill = currentTimeMill();
            if (currentTimeMill == lastTimeMill) {
                if (sequence >= 999L) {
                    sequence = 0L;
                    currentTimeMill = nextTimeMill();
                } else {
                    ++sequence;
                }
            } else {
                sequence = 0L;
            }

            currentSequence = sequence;
            lastTimeMill = currentTimeMill;
        }

        String sequenceStr;
        if (currentSequence < 10L) {
            sequenceStr = String.format("00%d", currentSequence);
        } else if (currentSequence < 100L) {
            sequenceStr = String.format("0%d", currentSequence);
        } else {
            sequenceStr = String.valueOf(currentSequence);
        }

        int machineId = ThreadLocalRandom.current().nextInt(10);
        return String.format("%d%s%d", currentTimeMill, sequenceStr, machineId).substring(2);
    }

    private static long currentTimeMill() {
        String now = LocalDateTime.now().toString();
        String replaceAll = RegexUtils.getReplaceAll(now, "[^\\d]+", "");
        return Long.valueOf(replaceAll);
    }

    private static long nextTimeMill() {
        long curTimeMill;
        for(curTimeMill = currentTimeMill(); curTimeMill <= lastTimeMill; curTimeMill = currentTimeMill()) {
        }

        return curTimeMill;
    }
}

