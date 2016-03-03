package es.litesolutions.sonar.ruby.grammar;

import es.litesolutions.sonar.ruby.tokens.Elements;
import org.sonar.sslr.grammar.GrammarRuleKey;
import org.sonar.sslr.grammar.LexerfulGrammarBuilder;

public enum IdentifiersGrammar
    implements GrammarRuleKey
{
    FUNCTION,
    ;

    public static void injectInto(final LexerfulGrammarBuilder builder)
    {
        builder.rule(FUNCTION).is(Elements.FUNCTION);
    }
}
