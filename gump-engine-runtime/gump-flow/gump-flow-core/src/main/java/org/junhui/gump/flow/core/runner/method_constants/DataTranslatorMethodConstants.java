package org.junhui.gump.flow.core.runner.method_constants;

public class DataTranslatorMethodConstants {

    public static String TRANS_TO_STRING(String param) {
        return String.format("transToString(%s)", param);
    }

    public static String TRANS_TO_OBJECT(String param) {
        return String.format("transToObject(%s)", param);
    }

    public static String TRANS_TO_NUMBER(String param) {
        return String.format("transToNumber(%s)", param);
    }

    public static String TRANS_TO_BOOLEAN(String param) {
        return String.format("transToBoolean(%s)", param);
    }

    public static String TRANS_TO_ARRAY(String param) {
        return String.format("transToArray(%s)", param);
    }

}
