package es.litesolutions.sonar.ruby.parser.helpers;

import com.github.fge.grappa.rules.Rule;
import es.litesolutions.sonar.ruby.parser.RubyParser;

public class RubyParserTest
    extends RubyParser
{
    public Rule stringLiteral()
    {
        return literals.stringLiteral();
    }

    public Rule number()
    {
        return literals.number();
    }

    public Rule var()
    {
        return identifiers.var();
    }

    public Rule functionDeclaration()
    {
        return function.declaration();
    }
}
