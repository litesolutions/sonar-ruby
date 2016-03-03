package es.litesolutions.sonar.ruby.parser;

import com.github.fge.grappa.annotations.Cached;
import com.github.fge.grappa.rules.Rule;
import com.sonar.sslr.api.TokenType;
import es.litesolutions.sonar.grappa.SonarParserBase;
import es.litesolutions.sonar.ruby.tokens.Literals;

// @formatter:off
public class LiteralsParser
    extends SonarParserBase
{
    /*
     * STRING
     */
    Rule doubleQuotedBackslashSequence()
    {
        return sequence(
            '\\',
            firstOf(
                anyOf("\"\\tnrfbaes"),
                sequence(digit(), optional(digit()), optional(digit())),
                sequence('x', hexDigit(), hexDigit()),
                sequence(metaCtl(), ANY),
                ANY
            )
        );
    }

    Rule metaCtl()
    {
        return firstOf(
            'c',
            sequence("M-", optional("\\C-")),
            "C-"
        );
    }

    Rule doubleQuotedString()
    {
        return sequence(
            '"',
            join(zeroOrMore(noneOf("\"\\")))
                .using(doubleQuotedBackslashSequence())
                .min(0),
            '"'
        );
    }

    Rule singleQuotedString()
    {
        return sequence(
            '\'',
            join(zeroOrMore(noneOf("'\\")))
                .using(sequence('\\', anyOf("'\\")))
                .min(0),
            '\''
        );
    }

    public Rule stringLiteral()
    {
        return sequence(
            firstOf(
                doubleQuotedString(),
                singleQuotedString()
            ),
            pushToken(Literals.STRING)
        );
    }

    /*
     * IDENTIFIERS
     */

    public Rule identifier()
    {
        return sequence(
            firstOf(alpha(), '_'),
            join(zeroOrMore(firstOf(alpha(), digit())))
                .using('_')
                .min(0)
        );
    }

    @Cached
    public Rule identifier(final TokenType tokenType)
    {
        return sequence(identifier(), pushToken(tokenType));
    }
}
