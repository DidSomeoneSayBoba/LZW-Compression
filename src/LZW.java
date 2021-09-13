

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

//ascii are 8bits, make slightly bigger table, add own characters, save multiple chars
//algorithm : current letter, next letter, output
//take normal alphabet, add special symbols
// is current+next in dict?
//if not, write to dict
// write out current shymbol to msg
/* comment like this
 * so you can click return and not have to // for each line
 * nerd
 */
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
	public void encode(String inputfile,String outputfile,int bytesize) throws IOException
	{
		BufferedReader reader;

		if(inputfile.contains(".txt")) {
			reader= new BufferedReader(new FileReader(new File(inputfile)));
		}
		else {
			reader= new BufferedReader(new FileReader(new File(inputfile+".txt")));
		}

		curr="";

		int index = 0;
		while(reader.ready())
		{
			nxt = (char)reader.read();

			if(dict.containsKey(curr+nxt))
			{
				curr = curr+nxt;

			}
			else {


				stringyboi.add(dict.get(curr));

				//System.out.println("curr "+curr+" dict "+dict.get(curr));
				if(index<Math.pow(2,bytesize)-127) {
					dict.put(curr+nxt, 127+index);
				}
				curr=""+nxt;
				index++;

			}




		}

		stringyboi.add(dict.get(curr));

		StringBuffer builder = new StringBuffer("");
		FileOutputStream writer = new FileOutputStream(outputfile+".txt");
		System.out.println(stringyboi.get(0));
		for(int a=0;a<stringyboi.size();a++)
		{

			//code		System.out.println(st)
			//System.out.println(stringyboi.get(a));
			if(!Objects.isNull(stringyboi.get(a)))
			{
				String binaryver = Integer.toBinaryString(stringyboi.get(a));
				binaryver = String.format("%"+bytesize+"s",binaryver);
				binaryver = binaryver.replaceAll(" ","0");
				System.out.println(binaryver);
				byte[] bytes = new BigInteger(binaryver,2).toByteArray();
				builder.append(stringyboi.get(a));
				writer.write(bytes);
			}
		}



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
	
	public static void decode(String inputFile, int binsize) throws IOException {
		FileReader reader = new FileReader (inputFile);
		HashMap <Integer, String>dict = new HashMap();
		//codes in 126 characters from ascii table at correct locations; custom starts at 127
		for(char ch = 32;ch<=126;ch++)
		{
			dict.put((int)ch, "" + ch);
		}
		StringBuilder output = new StringBuilder("");
		while (reader.ready()) {
			// gets binary for next code
			StringBuilder code = new StringBuilder("");
			for (int i = 0; i < binsize; i++) {
				code.append(reader.read());
			}
			// converts into decimal representation
			String str = code.toString();
			int dec = Integer.parseInt(str, 2);
			
		}
	}
}
