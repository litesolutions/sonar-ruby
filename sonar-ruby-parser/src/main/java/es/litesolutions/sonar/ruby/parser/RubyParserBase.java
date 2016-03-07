package es.litesolutions.sonar.ruby.parser;

import com.github.fge.grappa.annotations.Cached;
import com.github.fge.grappa.annotations.DontExtend;
import com.github.fge.grappa.rules.Rule;
import com.sonar.sslr.api.TokenType;
import es.litesolutions.sonar.grappa.SonarParserBase;
import es.litesolutions.sonar.ruby.tokens.WithValue;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RubyParserBase
    extends SonarParserBase
{
    protected <T extends WithValue> Rule token(final T tokenType)
    {
        return sequence(tokenType.getValue(), pushToken(tokenType));
    }

    /**
     * Try and match a token from a given set of value
     *
     * @param f function to obtain the token from a matched string
     * @param tokens the list of possible token values
     * @param <T> type of the token, implementing {@link WithValue}
     * @return true if at least one token value matches
     */
    // TODO: create a version with a Collection
    @SuppressWarnings("unchecked")
    @Cached
    public <T extends WithValue> Rule oneTokenAmong(final Function<String, T> f,
        final T... tokens)
    {
        return sequence(
            buildTrie(tokens),
            pushToken(f.apply(match()))
        );
    }

    // TODO: create a version with a Collection
    @SuppressWarnings("unchecked")
    @DontExtend
    protected <T extends WithValue> Rule buildTrie(final T... tokens)
    {
        if (tokens.length == 0)
            throw new IllegalStateException("token list must not be empty");

        final List<String> list = Arrays.stream(tokens)
            .map(TokenType::getValue)
            .collect(Collectors.toList());

        return trie(list);
    }
}
