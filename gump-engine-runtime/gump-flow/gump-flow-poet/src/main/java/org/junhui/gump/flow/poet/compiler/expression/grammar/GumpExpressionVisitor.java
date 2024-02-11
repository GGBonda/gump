package org.junhui.gump.flow.poet.compiler.expression.grammar;

import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junhui.gump.flow.core.runner.method_constants.BaseRunnerMethodConstants.ALL_STEP_OUTPUT;
import static org.junhui.gump.flow.core.runner.method_constants.GumpExpressionOperationMethodConstants.*;

public class GumpExpressionVisitor extends GumpExpressionParserBaseVisitor<String> {

    @Override
    public String visitIdentifier(GumpExpressionParser.IdentifierContext ctx) {
        //return super.visitIdentifier(ctx);

        String identifier = ctx.IDENTIFIER().getText();
        if (Objects.equals(identifier, "$")) {
            return ALL_STEP_OUTPUT();
        }
        return ctx.IDENTIFIER().getText();
    }

    @Override
    public String visitArrayLiteralExpr(GumpExpressionParser.ArrayLiteralExprContext ctx) {
        List<ParseTree> children = ctx.arrayLiteral().children;
        if (CollectionUtils.isEmpty(children)) {
            return "";
        }

        if (children.size() == 2) {
            return EMPTY_ARRAY();
        }

        List<String> elementList = new ArrayList<>();

        GumpExpressionParser.ElementListContext elementListContext = (GumpExpressionParser.ElementListContext) ctx.arrayLiteral().children.get(1);
        for (GumpExpressionParser.ExpressionContext expressionContext : elementListContext.expression()) {
            elementList.add(visit(expressionContext));
        }

        return NEW_ARRAY(String.join(",", elementList));
    }

    @Override
    public String visitEmptyObject(GumpExpressionParser.EmptyObjectContext ctx) {
        return EMPTY_OBJECT();
    }

    @Override
    public String visitBracketsExpr(GumpExpressionParser.BracketsExprContext ctx) {
        if (ctx.expression() == null) {
            return "";
        }
        return String.format("(%s)", visit(ctx.expression()));
    }

    @Override
    public String visitIdentifierExpr(GumpExpressionParser.IdentifierExprContext ctx) {
        return super.visitIdentifierExpr(ctx);
    }

    @Override
    public String visitLiteralExpr(GumpExpressionParser.LiteralExprContext ctx) {
        return super.visitLiteralExpr(ctx);
    }

    @Override
    public String visitLiteral(GumpExpressionParser.LiteralContext ctx) {
        return ctx.getText();
    }

    @Override
    public String visitIntegerLiteral(GumpExpressionParser.IntegerLiteralContext ctx) {
        return ctx.getText();
    }

    @Override
    public String visitFloatLiteral(GumpExpressionParser.FloatLiteralContext ctx) {
        return ctx.getText();
    }

    @Override
    public String visitExpressionList(GumpExpressionParser.ExpressionListContext ctx) {
        return super.visitExpressionList(ctx);
    }

    @Override
    public String visitMethodCall(GumpExpressionParser.MethodCallContext ctx) {
        String method = ctx.identifier().getText();
        String params = visit(ctx.expressionList());
        return String.format("%s(%s)", method, params);
    }

    @Override
    public String visitLogicOr(GumpExpressionParser.LogicOrContext ctx) {
        String left = visit(ctx.expression(0));
        String right = visit(ctx.expression(1));
        String op = ctx.bop.getText();

        if (Objects.equals(op, "||")) {
            return AND(left, right);
        }
        return left + op + right;
    }

    @Override
    public String visitPrimaryPointer(GumpExpressionParser.PrimaryPointerContext ctx) {
        return super.visitPrimaryPointer(ctx);
    }

    @Override
    public String visitAntiExpr(GumpExpressionParser.AntiExprContext ctx) {
        String op = ctx.prefix.getText();
        String expr = visit(ctx.expression());
        return op + expr;
    }

    @Override
    public String visitBitOr(GumpExpressionParser.BitOrContext ctx) {
        String left = visit(ctx.expression(0));
        String right = visit(ctx.expression(1));
        String op = ctx.bop.getText();
        return left + op + right;
    }

