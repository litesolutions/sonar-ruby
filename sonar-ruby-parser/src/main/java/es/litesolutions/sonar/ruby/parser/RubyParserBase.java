package es.litesolutions.sonar.ruby.parser;

import com.github.fge.grappa.rules.Rule;
import es.litesolutions.sonar.grappa.SonarParserBase;
import es.litesolutions.sonar.ruby.tokens.WithValue;

public class RubyParserBase
    extends SonarParserBase
{
    protected <T extends WithValue> Rule token(final T tokenType)
    {
        return sequence(tokenType.getValue(), pushToken(tokenType));
    }
}
