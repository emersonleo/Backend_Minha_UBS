package minhaubs.api.infra;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

@Service
public class Utils {

	public static String passwordMd5Enconde(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(password.getBytes());
        String passwordEncoded = new BigInteger(1, digest).toString(16);
        return passwordEncoded;
	}
}
