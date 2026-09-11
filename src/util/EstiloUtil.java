package util;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
public class EstiloUtil {

    // ============================================================
    // LOGO
    // ============================================================
    public static void definirLogo(
            JLabel label,
            String caminho,
            int largura,
            int altura) {

        java.net.URL recurso
                = EstiloUtil.class.getResource(
                        caminho
                );

        if (recurso == null) {
            return;
        }

        ImageIcon imagemOriginal
                = new ImageIcon(recurso);

        Image imagem
                = imagemOriginal
                        .getImage()
                        .getScaledInstance(
                                largura,
                                altura,
                                Image.SCALE_SMOOTH
                        );

        label.setIcon(
                new ImageIcon(imagem)
        );
    }
}
