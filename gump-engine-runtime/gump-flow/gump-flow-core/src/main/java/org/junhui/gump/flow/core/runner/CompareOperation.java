package org.junhui.gump.flow.core.runner;


import java.math.BigDecimal;
import java.util.Objects;

public interface CompareOperation extends GumpExpressionBaseOperation {

    default Boolean eq(Object left, Object right) {
        return Objects.equals(left, right);
    }

    default Boolean ne(Object left, Object right) {
        return !Objects.equals(left, right);
    }

    default Boolean gt(Object left, Object right) {
        if (left == null || right == null) {
            return null;
        }

        if (isNotNumber(left) || isNotNumber(right)) {
            throw new IllegalArgumentException(String.format("Operator '>' cannot be applied to '%s', '%s'", left.getClass().getName(), right.getClass().getName()));
        }
        return numberCompare(left, right, ">");
    }

    default Boolean ge(Object left, Object right) {
        if (left == null || right == null) {
            return null;
        }

        if (isNotNumber(left) || isNotNumber(right)) {
            throw new IllegalArgumentException(String.format("Operator '>=' cannot be applied to '%s', '%s'", left.getClass().getName(), right.getClass().getName()));
        }
        return numberCompare(left, right, ">=");
    }

    default Boolean lt(Object left, Object right) {
        if (left == null || right == null) {
            return null;
        }

        if (isNotNumber(left) || isNotNumber(right)) {
            throw new IllegalArgumentException(String.format("Operator '<' cannot be applied to '%s', '%s'", left.getClass().getName(), right.getClass().getName()));
        }
        return numberCompare(left, right, "<");
    }

    default Boolean le(Object left, Object right) {
        if (left == null || right == null) {
            return null;
        }

        if (isNotNumber(left) || isNotNumber(right)) {
            throw new IllegalArgumentException(String.format("Operator '<=' cannot be applied to '%s', '%s'", left.getClass().getName(), right.getClass().getName()));
        }
        return numberCompare(left, right, "<=");
    }

    default Boolean numberCompare(Object left, Object right, String operation) {
        BigDecimal leftBigDecimal = left instanceof BigDecimal ? (BigDecimal)left : new BigDecimal(left.toString());
        BigDecimal rightBigDecimal = right instanceof BigDecimal ? (BigDecimal)right : new BigDecimal(right.toString());

        int flag = leftBigDecimal.compareTo(rightBigDecimal);
        switch (operation) {
            case ">": return flag > 0;
            case ">=": return flag >= 0;
            case "<": return flag < 0;
            case "<=": return flag <= 0;
            //case "==": return flag == 0;
            //case "!=": return flag != 0;
        }
        return null;
    }
}
