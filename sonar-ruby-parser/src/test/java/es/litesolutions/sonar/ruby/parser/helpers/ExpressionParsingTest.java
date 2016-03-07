package es.litesolutions.sonar.ruby.parser.helpers;

import com.sonar.sslr.api.Token;
import es.litesolutions.sonar.ruby.grammar.ExpressionGrammar;
import es.litesolutions.sonar.ruby.tokens.BinaryOps;
import es.litesolutions.sonar.ruby.tokens.Literals;
import es.litesolutions.sonar.ruby.tokens.Symbols;
import es.litesolutions.sonar.ruby.tokens.Variables;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class ExpressionParsingTest
    extends ParsingTest
{
    public ExpressionParsingTest()
    {
        super(ExpressionGrammar.EXPRESSION, RubyParserTest::expression);
    }

    @Override
    protected Iterator<Object[]> parsingData()
        throws IOException
    {
        final List<Object[]> list = new ArrayList<>();

        String input;
        List<Token> tokens;

        input = "$/";
        tokens = TokenList.create()
            .add(1, 0, Variables.GLOBAL, input)
            .build();
        list.add(new Object[] { input, tokens });

        input = "Foo";
        tokens = TokenList.create()
            .add(1, 0, Variables.CONSTANT, input)
            .build();
        list.add(new Object[] { input, tokens });

        input = "( 4 ** e)";
        tokens = TokenList.create()
            .add(1, 0, Symbols.LPAREN, "(")
            .add(1, 2, Literals.NUMBER, "4")
            .add(1, 4, BinaryOps.POWER, "**")
            .add(1, 7, Variables.LOCAL, "e")
            .add(1, 8, Symbols.RPAREN, ")")
            .build();
        list.add(new Object[] { input, tokens });

        input = "1 + ($_ * 'r')";
        tokens = TokenList.create()
            .add(1, 0, Literals.NUMBER, "1")
            .add(1, 2, BinaryOps.PLUS, "+")
            .add(1, 4, Symbols.LPAREN, "(")
            .add(1, 5, Variables.GLOBAL, "$_")
            .add(1, 8, BinaryOps.MULTIPLY, "*")
            .add(1, 10, Literals.STRING, "'r'")
            .add(1, 13, Symbols.RPAREN, ")")
            .build();
        list.add(new Object[] { input, tokens });

        return list.iterator();
    }
}
