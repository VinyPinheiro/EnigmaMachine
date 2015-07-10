package enigma;

public class EnigmaMachine {

	private char[] plugBoard;
	private Rotor[] r;
	// EKMFLGDQVZNTOWYHXUSPAIBRCJ
	private final int[] k1 = { 4, 10, 12, 5, 11, 6, 3, 16, 21, 25, 13, 19, 14, 22, 24, 7, 23, 20, 18, 15, 0, 8, 1, 17,
			2, 9 };
	// AJDKSIRUXBLHWTMCQGZNPYFVOE
	private final int[] k2 = { 0, 9, 3, 10, 18, 8, 17, 20, 23, 1, 11, 7, 22, 19, 12, 2, 16, 6, 25, 13, 15, 24, 5, 21,
			14, 4 };
	// BDFHJLCPRTXVZNYEIWGAKMUSQO
	private final int[] k3 = { 1, 3, 5, 7, 9, 11, 2, 15, 17, 19, 23, 21, 25, 13, 24, 4, 8, 22, 6, 0, 10, 12, 20, 18, 16,
			14 };

	public EnigmaMachine(char[] plugBoard) {
		r = new Rotor[3];
		setPlugBoard(plugBoard);

		r[0] = new Rotor(k1);
		r[1] = new Rotor(k2);
		r[2] = new Rotor(k3);
	}

	public void reset() {
		for (Rotor i : r) {
			i.setCount(0);
		}
	}

	public Character Encode(char key, int r1, int r2, int r3) {
		char encrypted;
		int indice;
		encrypted = plugBoard[Character.getNumericValue(key) - 10];

		r[r1].count();
		indice = r[r1].getCount() + (Character.getNumericValue(encrypted) - 10);
		indice = r[r1].encryptStoF(indice);

		if (r[r1].getCount() == 22) {
			r[r2].count();
		}
		indice = indice - r[r1].getCount() + r[r2].getCount();

		indice = r[r2].encryptStoF(indice);
		

		if (r[r2].getCount() == 5) {
			r[r3].count();
		}
		indice = (indice - r[r2].getCount() + r[r3].getCount()) % 26;
		indice = r[r3].encryptStoF(indice);
		
		indice = indice - r[r3].getCount();
		indice = Reflector.encrypt(indice);
		
		indice = (indice + r[r3].getCount());
		indice = r[r3].encryptFtoS(indice);

		
		indice = indice + r[r2].getCount() - r[r3].getCount();

		indice = r[r2].encryptFtoS(indice);
		

		indice = indice + r[r1].getCount() - r[r2].getCount();

		indice = r[r1].encryptFtoS(indice);

		indice = (indice - r[r1].getCount() < 0)?indice - r[r1].getCount()+26:indice - r[r1].getCount();

		return plugBoard[indice];
		
		/*r[r1].count();
		System.err.println("Antes R1: " + (Character.getNumericValue(encrypted) - 9));

		indice = r[r1].encryptStoF(Character.getNumericValue(encrypted) - 9);

		System.err.println("Sai R1:" + indice);
		// encrypted = plugBoard[indice];
		System.err.println("antes R2:" + (indice - 1));
		if (r[r1].getCount() == 22) {
			r[r2].count();
		}
		indice = r[r2].encryptStoF(indice - 1);
		System.err.println("sai R2:" + (indice));

		System.err.println("antes R3:" + (indice));
		if (r[r2].getCount() == 5) {
			r[r1].count();
		}
		indice = r[r3].encryptStoF(indice);
		System.err.println("depois R3:" + (indice));

		indice = Reflector.encrypt(indice);
		System.err.println("depois Refletor:" + (indice));

		indice = r[r3].encryptFtoS(indice);
		System.err.println("depois R3:" + (indice));
		indice = r[r2].encryptFtoS(indice);
		System.err.println("depois R2:" + (indice));
		indice = r[r1].encryptFtoS(indice + r[r1].getCount());
		System.err.println("depois R1:" + (indice));

		System.err.println("f:" + indice);

		return plugBoard[(indice - r[r1].getCount()) % 26 < 0 ? (indice - r[r1].getCount()) % 26 + 25
				: (indice - r[r1].getCount()) % 26];*/
	}

	public char[] getPlugBoard() {
		return plugBoard;
	}

	public void setPlugBoard(char[] plugBoard) {
		this.plugBoard = plugBoard;
	}

	public void setPositionRotor(int position1, int position2, int position3) {
		r[0].setCount(position1);
		r[1].setCount(position2);
		r[2].setCount(position3);
	}

	public void setPositionAnel(int position1, int position2, int position3) {
		r[0].setAnel(position1);
		r[1].setAnel(position2);
		r[2].setAnel(position3);
	}

	public int[] getPositionRotor() {
		int[] positions = { r[0].getCount() % 26, r[1].getCount() % 26, r[2].getCount() % 26 };

		return positions;
	}

}
