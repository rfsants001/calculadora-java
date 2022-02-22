package br.com.calc.visao;

import br.com.calc.modelo.Memoria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class Teclado extends JPanel implements ActionListener {

    private final Color COR_CINZA_ESCURO = new Color(68,68,68);
    private final Color COR_CINZA_CLARO = new Color(97,100,99);
    private final Color COR_LARANJA = new Color(242,163,60);


    public Teclado() {
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();

        setBorder(BorderFactory.createLineBorder(new Color(64, 64, 64)));


        setLayout(layout);

        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;

        adicionarBotao("AC", COR_CINZA_ESCURO, constraints, 0, 0);
        adicionarBotao("+/-", COR_CINZA_ESCURO, constraints, 1,0);
        adicionarBotao("%", COR_CINZA_ESCURO, constraints, 2,0);
        //Operações matemática divisão
        adicionarBotao("/", COR_LARANJA, constraints, 3, 0);


        adicionarBotao("7", COR_CINZA_ESCURO, constraints, 0, 1);
        adicionarBotao("8", COR_CINZA_ESCURO, constraints, 1, 1);
        adicionarBotao("9", COR_CINZA_ESCURO, constraints, 2, 1);
        //Operações matemática multiplicação
        adicionarBotao("x", COR_LARANJA, constraints, 3, 1);

        adicionarBotao("4", COR_CINZA_ESCURO, constraints, 0, 3);
        adicionarBotao("5", COR_CINZA_ESCURO, constraints, 1, 3);
        adicionarBotao("6", COR_CINZA_ESCURO, constraints, 2, 3);
        //Operações matemática soma
        adicionarBotao("-", COR_LARANJA, constraints, 3, 3);

        adicionarBotao("1", COR_CINZA_ESCURO, constraints, 0, 4);
        adicionarBotao("2", COR_CINZA_ESCURO, constraints, 1, 4);
        adicionarBotao("3", COR_CINZA_ESCURO, constraints, 2, 4);
        //Operações matemática soma
        adicionarBotao("+", COR_LARANJA, constraints, 3, 4);

        constraints.gridwidth = 2;
        adicionarBotao("0", COR_CINZA_ESCURO, constraints, 0, 5);
        constraints.gridwidth = 1;
        adicionarBotao(",", COR_CINZA_ESCURO, constraints, 2, 5);
        //Operações matemática soma
        adicionarBotao("=", COR_LARANJA, constraints, 3, 5);


    }

    private void adicionarBotao(String texto, Color cor, GridBagConstraints constraints, int x, int y) {
        constraints.gridx = x;
        constraints.gridy = y;
        Botao botao = new Botao(texto,cor);

        botao.addActionListener(this);

        add(botao, constraints);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JButton){
            JButton botao = (JButton) e.getSource();
            Memoria.getInstance().processComando(botao.getText());
        }

    }
}
