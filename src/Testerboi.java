import java.io.IOException;

public class Testerboi {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		LZW boi = new LZW();
		boi.encode("lzw-file1", "boi");
		System.out.println(boi.dict);
		System.out.println(boi.stringyboi);
	}

}
