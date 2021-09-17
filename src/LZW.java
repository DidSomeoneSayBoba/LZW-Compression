

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.file.Files;
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

			System.out.println(stringyboi.get(a));
			if(!Objects.isNull(stringyboi.get(a)))
			{
				String binaryver = Integer.toBinaryString(stringyboi.get(a));
				binaryver = String.format("%"+bytesize+"s",binaryver);
				binaryver = binaryver.replaceAll(" ","0");
				//System.out.println(binaryver);
				byte[] bytes = new BigInteger(binaryver,2).toByteArray();
				//System.out.println(bytes[0]);
				//builder.append(stringyboi.get(a));
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
		//nexts in 126 characters from ascii table at correct locations; custom starts at 127
		for(char ch = 32;ch<=126;ch++)
		{
			dict.put((int)ch, "" + ch);
		}
		StringBuilder input = new StringBuilder("");
		byte[] bits = Files.readAllBytes(new File (inputFile).toPath());
		for (int i = 0; i < bits.length; i++) {
			input.append(toBinary((int)bits[i], binsize));
			System.out.println(Integer.toBinaryString(bits[i]));
		}
		reader.close();
		String str = input.toString();
		String current = str.substring(0, binsize);
		int index = 127;
		StringBuilder output = new StringBuilder("");
		for (int i = binsize; i < str.length(); i += binsize) {
			// gets binary for next next
			String next = str.substring(i, i+binsize);
			System.out.println(current);
			System.out.println (next);
			// converts into decimal representation
			int dec1 = Integer.parseInt(current.toString(), 2);
			int dec2 = Integer.parseInt(next.toString(), 2);
			System.out.println (dec1 + " , " + dec2);
			if (!dict.containsKey(dec1 + dec2)) {
				dict.put(index, "" + dec1 + dec2);
				output.append(dict.get(index));
				index++;
				current = next;
			}
			else {
				current = current + next;
			}
		}
		FileWriter writer = new FileWriter("output.txt");
		writer.write(output.toString());
		writer.close();
	}
	private static String toBinary(int x, int len)
    {
        if (len > 0)
        {
            return String.format("%" + len + "s",
                            Integer.toBinaryString(x)).replaceAll(" ", "0");
        }
 
        return null;
    }
}
