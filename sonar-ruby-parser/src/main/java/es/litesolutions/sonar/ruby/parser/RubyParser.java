package es.litesolutions.sonar.ruby.parser;

import com.github.fge.grappa.Grappa;
import com.github.fge.grappa.rules.Rule;
import es.litesolutions.sonar.ruby.tokens.BinaryOps;
import es.litesolutions.sonar.ruby.tokens.Symbols;

// @formatter:off
public class RubyParser
    extends RubyParserBase
{
    protected final SpacingParser spacing
        = Grappa.createParser(SpacingParser.class);
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
            literals.number(),
            sequence(
                token(Symbols.LPAREN),
                optional(spacing.spaces()),
                expression(),
                optional(spacing.spaces()),
                token(Symbols.RPAREN)
            )
        );
    }

    public Rule expression()
    {
        return firstOf(
            sequence(
                operand(),
                optional(spacing.spaces()),
                oneTokenAmong(BinaryOps::fromString, BinaryOps.values()),
                optional(spacing.spaces()),
                operand()
            ),
            operand()
        );
    }

    public Rule file()
    {
        return NOTHING;
    }
}
