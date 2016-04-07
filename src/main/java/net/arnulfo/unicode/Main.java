/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.arnulfo.unicode;

import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 *
 * @author Franck Arnulfo
 */
public class Main {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) throws CharacterCodingException {
    String euro = "\u20ac";
    System.out.println("Euro: " + euro);
    System.out.println("Size: " + euro.length());
    System.out.println("Bytes: " + Arrays.toString(euro.getBytes(StandardCharsets.UTF_16)));
    System.out.println("Bytes: " + Arrays.toString(euro.getBytes(StandardCharsets.US_ASCII)));
    
    CharsetEncoder encoder = StandardCharsets.US_ASCII.newEncoder();
    encoder.onMalformedInput(CodingErrorAction.REPORT);
    encoder.onUnmappableCharacter(CodingErrorAction.REPORT);
    
    
    //encoder.encode(CharBuffer.wrap(euro.toCharArray()));
    StandardCharsets.US_ASCII.encode(euro);
    
  }
  
}
