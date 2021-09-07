import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class Decompressor {
	HashMap <String, String> dict;
	ArrayList<String> stringyboi;
	String segment = "";
	BufferedReader reader;
	public Decompressor(String dictfile, String encodedfile,String outfile) throws IOException
	{
		stringyboi = new ArrayList();
		reader = new BufferedReader(new FileReader(new File(dictfile)));
		dict = new HashMap();
		
		while(reader.ready())
		{
			String line = reader.readLine();
			String[] minidict = line.split(" ");
			if(minidict.length<2)
			{
				dict.put(minidict[0], " ");
			}
			else {
			String key = minidict[0];
			String entry = minidict[1];
			dict.put(key,entry);
			}
		}
		reader = new BufferedReader(new FileReader(new File(encodedfile)));
		StringBuffer builder = new StringBuffer("");

		while(reader.ready())
		{
			char charac = (char) reader.read();
			if(charac != ' ')
			{
			segment += ""+charac;
			}
			else {
				builder.append(dict.get(segment));
				stringyboi.add(segment+" "+dict.get(segment));
				segment = "";
			}
		
		}
		PrintWriter writer = new PrintWriter(outfile+".txt");
		writer.print(builder.toString());
		writer.close();
	}
}
