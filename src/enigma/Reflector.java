package enigma;

public class Reflector {
	/* YRUHQSLDPXNGOKMIEBFZCWVJAT */
	private static final int[] key = { 24, 17, 20, 7, 16, 18, 11, 3, 15, 23, 13, 6, 14, 10, 12, 8, 4, 1, 5, 25, 2, 22,
			21, 9, 0, 19 };

	public static int encrypt(int x) {
		return key[(x % 26 < 0) ? x % 26 + 26 : x % 26];
	}

}
