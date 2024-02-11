package org.junhui.gump.flow.core.functions;

import org.apache.commons.lang3.SerializationUtils;
import org.junhui.gump.flow.core.annotation.GumpFunctionBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@GumpFunctionBean(desc = "集合")
public class CollectionFunctions {

    @GumpFunctionBean.Function(code = "reverse", defaultExpr="reverse()", desc="集合函数reverse方法")
    public Object reverse(Object param) {
        if (param == null) {
            return null;
        }

        if (!(param instanceof ArrayList)) {
            throw new IllegalArgumentException(String.format("Collection Function reverse can not accept '%s' param", param.getClass().getName()));
        }

        List<?> paramCopy = SerializationUtils.clone((ArrayList<?>)param);
        Collections.reverse(paramCopy);
        return paramCopy;
    }

    @GumpFunctionBean.Function(code = "union", defaultExpr="union( , )", desc="集合函数union方法")
    public Object union(Object a, Object b) {
        List<Object> res = new ArrayList<>();

        if (a == null && b == null) {
            return res;
        }

        if (a != null && !(a instanceof Collection)) {
            throw new IllegalArgumentException(String.format("Collection Function union can not accept '%s' param", a.getClass().getName()));
        }
        if (b != null && !(b instanceof Collection)) {
            throw new IllegalArgumentException(String.format("Collection Function union can not accept '%s' param", b.getClass().getName()));
        }

        if (a != null) {
            res.addAll((Collection<?>)a);
        }

        if (b != null) {
            res.addAll((Collection<?>)b);
        }
        return res;
    }


}
