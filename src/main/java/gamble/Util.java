package gamble;

import gamble.config.Config;

public class Util {

	public static void pause(long waitTime) {
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void print(String str) {
		System.out.println(str);
		pause(Config.DELAY);
	}

	public static void printNoDelay(String s) {
		System.out.println(s);
	}
}
