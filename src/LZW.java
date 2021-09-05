import java.util.HashMap;

//ascii are 8bits, make slightly bigger table, add own characters, save multiple chars
//algorithm : current letter, next letter, output
//take normal alphabet, add special symbols
// is current+next in dict?
//if not, write to dict
// write out current shymbol to msg
public class LZW {
	HashMap <Integer, String> dict;
	Integer[] stringyboi;
	String curr;
	
	public LZW()
	{
		for(char ch = 'a';ch<='z';ch++)
		{
			dict.put((int) ch, ""+ch);
		}
		
	}
	public void encode(String input,String outputfile)
	{
		for(int a=0;a<input.length();a++)
		{
			curr = ""+input.charAt(a);
			if(dict.containsValue(curr)) {
				
			}
		}
	}
}
