import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

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
			//if(!Objects.isNull(stringyboi.get(a)))
			//{
			String binaryver = Integer.toBinaryString(stringyboi.get(a));
			binaryver = String.format("%"+bytesize+"s",binaryver);
			binaryver = binaryver.replaceAll(" ","0");
			short number = Short.parseShort(binaryver, 2);
			ByteBuffer bytebuf = ByteBuffer.allocate(2).putShort(number);

			byte[] bytes = bytebuf.array();
		//	byte[] bytes = new BigInteger(binaryver,2).toByteArray();
			for(byte bit:bytes)
			{
				//System.out.println(bit);
			}
			//builder.append(stringyboi.get(a));
			writer.write(bytes);
			//}
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
}
