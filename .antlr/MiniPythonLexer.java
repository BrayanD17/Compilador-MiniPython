// Generated from c:/Users/dinar/Documents/GitHub/Compilador-MiniPython/MiniPythonLexer.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class MiniPythonLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IF=1, ELSE=2, PRINT=3, DEF=4, RETURN=5, WHILE=6, FOR=7, IN=8, LEN=9, PIZQ=10, 
		PDER=11, DOSPUN=12, ASIGN=13, COMMA=14, GT=15, LT=16, LBRACKET=17, RBRACKET=18, 
		MUL=19, DIV=20, MOD=21, SUM=22, REST=23, GE=24, LE=25, EQEQ=26, NOTEQ=27, 
		ID=28, NUM=29, FLOAT=30, STRING=31, WS=32, COMMENT=33, BLOCK_COMMENT=34, 
		NEWLINE=35, INDENT=36;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"IF", "ELSE", "PRINT", "DEF", "RETURN", "WHILE", "FOR", "IN", "LEN", 
			"PIZQ", "PDER", "DOSPUN", "ASIGN", "COMMA", "GT", "LT", "LBRACKET", "RBRACKET", 
			"MUL", "DIV", "MOD", "SUM", "REST", "GE", "LE", "EQEQ", "NOTEQ", "ID", 
			"NUM", "FLOAT", "STRING", "WS", "COMMENT", "BLOCK_COMMENT", "NEWLINE", 
			"INDENT"
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
			"NUM", "FLOAT", "STRING", "WS", "COMMENT", "BLOCK_COMMENT", "NEWLINE", 
			"INDENT"
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


	public MiniPythonLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MiniPythonLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000$\u00e8\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002"+
		"\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002"+
		"\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002"+
		"\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002"+
		"\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007"+
		"!\u0002\"\u0007\"\u0002#\u0007#\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b"+
		"\u0001\f\u0001\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000f\u0001"+
		"\u000f\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0012\u0001"+
		"\u0012\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0015\u0001"+
		"\u0015\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0001"+
		"\u001a\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0005\u001b\u009e"+
		"\b\u001b\n\u001b\f\u001b\u00a1\t\u001b\u0001\u001c\u0004\u001c\u00a4\b"+
		"\u001c\u000b\u001c\f\u001c\u00a5\u0001\u001d\u0004\u001d\u00a9\b\u001d"+
		"\u000b\u001d\f\u001d\u00aa\u0001\u001d\u0001\u001d\u0004\u001d\u00af\b"+
		"\u001d\u000b\u001d\f\u001d\u00b0\u0001\u001e\u0001\u001e\u0001\u001e\u0005"+
		"\u001e\u00b6\b\u001e\n\u001e\f\u001e\u00b9\t\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001 \u0001 \u0005 "+
		"\u00c3\b \n \f \u00c6\t \u0001 \u0001 \u0001!\u0001!\u0001!\u0001!\u0001"+
		"!\u0005!\u00cf\b!\n!\f!\u00d2\t!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001"+
		"!\u0001\"\u0003\"\u00db\b\"\u0001\"\u0001\"\u0005\"\u00df\b\"\n\"\f\""+
		"\u00e2\t\"\u0001#\u0004#\u00e5\b#\u000b#\f#\u00e6\u0001\u00d0\u0000$\u0001"+
		"\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007"+
		"\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d"+
		"\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0015+\u0016-\u0017/"+
		"\u00181\u00193\u001a5\u001b7\u001c9\u001d;\u001e=\u001f? A!C\"E#G$\u0001"+
		"\u0000\u0007\u0003\u0000AZ__az\u0004\u000009AZ__az\u0001\u000009\u0002"+
		"\u0000\"\"\\\\\u0004\u0000\t\n\r\r  ++\u0002\u0000\n\n\r\r\u0002\u0000"+
		"\t\t  \u00f2\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000"+
		"\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000"+
		"\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000"+
		"\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000"+
		"\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000"+
		"\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000"+
		"\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000"+
		"\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000"+
		"\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%"+
		"\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000)\u0001"+
		"\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001\u0000\u0000"+
		"\u0000\u0000/\u0001\u0000\u0000\u0000\u00001\u0001\u0000\u0000\u0000\u0000"+
		"3\u0001\u0000\u0000\u0000\u00005\u0001\u0000\u0000\u0000\u00007\u0001"+
		"\u0000\u0000\u0000\u00009\u0001\u0000\u0000\u0000\u0000;\u0001\u0000\u0000"+
		"\u0000\u0000=\u0001\u0000\u0000\u0000\u0000?\u0001\u0000\u0000\u0000\u0000"+
		"A\u0001\u0000\u0000\u0000\u0000C\u0001\u0000\u0000\u0000\u0000E\u0001"+
		"\u0000\u0000\u0000\u0000G\u0001\u0000\u0000\u0000\u0001I\u0001\u0000\u0000"+
		"\u0000\u0003L\u0001\u0000\u0000\u0000\u0005Q\u0001\u0000\u0000\u0000\u0007"+
		"W\u0001\u0000\u0000\u0000\t[\u0001\u0000\u0000\u0000\u000bb\u0001\u0000"+
		"\u0000\u0000\rh\u0001\u0000\u0000\u0000\u000fl\u0001\u0000\u0000\u0000"+
		"\u0011o\u0001\u0000\u0000\u0000\u0013s\u0001\u0000\u0000\u0000\u0015u"+
		"\u0001\u0000\u0000\u0000\u0017w\u0001\u0000\u0000\u0000\u0019y\u0001\u0000"+
		"\u0000\u0000\u001b{\u0001\u0000\u0000\u0000\u001d}\u0001\u0000\u0000\u0000"+
		"\u001f\u007f\u0001\u0000\u0000\u0000!\u0081\u0001\u0000\u0000\u0000#\u0083"+
		"\u0001\u0000\u0000\u0000%\u0085\u0001\u0000\u0000\u0000\'\u0087\u0001"+
		"\u0000\u0000\u0000)\u0089\u0001\u0000\u0000\u0000+\u008b\u0001\u0000\u0000"+
		"\u0000-\u008d\u0001\u0000\u0000\u0000/\u008f\u0001\u0000\u0000\u00001"+
		"\u0092\u0001\u0000\u0000\u00003\u0095\u0001\u0000\u0000\u00005\u0098\u0001"+
		"\u0000\u0000\u00007\u009b\u0001\u0000\u0000\u00009\u00a3\u0001\u0000\u0000"+
		"\u0000;\u00a8\u0001\u0000\u0000\u0000=\u00b2\u0001\u0000\u0000\u0000?"+
		"\u00bc\u0001\u0000\u0000\u0000A\u00c0\u0001\u0000\u0000\u0000C\u00c9\u0001"+
		"\u0000\u0000\u0000E\u00da\u0001\u0000\u0000\u0000G\u00e4\u0001\u0000\u0000"+
		"\u0000IJ\u0005i\u0000\u0000JK\u0005f\u0000\u0000K\u0002\u0001\u0000\u0000"+
		"\u0000LM\u0005e\u0000\u0000MN\u0005l\u0000\u0000NO\u0005s\u0000\u0000"+
		"OP\u0005e\u0000\u0000P\u0004\u0001\u0000\u0000\u0000QR\u0005p\u0000\u0000"+
		"RS\u0005r\u0000\u0000ST\u0005i\u0000\u0000TU\u0005n\u0000\u0000UV\u0005"+
		"t\u0000\u0000V\u0006\u0001\u0000\u0000\u0000WX\u0005d\u0000\u0000XY\u0005"+
		"e\u0000\u0000YZ\u0005f\u0000\u0000Z\b\u0001\u0000\u0000\u0000[\\\u0005"+
		"r\u0000\u0000\\]\u0005e\u0000\u0000]^\u0005t\u0000\u0000^_\u0005u\u0000"+
		"\u0000_`\u0005r\u0000\u0000`a\u0005n\u0000\u0000a\n\u0001\u0000\u0000"+
		"\u0000bc\u0005w\u0000\u0000cd\u0005h\u0000\u0000de\u0005i\u0000\u0000"+
		"ef\u0005l\u0000\u0000fg\u0005e\u0000\u0000g\f\u0001\u0000\u0000\u0000"+
		"hi\u0005f\u0000\u0000ij\u0005o\u0000\u0000jk\u0005r\u0000\u0000k\u000e"+
		"\u0001\u0000\u0000\u0000lm\u0005i\u0000\u0000mn\u0005n\u0000\u0000n\u0010"+
		"\u0001\u0000\u0000\u0000op\u0005l\u0000\u0000pq\u0005e\u0000\u0000qr\u0005"+
		"n\u0000\u0000r\u0012\u0001\u0000\u0000\u0000st\u0005(\u0000\u0000t\u0014"+
		"\u0001\u0000\u0000\u0000uv\u0005)\u0000\u0000v\u0016\u0001\u0000\u0000"+
		"\u0000wx\u0005:\u0000\u0000x\u0018\u0001\u0000\u0000\u0000yz\u0005=\u0000"+
		"\u0000z\u001a\u0001\u0000\u0000\u0000{|\u0005,\u0000\u0000|\u001c\u0001"+
		"\u0000\u0000\u0000}~\u0005>\u0000\u0000~\u001e\u0001\u0000\u0000\u0000"+
		"\u007f\u0080\u0005<\u0000\u0000\u0080 \u0001\u0000\u0000\u0000\u0081\u0082"+
		"\u0005[\u0000\u0000\u0082\"\u0001\u0000\u0000\u0000\u0083\u0084\u0005"+
		"]\u0000\u0000\u0084$\u0001\u0000\u0000\u0000\u0085\u0086\u0005*\u0000"+
		"\u0000\u0086&\u0001\u0000\u0000\u0000\u0087\u0088\u0005/\u0000\u0000\u0088"+
		"(\u0001\u0000\u0000\u0000\u0089\u008a\u0005%\u0000\u0000\u008a*\u0001"+
		"\u0000\u0000\u0000\u008b\u008c\u0005+\u0000\u0000\u008c,\u0001\u0000\u0000"+
		"\u0000\u008d\u008e\u0005-\u0000\u0000\u008e.\u0001\u0000\u0000\u0000\u008f"+
		"\u0090\u0005>\u0000\u0000\u0090\u0091\u0005=\u0000\u0000\u00910\u0001"+
		"\u0000\u0000\u0000\u0092\u0093\u0005<\u0000\u0000\u0093\u0094\u0005=\u0000"+
		"\u0000\u00942\u0001\u0000\u0000\u0000\u0095\u0096\u0005=\u0000\u0000\u0096"+
		"\u0097\u0005=\u0000\u0000\u00974\u0001\u0000\u0000\u0000\u0098\u0099\u0005"+
		"!\u0000\u0000\u0099\u009a\u0005=\u0000\u0000\u009a6\u0001\u0000\u0000"+
		"\u0000\u009b\u009f\u0007\u0000\u0000\u0000\u009c\u009e\u0007\u0001\u0000"+
		"\u0000\u009d\u009c\u0001\u0000\u0000\u0000\u009e\u00a1\u0001\u0000\u0000"+
		"\u0000\u009f\u009d\u0001\u0000\u0000\u0000\u009f\u00a0\u0001\u0000\u0000"+
		"\u0000\u00a08\u0001\u0000\u0000\u0000\u00a1\u009f\u0001\u0000\u0000\u0000"+
		"\u00a2\u00a4\u0007\u0002\u0000\u0000\u00a3\u00a2\u0001\u0000\u0000\u0000"+
		"\u00a4\u00a5\u0001\u0000\u0000\u0000\u00a5\u00a3\u0001\u0000\u0000\u0000"+
		"\u00a5\u00a6\u0001\u0000\u0000\u0000\u00a6:\u0001\u0000\u0000\u0000\u00a7"+
		"\u00a9\u0007\u0002\u0000\u0000\u00a8\u00a7\u0001\u0000\u0000\u0000\u00a9"+
		"\u00aa\u0001\u0000\u0000\u0000\u00aa\u00a8\u0001\u0000\u0000\u0000\u00aa"+
		"\u00ab\u0001\u0000\u0000\u0000\u00ab\u00ac\u0001\u0000\u0000\u0000\u00ac"+
		"\u00ae\u0005.\u0000\u0000\u00ad\u00af\u0007\u0002\u0000\u0000\u00ae\u00ad"+
		"\u0001\u0000\u0000\u0000\u00af\u00b0\u0001\u0000\u0000\u0000\u00b0\u00ae"+
		"\u0001\u0000\u0000\u0000\u00b0\u00b1\u0001\u0000\u0000\u0000\u00b1<\u0001"+
		"\u0000\u0000\u0000\u00b2\u00b7\u0005\"\u0000\u0000\u00b3\u00b6\b\u0003"+
		"\u0000\u0000\u00b4\u00b6\u0005\\\u0000\u0000\u00b5\u00b3\u0001\u0000\u0000"+
		"\u0000\u00b5\u00b4\u0001\u0000\u0000\u0000\u00b6\u00b9\u0001\u0000\u0000"+
		"\u0000\u00b7\u00b5\u0001\u0000\u0000\u0000\u00b7\u00b8\u0001\u0000\u0000"+
		"\u0000\u00b8\u00ba\u0001\u0000\u0000\u0000\u00b9\u00b7\u0001\u0000\u0000"+
		"\u0000\u00ba\u00bb\u0005\"\u0000\u0000\u00bb>\u0001\u0000\u0000\u0000"+
		"\u00bc\u00bd\u0007\u0004\u0000\u0000\u00bd\u00be\u0001\u0000\u0000\u0000"+
		"\u00be\u00bf\u0006\u001f\u0000\u0000\u00bf@\u0001\u0000\u0000\u0000\u00c0"+
		"\u00c4\u0005#\u0000\u0000\u00c1\u00c3\b\u0005\u0000\u0000\u00c2\u00c1"+
		"\u0001\u0000\u0000\u0000\u00c3\u00c6\u0001\u0000\u0000\u0000\u00c4\u00c2"+
		"\u0001\u0000\u0000\u0000\u00c4\u00c5\u0001\u0000\u0000\u0000\u00c5\u00c7"+
		"\u0001\u0000\u0000\u0000\u00c6\u00c4\u0001\u0000\u0000\u0000\u00c7\u00c8"+
		"\u0006 \u0000\u0000\u00c8B\u0001\u0000\u0000\u0000\u00c9\u00ca\u0005\""+
		"\u0000\u0000\u00ca\u00cb\u0005\"\u0000\u0000\u00cb\u00cc\u0005\"\u0000"+
		"\u0000\u00cc\u00d0\u0001\u0000\u0000\u0000\u00cd\u00cf\t\u0000\u0000\u0000"+
		"\u00ce\u00cd\u0001\u0000\u0000\u0000\u00cf\u00d2\u0001\u0000\u0000\u0000"+
		"\u00d0\u00d1\u0001\u0000\u0000\u0000\u00d0\u00ce\u0001\u0000\u0000\u0000"+
		"\u00d1\u00d3\u0001\u0000\u0000\u0000\u00d2\u00d0\u0001\u0000\u0000\u0000"+
		"\u00d3\u00d4\u0005\"\u0000\u0000\u00d4\u00d5\u0005\"\u0000\u0000\u00d5"+
		"\u00d6\u0005\"\u0000\u0000\u00d6\u00d7\u0001\u0000\u0000\u0000\u00d7\u00d8"+
		"\u0006!\u0000\u0000\u00d8D\u0001\u0000\u0000\u0000\u00d9\u00db\u0005\r"+
		"\u0000\u0000\u00da\u00d9\u0001\u0000\u0000\u0000\u00da\u00db\u0001\u0000"+
		"\u0000\u0000\u00db\u00dc\u0001\u0000\u0000\u0000\u00dc\u00e0\u0005\n\u0000"+
		"\u0000\u00dd\u00df\u0007\u0006\u0000\u0000\u00de\u00dd\u0001\u0000\u0000"+
		"\u0000\u00df\u00e2\u0001\u0000\u0000\u0000\u00e0\u00de\u0001\u0000\u0000"+
		"\u0000\u00e0\u00e1\u0001\u0000\u0000\u0000\u00e1F\u0001\u0000\u0000\u0000"+
		"\u00e2\u00e0\u0001\u0000\u0000\u0000\u00e3\u00e5\u0007\u0006\u0000\u0000"+
		"\u00e4\u00e3\u0001\u0000\u0000\u0000\u00e5\u00e6\u0001\u0000\u0000\u0000"+
		"\u00e6\u00e4\u0001\u0000\u0000\u0000\u00e6\u00e7\u0001\u0000\u0000\u0000"+
		"\u00e7H\u0001\u0000\u0000\u0000\f\u0000\u009f\u00a5\u00aa\u00b0\u00b5"+
		"\u00b7\u00c4\u00d0\u00da\u00e0\u00e6\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}