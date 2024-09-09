// Generated from c:/Users/dinar/Documents/GitHub/Compilador-MiniPython/MiniPythonParser.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class MiniPythonParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IF=1, ELSE=2, PRINT=3, DEF=4, RETURN=5, WHILE=6, FOR=7, IN=8, LEN=9, PIZQ=10, 
		PDER=11, DOSPUN=12, ASIGN=13, COMMA=14, GT=15, LT=16, LBRACKET=17, RBRACKET=18, 
		MUL=19, DIV=20, MOD=21, SUM=22, REST=23, GE=24, LE=25, EQEQ=26, NOTEQ=27, 
		ID=28, NUM=29, FLOAT=30, STRING=31, WS=32, COMMENT=33, BLOCK_COMMENT=34, 
		NEWLINE=35;
	public static final int
		RULE_program = 0, RULE_mainStatement = 1, RULE_defStatement = 2, RULE_argList = 3, 
		RULE_ifStatement = 4, RULE_whileStatement = 5, RULE_forStatement = 6, 
		RULE_returnStatement = 7, RULE_printStatement = 8, RULE_assignStatement = 9, 
		RULE_functionCallStatement = 10, RULE_sequence = 11, RULE_statement = 12, 
		RULE_expression = 13, RULE_comparison = 14, RULE_additionExpression = 15, 
		RULE_additionFactor = 16, RULE_multiplicationExpression = 17, RULE_multiplicationFactor = 18, 
		RULE_elementExpression = 19, RULE_elementAccess = 20, RULE_expressionList = 21, 
		RULE_primitiveExpression = 22;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "mainStatement", "defStatement", "argList", "ifStatement", 
			"whileStatement", "forStatement", "returnStatement", "printStatement", 
			"assignStatement", "functionCallStatement", "sequence", "statement", 
			"expression", "comparison", "additionExpression", "additionFactor", "multiplicationExpression", 
			"multiplicationFactor", "elementExpression", "elementAccess", "expressionList", 
			"primitiveExpression"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'if'", "'else'", "'print'", "'def'", "'return'", "'while'", "'for'", 
			"'in'", "'len'", "'('", "')'", "':'", "'='", "','", "'>'", "'<'", "'['", 
			"']'", "'*'", "'/'", "'%'", "'+'", "'-'", "'>='", "'<='", "'=='", "'!='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "IF", "ELSE", "PRINT", "DEF", "RETURN", "WHILE", "FOR", "IN", "LEN", 
			"PIZQ", "PDER", "DOSPUN", "ASIGN", "COMMA", "GT", "LT", "LBRACKET", "RBRACKET", 
			"MUL", "DIV", "MOD", "SUM", "REST", "GE", "LE", "EQEQ", "NOTEQ", "ID", 
			"NUM", "FLOAT", "STRING", "WS", "COMMENT", "BLOCK_COMMENT", "NEWLINE"
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
	public String getGrammarFileName() { return "MiniPythonParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MiniPythonParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(MiniPythonParser.EOF, 0); }
		public List<MainStatementContext> mainStatement() {
			return getRuleContexts(MainStatementContext.class);
		}
		public MainStatementContext mainStatement(int i) {
			return getRuleContext(MainStatementContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(MiniPythonParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(MiniPythonParser.NEWLINE, i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 34628174074L) != 0)) {
				{
				setState(48);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case IF:
				case PRINT:
				case DEF:
				case RETURN:
				case WHILE:
				case FOR:
				case ID:
					{
					setState(46);
					mainStatement();
					}
					break;
				case NEWLINE:
					{
					setState(47);
					match(NEWLINE);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(52);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(53);
			match(EOF);
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

	@SuppressWarnings("CheckReturnValue")
	public static class MainStatementContext extends ParserRuleContext {
		public DefStatementContext defStatement() {
			return getRuleContext(DefStatementContext.class,0);
		}
		public AssignStatementContext assignStatement() {
			return getRuleContext(AssignStatementContext.class,0);
		}
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public WhileStatementContext whileStatement() {
			return getRuleContext(WhileStatementContext.class,0);
		}
		public ForStatementContext forStatement() {
			return getRuleContext(ForStatementContext.class,0);
		}
		public PrintStatementContext printStatement() {
			return getRuleContext(PrintStatementContext.class,0);
		}
		public ReturnStatementContext returnStatement() {
			return getRuleContext(ReturnStatementContext.class,0);
		}
		public FunctionCallStatementContext functionCallStatement() {
			return getRuleContext(FunctionCallStatementContext.class,0);
		}
		public MainStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mainStatement; }
	}

	public final MainStatementContext mainStatement() throws RecognitionException {
		MainStatementContext _localctx = new MainStatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_mainStatement);
		try {
			setState(63);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(55);
				defStatement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(56);
				assignStatement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(57);
				ifStatement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(58);
				whileStatement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(59);
				forStatement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(60);
				printStatement();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(61);
				returnStatement();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(62);
				functionCallStatement();
				}
				break;
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

	@SuppressWarnings("CheckReturnValue")
	public static class DefStatementContext extends ParserRuleContext {
		public TerminalNode DEF() { return getToken(MiniPythonParser.DEF, 0); }
		public TerminalNode ID() { return getToken(MiniPythonParser.ID, 0); }
		public TerminalNode PIZQ() { return getToken(MiniPythonParser.PIZQ, 0); }
		public TerminalNode PDER() { return getToken(MiniPythonParser.PDER, 0); }
		public TerminalNode DOSPUN() { return getToken(MiniPythonParser.DOSPUN, 0); }
		public TerminalNode NEWLINE() { return getToken(MiniPythonParser.NEWLINE, 0); }
		public SequenceContext sequence() {
			return getRuleContext(SequenceContext.class,0);
		}
		public ArgListContext argList() {
			return getRuleContext(ArgListContext.class,0);
		}
		public DefStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defStatement; }
	}

	public final DefStatementContext defStatement() throws RecognitionException {
		DefStatementContext _localctx = new DefStatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_defStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			match(DEF);
			setState(66);
			match(ID);
			setState(67);
			match(PIZQ);
			setState(69);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(68);
				argList();
				}
			}

			setState(71);
			match(PDER);
			setState(72);
			match(DOSPUN);
			setState(73);
			match(NEWLINE);
			setState(74);
			sequence();
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

	@SuppressWarnings("CheckReturnValue")
	public static class ArgListContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(MiniPythonParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(MiniPythonParser.ID, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MiniPythonParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MiniPythonParser.COMMA, i);
		}
		public ArgListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argList; }
	}

	public final ArgListContext argList() throws RecognitionException {
		ArgListContext _localctx = new ArgListContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_argList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			match(ID);
			setState(81);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(77);
				match(COMMA);
				setState(78);
				match(ID);
				}
				}
				setState(83);
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

	@SuppressWarnings("CheckReturnValue")
	public static class IfStatementContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(MiniPythonParser.IF, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<TerminalNode> DOSPUN() { return getTokens(MiniPythonParser.DOSPUN); }
		public TerminalNode DOSPUN(int i) {
			return getToken(MiniPythonParser.DOSPUN, i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(MiniPythonParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(MiniPythonParser.NEWLINE, i);
		}
		public List<SequenceContext> sequence() {
			return getRuleContexts(SequenceContext.class);
		}
		public SequenceContext sequence(int i) {
			return getRuleContext(SequenceContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(MiniPythonParser.ELSE, 0); }
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_ifStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(IF);
			setState(85);
			expression();
			setState(86);
			match(DOSPUN);
			setState(87);
			match(NEWLINE);
			setState(88);
			sequence();
			setState(93);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(89);
				match(ELSE);
				setState(90);
				match(DOSPUN);
				setState(91);
				match(NEWLINE);
				setState(92);
				sequence();
				}
				break;
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

	@SuppressWarnings("CheckReturnValue")
	public static class WhileStatementContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(MiniPythonParser.WHILE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode DOSPUN() { return getToken(MiniPythonParser.DOSPUN, 0); }
		public TerminalNode NEWLINE() { return getToken(MiniPythonParser.NEWLINE, 0); }
		public SequenceContext sequence() {
			return getRuleContext(SequenceContext.class,0);
		}
		public WhileStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStatement; }
	}

	public final WhileStatementContext whileStatement() throws RecognitionException {
		WhileStatementContext _localctx = new WhileStatementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_whileStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			match(WHILE);
			setState(96);
			expression();
			setState(97);
			match(DOSPUN);
			setState(98);
			match(NEWLINE);
			setState(99);
			sequence();
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

	@SuppressWarnings("CheckReturnValue")
	public static class ForStatementContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(MiniPythonParser.FOR, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode IN() { return getToken(MiniPythonParser.IN, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public TerminalNode DOSPUN() { return getToken(MiniPythonParser.DOSPUN, 0); }
		public TerminalNode NEWLINE() { return getToken(MiniPythonParser.NEWLINE, 0); }
		public SequenceContext sequence() {
			return getRuleContext(SequenceContext.class,0);
		}
		public ForStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStatement; }
	}

	public final ForStatementContext forStatement() throws RecognitionException {
		ForStatementContext _localctx = new ForStatementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_forStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
			match(FOR);
			setState(102);
			expression();
			setState(103);
			match(IN);
			setState(104);
			expressionList();
			setState(105);
			match(DOSPUN);
			setState(106);
			match(NEWLINE);
			setState(107);
			sequence();
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

	@SuppressWarnings("CheckReturnValue")
	public static class ReturnStatementContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(MiniPythonParser.RETURN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(MiniPythonParser.NEWLINE, 0); }
		public ReturnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStatement; }
	}

	public final ReturnStatementContext returnStatement() throws RecognitionException {
		ReturnStatementContext _localctx = new ReturnStatementContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_returnStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			match(RETURN);
			setState(110);
			expression();
			setState(111);
			match(NEWLINE);
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

	@SuppressWarnings("CheckReturnValue")
	public static class PrintStatementContext extends ParserRuleContext {
		public TerminalNode PRINT() { return getToken(MiniPythonParser.PRINT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(MiniPythonParser.NEWLINE, 0); }
		public PrintStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_printStatement; }
	}

	public final PrintStatementContext printStatement() throws RecognitionException {
		PrintStatementContext _localctx = new PrintStatementContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_printStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			match(PRINT);
			setState(114);
			expression();
			setState(115);
			match(NEWLINE);
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

	@SuppressWarnings("CheckReturnValue")
	public static class AssignStatementContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MiniPythonParser.ID, 0); }
		public TerminalNode ASIGN() { return getToken(MiniPythonParser.ASIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(MiniPythonParser.NEWLINE, 0); }
		public AssignStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignStatement; }
	}

	public final AssignStatementContext assignStatement() throws RecognitionException {
		AssignStatementContext _localctx = new AssignStatementContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_assignStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			match(ID);
			setState(118);
			match(ASIGN);
			setState(119);
			expression();
			setState(120);
			match(NEWLINE);
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

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionCallStatementContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MiniPythonParser.ID, 0); }
		public TerminalNode PIZQ() { return getToken(MiniPythonParser.PIZQ, 0); }
		public TerminalNode PDER() { return getToken(MiniPythonParser.PDER, 0); }
		public TerminalNode NEWLINE() { return getToken(MiniPythonParser.NEWLINE, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public FunctionCallStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCallStatement; }
	}

	public final FunctionCallStatementContext functionCallStatement() throws RecognitionException {
		FunctionCallStatementContext _localctx = new FunctionCallStatementContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_functionCallStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			match(ID);
			setState(123);
			match(PIZQ);
			setState(125);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4035053056L) != 0)) {
				{
				setState(124);
				expressionList();
				}
			}

			setState(127);
			match(PDER);
			setState(128);
			match(NEWLINE);
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

	@SuppressWarnings("CheckReturnValue")
	public static class SequenceContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(MiniPythonParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(MiniPythonParser.NEWLINE, i);
		}
		public SequenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sequence; }
	}

	public final SequenceContext sequence() throws RecognitionException {
		SequenceContext _localctx = new SequenceContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_sequence);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(130);
					statement();
					setState(132);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
					case 1:
						{
						setState(131);
						match(NEWLINE);
						}
						break;
					}
					}
					} 
				}
				setState(138);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
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

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public DefStatementContext defStatement() {
			return getRuleContext(DefStatementContext.class,0);
		}
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public WhileStatementContext whileStatement() {
			return getRuleContext(WhileStatementContext.class,0);
		}
		public ForStatementContext forStatement() {
			return getRuleContext(ForStatementContext.class,0);
		}
		public ReturnStatementContext returnStatement() {
			return getRuleContext(ReturnStatementContext.class,0);
		}
		public PrintStatementContext printStatement() {
			return getRuleContext(PrintStatementContext.class,0);
		}
		public AssignStatementContext assignStatement() {
			return getRuleContext(AssignStatementContext.class,0);
		}
		public FunctionCallStatementContext functionCallStatement() {
			return getRuleContext(FunctionCallStatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_statement);
		try {
			setState(147);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(139);
				defStatement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(140);
				ifStatement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(141);
				whileStatement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(142);
				forStatement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(143);
				returnStatement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(144);
				printStatement();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(145);
				assignStatement();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(146);
				functionCallStatement();
				}
				break;
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

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public AdditionExpressionContext additionExpression() {
			return getRuleContext(AdditionExpressionContext.class,0);
		}
		public ComparisonContext comparison() {
			return getRuleContext(ComparisonContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			additionExpression();
			setState(151);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 251756544L) != 0)) {
				{
				setState(150);
				comparison();
				}
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

	@SuppressWarnings("CheckReturnValue")
	public static class ComparisonContext extends ParserRuleContext {
		public AdditionExpressionContext additionExpression() {
			return getRuleContext(AdditionExpressionContext.class,0);
		}
		public TerminalNode GT() { return getToken(MiniPythonParser.GT, 0); }
		public TerminalNode LT() { return getToken(MiniPythonParser.LT, 0); }
		public TerminalNode GE() { return getToken(MiniPythonParser.GE, 0); }
		public TerminalNode LE() { return getToken(MiniPythonParser.LE, 0); }
		public TerminalNode EQEQ() { return getToken(MiniPythonParser.EQEQ, 0); }
		public TerminalNode NOTEQ() { return getToken(MiniPythonParser.NOTEQ, 0); }
		public ComparisonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparison; }
	}

	public final ComparisonContext comparison() throws RecognitionException {
		ComparisonContext _localctx = new ComparisonContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_comparison);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 251756544L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(154);
			additionExpression();
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

	@SuppressWarnings("CheckReturnValue")
	public static class AdditionExpressionContext extends ParserRuleContext {
		public MultiplicationExpressionContext multiplicationExpression() {
			return getRuleContext(MultiplicationExpressionContext.class,0);
		}
		public List<AdditionFactorContext> additionFactor() {
			return getRuleContexts(AdditionFactorContext.class);
		}
		public AdditionFactorContext additionFactor(int i) {
			return getRuleContext(AdditionFactorContext.class,i);
		}
		public AdditionExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additionExpression; }
	}

	public final AdditionExpressionContext additionExpression() throws RecognitionException {
		AdditionExpressionContext _localctx = new AdditionExpressionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_additionExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			multiplicationExpression();
			setState(160);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SUM || _la==REST) {
				{
				{
				setState(157);
				additionFactor();
				}
				}
				setState(162);
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

	@SuppressWarnings("CheckReturnValue")
	public static class AdditionFactorContext extends ParserRuleContext {
		public MultiplicationExpressionContext multiplicationExpression() {
			return getRuleContext(MultiplicationExpressionContext.class,0);
		}
		public TerminalNode SUM() { return getToken(MiniPythonParser.SUM, 0); }
		public TerminalNode REST() { return getToken(MiniPythonParser.REST, 0); }
		public AdditionFactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additionFactor; }
	}

	public final AdditionFactorContext additionFactor() throws RecognitionException {
		AdditionFactorContext _localctx = new AdditionFactorContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_additionFactor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			_la = _input.LA(1);
			if ( !(_la==SUM || _la==REST) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(164);
			multiplicationExpression();
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

	@SuppressWarnings("CheckReturnValue")
	public static class MultiplicationExpressionContext extends ParserRuleContext {
		public ElementExpressionContext elementExpression() {
			return getRuleContext(ElementExpressionContext.class,0);
		}
		public List<MultiplicationFactorContext> multiplicationFactor() {
			return getRuleContexts(MultiplicationFactorContext.class);
		}
		public MultiplicationFactorContext multiplicationFactor(int i) {
			return getRuleContext(MultiplicationFactorContext.class,i);
		}
		public MultiplicationExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicationExpression; }
	}

	public final MultiplicationExpressionContext multiplicationExpression() throws RecognitionException {
		MultiplicationExpressionContext _localctx = new MultiplicationExpressionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_multiplicationExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			elementExpression();
			setState(170);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 3670016L) != 0)) {
				{
				{
				setState(167);
				multiplicationFactor();
				}
				}
				setState(172);
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

	@SuppressWarnings("CheckReturnValue")
	public static class MultiplicationFactorContext extends ParserRuleContext {
		public ElementExpressionContext elementExpression() {
			return getRuleContext(ElementExpressionContext.class,0);
		}
		public TerminalNode MUL() { return getToken(MiniPythonParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(MiniPythonParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(MiniPythonParser.MOD, 0); }
		public MultiplicationFactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicationFactor; }
	}

	public final MultiplicationFactorContext multiplicationFactor() throws RecognitionException {
		MultiplicationFactorContext _localctx = new MultiplicationFactorContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_multiplicationFactor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 3670016L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(174);
			elementExpression();
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

	@SuppressWarnings("CheckReturnValue")
	public static class ElementExpressionContext extends ParserRuleContext {
		public PrimitiveExpressionContext primitiveExpression() {
			return getRuleContext(PrimitiveExpressionContext.class,0);
		}
		public ElementAccessContext elementAccess() {
			return getRuleContext(ElementAccessContext.class,0);
		}
		public ElementExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementExpression; }
	}

	public final ElementExpressionContext elementExpression() throws RecognitionException {
		ElementExpressionContext _localctx = new ElementExpressionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_elementExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
			primitiveExpression();
			setState(178);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACKET) {
				{
				setState(177);
				elementAccess();
				}
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

	@SuppressWarnings("CheckReturnValue")
	public static class ElementAccessContext extends ParserRuleContext {
		public TerminalNode LBRACKET() { return getToken(MiniPythonParser.LBRACKET, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RBRACKET() { return getToken(MiniPythonParser.RBRACKET, 0); }
		public ElementAccessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementAccess; }
	}

	public final ElementAccessContext elementAccess() throws RecognitionException {
		ElementAccessContext _localctx = new ElementAccessContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_elementAccess);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			match(LBRACKET);
			setState(181);
			expression();
			setState(182);
			match(RBRACKET);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MiniPythonParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MiniPythonParser.COMMA, i);
		}
		public ExpressionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionList; }
	}

	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_expressionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			expression();
			setState(189);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(185);
				match(COMMA);
				setState(186);
				expression();
				}
				}
				setState(191);
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

	@SuppressWarnings("CheckReturnValue")
	public static class PrimitiveExpressionContext extends ParserRuleContext {
		public TerminalNode NUM() { return getToken(MiniPythonParser.NUM, 0); }
		public TerminalNode REST() { return getToken(MiniPythonParser.REST, 0); }
		public TerminalNode FLOAT() { return getToken(MiniPythonParser.FLOAT, 0); }
		public TerminalNode STRING() { return getToken(MiniPythonParser.STRING, 0); }
		public TerminalNode ID() { return getToken(MiniPythonParser.ID, 0); }
		public TerminalNode PIZQ() { return getToken(MiniPythonParser.PIZQ, 0); }
		public TerminalNode PDER() { return getToken(MiniPythonParser.PDER, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode LBRACKET() { return getToken(MiniPythonParser.LBRACKET, 0); }
		public TerminalNode RBRACKET() { return getToken(MiniPythonParser.RBRACKET, 0); }
		public TerminalNode LEN() { return getToken(MiniPythonParser.LEN, 0); }
		public PrimitiveExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primitiveExpression; }
	}

	public final PrimitiveExpressionContext primitiveExpression() throws RecognitionException {
		PrimitiveExpressionContext _localctx = new PrimitiveExpressionContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_primitiveExpression);
		int _la;
		try {
			setState(223);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(193);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==REST) {
					{
					setState(192);
					match(REST);
					}
				}

				setState(195);
				match(NUM);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(197);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==REST) {
					{
					setState(196);
					match(REST);
					}
				}

				setState(199);
				match(FLOAT);
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(200);
				match(STRING);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(201);
				match(ID);
				setState(207);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PIZQ) {
					{
					setState(202);
					match(PIZQ);
					setState(204);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4035053056L) != 0)) {
						{
						setState(203);
						expressionList();
						}
					}

					setState(206);
					match(PDER);
					}
				}

				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(209);
				match(PIZQ);
				setState(210);
				expression();
				setState(211);
				match(PDER);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(213);
				match(LBRACKET);
				setState(215);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4035053056L) != 0)) {
					{
					setState(214);
					expressionList();
					}
				}

				setState(217);
				match(RBRACKET);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(218);
				match(LEN);
				setState(219);
				match(PIZQ);
				setState(220);
				expression();
				setState(221);
				match(PDER);
				}
				break;
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

	public static final String _serializedATN =
		"\u0004\u0001#\u00e2\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0001\u0000\u0001\u0000\u0005\u00001\b\u0000"+
		"\n\u0000\f\u00004\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0003\u0001@\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0003\u0002F\b\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003P\b\u0003"+
		"\n\u0003\f\u0003S\t\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004"+
		"^\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\n\u0001\n\u0001\n\u0003\n~\b\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\u000b\u0001\u000b\u0003\u000b\u0085\b\u000b\u0005\u000b\u0087"+
		"\b\u000b\n\u000b\f\u000b\u008a\t\u000b\u0001\f\u0001\f\u0001\f\u0001\f"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u0094\b\f\u0001\r\u0001\r\u0003"+
		"\r\u0098\b\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f"+
		"\u0005\u000f\u009f\b\u000f\n\u000f\f\u000f\u00a2\t\u000f\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0005\u0011\u00a9\b\u0011\n"+
		"\u0011\f\u0011\u00ac\t\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0013\u0001\u0013\u0003\u0013\u00b3\b\u0013\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0005\u0015\u00bc"+
		"\b\u0015\n\u0015\f\u0015\u00bf\t\u0015\u0001\u0016\u0003\u0016\u00c2\b"+
		"\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u00c6\b\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u00cd\b\u0016\u0001"+
		"\u0016\u0003\u0016\u00d0\b\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u00d8\b\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u00e0"+
		"\b\u0016\u0001\u0016\u0000\u0000\u0017\u0000\u0002\u0004\u0006\b\n\f\u000e"+
		"\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,\u0000\u0003\u0002"+
		"\u0000\u000f\u0010\u0018\u001b\u0001\u0000\u0016\u0017\u0001\u0000\u0013"+
		"\u0015\u00f0\u00002\u0001\u0000\u0000\u0000\u0002?\u0001\u0000\u0000\u0000"+
		"\u0004A\u0001\u0000\u0000\u0000\u0006L\u0001\u0000\u0000\u0000\bT\u0001"+
		"\u0000\u0000\u0000\n_\u0001\u0000\u0000\u0000\fe\u0001\u0000\u0000\u0000"+
		"\u000em\u0001\u0000\u0000\u0000\u0010q\u0001\u0000\u0000\u0000\u0012u"+
		"\u0001\u0000\u0000\u0000\u0014z\u0001\u0000\u0000\u0000\u0016\u0088\u0001"+
		"\u0000\u0000\u0000\u0018\u0093\u0001\u0000\u0000\u0000\u001a\u0095\u0001"+
		"\u0000\u0000\u0000\u001c\u0099\u0001\u0000\u0000\u0000\u001e\u009c\u0001"+
		"\u0000\u0000\u0000 \u00a3\u0001\u0000\u0000\u0000\"\u00a6\u0001\u0000"+
		"\u0000\u0000$\u00ad\u0001\u0000\u0000\u0000&\u00b0\u0001\u0000\u0000\u0000"+
		"(\u00b4\u0001\u0000\u0000\u0000*\u00b8\u0001\u0000\u0000\u0000,\u00df"+
		"\u0001\u0000\u0000\u0000.1\u0003\u0002\u0001\u0000/1\u0005#\u0000\u0000"+
		"0.\u0001\u0000\u0000\u00000/\u0001\u0000\u0000\u000014\u0001\u0000\u0000"+
		"\u000020\u0001\u0000\u0000\u000023\u0001\u0000\u0000\u000035\u0001\u0000"+
		"\u0000\u000042\u0001\u0000\u0000\u000056\u0005\u0000\u0000\u00016\u0001"+
		"\u0001\u0000\u0000\u00007@\u0003\u0004\u0002\u00008@\u0003\u0012\t\u0000"+
		"9@\u0003\b\u0004\u0000:@\u0003\n\u0005\u0000;@\u0003\f\u0006\u0000<@\u0003"+
		"\u0010\b\u0000=@\u0003\u000e\u0007\u0000>@\u0003\u0014\n\u0000?7\u0001"+
		"\u0000\u0000\u0000?8\u0001\u0000\u0000\u0000?9\u0001\u0000\u0000\u0000"+
		"?:\u0001\u0000\u0000\u0000?;\u0001\u0000\u0000\u0000?<\u0001\u0000\u0000"+
		"\u0000?=\u0001\u0000\u0000\u0000?>\u0001\u0000\u0000\u0000@\u0003\u0001"+
		"\u0000\u0000\u0000AB\u0005\u0004\u0000\u0000BC\u0005\u001c\u0000\u0000"+
		"CE\u0005\n\u0000\u0000DF\u0003\u0006\u0003\u0000ED\u0001\u0000\u0000\u0000"+
		"EF\u0001\u0000\u0000\u0000FG\u0001\u0000\u0000\u0000GH\u0005\u000b\u0000"+
		"\u0000HI\u0005\f\u0000\u0000IJ\u0005#\u0000\u0000JK\u0003\u0016\u000b"+
		"\u0000K\u0005\u0001\u0000\u0000\u0000LQ\u0005\u001c\u0000\u0000MN\u0005"+
		"\u000e\u0000\u0000NP\u0005\u001c\u0000\u0000OM\u0001\u0000\u0000\u0000"+
		"PS\u0001\u0000\u0000\u0000QO\u0001\u0000\u0000\u0000QR\u0001\u0000\u0000"+
		"\u0000R\u0007\u0001\u0000\u0000\u0000SQ\u0001\u0000\u0000\u0000TU\u0005"+
		"\u0001\u0000\u0000UV\u0003\u001a\r\u0000VW\u0005\f\u0000\u0000WX\u0005"+
		"#\u0000\u0000X]\u0003\u0016\u000b\u0000YZ\u0005\u0002\u0000\u0000Z[\u0005"+
		"\f\u0000\u0000[\\\u0005#\u0000\u0000\\^\u0003\u0016\u000b\u0000]Y\u0001"+
		"\u0000\u0000\u0000]^\u0001\u0000\u0000\u0000^\t\u0001\u0000\u0000\u0000"+
		"_`\u0005\u0006\u0000\u0000`a\u0003\u001a\r\u0000ab\u0005\f\u0000\u0000"+
		"bc\u0005#\u0000\u0000cd\u0003\u0016\u000b\u0000d\u000b\u0001\u0000\u0000"+
		"\u0000ef\u0005\u0007\u0000\u0000fg\u0003\u001a\r\u0000gh\u0005\b\u0000"+
		"\u0000hi\u0003*\u0015\u0000ij\u0005\f\u0000\u0000jk\u0005#\u0000\u0000"+
		"kl\u0003\u0016\u000b\u0000l\r\u0001\u0000\u0000\u0000mn\u0005\u0005\u0000"+
		"\u0000no\u0003\u001a\r\u0000op\u0005#\u0000\u0000p\u000f\u0001\u0000\u0000"+
		"\u0000qr\u0005\u0003\u0000\u0000rs\u0003\u001a\r\u0000st\u0005#\u0000"+
		"\u0000t\u0011\u0001\u0000\u0000\u0000uv\u0005\u001c\u0000\u0000vw\u0005"+
		"\r\u0000\u0000wx\u0003\u001a\r\u0000xy\u0005#\u0000\u0000y\u0013\u0001"+
		"\u0000\u0000\u0000z{\u0005\u001c\u0000\u0000{}\u0005\n\u0000\u0000|~\u0003"+
		"*\u0015\u0000}|\u0001\u0000\u0000\u0000}~\u0001\u0000\u0000\u0000~\u007f"+
		"\u0001\u0000\u0000\u0000\u007f\u0080\u0005\u000b\u0000\u0000\u0080\u0081"+
		"\u0005#\u0000\u0000\u0081\u0015\u0001\u0000\u0000\u0000\u0082\u0084\u0003"+
		"\u0018\f\u0000\u0083\u0085\u0005#\u0000\u0000\u0084\u0083\u0001\u0000"+
		"\u0000\u0000\u0084\u0085\u0001\u0000\u0000\u0000\u0085\u0087\u0001\u0000"+
		"\u0000\u0000\u0086\u0082\u0001\u0000\u0000\u0000\u0087\u008a\u0001\u0000"+
		"\u0000\u0000\u0088\u0086\u0001\u0000\u0000\u0000\u0088\u0089\u0001\u0000"+
		"\u0000\u0000\u0089\u0017\u0001\u0000\u0000\u0000\u008a\u0088\u0001\u0000"+
		"\u0000\u0000\u008b\u0094\u0003\u0004\u0002\u0000\u008c\u0094\u0003\b\u0004"+
		"\u0000\u008d\u0094\u0003\n\u0005\u0000\u008e\u0094\u0003\f\u0006\u0000"+
		"\u008f\u0094\u0003\u000e\u0007\u0000\u0090\u0094\u0003\u0010\b\u0000\u0091"+
		"\u0094\u0003\u0012\t\u0000\u0092\u0094\u0003\u0014\n\u0000\u0093\u008b"+
		"\u0001\u0000\u0000\u0000\u0093\u008c\u0001\u0000\u0000\u0000\u0093\u008d"+
		"\u0001\u0000\u0000\u0000\u0093\u008e\u0001\u0000\u0000\u0000\u0093\u008f"+
		"\u0001\u0000\u0000\u0000\u0093\u0090\u0001\u0000\u0000\u0000\u0093\u0091"+
		"\u0001\u0000\u0000\u0000\u0093\u0092\u0001\u0000\u0000\u0000\u0094\u0019"+
		"\u0001\u0000\u0000\u0000\u0095\u0097\u0003\u001e\u000f\u0000\u0096\u0098"+
		"\u0003\u001c\u000e\u0000\u0097\u0096\u0001\u0000\u0000\u0000\u0097\u0098"+
		"\u0001\u0000\u0000\u0000\u0098\u001b\u0001\u0000\u0000\u0000\u0099\u009a"+
		"\u0007\u0000\u0000\u0000\u009a\u009b\u0003\u001e\u000f\u0000\u009b\u001d"+
		"\u0001\u0000\u0000\u0000\u009c\u00a0\u0003\"\u0011\u0000\u009d\u009f\u0003"+
		" \u0010\u0000\u009e\u009d\u0001\u0000\u0000\u0000\u009f\u00a2\u0001\u0000"+
		"\u0000\u0000\u00a0\u009e\u0001\u0000\u0000\u0000\u00a0\u00a1\u0001\u0000"+
		"\u0000\u0000\u00a1\u001f\u0001\u0000\u0000\u0000\u00a2\u00a0\u0001\u0000"+
		"\u0000\u0000\u00a3\u00a4\u0007\u0001\u0000\u0000\u00a4\u00a5\u0003\"\u0011"+
		"\u0000\u00a5!\u0001\u0000\u0000\u0000\u00a6\u00aa\u0003&\u0013\u0000\u00a7"+
		"\u00a9\u0003$\u0012\u0000\u00a8\u00a7\u0001\u0000\u0000\u0000\u00a9\u00ac"+
		"\u0001\u0000\u0000\u0000\u00aa\u00a8\u0001\u0000\u0000\u0000\u00aa\u00ab"+
		"\u0001\u0000\u0000\u0000\u00ab#\u0001\u0000\u0000\u0000\u00ac\u00aa\u0001"+
		"\u0000\u0000\u0000\u00ad\u00ae\u0007\u0002\u0000\u0000\u00ae\u00af\u0003"+
		"&\u0013\u0000\u00af%\u0001\u0000\u0000\u0000\u00b0\u00b2\u0003,\u0016"+
		"\u0000\u00b1\u00b3\u0003(\u0014\u0000\u00b2\u00b1\u0001\u0000\u0000\u0000"+
		"\u00b2\u00b3\u0001\u0000\u0000\u0000\u00b3\'\u0001\u0000\u0000\u0000\u00b4"+
		"\u00b5\u0005\u0011\u0000\u0000\u00b5\u00b6\u0003\u001a\r\u0000\u00b6\u00b7"+
		"\u0005\u0012\u0000\u0000\u00b7)\u0001\u0000\u0000\u0000\u00b8\u00bd\u0003"+
		"\u001a\r\u0000\u00b9\u00ba\u0005\u000e\u0000\u0000\u00ba\u00bc\u0003\u001a"+
		"\r\u0000\u00bb\u00b9\u0001\u0000\u0000\u0000\u00bc\u00bf\u0001\u0000\u0000"+
		"\u0000\u00bd\u00bb\u0001\u0000\u0000\u0000\u00bd\u00be\u0001\u0000\u0000"+
		"\u0000\u00be+\u0001\u0000\u0000\u0000\u00bf\u00bd\u0001\u0000\u0000\u0000"+
		"\u00c0\u00c2\u0005\u0017\u0000\u0000\u00c1\u00c0\u0001\u0000\u0000\u0000"+
		"\u00c1\u00c2\u0001\u0000\u0000\u0000\u00c2\u00c3\u0001\u0000\u0000\u0000"+
		"\u00c3\u00e0\u0005\u001d\u0000\u0000\u00c4\u00c6\u0005\u0017\u0000\u0000"+
		"\u00c5\u00c4\u0001\u0000\u0000\u0000\u00c5\u00c6\u0001\u0000\u0000\u0000"+
		"\u00c6\u00c7\u0001\u0000\u0000\u0000\u00c7\u00e0\u0005\u001e\u0000\u0000"+
		"\u00c8\u00e0\u0005\u001f\u0000\u0000\u00c9\u00cf\u0005\u001c\u0000\u0000"+
		"\u00ca\u00cc\u0005\n\u0000\u0000\u00cb\u00cd\u0003*\u0015\u0000\u00cc"+
		"\u00cb\u0001\u0000\u0000\u0000\u00cc\u00cd\u0001\u0000\u0000\u0000\u00cd"+
		"\u00ce\u0001\u0000\u0000\u0000\u00ce\u00d0\u0005\u000b\u0000\u0000\u00cf"+
		"\u00ca\u0001\u0000\u0000\u0000\u00cf\u00d0\u0001\u0000\u0000\u0000\u00d0"+
		"\u00e0\u0001\u0000\u0000\u0000\u00d1\u00d2\u0005\n\u0000\u0000\u00d2\u00d3"+
		"\u0003\u001a\r\u0000\u00d3\u00d4\u0005\u000b\u0000\u0000\u00d4\u00e0\u0001"+
		"\u0000\u0000\u0000\u00d5\u00d7\u0005\u0011\u0000\u0000\u00d6\u00d8\u0003"+
		"*\u0015\u0000\u00d7\u00d6\u0001\u0000\u0000\u0000\u00d7\u00d8\u0001\u0000"+
		"\u0000\u0000\u00d8\u00d9\u0001\u0000\u0000\u0000\u00d9\u00e0\u0005\u0012"+
		"\u0000\u0000\u00da\u00db\u0005\t\u0000\u0000\u00db\u00dc\u0005\n\u0000"+
		"\u0000\u00dc\u00dd\u0003\u001a\r\u0000\u00dd\u00de\u0005\u000b\u0000\u0000"+
		"\u00de\u00e0\u0001\u0000\u0000\u0000\u00df\u00c1\u0001\u0000\u0000\u0000"+
		"\u00df\u00c5\u0001\u0000\u0000\u0000\u00df\u00c8\u0001\u0000\u0000\u0000"+
		"\u00df\u00c9\u0001\u0000\u0000\u0000\u00df\u00d1\u0001\u0000\u0000\u0000"+
		"\u00df\u00d5\u0001\u0000\u0000\u0000\u00df\u00da\u0001\u0000\u0000\u0000"+
		"\u00e0-\u0001\u0000\u0000\u0000\u001502?EQ]}\u0084\u0088\u0093\u0097\u00a0"+
		"\u00aa\u00b2\u00bd\u00c1\u00c5\u00cc\u00cf\u00d7\u00df";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}