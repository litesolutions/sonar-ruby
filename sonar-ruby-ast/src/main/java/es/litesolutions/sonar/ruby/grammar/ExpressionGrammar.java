package es.litesolutions.sonar.ruby.grammar;

import es.litesolutions.sonar.ruby.tokens.BinaryOps;
import es.litesolutions.sonar.ruby.tokens.Literals;
import es.litesolutions.sonar.ruby.tokens.Symbols;
import org.sonar.sslr.grammar.GrammarRuleKey;
import org.sonar.sslr.grammar.LexerfulGrammarBuilder;

import java.util.Arrays;

public enum ExpressionGrammar
    implements GrammarRuleKey
{
    OPERAND,
    EXPRESSION,
    ;

    public static void injectInto(final LexerfulGrammarBuilder builder)
    {
        builder.rule(OPERAND).is(
            builder.firstOf(
                IdentifiersGrammar.VARIABLE,
                Literals.STRING,
                Literals.NUMBER,
                builder.sequence(
                    Symbols.LPAREN,
                    EXPRESSION,
                    Symbols.RPAREN
                )
            )
        );

        builder.rule(EXPRESSION).is(
            builder.firstOf(
                builder.sequence(
                    OPERAND,
                    builder.firstOf(
                        BinaryOps.values()[0],
                        BinaryOps.values()[1],
                        Arrays.stream(BinaryOps.values())
                            .skip(2L)
                            .toArray(BinaryOps[]::new)
                    ),
                    OPERAND
                ),
                OPERAND
            )
        );
    }
}
