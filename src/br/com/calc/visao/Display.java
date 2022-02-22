package br.com.calc.visao;

import br.com.calc.modelo.Memoria;
import br.com.calc.modelo.MemoriaObserver;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Display extends JPanel implements MemoriaObserver {

    private JLabel label;

    public Display(){
        Memoria.getInstance().addObserver(this);

        setBackground(new Color(46,49,50));
        label = new JLabel(Memoria.getInstance().getTextoAtual());
        label.setForeground(Color.WHITE);
        label.setFont(new Font("ubuntu", Font.PLAIN, 30));

        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 25));
        setBorder(BorderFactory.createLineBorder(new Color(46,49,50)));
        add(label);
    }

    @Override
    public void valorAlterado(String novoValor) {
        label.setText(novoValor);
    }
}
