package es.litesolutions.sonar.ruby.grammar;

import es.litesolutions.sonar.ruby.tokens.Literals;
import org.sonar.sslr.grammar.GrammarRuleKey;
import org.sonar.sslr.grammar.LexerfulGrammarBuilder;

public enum OperandGrammar
    implements GrammarRuleKey
{
    OPERAND,
    ;

    public static void injectInto(final LexerfulGrammarBuilder builder)
    {
        builder.rule(OPERAND).is(
            builder.firstOf(
                IdentifiersGrammar.VARIABLE,
                Literals.STRING,
                Literals.NUMBER
            )
        );
    }
}
