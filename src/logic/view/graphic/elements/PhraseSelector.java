package logic.view.graphic.elements;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import execption.AssistantException;

public class PhraseSelector {
	
	private PhraseSelector() {
		throw new IllegalStateException("Utility class");
	}
	
	public static String select(String from) throws AssistantException {
		String selection = null;
		Random random;
		
		
		try {
			random = SecureRandom.getInstanceStrong();
		} catch (NoSuchAlgorithmException e1) {
			throw new AssistantException("Error random getistance");
		}
		int loop;
		

	    try (BufferedReader buffer = new BufferedReader(new FileReader(from))) {
	    	
			int i = 0; 
			loop = random.nextInt(Integer.valueOf(buffer.readLine()));
			do {
				selection=buffer.readLine();
				i++;
			
		      if(selection==null)
		        break;
		      
			}while(i<loop+1);
			
				
				
			
		} catch (IOException e) {
			throw new AssistantException("PhraseSelector error in file read");	
		}

		return selection;
		
	}
}
