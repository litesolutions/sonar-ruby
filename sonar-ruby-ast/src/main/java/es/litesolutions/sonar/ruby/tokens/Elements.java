package es.litesolutions.sonar.ruby.tokens;

import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.TokenType;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public enum Elements
    implements TokenType
{
    FUNCTION,
    ARGUMENT,
    ;

    @Override
    public String getName()
    {
        return name();
    }

    @Override
    public String getValue()
    {
        return name();
    }

    @Override
    public boolean hasToBeSkippedFromAst(final AstNode node)
    {
        return false;
    }
}
