import java.util.HashMap;

//ascii are 8bits, make slightly bigger table, add own characters, save multiple chars
//algorithm : current letter, next letter, output
//take normal alphabet, add special symbols
// is current+next in dict?
//if not, write to dict
// write out current shymbol to msg
public class LZW {
	HashMap <Integer, String> dict;
	public LZW()
	{
		for(char ch = 0;ch<256;ch++)
		{
			dict.put((int) ch, ""+ch);
		}
	}
}
