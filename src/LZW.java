import java.util.ArrayList;
import java.util.HashMap;

//ascii are 8bits, make slightly bigger table, add own characters, save multiple chars
//algorithm : current letter, next letter, output
//take normal alphabet, add special symbols
// is current+next in dict?
//if not, write to dict
// write out current shymbol to msg
public class LZW {
	HashMap <String, Integer> dict;
	ArrayList<Integer> stringyboi;
	String curr;
	char nxt;
	public LZW()
	{
		for(char ch = 'a';ch<='z';ch++)
		{
			dict.put(""+ch,(int) ch);
		}
		
	}
	public void encode(String input,String outputfile)
	{
		int index=0;
		while(stringyboi.size()<=input.length())
		{
			nxt = input.charAt(index);
			if(curr!=null)
			{
				
			
			if(dict.containsKey(curr))
			{
				stringyboi.add(dict.get(curr));
				
			}
			curr = curr + nxt;
			}
			index++;
		}
		for(int a=0;a<input.length();a++)
		{
			curr = ""+input.charAt(a);
			if(dict.containsKey(curr)) {
				
			}
		}
	}
}
