package es.litesolutions.sonar.ruby.tokens;

import javax.annotation.Nonnull;

public enum Keywords
    implements WithValue
{
    DEF("def"),
    END("end"),
    ;

    private final String value;

    Keywords(final String value)
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
