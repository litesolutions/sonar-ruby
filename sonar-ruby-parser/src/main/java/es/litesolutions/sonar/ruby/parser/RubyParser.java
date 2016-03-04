package es.litesolutions.sonar.ruby.parser;

import com.github.fge.grappa.rules.Rule;
import es.litesolutions.sonar.grappa.SonarParserBase;

public class RubyParser
    extends SonarParserBase
{
    public Rule file()
    {
        return NOTHING;
    }
}
