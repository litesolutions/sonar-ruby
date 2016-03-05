package es.litesolutions.sonar.ruby.parser;

import com.sonar.sslr.api.Token;
import es.litesolutions.sonar.ruby.grammar.FunctionGrammar;
import es.litesolutions.sonar.ruby.parser.helpers.ParsingTest;
import es.litesolutions.sonar.ruby.parser.helpers.RubyParserTest;
import es.litesolutions.sonar.ruby.parser.helpers.TokenList;
import es.litesolutions.sonar.ruby.tokens.Elements;
import es.litesolutions.sonar.ruby.tokens.Keywords;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class FunctionDeclarationParsingTest
    extends ParsingTest
{
    public FunctionDeclarationParsingTest()
    {
        super(FunctionGrammar.DECLARATION, RubyParserTest::functionDeclaration);
    }

    @Override
    protected Iterator<Object[]> parsingData()
        throws IOException
    {
        final List<Object[]> list = new ArrayList<>();

        String input;
        List<Token> tokens;

        input = "def x";
        tokens = TokenList.create()
            .add(1, 0, Keywords.DEF, "def")
            .add(1, 4, Elements.FUNCTION, "x")
            .build();
        list.add(new Object[] { input, tokens });

        input = "def x()";
        tokens = TokenList.create()
            .add(1, 0, Keywords.DEF, "def")
            .add(1, 4, Elements.FUNCTION, "x")
            .build();
        list.add(new Object[] { input, tokens });

        input = "def   fooo ( bar, baz )";
        tokens = TokenList.create()
            .add(1, 0, Keywords.DEF, "def")
            .add(1, 6, Elements.FUNCTION, "fooo")
            .add(1, 13, Elements.ARGUMENT, "bar")
            .add(1, 18, Elements.ARGUMENT, "baz")
            .build();
        list.add(new Object[] { input, tokens });

        return list.iterator();
    }
}
