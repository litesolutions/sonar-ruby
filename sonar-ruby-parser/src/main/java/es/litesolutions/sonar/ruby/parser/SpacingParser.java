package es.litesolutions.sonar.ruby.parser;

import com.github.fge.grappa.rules.Rule;
import es.litesolutions.sonar.grappa.SonarParserBase;

public class SpacingParser
    extends SonarParserBase
{
    public Rule spaces()
    {
        return repeat(wsp()).min(1);
    }

    public Rule nl()
    {
        return sequence(optional(cr()), lf());
    }

    public Rule spaces(final char separator)
    {
        return sequence(
            repeat(wsp()).min(0),
            separator,
            repeat(wsp()).min(0)
        );
    }
}
