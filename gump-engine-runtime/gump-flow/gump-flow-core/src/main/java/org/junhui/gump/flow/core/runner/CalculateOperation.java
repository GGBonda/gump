package org.junhui.gump.flow.core.runner;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 实现计算操作
 * 对null值敏感，即参与计算的数据中如果存在null值，则直接返回null
 * 如果对null不敏感的话，那就要针对null值的加、减、乘、除、逻辑与、逻辑或等一系列运算操作设定默认处理方式，默认处理方式会造成学习、排查问题的成本，所以对null值默认敏感。
 * 用户可以为参数设置默认值的方式，显式处理null的情况。
 * */
public interface CalculateOperation extends GumpExpressionBaseOperation {

    default Object add(Object left, Object right) {
        if (left == null || right == null) {
            return null;
        }

        if (!(left instanceof String || left instanceof Number)
                || !(right instanceof String || right instanceof Number)) {
            throw new IllegalArgumentException(String.format("Operator '+' cannot be applied to '%s', '%s'", left.getClass().getName(), right.getClass().getName()));
        }

        if (left instanceof String || right instanceof String) {
            return right + left.toString();
        }

        return numberOperation(left, right, '+');
    }

    default Object sub(Object left, Object right) {
        if (left == null || right == null) {
            return null;
        }

        if (isNotNumber(left) || isNotNumber(right)) {
            throw new IllegalArgumentException(String.format("Operator '-' cannot be applied to '%s', '%s'", left.getClass().getName(), right.getClass().getName()));
        }
        return numberOperation(left, right, '-');
    }

    default Object mul(Object left, Object right) {
        if (left == null || right == null) {
            return null;
        }

        if (isNotNumber(left) || isNotNumber(right)) {
            throw new IllegalArgumentException(String.format("Operator '*' cannot be applied to '%s', '%s'", left.getClass().getName(), right.getClass().getName()));
        }
        return numberOperation(left, right, '*');
    }

    default Object div(Object left, Object right) {
        if (left == null || right == null) {
            return null;
        }

        if (isNotNumber(left) || isNotNumber(right)) {
            throw new IllegalArgumentException(String.format("Operator '/' cannot be applied to '%s', '%s'", left.getClass().getName(), right.getClass().getName()));
        }
        return numberOperation(left, right, '/');
    }


    default Object numberOperation(Object left, Object right, char operation) {
        BigDecimal leftBigDecimal = left instanceof BigDecimal ? (BigDecimal)left : new BigDecimal(left.toString());
        BigDecimal rightBigDecimal = right instanceof BigDecimal ? (BigDecimal)right : new BigDecimal(right.toString());

        BigDecimal res;
        switch (operation) {
            case '+': res = leftBigDecimal.add(rightBigDecimal);break;
            case '-': res = leftBigDecimal.subtract(rightBigDecimal);break;
            case '*': res = leftBigDecimal.multiply(rightBigDecimal);break;
            case '/': res = leftBigDecimal.divide(rightBigDecimal, 8, RoundingMode.HALF_UP);break;
            default: res = new BigDecimal(0);
        }

        Class<? extends Number> resType = defineResType(left, right);

        if (resType == Double.class) {
            return res.doubleValue();
        }
        if (resType == Float.class) {
            return res.floatValue();
        }
        if (resType == Long.class) {
            return res.longValue();
        }
        return res.intValue();
    }

    default Object mod(Object left, Object right) {
        if (left == null || right == null) {
            return null;
        }

        if (left instanceof Integer) {
            if (right instanceof Integer) {
                return ((Integer) left) % ((Integer) right);
            }

            if (right instanceof Long) {
                return ((Integer) left) % ((Long) right);
            }
        }

        if (left instanceof Long) {
            if (right instanceof Integer) {
                return ((Long) left) % ((Integer) right);
            }

            if (right instanceof Long) {
                return ((Long) left) % ((Long) right);
            }
        }

        throw new IllegalArgumentException(String.format("Operator 'mod' cannot be applied to '%s', '%s'", left.getClass().getName(), right.getClass().getName()));
    }

    default Class<? extends Number> defineResType(Object left, Object right) {
        if (left instanceof Double || right instanceof Double) {
            return Double.class;
        }

        if (left instanceof Float || right instanceof Float) {
            return Float.class;
        }

        if (left instanceof Long || right instanceof Long) {
            return Long.class;
        }

        if (left instanceof Integer || right instanceof Integer) {
            return Integer.class;
        }
        throw new IllegalArgumentException(String.format("Can't define Calculation results type, left type: '%s', right type: '%s'", left.getClass().getName(), right.getClass().getName()));
    }

}
