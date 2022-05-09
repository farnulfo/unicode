/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.arnulfo.unicode;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Franck Arnulfo
 */
public class MainTest {

  public static final String EURO = "\u20ac";

  @Test
  public void testFaceWithTearsOfJoy() throws Exception {
    String FACE_WITH_TEARS_OF_JOY = "😂";

    // String.length() returns length in code unit, not in code point
    assertEquals(2, FACE_WITH_TEARS_OF_JOY.length());

    assertEquals(1, FACE_WITH_TEARS_OF_JOY.codePoints().count());

    long codePoint = 0x1F602;
    assertEquals(FACE_WITH_TEARS_OF_JOY, Character.toString((char)codePoint));
     
  }

  @Test
  public void testLength() throws Exception {
    assertEquals(1, EURO.length());
  }

  @Test
  public void testUTF16() throws Exception {
    byte[] BYTE_ORDER_MARK = new byte[]{(byte) 0xFE, (byte) 0xFF};

    assertArrayEquals(
      new byte[]{BYTE_ORDER_MARK[0], BYTE_ORDER_MARK[1], (byte) 0x20, (byte) 0xAC},
      EURO.getBytes(StandardCharsets.UTF_16));
  }

  @Test
  public void testUSASCII() throws Exception {
    assertArrayEquals(new byte[]{'?'}, EURO.getBytes(StandardCharsets.US_ASCII));
  }

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void testStrictEncoder() throws Exception {
    thrown.expect(java.nio.charset.UnmappableCharacterException.class);
    thrown.expectMessage("Input length = 1");

    CharsetEncoder encoder = StandardCharsets.US_ASCII.newEncoder();
    encoder.onMalformedInput(CodingErrorAction.REPORT);
    encoder.onUnmappableCharacter(CodingErrorAction.REPORT);

    encoder.encode(CharBuffer.wrap(EURO.toCharArray()));
  }

  @Test
  public void testLossyEncoder() {
    ByteBuffer bf = StandardCharsets.US_ASCII.encode(EURO);
    assertArrayEquals(new byte[] {(byte) '?'}, bf.array());
  }
}
