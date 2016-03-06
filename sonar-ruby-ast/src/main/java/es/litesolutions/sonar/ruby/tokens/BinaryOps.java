package es.litesolutions.sonar.ruby.tokens;

import javax.annotation.Nonnull;

public enum BinaryOps
    implements WithValue
{
    // Numeric
    PLUS("+"),
    MINUS("-"),
    DIVIDE("/"),
    MULTIPLY("*"),
    POWER("**"),
    ;

    private final String value;

    BinaryOps(final String value)
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
