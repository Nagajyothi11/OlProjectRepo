import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {
	public static void main (String[]args) {
		BCryptPasswordEncoder PasswordEncoder = new BCryptPasswordEncoder();
		System.out.println(PasswordEncoder.encode("12345"));
}
}