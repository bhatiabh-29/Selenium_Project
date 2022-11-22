package common;



import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class EncodingDecoding {

    public static void main(String[] args) throws UnsupportedEncodingException {
        simpleEncodingAndDecoding();
        final String textDate = "Java8 base64 encoding-decoding!!!";
        String encodedText = Base64.getUrlEncoder().encodeToString(textDate.getBytes("UTF-8"));
        System.out.println(encodedText);

        byte[] decodedArr = Base64.getUrlDecoder().decode(encodedText);
        String decodedText = new String(decodedArr, "UTF-8");

        System.out.println(decodedText);
    }

    private static void simpleEncodingAndDecoding() throws UnsupportedEncodingException {
        final String textDate = "Java8 base64 encoding-decoding!!!";
        String encodedText = Base64.getEncoder().encodeToString(textDate.getBytes("UTF-8"));
        System.out.println(encodedText);

        byte[] decodedArr = Base64.getDecoder().decode(encodedText);
        String decodedText = new String(decodedArr, "UTF-8");

        System.out.println(decodedText);
    }

}