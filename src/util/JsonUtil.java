package util;

public class JsonUtil {

    public static String obterValor(
            String json,
            String campo) {

        String busca =
                "\"" + campo + "\":";

        int inicio =
                json.indexOf(busca);

        if (inicio == -1) {
            return null;
        }

        inicio += busca.length();

        while (inicio < json.length()
                && Character.isWhitespace(
                        json.charAt(inicio))) {

            inicio++;
        }

        if (inicio >= json.length()) {
            return null;
        }

        if (json.charAt(inicio) == '"') {

            inicio++;

            int fim =
                    json.indexOf(
                            "\"",
                            inicio
                    );

            if (fim == -1) {
                return null;
            }

            return json.substring(
                    inicio,
                    fim
            );
        }

        int fim =
                json.indexOf(
                        ",",
                        inicio
                );

        if (fim == -1) {

            fim =
                    json.indexOf(
                            "}",
                            inicio
                    );
        }

        return json.substring(
                inicio,
                fim
        ).trim();
    }

    public static boolean obterBoolean(
            String json,
            String campo) {

        String valor =
                obterValor(
                        json,
                        campo
                );

        return "true".equalsIgnoreCase(valor);
    }
}