package it.ipfsdownloadmonitor.commons;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

public class Utility {

	public static String humanReadableByteCountBin(long bytes) {
		long absB = bytes == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(bytes);
		if (absB < 1024) {
			return bytes + " B";
		}
		long value = absB;
		CharacterIterator ci = new StringCharacterIterator("KMGTPE");
		for (int i = 40; i >= 0 && absB > 0xfffccccccccccccL >> i; i -= 10) {
			value >>= 10;
			ci.next();
		}
		value *= Long.signum(bytes);
		return String.format("%.1f %ciB", value / 1024.0, ci.current());
	}
	
	
	public static double calcPerc (double tot, double part) {
		double ratio = part / tot;
		double perc = ratio * 100;
		return perc;
	}
}
