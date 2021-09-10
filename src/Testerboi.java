import java.io.IOException;

public class Testerboi {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		LZW boi = new LZW();
		String outname = "boi";
		int bytecount = 9;
		boi.encode("lzw-file3", outname,bytecount);

		//System.out.println(dec.stringyboi);
	}

}
