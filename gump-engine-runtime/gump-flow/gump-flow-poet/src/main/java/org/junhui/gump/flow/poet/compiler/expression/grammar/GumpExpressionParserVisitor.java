// Generated from /Users/shengjunhui/Code/org_project/gump/gump-engine-runtime/gump-flow/gump-flow-poet/src/main/resources/GumpExpressionParser.g4 by ANTLR 4.9.1

    package org.junhui.gump.flow.poet.compiler.expression.grammar;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GumpExpressionParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GumpExpressionParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GumpExpressionParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(GumpExpressionParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link GumpExpressionParser#emptyObject}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyObject(GumpExpressionParser.EmptyObjectContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BracketsExpr}
	 * labeled alternative in {@link GumpExpressionParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBracketsExpr(GumpExpressionParser.BracketsExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IdentifierExpr}
	 * labeled alternative in {@link GumpExpressionParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierExpr(GumpExpressionParser.IdentifierExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LiteralExpr}
	 * labeled alternative in {@link GumpExpressionParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralExpr(GumpExpressionParser.LiteralExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link GumpExpressionParser#expressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionList(GumpExpressionParser.ExpressionListContext ctx);
	/**
	 * Visit a parse tree produced by {@link GumpExpressionParser#methodCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodCall(GumpExpressionParser.MethodCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link GumpExpressionParser#arrayLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayLiteral(GumpExpressionParser.ArrayLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link GumpExpressionParser#elementList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementList(GumpExpressionParser.ElementListContext ctx);
	/**
	 * Visit a parse tree produced by {@link GumpExpressionParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(GumpExpressionParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link GumpExpressionParser#integerLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerLiteral(GumpExpressionParser.IntegerLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link GumpExpressionParser#floatLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatLiteral(GumpExpressionParser.FloatLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LogicOr}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicOr(GumpExpressionParser.LogicOrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrimaryPointer}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryPointer(GumpExpressionParser.PrimaryPointerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AntiExpr}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAntiExpr(GumpExpressionParser.AntiExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BitOr}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitOr(GumpExpressionParser.BitOrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SelfAddSubPrefix}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelfAddSubPrefix(GumpExpressionParser.SelfAddSubPrefixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSub(GumpExpressionParser.AddSubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MulDivMod}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDivMod(GumpExpressionParser.MulDivModContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BitXOR}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitXOR(GumpExpressionParser.BitXORContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Equal}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqual(GumpExpressionParser.EqualContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArrayLiteralExpr}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayLiteralExpr(GumpExpressionParser.ArrayLiteralExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BitAnd}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitAnd(GumpExpressionParser.BitAndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TernaryOperator}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTernaryOperator(GumpExpressionParser.TernaryOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Compare}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompare(GumpExpressionParser.CompareContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LogicAnd}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicAnd(GumpExpressionParser.LogicAndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SelfAddSubSuffix}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelfAddSubSuffix(GumpExpressionParser.SelfAddSubSuffixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MethodCallExpr}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodCallExpr(GumpExpressionParser.MethodCallExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EmptyObjectExpr}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyObjectExpr(GumpExpressionParser.EmptyObjectExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PointerChain}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPointerChain(GumpExpressionParser.PointerChainContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArrayPointer}
	 * labeled alternative in {@link GumpExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayPointer(GumpExpressionParser.ArrayPointerContext ctx);
}