// Generated from /Users/shengjunhui/Code/org_project/gump/gump-engine-runtime/gump-flow/gump-flow-poet/src/main/resources/GumpExpressionParser.g4 by ANTLR 4.9.1

    package org.junhui.gump.flow.poet.compiler.expression.grammar;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GumpExpressionParser}.
 */
public interface GumpExpressionParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GumpExpressionParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(GumpExpressionParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link GumpExpressionParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(GumpExpressionParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link GumpExpressionParser#emptyObject}.
	 * @param ctx the parse tree
	 */
	void enterEmptyObject(GumpExpressionParser.EmptyObjectContext ctx);
	/**
	 * Exit a parse tree produced by {@link GumpExpressionParser#emptyObject}.
	 * @param ctx the parse tree
	 */
	void exitEmptyObject(GumpExpressionParser.EmptyObjectContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BracketsExpr}
	 * labeled alternative in {@link GumpExpressionParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterBracketsExpr(GumpExpressionParser.BracketsExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BracketsExpr}
	 * labeled alternative in {@link GumpExpressionParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitBracketsExpr(GumpExpressionParser.BracketsExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IdentifierExpr}
	 * labeled alternative in {@link GumpExpressionParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierExpr(GumpExpressionParser.IdentifierExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IdentifierExpr}
	 * labeled alternative in {@link GumpExpressionParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierExpr(GumpExpressionParser.IdentifierExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LiteralExpr}
	 * labeled alternative in {@link GumpExpressionParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterLiteralExpr(GumpExpressionParser.LiteralExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LiteralExpr}
	 * labeled alternative in {@link GumpExpressionParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitLiteralExpr(GumpExpressionParser.LiteralExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link GumpExpressionParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList(GumpExpressionParser.ExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link GumpExpressionParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList(GumpExpressionParser.ExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link GumpExpressionParser#methodCall}.
	 * @param ctx the parse tree
	 */
	void enterMethodCall(GumpExpressionParser.MethodCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link GumpExpressionParser#methodCall}.
	 * @param ctx the parse tree
	 */
	void exitMethodCall(GumpExpressionParser.MethodCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link GumpExpressionParser#arrayLiteral}.
	 * @param ctx the parse tree
	 */
	void enterArrayLiteral(GumpExpressionParser.ArrayLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link GumpExpressionParser#arrayLiteral}.
	 * @param ctx the parse tree
	 */
	void exitArrayLiteral(GumpExpressionParser.ArrayLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link GumpExpressionParser#elementList}.
	 * @param ctx the parse tree
	 */
	void enterElementList(GumpExpressionParser.ElementListContext ctx);
	/**
	 * Exit a parse tree produced by {@link GumpExpressionParser#elementList}.
	 * @param ctx the parse tree
	 */
	void exitElementList(GumpExpressionParser.ElementListContext ctx);
	/**
	 * Enter a parse tree produced by {@link GumpExpressionParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(GumpExpressionParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link GumpExpressionParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(GumpExpressionParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link GumpExpressionParser#integerLiteral}.
	 * @param ctx the parse tree
	 */
	void enterIntegerLiteral(GumpExpressionParser.IntegerLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link GumpExpressionParser#integerLiteral}.
	 * @param ctx the parse tree
	 */
	void exitIntegerLiteral(GumpExpressionParser.IntegerLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link GumpExpressionParser#floatLiteral}.
	 * @param ctx the parse tree
	 */
	void enterFloatLiteral(GumpExpressionParser.FloatLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link GumpExpressionParser#floatLiteral}.
	 * @param ctx the parse tree
	 */
	void exitFloatLiteral(GumpExpressionParser.FloatLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LogicOr}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogicOr(GumpExpressionParser.LogicOrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LogicOr}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogicOr(GumpExpressionParser.LogicOrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrimaryPointer}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryPointer(GumpExpressionParser.PrimaryPointerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrimaryPointer}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryPointer(GumpExpressionParser.PrimaryPointerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AntiExpr}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAntiExpr(GumpExpressionParser.AntiExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AntiExpr}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAntiExpr(GumpExpressionParser.AntiExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BitOr}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBitOr(GumpExpressionParser.BitOrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BitOr}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBitOr(GumpExpressionParser.BitOrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SelfAddSubPrefix}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSelfAddSubPrefix(GumpExpressionParser.SelfAddSubPrefixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SelfAddSubPrefix}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSelfAddSubPrefix(GumpExpressionParser.SelfAddSubPrefixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAddSub(GumpExpressionParser.AddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAddSub(GumpExpressionParser.AddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MulDivMod}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMulDivMod(GumpExpressionParser.MulDivModContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MulDivMod}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMulDivMod(GumpExpressionParser.MulDivModContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BitXOR}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBitXOR(GumpExpressionParser.BitXORContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BitXOR}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBitXOR(GumpExpressionParser.BitXORContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Equal}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterEqual(GumpExpressionParser.EqualContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Equal}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitEqual(GumpExpressionParser.EqualContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArrayLiteralExpr}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArrayLiteralExpr(GumpExpressionParser.ArrayLiteralExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayLiteralExpr}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArrayLiteralExpr(GumpExpressionParser.ArrayLiteralExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BitAnd}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBitAnd(GumpExpressionParser.BitAndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BitAnd}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBitAnd(GumpExpressionParser.BitAndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TernaryOperator}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterTernaryOperator(GumpExpressionParser.TernaryOperatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TernaryOperator}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitTernaryOperator(GumpExpressionParser.TernaryOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Compare}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCompare(GumpExpressionParser.CompareContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Compare}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCompare(GumpExpressionParser.CompareContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LogicAnd}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogicAnd(GumpExpressionParser.LogicAndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LogicAnd}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogicAnd(GumpExpressionParser.LogicAndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SelfAddSubSuffix}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSelfAddSubSuffix(GumpExpressionParser.SelfAddSubSuffixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SelfAddSubSuffix}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSelfAddSubSuffix(GumpExpressionParser.SelfAddSubSuffixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MethodCallExpr}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMethodCallExpr(GumpExpressionParser.MethodCallExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MethodCallExpr}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMethodCallExpr(GumpExpressionParser.MethodCallExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EmptyObjectExpr}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterEmptyObjectExpr(GumpExpressionParser.EmptyObjectExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EmptyObjectExpr}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitEmptyObjectExpr(GumpExpressionParser.EmptyObjectExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PointerChain}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPointerChain(GumpExpressionParser.PointerChainContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PointerChain}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPointerChain(GumpExpressionParser.PointerChainContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArrayPointer}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArrayPointer(GumpExpressionParser.ArrayPointerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayPointer}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArrayPointer(GumpExpressionParser.ArrayPointerContext ctx);
}