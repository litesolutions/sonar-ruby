package es.litesolutions.sonar.ruby.parser.helpers;

import com.sonar.sslr.api.Token;
import com.sonar.sslr.api.TokenType;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class TokenList
{
    private static final URI DUMMY_URI = URI.create("foo://bar");

    private final List<Token> tokens = new ArrayList<>();

    public static TokenList create()
    {
        return new TokenList();
    }

    public TokenList add(final int line, final int column,
        final TokenType tokenType, final String value)
    {
        final Token token = Token.builder()
            .setLine(line)
            .setColumn(column)
            .setType(tokenType)
            .setValueAndOriginalValue(value)
            .setURI(DUMMY_URI)
            .build();

        tokens.add(token);

        return this;
    }

    public List<Token> build()
    {
        return Collections.unmodifiableList(tokens);
    }
}
