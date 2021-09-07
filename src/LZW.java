import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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
		dict = new HashMap();
		stringyboi = new ArrayList();
		for(char ch = 32;ch<=126;ch++)
		{
			dict.put(""+ch,(int) ch);
		}
		
	}
	public void encode(String inputfile,String outputfile) throws IOException
	{
		BufferedReader reader;
	
		if(inputfile.contains(".txt")) {
			reader= new BufferedReader(new FileReader(new File(inputfile)));
		}
		else {
			reader= new BufferedReader(new FileReader(new File(inputfile+".txt")));
		}

		curr="";
		
		int index=0;
		while(reader.ready())
		{
			nxt = (char)reader.read();
			
				if(dict.containsKey(curr+nxt))
				{
					curr = curr+nxt;
				
				}
				else {
					stringyboi.add(dict.get(curr));
					dict.put(curr+nxt, dict.keySet().size());
					curr=""+nxt;
				}
				
			
			
			index++;
		}
		stringyboi.add(dict.get(curr));
		StringBuffer builder = new StringBuffer("");

		for(int a=0;a<stringyboi.size();a++)
		{
			builder.append(stringyboi.get(a)+" ");
		}
		PrintWriter writer = new PrintWriter(outputfile+".txt");
		writer.print(builder.toString());
		writer.close();
		StringBuffer buffer = new StringBuffer("");

		for(String key:dict.keySet())
		{
			
			buffer.append(dict.get(key)+" "+key+"\n");
		}
		PrintWriter kEYS = new PrintWriter(outputfile+"keys.txt");
		kEYS.print(buffer.toString());
		kEYS.close();
	}
}
