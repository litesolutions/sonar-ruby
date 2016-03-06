package es.litesolutions.sonar.ruby.parser;

import com.github.fge.grappa.Grappa;
import com.github.fge.grappa.rules.Rule;
import es.litesolutions.sonar.grappa.SonarParserBase;

// @formatter:off
public class RubyParser
    extends SonarParserBase
{
    protected final LiteralsParser literals
        = Grappa.createParser(LiteralsParser.class);
    protected final IdentifiersParser identifiers
        = Grappa.createParser(IdentifiersParser.class);
    protected final FunctionParser function
        = Grappa.createParser(FunctionParser.class);

    public Rule operand()
    {
        return firstOf(
            identifiers.localVar(),
            identifiers.instanceVar(),
            identifiers.constant(),
            identifiers.global(),
            literals.stringLiteral(),
            literals.number()
        );
    }

    public Rule file()
    {
        return NOTHING;
    }
}
