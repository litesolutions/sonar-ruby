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

public final class StringLiteralsParsingTest
    extends ParsingTest
{
    public StringLiteralsParsingTest()
    {
        super(LiteralsGrammar.STRING, RubyParserTest::stringLiteral);
    }

    @Override
    protected Iterator<Object[]> parsingData()
        throws IOException
    {
        final List<Object[]> list = new ArrayList<>();

        String input;
        List<Token> tokens;

        input = "\"hello\"";
        tokens = TokenList.create()
            .add(1, 0, Literals.STRING, input)
            .build();
        list.add(new Object[] { input, tokens });

        input = "\"\\0\"";
        tokens = TokenList.create()
            .add(1, 0, Literals.STRING, input)
            .build();
        list.add(new Object[] { input, tokens });

        input = "\"Hello world!\\n\"";
        tokens = TokenList.create()
            .add(1, 0, Literals.STRING, input)
            .build();
        list.add(new Object[] { input, tokens });

        input = "\"\\xefhello\\\\\\\"\"";
        tokens = TokenList.create()
            .add(1, 0, Literals.STRING, input)
            .build();
        list.add(new Object[] { input, tokens });

        input = "\"\\C-e\"";
        tokens = TokenList.create()
            .add(1, 0, Literals.STRING, input)
            .build();
        list.add(new Object[] { input, tokens });

        input = "'hello'";
        tokens = TokenList.create()
            .add(1, 0, Literals.STRING, input)
            .build();
        list.add(new Object[] { input, tokens });

        input = "'\\\''";
        tokens = TokenList.create()
            .add(1, 0, Literals.STRING, input)
            .build();
        list.add(new Object[] { input, tokens });

        input = "''";
        tokens = TokenList.create()
            .add(1, 0, Literals.STRING, input)
            .build();
        list.add(new Object[] { input, tokens });

        input = "'This is a backslash: \\\\'";
        tokens = TokenList.create()
            .add(1, 0, Literals.STRING, input)
            .build();
        list.add(new Object[] { input, tokens });

        return list.iterator();
    }
}
