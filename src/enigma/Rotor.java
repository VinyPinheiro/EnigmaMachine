package enigma;

public class Rotor {

	private int[] rotor;
	private int count;
	private int anel;

	public Rotor(int[] rotor) {
		this.rotor = rotor;
		count = 0;
		anel = 0;
	}
	
	private int locateIndex(int x)
	{
		x = (x < 0) ? x + 26 : x;
		for (int i = 0; i < rotor.length; i++) {
			if(rotor[(i-anel < 0)? i-anel + 26 : i-anel] == x)
				
				return i;
		}
		return -1;
	}
	
	private int locateResult(int x)
	{
		return rotor[((x + count) % 26 < 0) ? (x + count) % 26 + 25 : (x + count) % 26];
	}
	
	public int encryptStoF(int x)
	{
		return rotor[(x%26 < 0 )? x%26 +26 : x % 26];
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count % 26;
	}
	public void count() {
		this.count++;
	}

	public int encryptFtoS(int x)
	{
		return locateIndex(x % 26);
	}
	
	public void setAnel(int x)
	{
		this.anel = x;
	}
	
	
}
