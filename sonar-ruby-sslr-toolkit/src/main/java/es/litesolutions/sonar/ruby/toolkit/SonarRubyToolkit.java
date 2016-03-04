package es.litesolutions.sonar.ruby.toolkit;

import com.sonar.sslr.api.Grammar;
import com.sonar.sslr.impl.Parser;
import es.litesolutions.sonar.grappa.GrappaSslrFactory;
import es.litesolutions.sonar.ruby.grammar.RubyGrammar;
import es.litesolutions.sonar.ruby.parser.RubyParser;
import org.sonar.colorizer.Tokenizer;
import org.sonar.sslr.toolkit.AbstractConfigurationModel;
import org.sonar.sslr.toolkit.ConfigurationProperty;
import org.sonar.sslr.toolkit.Toolkit;

import java.util.Collections;
import java.util.List;

public final class SonarRubyToolkit
{
    private SonarRubyToolkit()
    {
        throw new Error("nice try!");
    }

    public static void main(final String... args)
    {
        final GrappaSslrFactory factory
            = GrappaSslrFactory.withParserClass(RubyParser.class)
            .withGrammarClass(RubyGrammar.class)
            .withMainRule(RubyParser::file)
            .withEntryPoint(RubyGrammar.FILE)
            .addListenerSupplier(new TracerSupplier())
            .build();

        final Parser<Grammar> sslrParser = factory.getParser();

        final Toolkit toolkit = new Toolkit("foo",
            new DummyConfigurationModel(sslrParser));

        toolkit.run();
    }

    private static final class DummyConfigurationModel
        extends AbstractConfigurationModel
    {
        private final Parser<Grammar> parser;

        private DummyConfigurationModel(final Parser<Grammar> parser)
        {
            this.parser = parser;
        }

        @Override
        public Parser doGetParser()
        {
            return parser;
        }

        @Override
        public List<Tokenizer> doGetTokenizers()
        {
            return Collections.emptyList();
        }

        @Override
        public List<ConfigurationProperty> getProperties()
        {
            return Collections.emptyList();
        }
    }
}
