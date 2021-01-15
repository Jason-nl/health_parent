package cn.itcast.health.utils.valid;

import java.util.Collection;
import java.util.Map;

/**
 * created by Ethan on 2020/12/6 4:18 下午
 */
public class ValidationUtil {

    /**
     * String是否为null或者""
     * @param obj String
     * @return 是否为空
     */
    public static boolean isEmpty(String obj) {
        return (obj == null || "".equals(obj));
    }

    /**
     * Array是否为null或者size为0
     * @param obj Array
     * @return 是否为空
     */
    public static boolean isEmpty(Object[] obj) {
        return obj == null || obj.length == 0;
    }

    /**
     * Collection是否为null或者size为0
     * @param obj collection
     * @return 是否为空
     */
    public static boolean isEmpty(Collection<?> obj) {
        return obj == null || obj.isEmpty();
    }

    /**
     * Map是否为null或者size为0
     * @param obj
     * @return
     */
    public static boolean isEmpty(Map<?, ?> obj) {
        return obj == null || obj.isEmpty();
    }
}
