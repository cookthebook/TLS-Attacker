/**
 * TLS-Attacker - A Modular Penetration Testing Framework for TLS
 *
 * Copyright 2014-2016 Ruhr University Bochum / Hackmanit GmbH
 *
 * Licensed under Apache License 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package tlsattacker.fuzzer.mutator;

import de.rub.nds.tlsattacker.tests.IntegrationTest;
import tlsattacker.fuzzer.mutator.certificate.FixedCertificateMutator;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import tlsattacker.fuzzer.config.EvolutionaryFuzzerConfig;

/**
 * 
 * @author Robert Merget - robert.merget@rub.de
 */
public class SimpleMutatorTest {

    @Test
    @Category(IntegrationTest.class)
    public void testMutation() {
        EvolutionaryFuzzerConfig config = new EvolutionaryFuzzerConfig();
        SimpleMutator mutator = new SimpleMutator(config, new FixedCertificateMutator(config));
        mutator.getNewMutation();
    }
}
