package es.litesolutions.sonar.ruby.grammar;

import es.litesolutions.sonar.ruby.tokens.Elements;
import es.litesolutions.sonar.ruby.tokens.Keywords;
import org.sonar.sslr.grammar.GrammarRuleKey;
import org.sonar.sslr.grammar.LexerfulGrammarBuilder;

public enum FunctionGrammar
    implements GrammarRuleKey
{
    ARGUMENTS,
    DECLARATION,
    ;

    public static void injectInto(final LexerfulGrammarBuilder builder)
    {
        builder.rule(ARGUMENTS).is(
            builder.zeroOrMore(Elements.ARGUMENT)
        ).skip();

        builder.rule(DECLARATION).is(
            Keywords.DEF, Elements.FUNCTION, ARGUMENTS
        );
    }
}
