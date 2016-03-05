package es.litesolutions.sonar.ruby.parser;

import com.github.fge.grappa.Grappa;
import com.github.fge.grappa.rules.Rule;
import es.litesolutions.sonar.grappa.SonarParserBase;

public class RubyParser
    extends SonarParserBase
{
    protected final LiteralsParser literals
        = Grappa.createParser(LiteralsParser.class);
    protected final IdentifiersParser identifiers
        = Grappa.createParser(IdentifiersParser.class);
    protected final FunctionParser function
        = Grappa.createParser(FunctionParser.class);

    public Rule file()
    {
        return NOTHING;
    }
}
