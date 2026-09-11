package util;

public class ValidadorUtil {

    public static boolean emailValido(
            String email) {

        return email.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
        );
    }
}
