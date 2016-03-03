package es.litesolutions.sonar.ruby.tokens;

import javax.annotation.Nonnull;

public enum Special
    implements WithValue
{
    SELF("self"),
    NIL("nil"),
    TRUE("true"),
    FALSE("false"),
    FILE("__FILE__"),
    LINE("__LINE__"),
    ;

    private final String value;

    Special(final String value)
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
