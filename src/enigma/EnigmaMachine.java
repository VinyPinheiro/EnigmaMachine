package enigma;

public class EnigmaMachine {

	private char[] plugBoard;
	private Rotor r1,r2,r3;
//22 
	//e->f
	
	public EnigmaMachine(char[] plugBoard) {
		//EKMFLGDQVZNTOWYHXUSPAIBRCJ
		int[] k1 = {4,10,12,5,11,6,3,16,21,25,13,19,14,22,24,7,23,20,18,15,0,8,1,17,2,9};
		//AJDKSIRUXBLHWTMCQGZNPYFVOE
		int[] k2 = {0,9,3,10,18,8,17,20,23,1,11,7,22,19,12,2,16,6,25,13,15,24,5,21,14,4};
		//BDFHJLCPRTXVZNYEIWGAKMUSQO
		int[] k3 = {};
		setPlugBoard(plugBoard);
		
//		r1 = new Rotor();
	}

	public Character Encode(char key) {
		char encrypted;
		
		encrypted = plugBoard[Character.getNumericValue(key) - 10];
		
		
		
		return encrypted;
	}

	public char[] getPlugBoard() {
		return plugBoard;
	}

	public void setPlugBoard(char[] plugBoard) {
		this.plugBoard = plugBoard;
	}
	
	

}
