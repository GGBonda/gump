package org.junhui.gump.flow.core.compile.java;

import java.util.Map;

public interface JavaClassCompiler {

    Map<String, Class<?>> compile(Map<String, String> javaCodes);
}
