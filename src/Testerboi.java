import java.io.IOException;

public class Testerboi {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		LZW boi = new LZW();
		String outname = "boi";
		int bytecount = 9;
		byte num = 10;
		boi.encode("lzw-file2", outname,bytecount);
		boi.decode("boi.txt", bytecount);
	}

}
