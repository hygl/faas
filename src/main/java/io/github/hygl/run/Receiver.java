package io.github.hygl.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Receiver {

	private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class) ;
	
	public void receiveMessage(String message) {
		LOGGER.info("Recieved: " + message);
		
	}
	
}
