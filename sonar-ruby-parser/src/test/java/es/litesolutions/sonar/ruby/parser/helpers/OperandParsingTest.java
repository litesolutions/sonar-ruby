package es.litesolutions.sonar.ruby.parser.helpers;

import com.sonar.sslr.api.Token;
import es.litesolutions.sonar.ruby.grammar.OperandGrammar;
import es.litesolutions.sonar.ruby.tokens.Variables;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class OperandParsingTest
    extends ParsingTest
{
    public OperandParsingTest()
    {
        super(OperandGrammar.OPERAND, RubyParserTest::operand);
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

        return list.iterator();
    }
}
