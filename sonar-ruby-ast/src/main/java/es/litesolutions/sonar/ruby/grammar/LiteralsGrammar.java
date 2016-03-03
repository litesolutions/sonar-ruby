package es.litesolutions.sonar.ruby.grammar;

import es.litesolutions.sonar.ruby.tokens.Literals;
import org.sonar.sslr.grammar.GrammarRuleKey;
import org.sonar.sslr.grammar.LexerfulGrammarBuilder;

public enum LiteralsGrammar
    implements GrammarRuleKey
{
    STRING,
    NUMBER,
    ;

    public static void injectInto(final LexerfulGrammarBuilder builder)
    {
        builder.rule(STRING).is(Literals.STRING);
        builder.rule(NUMBER).is(Literals.NUMBER);
    }
}
