package org.junhui.gump.flow.core.runner;

import java.lang.reflect.Array;
import java.util.*;

public interface DataTranslator {

    default String transToString(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof String) {
            return (String) value;
        }

        return String.valueOf(value);
    }

    default Map<String, Object> transToObject(Object value) {
        if (value == null) {
            return null;
        }

        return (Map<String, Object>) value;
    }

    default Number transToNumber(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Number) {
            return (Number) value;
        }

        if (value instanceof String && value.toString().matches("-?\\d+\\.?\\d*")) {
            try {
                try {
                    return Integer.parseInt(value.toString());
                } catch (NumberFormatException e) {
                    return Long.parseLong(value.toString());
                }
            } catch (NumberFormatException longE) {
                try {
                    return Double.valueOf(value.toString());
                } catch (NumberFormatException doubleE) {
                    throw new IllegalArgumentException(String.format("%s can't be cast to Number", value));
                }
            }
        }
        throw new IllegalArgumentException(String.format("%s can't be cast to Number", value));
    }

    default Boolean transToBoolean(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Boolean) {
            return (Boolean) value;
        }


        throw new IllegalArgumentException(String.format("%s can't be cast to Boolean", value));
    }

    default List<Object> transToArray(Object value) {
        if (value == null) {
            return null;
        }

        if (value.getClass().isArray() && Array.getLength(value) > 0) {
            int length = Array.getLength(value);
            List<Object> res = new ArrayList<>(length);
            for (int i = 0; i < length; i++) {
                res.add(Array.get(value, i));
            }
            return res;
        }

        if (value instanceof Collection) {
            return new ArrayList<>((Collection<?>) value);
        }

        return null;
    }
}
