package org.junhui.gump.flow.core.runner.method_constants;

public class GumpExpressionOperationMethodConstants {

    public static String CHILD(String parentPointer, String childKey) {
        return String.format("child(%s, \"%s\")", parentPointer, childKey);
    }

    public static String EMPTY_OBJECT() {
        return "emptyObject()";
    }

    public static String EMPTY_ARRAY() {
        return "emptyList()";
    }

    public static String NEW_ARRAY(String elementList) {
        return String.format("newList(%s)", elementList);
    }

    public static String ARRAY_POINT(String listPointer, String index) {
        return String.format("listGet(%s, %s)", listPointer, index);
    }

    public static String ADD(String left, String right) {
        return String.format("add(%s, %s)", left, right);
    }

    public static String SUB(String left, String right) {
        return String.format("sub(%s, %s)", left, right);
    }

    public static String MUL(String left, String right) {
        return String.format("mul(%s, %s)", left, right);
    }

    public static String DIV(String left, String right) {
        return String.format("div(%s, %s)", left, right);
    }

    public static String MOD(String left, String right) {
        return String.format("mod(%s, %s)", left, right);
    }

    public static String EQ(String left, String right) {
        return String.format("eq(%s, %s)", left, right);
    }

    public static String NE(String left, String right) {
        return String.format("ne(%s, %s)", left, right);
    }

    public static String GT(String left, String right) {
        return String.format("gt(%s, %s)", left, right);
    }

    public static String GE(String left, String right) {
        return String.format("ge(%s, %s)", left, right);
    }

    public static String LT(String left, String right) {
        return String.format("lt(%s, %s)", left, right);
    }

    public static String LE(String left, String right) {
        return String.format("le(%s, %s)", left, right);
    }

    public static String AND(String left, String right) {
        return String.format("and(%s, %s)", left, right);
    }

    public static String OR(String left, String right) {
        return String.format("or(%s, %s)", left, right);
    }
}
