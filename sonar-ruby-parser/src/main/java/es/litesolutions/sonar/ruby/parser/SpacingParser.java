package es.litesolutions.sonar.ruby.parser;

import com.github.fge.grappa.rules.Rule;
import es.litesolutions.sonar.grappa.SonarParserBase;

public class SpacingParser
    extends SonarParserBase
{
    public Rule nl()
    {
        return sequence(optional(cr()), lf());
    }
}
