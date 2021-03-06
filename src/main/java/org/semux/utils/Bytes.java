/*
 * Copyright (c) 2017 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.utils;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;

public class Bytes {

    private static SecureRandom secureRandom = new SecureRandom();

    /**
     * Default charset.
     */
    public static final String CHARSET = "UTF-8";

    /**
     * Empty byte array.
     */
    public static final byte[] EMPY_BYTES = new byte[0];

    /**
     * Empty address.
     */
    public static final byte[] EMPTY_ADDRESS = new byte[20];

    /**
     * Empty 256-bit hash.
     */
    public static final byte[] EMPTY_HASH = new byte[32];

    /**
     * Generate a random byte array of required length.
     * 
     * @param n
     * @return
     */
    public static byte[] random(int n) {
        byte[] bytes = new byte[n];
        secureRandom.nextBytes(bytes);

        return bytes;
    }

    /**
     * Merge two byte arrays into one.
     * 
     * @param b1
     * @param b2
     * @return
     */
    public static byte[] merge(byte[] b1, byte[] b2) {
        byte[] res = new byte[b1.length + b2.length];
        System.arraycopy(b1, 0, res, 0, b1.length);
        System.arraycopy(b2, 0, res, b1.length, b2.length);

        return res;
    }

    /**
     * Merge three byte arrays into one.
     * 
     * @param b1
     * @param b2
     * @param b3
     * @return
     */
    public static byte[] merge(byte[] b1, byte[] b2, byte[] b3) {
        byte[] res = new byte[b1.length + b2.length + b3.length];
        System.arraycopy(b1, 0, res, 0, b1.length);
        System.arraycopy(b2, 0, res, b1.length, b2.length);
        System.arraycopy(b3, 0, res, b1.length + b2.length, b3.length);

        return res;
    }

    /**
     * Convert string ininto an byte array.
     * 
     * @param str
     * @return
     */
    public static byte[] of(String str) {
        try {
            return str.getBytes(CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Convert a byte into an byte array.
     * 
     * @param b
     * @return
     */
    public static byte[] of(byte b) {
        return new byte[] { b };
    }

    /**
     * Convert a short integer into an byte array.
     * 
     * @param s
     * @return
     */
    public static byte[] of(short s) {
        byte[] bytes = new byte[2];
        bytes[0] = (byte) ((s >> 8) & 0xff);
        bytes[1] = (byte) (s & 0xff);
        return bytes;
    }

    /**
     * Convert an integer into an byte array.
     * 
     * @param i
     * @return
     */
    public static byte[] of(int i) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) ((i >> 24) & 0xff);
        bytes[1] = (byte) ((i >> 16) & 0xff);
        bytes[2] = (byte) ((i >> 8) & 0xff);
        bytes[3] = (byte) (i & 0xff);
        return bytes;
    }

    /**
     * Convert a long integer into an byte array.
     * 
     * @param i
     * @return
     */
    public static byte[] of(long i) {
        byte[] bytes = new byte[8];
        bytes[0] = (byte) ((i >> 56) & 0xff);
        bytes[1] = (byte) ((i >> 48) & 0xff);
        bytes[2] = (byte) ((i >> 40) & 0xff);
        bytes[3] = (byte) ((i >> 32) & 0xff);
        bytes[4] = (byte) ((i >> 24) & 0xff);
        bytes[5] = (byte) ((i >> 16) & 0xff);
        bytes[6] = (byte) ((i >> 8) & 0xff);
        bytes[7] = (byte) (i & 0xff);
        return bytes;
    }

    /**
     * Convert byte array into string.
     * 
     * @param bytes
     * @return
     */
    public static String toString(byte[] bytes) {
        try {
            return new String(bytes, CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Covert byte array into a byte.
     * 
     * @param bytes
     * @return
     */
    public static byte toByte(byte[] bytes) {
        return bytes[0];
    }

    /**
     * Covert byte array into a short integer.
     * 
     * @param bytes
     * @return
     */
    public static short toShort(byte[] bytes) {
        return (short) (((bytes[0] & 0xff) << 8) | (bytes[1] & 0xff));
    }

    /**
     * Covert byte array into an integer.
     * 
     * @param bytes
     * @return
     */
    public static int toInt(byte[] bytes) {
        return ((bytes[0] & 0xff) << 24) | ((bytes[1] & 0xff) << 16) | ((bytes[2] & 0xff) << 8) | (bytes[3] & 0xff);
    }

    /**
     * Covert byte array into a long integer.
     * 
     * @param bytes
     * @return
     */
    public static long toLong(byte[] bytes) {
        return ((bytes[0] & 0xffL) << 56) | ((bytes[1] & 0xffL) << 48) | ((bytes[2] & 0xffL) << 40)
                | ((bytes[3] & 0xffL) << 32) | ((bytes[4] & 0xffL) << 24) | ((bytes[5] & 0xffL) << 16)
                | ((bytes[6] & 0xffL) << 8) | (bytes[7] & 0xff);
    }
}
