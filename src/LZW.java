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
	boolean hamster = false;
	public LZW()
	{
		dict = new HashMap();
		dict.put("#",0);
		stringyboi = new ArrayList();
		for(char ch = 'a';ch<='z';ch++)
		{
			dict.put(""+ch,(int) ch-'a'+1);
		}
		
	}
	public void encode(String input,String outputfile)
	{
		curr="";
		int index=0;
		while(index<input.length())
		{
			nxt = input.charAt(index);
			
				if(dict.containsKey(curr+nxt))
				{
					curr = curr+nxt;
				
				}
				else {
					stringyboi.add(dict.get(curr));
					System.out.println("curr "+curr+", index"+index);
					dict.put(curr+nxt, dict.keySet().size());
					curr=""+nxt;
				}
				
			
			
			index++;
		}
		stringyboi.add(dict.get(curr));
	}
}
