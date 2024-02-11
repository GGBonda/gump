package org.junhui.gump.flow.compile;

import org.eclipse.jdt.internal.compiler.env.ICompilationUnit;

import java.io.File;
import java.util.StringTokenizer;
import java.util.regex.Matcher;

public class JDTCompilationUnit implements ICompilationUnit {
    private final String className;
    private final String code;

    public JDTCompilationUnit(String className, String code) {
        this.className = className;
        this.code = code;
    }

    @Override
    public char[] getContents() {
        return code.toCharArray();
    }

    @Override
    public char[] getMainTypeName() {
        int dot = className.lastIndexOf('.');
        if (dot > 0) {
            return className.substring(dot + 1).toCharArray();
        }
        return className.toCharArray();
    }

    @Override
    public char[][] getPackageName() {
        StringTokenizer stringTokenizer = new StringTokenizer(className, ".");
        char[][] result = new char[stringTokenizer.countTokens() - 1][];
        for (int i = 0; i < result.length; i++) {
            String tok = stringTokenizer.nextToken();
            result[i] = tok.toCharArray();
        }
        return result;
    }

    @Override
    public char[] getFileName() {
        String fileName = className.replaceAll("\\.", Matcher.quoteReplacement(File.separator)) + ".java";
        return fileName.toCharArray();
    }
}
