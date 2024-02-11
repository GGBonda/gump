package org.junhui.gump.flow.core.runner;


public interface LogicOperation extends GumpExpressionBaseOperation {

    default Boolean and(Object left, Object right) {
        if (left == null || right == null) {
            return null;
        }

        if (!(left instanceof Boolean) || !(right instanceof Boolean)) {
            throw new IllegalArgumentException(String.format("Operator '&&' cannot be applied to '%s', '%s'", left.getClass().getName(), right.getClass().getName()));
        }

        return ((Boolean) left) && ((Boolean) right);
    }

    default Boolean or(Object left, Object right) {
        if (left == null || right == null) {
            return null;
        }

        if (!(left instanceof Boolean) || !(right instanceof Boolean)) {
            throw new IllegalArgumentException(String.format("Operator '&&' cannot be applied to '%s', '%s'", left.getClass().getName(), right.getClass().getName()));
        }

        return ((Boolean) left) || ((Boolean) right);
    }
}
