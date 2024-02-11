package org.junhui.gump.flow.core.runner;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public interface GumpExpressionBaseOperation {

    default boolean isNotNumber(Object obj) {
        if (obj == null) {
            return true;
        }

        if (obj instanceof Number) {
            return false;
        }

        return !(obj instanceof String && obj.toString().matches("-?\\d+\\.?\\d*"));
    }

    default Object child(Object parent, String childKey) {
        if (parent == null || StringUtils.isEmpty(childKey)) {
            return null;
        }

        if (parent instanceof Map) {
            return ((Map<?, ?>)parent).get(childKey);
        }
        return null;
    }

    default Object emptyObject() {
        return new HashMap<>(0);
    }

    default Object emptyList() {
        return new ArrayList<>(0);
    }

    default Object newList(Object... a) {
        return new ArrayList<>(Arrays.asList(a));
    }

    default Object listGet(Object listObj, Object indexObj) {
        List<?> list = (List<?>) listObj;
        Integer index = (Integer) indexObj;

        if (CollectionUtils.isEmpty(list) || index == null || index < 0 || list.size() <= index) {
            return null;
        }

        return list.get(index);
    }
}
