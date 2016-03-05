package es.litesolutions.sonar.ruby.parser;

import com.github.fge.grappa.annotations.Cached;
import com.github.fge.grappa.rules.Rule;
import com.sonar.sslr.api.TokenType;
import es.litesolutions.sonar.grappa.SonarParserBase;
import es.litesolutions.sonar.ruby.tokens.Variables;

public class IdentifiersParser
    extends SonarParserBase
{
    Rule idenfitierCont()
    {
        return join(zeroOrMore(firstOf(alpha(), digit())))
            .using('_')
            .min(0);
    }
    Rule regularIdentifier()
    {
        return sequence(
            firstOf(charRange('a', 'z'), '_'),
            idenfitierCont()
        );
    }

    @Cached
    public Rule regular(final TokenType type)
    {
        return sequence(regularIdentifier(), pushToken(type));
    }

    public Rule localVar()
    {
        return regular(Variables.LOCAL);
    }

    Rule doConstant()
    {
        return sequence(
            charRange('A', 'Z'),
            idenfitierCont()
        );
    }

    public Rule constant()
    {
        return sequence(doConstant(), pushToken(Variables.CONSTANT));
    }

    // FIXME: probably not accurate
    Rule doGlobal()
    {
        return sequence('$', oneOrMore(noneOf(" \t\r\n")));
    }

    public Rule global()
    {
        return sequence(doGlobal(), pushToken(Variables.GLOBAL));
    }

    Rule doInstanceVar()
    {
        return sequence('@', regularIdentifier());
    }

    public Rule instanceVar()
    {
        return sequence(doInstanceVar(), pushToken(Variables.INSTANCE));
    }

    public Rule var()
    {
        return firstOf(
            localVar(),
            instanceVar(),
            constant(),
            global()
        );
    }
}
