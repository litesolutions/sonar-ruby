package es.litesolutions.sonar.ruby.grammar;

import es.litesolutions.sonar.ruby.tokens.Variables;
import org.sonar.sslr.grammar.GrammarRuleKey;
import org.sonar.sslr.grammar.LexerfulGrammarBuilder;

public enum IdentifiersGrammar
    implements GrammarRuleKey
{
    VARIABLE,
    ;

    public static void injectInto(final LexerfulGrammarBuilder builder)
    {
        builder.rule(VARIABLE).is(
            builder.firstOf(
                Variables.LOCAL,
                Variables.INSTANCE,
                Variables.CONSTANT,
                Variables.GLOBAL
            )
        );
    }
}
