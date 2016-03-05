package es.litesolutions.sonar.ruby.parser;

import com.github.fge.grappa.Grappa;
import com.github.fge.grappa.rules.Rule;
import es.litesolutions.sonar.ruby.tokens.Elements;
import es.litesolutions.sonar.ruby.tokens.Keywords;

public class FunctionParser
    extends RubyParserBase
{
    protected final SpacingParser spacing
        = Grappa.createParser(SpacingParser.class);
    protected final IdentifiersParser identifiers
        = Grappa.createParser(IdentifiersParser.class);

    Rule arguments()
    {
        return sequence(
            '(',
            optional(spacing.spaces()),
            join(identifiers.regular(Elements.ARGUMENT))
                .using(spacing.spaces(','))
                .min(0),
            optional(spacing.spaces()),
            ')'
        );
    }

    public Rule declaration()
    {
        return sequence(
            token(Keywords.DEF),
            spacing.spaces(),
            identifiers.regular(Elements.FUNCTION),
            optional(spacing.spaces()),
            optional(arguments())
        );
    }
}
