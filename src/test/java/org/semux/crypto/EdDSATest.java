/*
 * Copyright (c) 2017 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.crypto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

import org.junit.Assert;
import org.junit.Test;
import org.semux.Config;
import org.semux.utils.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EdDSATest {

    private static Logger logger = LoggerFactory.getLogger(EdDSATest.class);

    @Test
    public void testGenerateKeyPair() throws InvalidKeySpecException {
        EdDSA key1 = new EdDSA();

        assertEquals(44, key1.getPublicKey().length);
        assertEquals(48, key1.getPrivateKey().length);

        EdDSA key2 = new EdDSA(key1.getPublicKey(), key1.getPrivateKey());

        Assert.assertArrayEquals(key1.getPublicKey(), key2.getPublicKey());
        Assert.assertArrayEquals(key1.getPrivateKey(), key2.getPrivateKey());
    }

    @Test
    public void testSignAndVerify() throws SignatureException {
        EdDSA key = new EdDSA();
        byte[] data = Bytes.of("test");

        byte[] hash = Hash.h256(data);
        byte[] sig = key.sign(hash).toBytes();

        boolean isValid = EdDSA.verify(hash, sig);
        assertTrue(isValid);
    }

    @Test
    public void testSignatureSize() {
        EdDSA key = new EdDSA();
        byte[] data = Bytes.of("test");

        byte[] hash = Hash.h256(data);
        byte[] sig = key.sign(hash).toBytes();

        logger.info("signature size: {}", sig.length);
        logger.info("Signature size (1y): {} GB", 64.0 * sig.length * Config.DAY * 365 / 1024 / 1024 / 1024);
    }
}
