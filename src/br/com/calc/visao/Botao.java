package br.com.calc.visao;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Botao extends JButton {

    public Botao(String label, Color cor){
        setText(label);
        setFont(new Font("ubuntu", Font.PLAIN, 20));
        setOpaque(true);
        setBackground(cor);
        setForeground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(new Color(64, 64, 64)));
    }
}
