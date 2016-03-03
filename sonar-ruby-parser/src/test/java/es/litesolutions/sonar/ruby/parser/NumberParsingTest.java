package es.litesolutions.sonar.ruby.parser;

import com.sonar.sslr.api.Token;
import es.litesolutions.sonar.ruby.grammar.LiteralsGrammar;
import es.litesolutions.sonar.ruby.parser.helpers.ParsingTest;
import es.litesolutions.sonar.ruby.parser.helpers.RubyParserTest;
import es.litesolutions.sonar.ruby.parser.helpers.TokenList;
import es.litesolutions.sonar.ruby.tokens.Literals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class NumberParsingTest
    extends ParsingTest
{
    public NumberParsingTest()
    {
        super(LiteralsGrammar.NUMBER, RubyParserTest::number);
    }

    @Override
    protected Iterator<Object[]> parsingData()
        throws IOException
    {
        final List<Object[]> list = new ArrayList<>();

        String input;
        List<Token> tokens;

        input = "1";
        tokens = TokenList.create()
            .add(1, 0, Literals.NUMBER, input)
            .build();
        list.add(new Object[] { input, tokens });

        input = "0xfae4A";
        tokens = TokenList.create()
            .add(1, 0, Literals.NUMBER, input)
            .build();
        list.add(new Object[] { input, tokens });

        input = "0b00_1111_0_1";
        tokens = TokenList.create()
            .add(1, 0, Literals.NUMBER, input)
            .build();
        list.add(new Object[] { input, tokens });

        input = "3.14";
        tokens = TokenList.create()
            .add(1, 0, Literals.NUMBER, input)
            .build();
        list.add(new Object[] { input, tokens });

        input = "3.14e9_0";
        tokens = TokenList.create()
            .add(1, 0, Literals.NUMBER, input)
            .build();
        list.add(new Object[] { input, tokens });

        return list.iterator();
    }
}
