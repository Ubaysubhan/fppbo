package pbo;

import java.util.Date;
import java.util.Random;

public class spawngreenapple extends locategreen{
	Random random;

	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int UNIT_SIZE = 25;
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/(UNIT_SIZE*UNIT_SIZE);

	    int x;
	    int y;

	    
	    private int generateRandomNumber(int min, int max) {
	    	System.out.println((long)(new Date().getTime() % 5));
	    	Random random = new Random((long)(new Date().getTime() % 5));
	    	return random.nextInt(max - min) + min;
	    	}
	    
	    public int getX()
	    {
	        return x;
	    }

	    public int getY()
	    {
	        return y;   
	    }

		@Override
		public void locategreen() {
			// TODO Auto-generated method stub
			x = generateRandomNumber(0, SCREEN_WIDTH/UNIT_SIZE)*UNIT_SIZE;
            y = generateRandomNumber(0, SCREEN_HEIGHT/UNIT_SIZE)*UNIT_SIZE;
		}

}
