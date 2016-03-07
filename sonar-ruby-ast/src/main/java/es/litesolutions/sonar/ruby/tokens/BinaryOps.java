package es.litesolutions.sonar.ruby.tokens;

import com.google.common.collect.ImmutableMap;

import javax.annotation.Nonnull;
import java.util.Map;

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

    private static final Map<String, BinaryOps> REVERSE_MAP;

    static {
        final ImmutableMap.Builder<String, BinaryOps> builder
            = ImmutableMap.builder();

        for (final BinaryOps op: values())
            builder.put(op.value, op);

        REVERSE_MAP = builder.build();
    }

    public static BinaryOps fromString(final String input)
    {
        return REVERSE_MAP.get(input);
    }

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
