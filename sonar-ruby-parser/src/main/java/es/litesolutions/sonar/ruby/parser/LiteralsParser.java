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
    Rule backslashNotation()
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
                .using(backslashNotation())
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
     * NUMBERS
     */

    // Note: in fact, this also swallows octals. Meh.
    Rule regularNumber()
    {
        return sequence(
            join(oneOrMore(digit()))
                .using('_')
                .min(1),
            optional(
                '.',
                join(oneOrMore(digit()))
                    .using('_')
                    .min(1)
            )
        );
    }

    Rule hexadecimalNumber()
    {
        return sequence(
            ignoreCase("0x"),
            join(oneOrMore(hexDigit()))
                .using('_')
                .min(1)
        );
    }

    Rule binaryNumber()
    {
        return sequence(
            ignoreCase("0b"),
            join(oneOrMore(bit()))
                .using('_')
                .min(1)
        );
    }

    Rule exponent()
    {
        return sequence(
            ignoreCase('e'),
            optional('-'),
            regularNumber()
        );
    }

    Rule doNumber()
    {
        return sequence(
            zeroOrMore('-'),
            firstOf(hexadecimalNumber(), binaryNumber(), regularNumber()),
            optional(exponent())
        );
    }

    public Rule number()
    {
        return sequence(doNumber(), pushToken(Literals.NUMBER));
    }
}
