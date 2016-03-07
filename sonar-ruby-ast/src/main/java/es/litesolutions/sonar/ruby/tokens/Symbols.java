package es.litesolutions.sonar.ruby.tokens;

import javax.annotation.Nonnull;

public enum Symbols
    implements WithValue
{
    LPAREN("("),
    RPAREN(")"),
    ;

    private final String value;

    Symbols(final String value)
    {
        this.value = value;
    }

    @Override
    public String getName()
    {
        return name();
    }

    @Nonnull
    @Override
    public String getValue()
    {
        return value;
    }
}
