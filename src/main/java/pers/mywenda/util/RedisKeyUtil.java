package pers.mywenda.util;

public class RedisKeyUtil {
    private static String BIZ_EVENTQUEUE = "EVENT_QUEUE";

    public static String getEventQueueKey() {
        return BIZ_EVENTQUEUE;
    }
}
