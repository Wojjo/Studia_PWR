package zad7;

import java.util.Base64;

public class Szyfr

{
	public Szyfr() {
	}

	public String szyfruj(String text) throws java.io.UnsupportedEncodingException {
		text = Base64.getEncoder().encodeToString(text.getBytes("utf-8"));
		return text;
	}

	public String odszyfruj(String text) throws java.io.UnsupportedEncodingException {
		byte[] base64decodedBytes = Base64.getDecoder().decode(text);
		text = new String(base64decodedBytes, "utf-8");
		return text;
	}
}