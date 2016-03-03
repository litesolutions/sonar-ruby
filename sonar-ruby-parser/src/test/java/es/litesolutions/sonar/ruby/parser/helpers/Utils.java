package es.litesolutions.sonar.ruby.parser.helpers;

import com.sonar.sslr.api.Token;
import org.assertj.core.api.AutoCloseableSoftAssertions;

import java.util.List;

public final class Utils
{
    private Utils()
    {
        throw new Error("nice try!");
    }


    public static void checkTokenList(final List<Token> actual,
        final List<Token> expected)
    {
        final int actualSize = actual.size();
        final int expectedSize = expected.size();

        final int minSize = Math.min(actualSize, expectedSize);

        try (
            final AutoCloseableSoftAssertions soft
                = new AutoCloseableSoftAssertions();
        ) {
            for (int index = 0; index < minSize; index++)
                checkOneToken(soft, index, actual.get(index),
                    expected.get(index));

            soft.assertThat(actual)
                .as("number of tokens")
                .hasSameSizeAs(expected);
        }
    }

    private static void checkOneToken(final AutoCloseableSoftAssertions soft,
        final int index, final Token actualToken, final Token expectedToken)
    {
        soft.assertThat(actualToken.getLine())
            .as("line number (#" + index + ')')
            .isEqualTo(expectedToken.getLine());
        soft.assertThat(actualToken.getColumn())
            .as("column number (#" + index + ')')
            .isEqualTo(expectedToken.getColumn());
        soft.assertThat(actualToken.getType())
            .as("token type (#" + index + ')')
            .isEqualTo(expectedToken.getType());
        soft.assertThat(actualToken.getValue())
            .as("token value (#" + index + ')')
            .isEqualTo(expectedToken.getValue());
    }
}
