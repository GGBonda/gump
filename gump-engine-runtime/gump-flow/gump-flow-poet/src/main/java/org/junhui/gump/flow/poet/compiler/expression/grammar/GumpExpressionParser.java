// Generated from /Users/shengjunhui/Code/org_project/gump/gump-engine-runtime/gump-flow/gump-flow-poet/src/main/resources/GumpExpressionParser.g4 by ANTLR 4.9.1

    package org.junhui.gump.flow.poet.compiler.expression.grammar;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GumpExpressionParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		CHAR_LITERAL=1, STRING_LITERAL=2, NULL_LITERAL=3, BOOL_LITERAL=4, EMPTY_OBJECT=5, 
		DECIMAL_LITERAL=6, HEX_LITERAL=7, OCT_LITERAL=8, BINARY_LITERAL=9, FLOAT_LITERAL=10, 
		HEX_FLOAT_LITERAL=11, LPAREN=12, RPAREN=13, LBRACE=14, RBRACE=15, LBRACK=16, 
		RBRACK=17, SEMI=18, COMMA=19, DOT=20, ASSIGN=21, GT=22, LT=23, BANG=24, 
		TILDE=25, QUESTION=26, COLON=27, EQUAL=28, LE=29, GE=30, NOTEQUAL=31, 
		AND=32, OR=33, INC=34, DEC=35, ADD=36, SUB=37, MUL=38, DIV=39, BITAND=40, 
		BITOR=41, CARET=42, MOD=43, IDENTIFIER=44, WS=45, COMMENT=46, LINE_COMMENT=47;
	public static final int
		RULE_identifier = 0, RULE_emptyObject = 1, RULE_primary = 2, RULE_expressionList = 3, 
		RULE_methodCall = 4, RULE_arrayLiteral = 5, RULE_elementList = 6, RULE_literal = 7, 
		RULE_integerLiteral = 8, RULE_floatLiteral = 9, RULE_expression = 10;
	private static String[] makeRuleNames() {
		return new String[] {
			"identifier", "emptyObject", "primary", "expressionList", "methodCall", 
			"arrayLiteral", "elementList", "literal", "integerLiteral", "floatLiteral", 
			"expression"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, "'null'", null, "'{}'", null, null, null, null, null, 
			null, "'('", "')'", "'{'", "'}'", "'['", "']'", "';'", "','", "'.'", 
			"'='", "'>'", "'<'", "'!'", "'~'", "'?'", "':'", "'=='", "'<='", "'>='", 
			"'!='", "'&&'", "'||'", "'++'", "'--'", "'+'", "'-'", "'*'", "'/'", "'&'", 
			"'|'", "'^'", "'%'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "CHAR_LITERAL", "STRING_LITERAL", "NULL_LITERAL", "BOOL_LITERAL", 
			"EMPTY_OBJECT", "DECIMAL_LITERAL", "HEX_LITERAL", "OCT_LITERAL", "BINARY_LITERAL", 
			"FLOAT_LITERAL", "HEX_FLOAT_LITERAL", "LPAREN", "RPAREN", "LBRACE", "RBRACE", 
			"LBRACK", "RBRACK", "SEMI", "COMMA", "DOT", "ASSIGN", "GT", "LT", "BANG", 
			"TILDE", "QUESTION", "COLON", "EQUAL", "LE", "GE", "NOTEQUAL", "AND", 
			"OR", "INC", "DEC", "ADD", "SUB", "MUL", "DIV", "BITAND", "BITOR", "CARET", 
			"MOD", "IDENTIFIER", "WS", "COMMENT", "LINE_COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "GumpExpressionParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public GumpExpressionParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(GumpExpressionParser.IDENTIFIER, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).exitIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GumpExpressionParserVisitor ) return ((GumpExpressionParserVisitor<? extends T>)visitor).visitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(22);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EmptyObjectContext extends ParserRuleContext {
		public TerminalNode EMPTY_OBJECT() { return getToken(GumpExpressionParser.EMPTY_OBJECT, 0); }
		public EmptyObjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_emptyObject; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).enterEmptyObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).exitEmptyObject(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GumpExpressionParserVisitor ) return ((GumpExpressionParserVisitor<? extends T>)visitor).visitEmptyObject(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EmptyObjectContext emptyObject() throws RecognitionException {
		EmptyObjectContext _localctx = new EmptyObjectContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_emptyObject);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			match(EMPTY_OBJECT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimaryContext extends ParserRuleContext {
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
	 
		public PrimaryContext() { }
		public void copyFrom(PrimaryContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class IdentifierExprContext extends PrimaryContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public IdentifierExprContext(PrimaryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).enterIdentifierExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).exitIdentifierExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GumpExpressionParserVisitor ) return ((GumpExpressionParserVisitor<? extends T>)visitor).visitIdentifierExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LiteralExprContext extends PrimaryContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public LiteralExprContext(PrimaryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).enterLiteralExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).exitLiteralExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GumpExpressionParserVisitor ) return ((GumpExpressionParserVisitor<? extends T>)visitor).visitLiteralExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BracketsExprContext extends PrimaryContext {
		public TerminalNode LPAREN() { return getToken(GumpExpressionParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(GumpExpressionParser.RPAREN, 0); }
		public BracketsExprContext(PrimaryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).enterBracketsExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).exitBracketsExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GumpExpressionParserVisitor ) return ((GumpExpressionParserVisitor<? extends T>)visitor).visitBracketsExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_primary);
		try {
			setState(32);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAREN:
				_localctx = new BracketsExprContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(26);
				match(LPAREN);
				setState(27);
				expression(0);
				setState(28);
				match(RPAREN);
				}
				break;
			case IDENTIFIER:
				_localctx = new IdentifierExprContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(30);
				identifier();
				}
				break;
			case STRING_LITERAL:
			case NULL_LITERAL:
			case BOOL_LITERAL:
			case DECIMAL_LITERAL:
			case HEX_LITERAL:
			case OCT_LITERAL:
			case BINARY_LITERAL:
			case FLOAT_LITERAL:
			case HEX_FLOAT_LITERAL:
				_localctx = new LiteralExprContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(31);
				literal();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(GumpExpressionParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(GumpExpressionParser.COMMA, i);
		}
		public ExpressionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).enterExpressionList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).exitExpressionList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GumpExpressionParserVisitor ) return ((GumpExpressionParserVisitor<? extends T>)visitor).visitExpressionList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_expressionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			expression(0);
			setState(39);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(35);
				match(COMMA);
				setState(36);
				expression(0);
				}
				}
				setState(41);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodCallContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(GumpExpressionParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(GumpExpressionParser.RPAREN, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public MethodCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).enterMethodCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).exitMethodCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GumpExpressionParserVisitor ) return ((GumpExpressionParserVisitor<? extends T>)visitor).visitMethodCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodCallContext methodCall() throws RecognitionException {
		MethodCallContext _localctx = new MethodCallContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_methodCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			identifier();
			setState(43);
			match(LPAREN);
			setState(45);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING_LITERAL) | (1L << NULL_LITERAL) | (1L << BOOL_LITERAL) | (1L << EMPTY_OBJECT) | (1L << DECIMAL_LITERAL) | (1L << HEX_LITERAL) | (1L << OCT_LITERAL) | (1L << BINARY_LITERAL) | (1L << FLOAT_LITERAL) | (1L << HEX_FLOAT_LITERAL) | (1L << LPAREN) | (1L << LBRACK) | (1L << BANG) | (1L << TILDE) | (1L << INC) | (1L << DEC) | (1L << ADD) | (1L << SUB) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(44);
				expressionList();
				}
			}

			setState(47);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayLiteralContext extends ParserRuleContext {
		public TerminalNode LBRACK() { return getToken(GumpExpressionParser.LBRACK, 0); }
		public ElementListContext elementList() {
			return getRuleContext(ElementListContext.class,0);
		}
		public TerminalNode RBRACK() { return getToken(GumpExpressionParser.RBRACK, 0); }
		public ArrayLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).enterArrayLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).exitArrayLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GumpExpressionParserVisitor ) return ((GumpExpressionParserVisitor<? extends T>)visitor).visitArrayLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayLiteralContext arrayLiteral() throws RecognitionException {
		ArrayLiteralContext _localctx = new ArrayLiteralContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_arrayLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(49);
			match(LBRACK);
			setState(50);
			elementList();
			setState(51);
			match(RBRACK);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementListContext extends ParserRuleContext {
		public List<TerminalNode> COMMA() { return getTokens(GumpExpressionParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(GumpExpressionParser.COMMA, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ElementListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).enterElementList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).exitElementList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GumpExpressionParserVisitor ) return ((GumpExpressionParserVisitor<? extends T>)visitor).visitElementList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementListContext elementList() throws RecognitionException {
		ElementListContext _localctx = new ElementListContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_elementList);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(53);
					match(COMMA);
					}
					} 
				}
				setState(58);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			setState(60);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING_LITERAL) | (1L << NULL_LITERAL) | (1L << BOOL_LITERAL) | (1L << EMPTY_OBJECT) | (1L << DECIMAL_LITERAL) | (1L << HEX_LITERAL) | (1L << OCT_LITERAL) | (1L << BINARY_LITERAL) | (1L << FLOAT_LITERAL) | (1L << HEX_FLOAT_LITERAL) | (1L << LPAREN) | (1L << LBRACK) | (1L << BANG) | (1L << TILDE) | (1L << INC) | (1L << DEC) | (1L << ADD) | (1L << SUB) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(59);
				expression(0);
				}
			}

			setState(70);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(63); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(62);
						match(COMMA);
						}
						}
						setState(65); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==COMMA );
					setState(67);
					expression(0);
					}
					} 
				}
				setState(72);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			setState(76);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(73);
				match(COMMA);
				}
				}
				setState(78);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public IntegerLiteralContext integerLiteral() {
			return getRuleContext(IntegerLiteralContext.class,0);
		}
		public FloatLiteralContext floatLiteral() {
			return getRuleContext(FloatLiteralContext.class,0);
		}
		public TerminalNode STRING_LITERAL() { return getToken(GumpExpressionParser.STRING_LITERAL, 0); }
		public TerminalNode BOOL_LITERAL() { return getToken(GumpExpressionParser.BOOL_LITERAL, 0); }
		public TerminalNode NULL_LITERAL() { return getToken(GumpExpressionParser.NULL_LITERAL, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GumpExpressionParserVisitor ) return ((GumpExpressionParserVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_literal);
		try {
			setState(84);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DECIMAL_LITERAL:
			case HEX_LITERAL:
			case OCT_LITERAL:
			case BINARY_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(79);
				integerLiteral();
				}
				break;
			case FLOAT_LITERAL:
			case HEX_FLOAT_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(80);
				floatLiteral();
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(81);
				match(STRING_LITERAL);
				}
				break;
			case BOOL_LITERAL:
				enterOuterAlt(_localctx, 4);
				{
				setState(82);
				match(BOOL_LITERAL);
				}
				break;
			case NULL_LITERAL:
				enterOuterAlt(_localctx, 5);
				{
				setState(83);
				match(NULL_LITERAL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntegerLiteralContext extends ParserRuleContext {
		public TerminalNode DECIMAL_LITERAL() { return getToken(GumpExpressionParser.DECIMAL_LITERAL, 0); }
		public TerminalNode HEX_LITERAL() { return getToken(GumpExpressionParser.HEX_LITERAL, 0); }
		public TerminalNode OCT_LITERAL() { return getToken(GumpExpressionParser.OCT_LITERAL, 0); }
		public TerminalNode BINARY_LITERAL() { return getToken(GumpExpressionParser.BINARY_LITERAL, 0); }
		public IntegerLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integerLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).enterIntegerLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).exitIntegerLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GumpExpressionParserVisitor ) return ((GumpExpressionParserVisitor<? extends T>)visitor).visitIntegerLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntegerLiteralContext integerLiteral() throws RecognitionException {
		IntegerLiteralContext _localctx = new IntegerLiteralContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_integerLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DECIMAL_LITERAL) | (1L << HEX_LITERAL) | (1L << OCT_LITERAL) | (1L << BINARY_LITERAL))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FloatLiteralContext extends ParserRuleContext {
		public TerminalNode FLOAT_LITERAL() { return getToken(GumpExpressionParser.FLOAT_LITERAL, 0); }
		public TerminalNode HEX_FLOAT_LITERAL() { return getToken(GumpExpressionParser.HEX_FLOAT_LITERAL, 0); }
		public FloatLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_floatLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).enterFloatLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).exitFloatLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GumpExpressionParserVisitor ) return ((GumpExpressionParserVisitor<? extends T>)visitor).visitFloatLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FloatLiteralContext floatLiteral() throws RecognitionException {
		FloatLiteralContext _localctx = new FloatLiteralContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_floatLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			_la = _input.LA(1);
			if ( !(_la==FLOAT_LITERAL || _la==HEX_FLOAT_LITERAL) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class LogicOrContext extends ExpressionContext {
		public Token bop;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode OR() { return getToken(GumpExpressionParser.OR, 0); }
		public LogicOrContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).enterLogicOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).exitLogicOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GumpExpressionParserVisitor ) return ((GumpExpressionParserVisitor<? extends T>)visitor).visitLogicOr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrimaryPointerContext extends ExpressionContext {
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public PrimaryPointerContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).enterPrimaryPointer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).exitPrimaryPointer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GumpExpressionParserVisitor ) return ((GumpExpressionParserVisitor<? extends T>)visitor).visitPrimaryPointer(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AntiExprContext extends ExpressionContext {
		public Token prefix;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode TILDE() { return getToken(GumpExpressionParser.TILDE, 0); }
		public TerminalNode BANG() { return getToken(GumpExpressionParser.BANG, 0); }
		public AntiExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).enterAntiExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).exitAntiExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GumpExpressionParserVisitor ) return ((GumpExpressionParserVisitor<? extends T>)visitor).visitAntiExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BitOrContext extends ExpressionContext {
		public Token bop;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode BITOR() { return getToken(GumpExpressionParser.BITOR, 0); }
		public BitOrContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).enterBitOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).exitBitOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GumpExpressionParserVisitor ) return ((GumpExpressionParserVisitor<? extends T>)visitor).visitBitOr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SelfAddSubPrefixContext extends ExpressionContext {
		public Token prefix;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode ADD() { return getToken(GumpExpressionParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(GumpExpressionParser.SUB, 0); }
		public TerminalNode INC() { return getToken(GumpExpressionParser.INC, 0); }
		public TerminalNode DEC() { return getToken(GumpExpressionParser.DEC, 0); }
		public SelfAddSubPrefixContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).enterSelfAddSubPrefix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).exitSelfAddSubPrefix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GumpExpressionParserVisitor ) return ((GumpExpressionParserVisitor<? extends T>)visitor).visitSelfAddSubPrefix(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AddSubContext extends ExpressionContext {
		public Token bop;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode ADD() { return getToken(GumpExpressionParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(GumpExpressionParser.SUB, 0); }
		public AddSubContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).enterAddSub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).exitAddSub(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GumpExpressionParserVisitor ) return ((GumpExpressionParserVisitor<? extends T>)visitor).visitAddSub(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MulDivModContext extends ExpressionContext {
		public Token bop;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode MUL() { return getToken(GumpExpressionParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(GumpExpressionParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(GumpExpressionParser.MOD, 0); }
		public MulDivModContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).enterMulDivMod(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).exitMulDivMod(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GumpExpressionParserVisitor ) return ((GumpExpressionParserVisitor<? extends T>)visitor).visitMulDivMod(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BitXORContext extends ExpressionContext {
		public Token bop;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode CARET() { return getToken(GumpExpressionParser.CARET, 0); }
		public BitXORContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).enterBitXOR(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).exitBitXOR(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GumpExpressionParserVisitor ) return ((GumpExpressionParserVisitor<? extends T>)visitor).visitBitXOR(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EqualContext extends ExpressionContext {
		public Token bop;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode EQUAL() { return getToken(GumpExpressionParser.EQUAL, 0); }
		public TerminalNode NOTEQUAL() { return getToken(GumpExpressionParser.NOTEQUAL, 0); }
		public EqualContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).enterEqual(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).exitEqual(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GumpExpressionParserVisitor ) return ((GumpExpressionParserVisitor<? extends T>)visitor).visitEqual(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayLiteralExprContext extends ExpressionContext {
		public ArrayLiteralContext arrayLiteral() {
			return getRuleContext(ArrayLiteralContext.class,0);
		}
		public ArrayLiteralExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).enterArrayLiteralExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).exitArrayLiteralExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GumpExpressionParserVisitor ) return ((GumpExpressionParserVisitor<? extends T>)visitor).visitArrayLiteralExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BitAndContext extends ExpressionContext {
		public Token bop;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode BITAND() { return getToken(GumpExpressionParser.BITAND, 0); }
		public BitAndContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).enterBitAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).exitBitAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GumpExpressionParserVisitor ) return ((GumpExpressionParserVisitor<? extends T>)visitor).visitBitAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TernaryOperatorContext extends ExpressionContext {
		public Token bop;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode COLON() { return getToken(GumpExpressionParser.COLON, 0); }
		public TerminalNode QUESTION() { return getToken(GumpExpressionParser.QUESTION, 0); }
		public TernaryOperatorContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).enterTernaryOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).exitTernaryOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GumpExpressionParserVisitor ) return ((GumpExpressionParserVisitor<? extends T>)visitor).visitTernaryOperator(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CompareContext extends ExpressionContext {
		public Token bop;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode LE() { return getToken(GumpExpressionParser.LE, 0); }
		public TerminalNode GE() { return getToken(GumpExpressionParser.GE, 0); }
		public TerminalNode GT() { return getToken(GumpExpressionParser.GT, 0); }
		public TerminalNode LT() { return getToken(GumpExpressionParser.LT, 0); }
		public CompareContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).enterCompare(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).exitCompare(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GumpExpressionParserVisitor ) return ((GumpExpressionParserVisitor<? extends T>)visitor).visitCompare(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LogicAndContext extends ExpressionContext {
		public Token bop;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode AND() { return getToken(GumpExpressionParser.AND, 0); }
		public LogicAndContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).enterLogicAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).exitLogicAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GumpExpressionParserVisitor ) return ((GumpExpressionParserVisitor<? extends T>)visitor).visitLogicAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SelfAddSubSuffixContext extends ExpressionContext {
		public Token postfix;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode INC() { return getToken(GumpExpressionParser.INC, 0); }
		public TerminalNode DEC() { return getToken(GumpExpressionParser.DEC, 0); }
		public SelfAddSubSuffixContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).enterSelfAddSubSuffix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).exitSelfAddSubSuffix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GumpExpressionParserVisitor ) return ((GumpExpressionParserVisitor<? extends T>)visitor).visitSelfAddSubSuffix(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MethodCallExprContext extends ExpressionContext {
		public MethodCallContext methodCall() {
			return getRuleContext(MethodCallContext.class,0);
		}
		public MethodCallExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).enterMethodCallExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).exitMethodCallExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GumpExpressionParserVisitor ) return ((GumpExpressionParserVisitor<? extends T>)visitor).visitMethodCallExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EmptyObjectExprContext extends ExpressionContext {
		public EmptyObjectContext emptyObject() {
			return getRuleContext(EmptyObjectContext.class,0);
		}
		public EmptyObjectExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).enterEmptyObjectExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).exitEmptyObjectExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GumpExpressionParserVisitor ) return ((GumpExpressionParserVisitor<? extends T>)visitor).visitEmptyObjectExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PointerChainContext extends ExpressionContext {
		public Token bop;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode DOT() { return getToken(GumpExpressionParser.DOT, 0); }
		public PointerChainContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).enterPointerChain(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).exitPointerChain(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GumpExpressionParserVisitor ) return ((GumpExpressionParserVisitor<? extends T>)visitor).visitPointerChain(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayPointerContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode LBRACK() { return getToken(GumpExpressionParser.LBRACK, 0); }
		public TerminalNode RBRACK() { return getToken(GumpExpressionParser.RBRACK, 0); }
		public ArrayPointerContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).enterArrayPointer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GumpExpressionParserListener ) ((GumpExpressionParserListener)listener).exitArrayPointer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GumpExpressionParserVisitor ) return ((GumpExpressionParserVisitor<? extends T>)visitor).visitArrayPointer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				_localctx = new PrimaryPointerContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(91);
				primary();
				}
				break;
			case 2:
				{
				_localctx = new MethodCallExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(92);
				methodCall();
				}
				break;
			case 3:
				{
				_localctx = new EmptyObjectExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(93);
				emptyObject();
				}
				break;
			case 4:
				{
				_localctx = new ArrayLiteralExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(94);
				arrayLiteral();
				}
				break;
			case 5:
				{
				_localctx = new SelfAddSubPrefixContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(95);
				((SelfAddSubPrefixContext)_localctx).prefix = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INC) | (1L << DEC) | (1L << ADD) | (1L << SUB))) != 0)) ) {
					((SelfAddSubPrefixContext)_localctx).prefix = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(96);
				expression(12);
				}
				break;
			case 6:
				{
				_localctx = new AntiExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(97);
				((AntiExprContext)_localctx).prefix = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==BANG || _la==TILDE) ) {
					((AntiExprContext)_localctx).prefix = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(98);
				expression(11);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(146);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(144);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
					case 1:
						{
						_localctx = new MulDivModContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(101);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(102);
						((MulDivModContext)_localctx).bop = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << MOD))) != 0)) ) {
							((MulDivModContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(103);
						expression(11);
						}
						break;
					case 2:
						{
						_localctx = new AddSubContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(104);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(105);
						((AddSubContext)_localctx).bop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==SUB) ) {
							((AddSubContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(106);
						expression(10);
						}
						break;
					case 3:
						{
						_localctx = new CompareContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(107);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(108);
						((CompareContext)_localctx).bop = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GT) | (1L << LT) | (1L << LE) | (1L << GE))) != 0)) ) {
							((CompareContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(109);
						expression(9);
						}
						break;
					case 4:
						{
						_localctx = new EqualContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(110);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(111);
						((EqualContext)_localctx).bop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==EQUAL || _la==NOTEQUAL) ) {
							((EqualContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(112);
						expression(8);
						}
						break;
					case 5:
						{
						_localctx = new BitAndContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(113);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(114);
						((BitAndContext)_localctx).bop = match(BITAND);
						setState(115);
						expression(7);
						}
						break;
					case 6:
						{
						_localctx = new BitXORContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(116);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(117);
						((BitXORContext)_localctx).bop = match(CARET);
						setState(118);
						expression(6);
						}
						break;
					case 7:
						{
						_localctx = new BitOrContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(119);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(120);
						((BitOrContext)_localctx).bop = match(BITOR);
						setState(121);
						expression(5);
						}
						break;
					case 8:
						{
						_localctx = new LogicAndContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(122);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(123);
						((LogicAndContext)_localctx).bop = match(AND);
						setState(124);
						expression(4);
						}
						break;
					case 9:
						{
						_localctx = new LogicOrContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(125);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(126);
						((LogicOrContext)_localctx).bop = match(OR);
						setState(127);
						expression(3);
						}
						break;
					case 10:
						{
						_localctx = new TernaryOperatorContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(128);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(129);
						((TernaryOperatorContext)_localctx).bop = match(QUESTION);
						setState(130);
						expression(0);
						setState(131);
						match(COLON);
						setState(132);
						expression(1);
						}
						break;
					case 11:
						{
						_localctx = new PointerChainContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(134);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(135);
						((PointerChainContext)_localctx).bop = match(DOT);
						setState(136);
						identifier();
						}
						break;
					case 12:
						{
						_localctx = new ArrayPointerContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(137);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(138);
						match(LBRACK);
						setState(139);
						expression(0);
						setState(140);
						match(RBRACK);
						}
						break;
					case 13:
						{
						_localctx = new SelfAddSubSuffixContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(142);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(143);
						((SelfAddSubSuffixContext)_localctx).postfix = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==INC || _la==DEC) ) {
							((SelfAddSubSuffixContext)_localctx).postfix = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					}
					} 
				}
				setState(148);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 10:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 10);
		case 1:
			return precpred(_ctx, 9);
		case 2:
			return precpred(_ctx, 8);
		case 3:
			return precpred(_ctx, 7);
		case 4:
			return precpred(_ctx, 6);
		case 5:
			return precpred(_ctx, 5);
		case 6:
			return precpred(_ctx, 4);
		case 7:
			return precpred(_ctx, 3);
		case 8:
			return precpred(_ctx, 2);
		case 9:
			return precpred(_ctx, 1);
		case 10:
			return precpred(_ctx, 18);
		case 11:
			return precpred(_ctx, 17);
		case 12:
			return precpred(_ctx, 13);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\61\u0098\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\5\4#\n\4\3\5\3\5"+
		"\3\5\7\5(\n\5\f\5\16\5+\13\5\3\6\3\6\3\6\5\6\60\n\6\3\6\3\6\3\7\3\7\3"+
		"\7\3\7\3\b\7\b9\n\b\f\b\16\b<\13\b\3\b\5\b?\n\b\3\b\6\bB\n\b\r\b\16\b"+
		"C\3\b\7\bG\n\b\f\b\16\bJ\13\b\3\b\7\bM\n\b\f\b\16\bP\13\b\3\t\3\t\3\t"+
		"\3\t\3\t\5\tW\n\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\5\ff\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u0093\n\f\f\f\16\f\u0096"+
		"\13\f\3\f\2\3\26\r\2\4\6\b\n\f\16\20\22\24\26\2\13\3\2\b\13\3\2\f\r\3"+
		"\2$\'\3\2\32\33\4\2()--\3\2&\'\4\2\30\31\37 \4\2\36\36!!\3\2$%\2\u00ab"+
		"\2\30\3\2\2\2\4\32\3\2\2\2\6\"\3\2\2\2\b$\3\2\2\2\n,\3\2\2\2\f\63\3\2"+
		"\2\2\16:\3\2\2\2\20V\3\2\2\2\22X\3\2\2\2\24Z\3\2\2\2\26e\3\2\2\2\30\31"+
		"\7.\2\2\31\3\3\2\2\2\32\33\7\7\2\2\33\5\3\2\2\2\34\35\7\16\2\2\35\36\5"+
		"\26\f\2\36\37\7\17\2\2\37#\3\2\2\2 #\5\2\2\2!#\5\20\t\2\"\34\3\2\2\2\""+
		" \3\2\2\2\"!\3\2\2\2#\7\3\2\2\2$)\5\26\f\2%&\7\25\2\2&(\5\26\f\2\'%\3"+
		"\2\2\2(+\3\2\2\2)\'\3\2\2\2)*\3\2\2\2*\t\3\2\2\2+)\3\2\2\2,-\5\2\2\2-"+
		"/\7\16\2\2.\60\5\b\5\2/.\3\2\2\2/\60\3\2\2\2\60\61\3\2\2\2\61\62\7\17"+
		"\2\2\62\13\3\2\2\2\63\64\7\22\2\2\64\65\5\16\b\2\65\66\7\23\2\2\66\r\3"+
		"\2\2\2\679\7\25\2\28\67\3\2\2\29<\3\2\2\2:8\3\2\2\2:;\3\2\2\2;>\3\2\2"+
		"\2<:\3\2\2\2=?\5\26\f\2>=\3\2\2\2>?\3\2\2\2?H\3\2\2\2@B\7\25\2\2A@\3\2"+
		"\2\2BC\3\2\2\2CA\3\2\2\2CD\3\2\2\2DE\3\2\2\2EG\5\26\f\2FA\3\2\2\2GJ\3"+
		"\2\2\2HF\3\2\2\2HI\3\2\2\2IN\3\2\2\2JH\3\2\2\2KM\7\25\2\2LK\3\2\2\2MP"+
		"\3\2\2\2NL\3\2\2\2NO\3\2\2\2O\17\3\2\2\2PN\3\2\2\2QW\5\22\n\2RW\5\24\13"+
		"\2SW\7\4\2\2TW\7\6\2\2UW\7\5\2\2VQ\3\2\2\2VR\3\2\2\2VS\3\2\2\2VT\3\2\2"+
		"\2VU\3\2\2\2W\21\3\2\2\2XY\t\2\2\2Y\23\3\2\2\2Z[\t\3\2\2[\25\3\2\2\2\\"+
		"]\b\f\1\2]f\5\6\4\2^f\5\n\6\2_f\5\4\3\2`f\5\f\7\2ab\t\4\2\2bf\5\26\f\16"+
		"cd\t\5\2\2df\5\26\f\re\\\3\2\2\2e^\3\2\2\2e_\3\2\2\2e`\3\2\2\2ea\3\2\2"+
		"\2ec\3\2\2\2f\u0094\3\2\2\2gh\f\f\2\2hi\t\6\2\2i\u0093\5\26\f\rjk\f\13"+
		"\2\2kl\t\7\2\2l\u0093\5\26\f\fmn\f\n\2\2no\t\b\2\2o\u0093\5\26\f\13pq"+
		"\f\t\2\2qr\t\t\2\2r\u0093\5\26\f\nst\f\b\2\2tu\7*\2\2u\u0093\5\26\f\t"+
		"vw\f\7\2\2wx\7,\2\2x\u0093\5\26\f\byz\f\6\2\2z{\7+\2\2{\u0093\5\26\f\7"+
		"|}\f\5\2\2}~\7\"\2\2~\u0093\5\26\f\6\177\u0080\f\4\2\2\u0080\u0081\7#"+
		"\2\2\u0081\u0093\5\26\f\5\u0082\u0083\f\3\2\2\u0083\u0084\7\34\2\2\u0084"+
		"\u0085\5\26\f\2\u0085\u0086\7\35\2\2\u0086\u0087\5\26\f\3\u0087\u0093"+
		"\3\2\2\2\u0088\u0089\f\24\2\2\u0089\u008a\7\26\2\2\u008a\u0093\5\2\2\2"+
		"\u008b\u008c\f\23\2\2\u008c\u008d\7\22\2\2\u008d\u008e\5\26\f\2\u008e"+
		"\u008f\7\23\2\2\u008f\u0093\3\2\2\2\u0090\u0091\f\17\2\2\u0091\u0093\t"+
		"\n\2\2\u0092g\3\2\2\2\u0092j\3\2\2\2\u0092m\3\2\2\2\u0092p\3\2\2\2\u0092"+
		"s\3\2\2\2\u0092v\3\2\2\2\u0092y\3\2\2\2\u0092|\3\2\2\2\u0092\177\3\2\2"+
		"\2\u0092\u0082\3\2\2\2\u0092\u0088\3\2\2\2\u0092\u008b\3\2\2\2\u0092\u0090"+
		"\3\2\2\2\u0093\u0096\3\2\2\2\u0094\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095"+
		"\27\3\2\2\2\u0096\u0094\3\2\2\2\16\")/:>CHNVe\u0092\u0094";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}