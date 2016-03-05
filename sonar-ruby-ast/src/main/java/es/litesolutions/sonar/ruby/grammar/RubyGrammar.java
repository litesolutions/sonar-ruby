package es.litesolutions.sonar.ruby.grammar;

import org.sonar.sslr.grammar.GrammarRuleKey;
import org.sonar.sslr.grammar.LexerfulGrammarBuilder;

public enum RubyGrammar
    implements GrammarRuleKey
{
    FILE,
    ;

    public static void injectInto(final LexerfulGrammarBuilder builder)
    {
        LiteralsGrammar.injectInto(builder);
        IdentifiersGrammar.injectInto(builder);
        FunctionGrammar.injectInto(builder);

        // For now...
        builder.rule(FILE).is(LiteralsGrammar.STRING);
    }
}
