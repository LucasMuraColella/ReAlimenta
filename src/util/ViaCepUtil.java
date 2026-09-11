package util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ViaCepUtil {

    private static final HttpClient CLIENT
            = HttpClient.newHttpClient();

    public static String consultarCep(
            String cep) throws IOException,
            InterruptedException {

        String cepLimpo
                = cep.replaceAll("\\D", "");

        if (!cepLimpo.matches("\\d{8}")) {

            throw new IllegalArgumentException(
                    "O CEP deve possuir 8 dígitos."
            );
        }

        String url
                = "https://viacep.com.br/ws/"
                + cepLimpo
                + "/json/";

        HttpRequest request
                = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .GET()
                        .build();

        HttpResponse<String> response
                = CLIENT.send(
                        request,
                        HttpResponse.BodyHandlers.ofString()
                );

        if (response.statusCode() != 200) {

            throw new IOException(
                    "Erro ao consultar o ViaCEP. "
                    + "Código HTTP: "
                    + response.statusCode()
            );
        }

        return response.body();
    }

    public static CepResposta buscar(
            String cep)
            throws IOException,
            InterruptedException {

        String json
                = consultarCep(cep);

        CepResposta resposta
                = new CepResposta();

        resposta.setErro(
                JsonUtil.obterBoolean(
                        json,
                        "erro"
                )
        );

        if (resposta.isErro()) {

            return resposta;
        }

        resposta.setLogradouro(
                JsonUtil.obterValor(
                        json,
                        "logradouro"
                )
        );

        resposta.setBairro(
                JsonUtil.obterValor(
                        json,
                        "bairro"
                )
        );

        resposta.setLocalidade(
                JsonUtil.obterValor(
                        json,
                        "localidade"
                )
        );

        resposta.setUf(
                JsonUtil.obterValor(
                        json,
                        "uf"
                )
        );

        return resposta;
    }
}
