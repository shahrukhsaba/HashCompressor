package utils;
import static org.junit.Assert.assertEquals;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;

public class CompressorTest {
	
	@Test
	public void getCompressedStringTest() throws NoSuchAlgorithmException {
		String str = Compressor.getCompressedString("cdhwhcwhe2he732y4fd324yfd72wdu2hd", 8);
		assertEquals("4UN503QI", str);
	}
}
