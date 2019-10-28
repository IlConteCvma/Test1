package Run;

import java.util.logging.Logger;
import java.util.logging.Level;

public class HelloWorld {

	public static void main(String[] args) {
		//Print Hello World on output.
		Logger logger; 
		logger = Logger.getLogger( HelloWorld.class.getName()); 
		logger.log(Level.INFO, "Hello world!");
		
	}
	
}
