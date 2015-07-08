package enigma;

public class Rotor {

	private int[] rotor;
	private int count;

	public Rotor(int[] rotor) {
		this.rotor = rotor;
		count = 0;
	}
	
	private int locateIndex(int x)
	{
		for (int i = 0; i < rotor.length; i++) {
			if(rotor[i] == x)
				return ((i - count) % 26 < 0) ? ((i - count) % 26) * -1 : (i - count) % 26;
		}
		return -1;
	}
	
	private int locateResult(int x)
	{
		return rotor[x + count];
	}
	
	public int encryptStoF(int x)
	{
		return locateResult(x);
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int encryptFtoS(int x)
	{
		return locateIndex(x);
	}
	
	
}
