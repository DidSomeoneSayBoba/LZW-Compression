import java.io.IOException;

public class Testerboi {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		LZW boi = new LZW();
		String outname = "boi";
		
		boi.encode("lzw-file1", outname,9);
		System.out.println(boi.dict);
		System.out.println(boi.stringyboi);
		Decompressor dec = new Decompressor(outname+"keys.txt",outname+".txt","original");
		System.out.println(dec.dict);
		//System.out.println(dec.stringyboi);
	}

}
