package org.junhui.gump.flow.poet.compiler.expression;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junhui.gump.flow.poet.compiler.expression.grammar.GumpExpressionLexer;
import org.junhui.gump.flow.poet.compiler.expression.grammar.GumpExpressionParser;
import org.junhui.gump.flow.poet.compiler.expression.grammar.GumpExpressionVisitor;

public class ANTLRCompiler {

    public static String compile(String expression) {
        CharStream input = CharStreams.fromString(expression);
        GumpExpressionLexer lexer = new GumpExpressionLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        GumpExpressionParser parser = new GumpExpressionParser(tokens);
        ParseTree tree = parser.expression();

        GumpExpressionVisitor visitor = new GumpExpressionVisitor();
        return visitor.visit(tree);
    }

    public static String parseTree(String expr) {
        CharStream input = CharStreams.fromString(expr);
        GumpExpressionLexer lexer = new GumpExpressionLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        GumpExpressionParser parser = new GumpExpressionParser(tokens);
        ParseTree tree = parser.expression();

        return tree.toStringTree(parser);
    }
}
