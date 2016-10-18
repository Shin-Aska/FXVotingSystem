/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StringUtilities;

import java.util.Random;

/**
 *
 * @author Richard
 */
public class RandomStringGenerator {

    public static String randomString(char[] characterSet, int length) {
        Random random = new Random();
        char[] result = new char[length];
        for (int i = 0; i < result.length; i++) {
            // picks a random index out of character set > random character
            int randomCharIndex = random.nextInt(characterSet.length);
            result[i] = characterSet[randomCharIndex];
        }
        return new String(result);
    }
}
