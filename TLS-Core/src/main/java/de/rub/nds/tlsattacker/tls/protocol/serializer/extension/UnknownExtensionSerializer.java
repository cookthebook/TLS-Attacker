/**
 * TLS-Attacker - A Modular Penetration Testing Framework for TLS
 *
 * Copyright 2014-2016 Ruhr University Bochum / Hackmanit GmbH
 *
 * Licensed under Apache License 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package de.rub.nds.tlsattacker.tls.protocol.serializer.extension;

import de.rub.nds.tlsattacker.tls.protocol.extension.UnknownExtensionMessage;

/**
 *
 * @author Robert Merget - robert.merget@rub.de
 * @param <T>
 */
public class UnknownExtensionSerializer<T extends UnknownExtensionMessage> extends ExtensionSerializer<T> {

    public UnknownExtensionSerializer() {
    }

    @Override
    protected void serializeBytes() {
        throw new UnsupportedOperationException("Not supported yet."); // To
                                                                       // change
                                                                       // body
                                                                       // of
                                                                       // generated
                                                                       // methods,
                                                                       // choose
                                                                       // Tools
                                                                       // |
                                                                       // Templates.
    }

}