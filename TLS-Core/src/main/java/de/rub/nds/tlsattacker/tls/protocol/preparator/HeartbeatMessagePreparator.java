/**
 * TLS-Attacker - A Modular Penetration Testing Framework for TLS
 *
 * Copyright 2014-2016 Ruhr University Bochum / Hackmanit GmbH
 *
 * Licensed under Apache License 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package de.rub.nds.tlsattacker.tls.protocol.preparator;

import de.rub.nds.tlsattacker.tls.constants.HeartbeatMessageType;
import de.rub.nds.tlsattacker.tls.exceptions.ConfigurationException;
import de.rub.nds.tlsattacker.tls.protocol.message.HeartbeatMessage;
import de.rub.nds.tlsattacker.tls.workflow.TlsContext;
import de.rub.nds.tlsattacker.util.RandomHelper;
import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Robert Merget - robert.merget@rub.de
 */
public class HeartbeatMessagePreparator extends ProtocolMessagePreparator<HeartbeatMessage> {

    private static final Logger LOGGER = LogManager.getLogger("PREPARATOR");

    private final HeartbeatMessage msg;

    public HeartbeatMessagePreparator(TlsContext context, HeartbeatMessage message) {
        super(context, message);
        this.msg = message;
    }

    private byte[] generatePayload() {
        byte[] payload = new byte[context.getConfig().getHeartbeatPayloadLength()];
        RandomHelper.getRandom().nextBytes(payload);
        return payload;
    }

    private byte[] generatePadding() {
        int min = context.getConfig().getHeartbeatMinPaddingLength();
        int max = context.getConfig().getHeartbeatMaxPaddingLength();
        if (max < min) { // TODO perhaps check somewhere different
            throw new ConfigurationException(
                    "Heartbeat minimum padding Length is greater than Heartbeat maxmimum padding length");
        }
        int paddingLength = RandomHelper.getRandom().nextInt(max - min) + min;
        byte[] padding = new byte[paddingLength];
        RandomHelper.getRandom().nextBytes(padding);
        return padding;
    }

    @Override
    protected void prepareProtocolMessageContents() {
        // TODO currently only requests supported
        prepareHeartbeatMessageType(msg);
        preparePayload(msg);
        preparePayloadLength(msg);
        preparePadding(msg);
    }

    private void prepareHeartbeatMessageType(HeartbeatMessage msg) {
        msg.setHeartbeatMessageType(HeartbeatMessageType.HEARTBEAT_REQUEST.getValue());
        LOGGER.debug("HeartbeatMessageType: " + msg.getHeartbeatMessageType().getValue());
    }

    private void preparePayload(HeartbeatMessage msg) {
        msg.setPayload(generatePayload());
        LOGGER.debug("Payload: " + Arrays.toString(msg.getPayload().getValue()));
    }

    private void preparePayloadLength(HeartbeatMessage msg) {
        msg.setPayloadLength(msg.getPayload().getValue().length);
        LOGGER.debug("PayloadLength: " + msg.getPayloadLength().getValue());
    }

    private void preparePadding(HeartbeatMessage msg) {
        msg.setPadding(generatePadding());
        LOGGER.debug("Padding: " + Arrays.toString(msg.getPadding().getValue()));
    }
}
