package es.litesolutions.sonar.ruby.parser.helpers;

import com.github.fge.grappa.rules.Rule;
import com.sonar.sslr.api.Grammar;
import com.sonar.sslr.api.Token;
import com.sonar.sslr.impl.Parser;
import es.litesolutions.sonar.grappa.GrappaSslrFactory;
import es.litesolutions.sonar.ruby.grammar.RubyGrammar;
import org.sonar.sslr.grammar.GrammarRuleKey;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import r.com.google.common.io.CharStreams;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

@Test
public abstract class ParsingTest
{
    private static final Class<?> ME = ParsingTest.class;
    private static final Charset UTF8 = StandardCharsets.UTF_8;

    private final Parser<Grammar> parser;

    protected ParsingTest(final GrammarRuleKey key,
        final Function<RubyParserTest, Rule> ruleFunction)
    {
        parser = GrappaSslrFactory.withParserClass(RubyParserTest.class)
            .withGrammarClass(RubyGrammar.class)
            .withEntryPoint(key)
            .withMainRule(ruleFunction)
            .build()
            .getParser();
    }

    protected abstract Iterator<Object[]> parsingData()
        throws IOException;

    @DataProvider
    protected Iterator<Object[]> data()
        throws IOException
    {
        return parsingData();
    }

    @Test(dataProvider = "data")
    public final void astNodeTest(final String input,
        final List<Token> actualTokens)
    {
        final List<Token> expectedTokens = parser.parse(input).getTokens();

        Utils.checkTokenList(actualTokens, expectedTokens);
    }

    protected static String fromResource(final String resourcePath)
        throws IOException
    {
        final URL r = ME.getResource(resourcePath);

        if (r == null)
            throw new IOException(resourcePath + " not found");

        try (
            final InputStream in = r.openStream();
            final InputStreamReader reader = new InputStreamReader(in, UTF8);
        ) {
            return CharStreams.toString(reader).trim();
        }
    }
}
