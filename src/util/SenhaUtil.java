package util;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import java.util.Base64;

public class SenhaUtil {

    private static final int ITERACOES = 65536;
    private static final int TAMANHO_CHAVE = 256;
    private static final int TAMANHO_SALT = 16;

    public static String gerarHash(String senha) {

        try {

            SecureRandom random =
                    new SecureRandom();

            byte[] salt =
                    new byte[TAMANHO_SALT];

            random.nextBytes(salt);

            KeySpec spec =
                    new PBEKeySpec(
                            senha.toCharArray(),
                            salt,
                            ITERACOES,
                            TAMANHO_CHAVE
                    );

            SecretKeyFactory factory =
                    SecretKeyFactory.getInstance(
                            "PBKDF2WithHmacSHA256"
                    );

            byte[] hash =
                    factory.generateSecret(
                            spec
                    ).getEncoded();

            String saltBase64 =
                    Base64.getEncoder()
                            .encodeToString(salt);

            String hashBase64 =
                    Base64.getEncoder()
                            .encodeToString(hash);

            return saltBase64 + ":" + hashBase64;

        } catch (NoSuchAlgorithmException |
                 InvalidKeySpecException e) {

            throw new RuntimeException(
                    "Erro ao gerar hash da senha.",
                    e
            );
        }
    }

    public static boolean verificarSenha(
            String senha,
            String hashArmazenado) {

        try {

            String[] partes =
                    hashArmazenado.split(":");

            if (partes.length != 2) {
                return false;
            }

            byte[] salt =
                    Base64.getDecoder()
                            .decode(partes[0]);

            byte[] hashEsperado =
                    Base64.getDecoder()
                            .decode(partes[1]);

            KeySpec spec =
                    new PBEKeySpec(
                            senha.toCharArray(),
                            salt,
                            ITERACOES,
                            TAMANHO_CHAVE
                    );

            SecretKeyFactory factory =
                    SecretKeyFactory.getInstance(
                            "PBKDF2WithHmacSHA256"
                    );

            byte[] hashAtual =
                    factory.generateSecret(
                            spec
                    ).getEncoded();

            if (hashAtual.length !=
                    hashEsperado.length) {

                return false;
            }

            int resultado = 0;

            for (int i = 0;
                 i < hashAtual.length;
                 i++) {

                resultado |=
                        hashAtual[i]
                        ^ hashEsperado[i];
            }

            return resultado == 0;

        } catch (Exception e) {

            return false;
        }
    }
}