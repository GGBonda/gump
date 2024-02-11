package org.junhui.gump.flow.compile;

import com.google.common.io.ByteStreams;
import org.eclipse.jdt.internal.compiler.classfmt.ClassFileReader;
import org.eclipse.jdt.internal.compiler.classfmt.ClassFormatException;
import org.eclipse.jdt.internal.compiler.env.ICompilationUnit;
import org.eclipse.jdt.internal.compiler.env.INameEnvironment;
import org.eclipse.jdt.internal.compiler.env.NameEnvironmentAnswer;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class JDTNameEnvironment implements INameEnvironment {

    private final Map<String, String> javaCodes;

    public JDTNameEnvironment(Map<String, String> javaCodes) {
        this.javaCodes = javaCodes;
    }
    @Override
    public NameEnvironmentAnswer findType(char[][] compoundTypeName) {
        String result = joinClassName(compoundTypeName);
        return findType(result);
    }

    @Override
    public NameEnvironmentAnswer findType(char[] typeName, char[][] packageName) {
        String result = joinClassName(packageName) + "." + new String(typeName);
        return findType(result);
    }

    @Override
    public boolean isPackage(char[][] parentPackageName, char[] packageName) {
        String pkgName = new String(packageName);
        String parentPkgName = joinClassName(parentPackageName);

        String path = parentPkgName +"."+ pkgName;
        if (javaCodes.containsKey(path)) {
            return false;
        }

        String resourceName = path.replace('.', '/') + ".class";
        InputStream is = JDTCompiler.FLOW_CLASS_LOADER.getResourceAsStream(resourceName);
        return is == null;
    }

    /*@Override
    public boolean isPackage(char[][] parentPackageName, char[] packageName) {
        String pkgName = new String(packageName);
        String firstChar = "" + pkgName.charAt(0);
        if (Objects.equals(firstChar, firstChar.toLowerCase())) {
            return true;
        }

        String result = "";
        if (parentPackageName != null) {
            result = joinClassName(parentPackageName) + ".";
        }

        result += pkgName;
        return isPackage(result, pkgName);
    }

    private boolean isPackage(String result, String lastName) {
        if (javaCodes.containsKey(result)) {
            return false;
        }

        String firstChar = "" + lastName.charAt(0);
        if (!Objects.equals(firstChar, firstChar.toLowerCase())) {
            return false;
        }

        String resourceName = result.replace('.', '/') + ".class";
        InputStream is = JDTCompiler.FLOW_CLASS_LOADER.getResourceAsStream(resourceName);
        return is == null;
    }*/

    @Override
    public void cleanup() {

    }

    private String joinClassName(char[][] compoundTypeName) {
        if (compoundTypeName == null) {
            return "";
        }
        List<String> parts = new ArrayList<>(compoundTypeName.length);
        for (char[] part : compoundTypeName) {
            parts.add(new String(part));
        }

        return String.join(".", parts);
    }

    private NameEnvironmentAnswer findType(String className) {
        InputStream is = null;
        try {
            if (javaCodes.containsKey(className)) {
                String code = javaCodes.get(className);
                ICompilationUnit compilationUnit = new JDTCompilationUnit(className, code);
                return  new NameEnvironmentAnswer(compilationUnit, null);
            }

            String resourceName = className.replace('.', '/') + ".class";
            is = JDTCompiler.FLOW_CLASS_LOADER.getResourceAsStream(resourceName);
            if (is != null) {
                byte[] classBytes = ByteStreams.toByteArray(is);
                ClassFileReader classFileReader = new ClassFileReader(classBytes, className.toCharArray(), true);
                return new NameEnvironmentAnswer(classFileReader, null);
            }
        } catch (IOException | ClassFormatException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
