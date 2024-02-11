package org.junhui.gump.flow.poet.compiler.expression;



public class GrammarTest {

    public static void main(String[] args) {
        String expression = "[$.a.b, 1, \"2\", b.c]";

        ANTLRCompiler compiler = new ANTLRCompiler();
        System.out.println(compiler.compile(expression));

        System.out.println(compiler.parseTree(expression));
    }
}
