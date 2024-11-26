package com.workforce.automation.randomstringtransform;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.util.NameTransformer;

//import javax.xml.transform.Transformer;
//import cucumber.api.Transformer;

public class RandomStringTransform extends NameTransformer {
    private static final Map<String, String> RANDOM_STRINGS = new HashMap<>();   //Key -> random string
    public static final RandomStringTransform INSTANCE = new RandomStringTransform();

    @Override
    public String transform(String input) {
        return transformString(input);
    }



    private String transformString(String prefix) {
    	return this.getAlphaNumericString(6,prefix);
    }
    
    private String getAlphaNumericString(int n,String prefix )
    {
  
        // length is bounded by 256 Character
        byte[] array = new byte[256];
        new Random().nextBytes(array);
  
        String randomString
            = new String(array, Charset.forName("UTF-8"));
  
        // Create a StringBuffer to store the result
        StringBuffer r = new StringBuffer();
        r.append(prefix);
        // Append first 20 alphanumeric characters
        // from the generated random String into the result
        for (int k = 0; k < randomString.length(); k++) {
  
            char ch = randomString.charAt(k);
  
            if (((ch >= 'a' && ch <= 'z')
                 || (ch >= 'A' && ch <= 'Z')
                 || (ch >= '0' && ch <= '9'))
                && (n > 0)) {
  
                r.append(ch);
                n--;
            }
        }
        
  
        // return the resultant string
        return r.toString();
    }



	@Override
	public String reverse(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}
  
}
