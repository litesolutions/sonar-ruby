package es.litesolutions.sonar.ruby.parser.helpers;

import com.github.fge.grappa.Grappa;
import com.github.fge.grappa.rules.Rule;
import es.litesolutions.sonar.ruby.parser.LiteralsParser;
import es.litesolutions.sonar.ruby.parser.RubyParser;

public class RubyParserTest
    extends RubyParser
{
    protected final LiteralsParser literals
        = Grappa.createParser(LiteralsParser.class);

    public Rule stringLiteral()
    {
        return literals.stringLiteral();
    }

    public Rule number()
    {
        return literals.number();
    }
}
