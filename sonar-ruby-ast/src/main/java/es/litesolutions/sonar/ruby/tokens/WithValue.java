package es.litesolutions.sonar.ruby.tokens;

import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.TokenType;

import javax.annotation.Nonnull;

/**
 * A {@link TokenType} whose value is also the expected matching text
 *
 * <p>The default {@link #getValue()} implementation is to return the same as
 * {@link #getName()}.</p>
 */
@FunctionalInterface
public interface WithValue
    extends TokenType
{
    @Override
    @Nonnull
    default String getValue()
    {
        return getName();
    }

    @Override
    default boolean hasToBeSkippedFromAst(@Nonnull final AstNode node)
    {
        return false;
    }
}
