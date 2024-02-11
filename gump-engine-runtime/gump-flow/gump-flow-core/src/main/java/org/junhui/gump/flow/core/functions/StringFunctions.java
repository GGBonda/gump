package org.junhui.gump.flow.core.functions;

import org.junhui.gump.flow.core.annotation.GumpFunctionBean;

@GumpFunctionBean(desc = "文本")
public class StringFunctions {

    @GumpFunctionBean.Function(code = "spilt", defaultExpr="spilt( , )", desc="文本函数spilt方法")
    public Object spilt(Object param, Object regex) {
         if (param == null || regex == null) {
             return null;
         }

         if (!(param instanceof String) || !(regex instanceof String)) {
             throw new IllegalArgumentException(String.format("String Function spilt can not accept '%s', '%s' param", param.getClass().getName(), regex.getClass().getName()));
         }

         return ((String)param).split((String) regex);
    }
}
