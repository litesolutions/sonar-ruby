package es.litesolutions.sonar.ruby.parser;

import com.github.fge.grappa.rules.Rule;
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
}
