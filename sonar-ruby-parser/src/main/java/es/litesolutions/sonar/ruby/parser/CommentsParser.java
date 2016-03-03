package es.litesolutions.sonar.ruby.parser;

import com.github.fge.grappa.Grappa;
import com.github.fge.grappa.rules.Rule;
import com.sonar.sslr.api.GenericTokenType;
import es.litesolutions.sonar.grappa.SonarParserBase;

public class CommentsParser
    extends SonarParserBase
{
    protected final SpacingParser spacing
        = Grappa.createParser(SpacingParser.class);

    Rule doRegularComment()
    {
        return sequence('#', zeroOrMore(noneOf("\r\n")));
    }

    public Rule regularComment()
    {
        return sequence(
            doRegularComment(),
            pushToken(GenericTokenType.COMMENT)
        );
    }

    // Note: begins at the beginning of a line
    Rule doInlineDoc()
    {
        return sequence(
            "=begin",
            wsp(),
            // After =begin...
            zeroOrMore(noneOf("\r\n")), spacing.nl(),
            // Lines after that...
            zeroOrMore(
                testNot("=end", wsp()),
                zeroOrMore(noneOf("\r\n"), spacing.nl())
            ),
            "=end", test(wsp())
        );
    }

    public Rule inlineDoc()
    {
        return sequence(
            doInlineDoc(),
            pushToken(GenericTokenType.COMMENT)
        );
    }
}
