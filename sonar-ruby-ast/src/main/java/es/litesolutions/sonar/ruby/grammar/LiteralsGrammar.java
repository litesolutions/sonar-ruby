package es.litesolutions.sonar.ruby.grammar;

import es.litesolutions.sonar.ruby.tokens.Literals;
import org.sonar.sslr.grammar.GrammarRuleKey;
import org.sonar.sslr.grammar.LexerfulGrammarBuilder;

public enum LiteralsGrammar
    implements GrammarRuleKey
{
    STRING,
    ;

    public static void injectInto(final LexerfulGrammarBuilder builder)
    {
        builder.rule(STRING).is(Literals.STRING);
    }
}
