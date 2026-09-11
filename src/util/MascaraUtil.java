package util;

import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

public class MascaraUtil {

    // ============================================================
    // REMOVE TUDO QUE NÃO FOR NÚMERO
    // ============================================================
    public static String somenteNumeros(String texto) {

        if (texto == null) {
            return "";
        }

        return texto.replaceAll("\\D", "");
    }

    // ============================================================
    // FORMATA CEP
    // Exemplo: 15775000 -> 15775-000
    // ============================================================
    public static String formatarCep(String texto) {

        String numeros = somenteNumeros(texto);

        if (numeros.length() > 8) {
            numeros = numeros.substring(0, 8);
        }

        if (numeros.length() > 5) {
            return numeros.substring(0, 5)
                    + "-"
                    + numeros.substring(5);
        }

        return numeros;
    }

    // ============================================================
    // FORMATA TELEFONE
    // Exemplo:
    // 1736314521  -> (17) 3631-4521
    // 17991234567 -> (17) 99123-4567
    // ============================================================
    public static String formatarTelefone(String texto) {

        String numeros = somenteNumeros(texto);

        if (numeros.length() > 11) {
            numeros = numeros.substring(0, 11);
        }

        if (numeros.length() <= 2) {
            return numeros;
        }

        if (numeros.length() <= 6) {

            return "("
                    + numeros.substring(0, 2)
                    + ") "
                    + numeros.substring(2);
        }

        if (numeros.length() <= 10) {

            return "("
                    + numeros.substring(0, 2)
                    + ") "
                    + numeros.substring(2, 6)
                    + "-"
                    + numeros.substring(6);
        }

        return "("
                + numeros.substring(0, 2)
                + ") "
                + numeros.substring(2, 7)
                + "-"
                + numeros.substring(7);
    }

    // ============================================================
    // FORMATA CPF OU CNPJ
    //
    // 12345678900      -> 123.456.789-00
    // 12345678000190   -> 12.345.678/0001-90
    // ============================================================
    public static String formatarCpfCnpj(String texto) {

        String numeros = somenteNumeros(texto);

        if (numeros.length() > 14) {
            numeros = numeros.substring(0, 14);
        }

        // CPF
        if (numeros.length() <= 11) {

            if (numeros.length() <= 3) {
                return numeros;
            }

            if (numeros.length() <= 6) {

                return numeros.substring(0, 3)
                        + "."
                        + numeros.substring(3);
            }

            if (numeros.length() <= 9) {

                return numeros.substring(0, 3)
                        + "."
                        + numeros.substring(3, 6)
                        + "."
                        + numeros.substring(6);
            }

            return numeros.substring(0, 3)
                    + "."
                    + numeros.substring(3, 6)
                    + "."
                    + numeros.substring(6, 9)
                    + "-"
                    + numeros.substring(9);
        }

        // CNPJ
        if (numeros.length() <= 2) {
            return numeros;
        }

        if (numeros.length() <= 5) {

            return numeros.substring(0, 2)
                    + "."
                    + numeros.substring(2);
        }

        if (numeros.length() <= 8) {

            return numeros.substring(0, 2)
                    + "."
                    + numeros.substring(2, 5)
                    + "."
                    + numeros.substring(5);
        }

        if (numeros.length() <= 12) {

            return numeros.substring(0, 2)
                    + "."
                    + numeros.substring(2, 5)
                    + "."
                    + numeros.substring(5, 8)
                    + "/"
                    + numeros.substring(8);
        }

        return numeros.substring(0, 2)
                + "."
                + numeros.substring(2, 5)
                + "."
                + numeros.substring(5, 8)
                + "/"
                + numeros.substring(8, 12)
                + "-"
                + numeros.substring(12);
    }

    // ============================================================
    // APLICA MÁSCARA AO CAMPO
    // ============================================================
    private static void aplicarMascara(
            JTextField campo,
            Formatador formatador,
            int quantidadeMaxima) {

        ((AbstractDocument) campo.getDocument())
                .setDocumentFilter(
                        new DocumentFilter() {

                    @Override
                    public void insertString(
                            FilterBypass fb,
                            int offset,
                            String string,
                            javax.swing.text.AttributeSet attr)
                            throws BadLocationException {

                        replace(
                                fb,
                                offset,
                                0,
                                string,
                                attr
                        );
                    }

                    @Override
                    public void replace(
                            FilterBypass fb,
                            int offset,
                            int length,
                            String text,
                            javax.swing.text.AttributeSet attrs)
                            throws BadLocationException {

                        String atual
                                = fb.getDocument()
                                        .getText(
                                                0,
                                                fb.getDocument()
                                                        .getLength()
                                        );

                        String novo
                                = atual.substring(0, offset)
                                + (text == null ? "" : text)
                                + atual.substring(
                                        offset + length
                                );

                        String numeros
                                = somenteNumeros(novo);

                        if (numeros.length()
                                > quantidadeMaxima) {

                            numeros
                                    = numeros.substring(
                                            0,
                                            quantidadeMaxima
                                    );
                        }

                        String formatado
                                = formatador.formatar(numeros);

                        fb.remove(
                                0,
                                fb.getDocument().getLength()
                        );

                        fb.insertString(
                                0,
                                formatado,
                                attrs
                        );
                    }

                    @Override
                    public void remove(
                            FilterBypass fb,
                            int offset,
                            int length)
                            throws BadLocationException {

                        String atual
                                = fb.getDocument()
                                        .getText(
                                                0,
                                                fb.getDocument()
                                                        .getLength()
                                        );

                        String novo
                                = atual.substring(0, offset)
                                + atual.substring(
                                        offset + length
                                );

                        String numeros
                                = somenteNumeros(novo);

                        String formatado
                                = formatador.formatar(numeros);

                        fb.remove(
                                0,
                                fb.getDocument().getLength()
                        );

                        fb.insertString(
                                0,
                                formatado,
                                null
                        );
                    }
                }
                );
    }

    // ============================================================
    // MÁSCARAS PÚBLICAS
    // ============================================================
    public static void aplicarMascaraCep(
            JTextField campo) {

        aplicarMascara(
                campo,
                MascaraUtil::formatarCep,
                8
        );
    }

    public static void aplicarMascaraTelefone(
            JTextField campo) {

        aplicarMascara(
                campo,
                MascaraUtil::formatarTelefone,
                11
        );
    }

    public static void aplicarMascaraCpfCnpj(
            JTextField campo) {

        aplicarMascara(
                campo,
                MascaraUtil::formatarCpfCnpj,
                14
        );
    }

    // ============================================================
    // INTERFACE FUNCIONAL
    // ============================================================
    @FunctionalInterface
    private interface Formatador {

        String formatar(String texto);
    }
}
