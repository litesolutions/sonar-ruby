package es.litesolutions.sonar.ruby.parser;

import com.sonar.sslr.api.Token;
import es.litesolutions.sonar.ruby.grammar.IdentifiersGrammar;
import es.litesolutions.sonar.ruby.parser.helpers.ParsingTest;
import es.litesolutions.sonar.ruby.parser.helpers.RubyParserTest;
import es.litesolutions.sonar.ruby.parser.helpers.TokenList;
import es.litesolutions.sonar.ruby.tokens.Variables;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class VarParsingTest
    extends ParsingTest
{
    public VarParsingTest()
    {
        super(IdentifiersGrammar.VARIABLE, RubyParserTest::var);
    }

    @Override
    protected Iterator<Object[]> parsingData()
        throws IOException
    {
        final List<Object[]> list = new ArrayList<>();

        String input;
        List<Token> tokens;

        input = "__";
        tokens = TokenList.create()
            .add(1, 0, Variables.LOCAL, input)
            .build();
        list.add(new Object[] { input, tokens });

        input = "Foo_bar";
        tokens = TokenList.create()
            .add(1, 0, Variables.CONSTANT, input)
            .build();
        list.add(new Object[] { input, tokens });

        input = "@meh";
        tokens = TokenList.create()
            .add(1, 0, Variables.INSTANCE, input)
            .build();
        list.add(new Object[] { input, tokens });

        input = "$/";
        tokens = TokenList.create()
            .add(1, 0, Variables.GLOBAL, input)
            .build();
        list.add(new Object[] { input, tokens });

        input = "i";
        tokens = TokenList.create()
            .add(1, 0, Variables.LOCAL, input)
            .build();
        list.add(new Object[] { input, tokens });

        return list.iterator();
    }
}
