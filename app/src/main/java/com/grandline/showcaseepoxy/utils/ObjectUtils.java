package com.grandline.showcaseepoxy.utils;

import java.util.List;

/**
 * Created by home on 8/29/17.
 */

public class ObjectUtils {
    public static boolean isEmpty(String string) {
        return string == null || string.length() == 0;
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isEmptyList(List list) {
        return list == null || list.isEmpty();
    }
}