    @Override
    public String visitSelfAddSubPrefix(GumpExpressionParser.SelfAddSubPrefixContext ctx) {
        String op = ctx.prefix.getText();
        String expr = visit(ctx.expression());
        return op + expr;
    }

    @Override
    public String visitAddSub(GumpExpressionParser.AddSubContext ctx) {
        String left = visit(ctx.expression(0));
        String right = visit(ctx.expression(1));
        String op = ctx.bop.getText();

        if (Objects.equals(op, "+")) {
            return ADD(left, right);
        }

        if (Objects.equals(op, "-")) {
            return SUB(left, right);
        }
        return left + op + right;
    }

    @Override
    public String visitMulDivMod(GumpExpressionParser.MulDivModContext ctx) {
        String left = visit(ctx.expression(0));
        String right = visit(ctx.expression(1));
        String op = ctx.bop.getText();

        if (Objects.equals(op, "*")) {
            return MUL(left, right);
        }

        if (Objects.equals(op, "/")) {
            return DIV(left, right);
        }

        if (Objects.equals(op, "%")) {
            return MOD(left, right);
        }
        return left + op + right;
    }

    @Override
    public String visitBitXOR(GumpExpressionParser.BitXORContext ctx) {
        String left = visit(ctx.expression(0));
        String right = visit(ctx.expression(1));
        String op = ctx.bop.getText();
        return left + op + right;
    }

    @Override
    public String visitEqual(GumpExpressionParser.EqualContext ctx) {
        String left = visit(ctx.expression(0));
        String right = visit(ctx.expression(1));
        String op = ctx.bop.getText();

        if (Objects.equals(op, "==")) {
            return EQ(left, right);
        }

        if (Objects.equals(op, "!=")) {
            return NE(left, right);
        }

        return left + op + right;
    }

    @Override
    public String visitBitAnd(GumpExpressionParser.BitAndContext ctx) {
        String left = visit(ctx.expression(0));
        String right = visit(ctx.expression(1));
        String op = ctx.bop.getText();
        return left + op + right;
    }

    @Override
    public String visitTernaryOperator(GumpExpressionParser.TernaryOperatorContext ctx) {
        String judge = visit(ctx.expression(0));
        String trueValue = visit(ctx.expression(1));
        String falseValue = visit(ctx.expression(2));
        return String.format("%s ? %s : %s", judge, trueValue, falseValue);
    }

    @Override
    public String visitCompare(GumpExpressionParser.CompareContext ctx) {
        String left = visit(ctx.expression(0));
        String right = visit(ctx.expression(1));
        String op = ctx.bop.getText();

        if (Objects.equals(op, ">")) {
            return GT(left, right);
        }

        if (Objects.equals(op, ">=")) {
            return GE(left, right);
        }

        if (Objects.equals(op, "<")) {
            return LT(left, right);
        }

        if (Objects.equals(op, "<=")) {
            return LE(left, right);
        }

        return left + op + right;
    }

    @Override
    public String visitLogicAnd(GumpExpressionParser.LogicAndContext ctx) {
        String left = visit(ctx.expression(0));
        String right = visit(ctx.expression(1));
        String op = ctx.bop.getText();

        if (Objects.equals(op, "&&")) {
            return AND(left, right);
        }
        return left + op + right;
    }

    @Override
    public String visitSelfAddSubSuffix(GumpExpressionParser.SelfAddSubSuffixContext ctx) {
        String op = ctx.postfix.getText();
        String expr = visit(ctx.expression());
        return expr + op;
    }

    @Override
    public String visitMethodCallExpr(GumpExpressionParser.MethodCallExprContext ctx) {
        return super.visitMethodCallExpr(ctx);
    }

    @Override
    public String visitPointerChain(GumpExpressionParser.PointerChainContext ctx) {
        ParseTree exprParseTree = ctx.expression();
        ParseTree identifierParseTree = ctx.identifier();

        return CHILD(visit(exprParseTree), identifierParseTree.getText());
    }

    @Override
    public String visitArrayPointer(GumpExpressionParser.ArrayPointerContext ctx) {
        String arrayPointer = visit(ctx.expression(0));
        String index = visit(ctx.expression(1));
        return ARRAY_POINT(arrayPointer, index);
    }


}
