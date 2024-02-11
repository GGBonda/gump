package org.junhui.gump.flow.compile;

import java.util.HashMap;
import java.util.Map;

public class FlowClassLoader extends ClassLoader {

    private final Map<String, byte[]> classNameBytesMap = new HashMap<>();

    public byte[] getClassBytes(String className) {
        return classNameBytesMap.get(className);
    }

    public void put(String className, byte[] bytes) {
        classNameBytesMap.put(className, bytes);
    }

    public FlowClassLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classBytes = classNameBytesMap.remove(name);
        if (classBytes != null) {
            return defineClass(name, classBytes, 0, classBytes.length);
        }
        return super.findClass(name);
    }
}