package br.com.calc.modelo;

import java.util.ArrayList;
import java.util.List;

public class Memoria {

    private enum TipoComando {
        ZERAR, SINAL, PORCENTAGEM, NUMERO, DIV, MULT, SUB, SOMA, IGUAL, VIRGULA;
    };

    private static final Memoria instance = new Memoria();

    private final List<MemoriaObserver> observers = new ArrayList<>();


    private TipoComando ultimaOperacao = null;
    private boolean substituir = false;

    private String textoAtual = "";
    private String textoBuffer = "";

    public Memoria() {
    }

    public static Memoria getInstance() {
        return instance;
    }

    public void addObserver(MemoriaObserver observer) {
        observers.add(observer);
    }

    public String getTextoAtual() {
        return textoAtual.isEmpty() ? "0" : textoAtual;
    }

    public void processComando(String texto) {

        TipoComando tipoComando = detectarTipoComando(texto);

        if(tipoComando == null){
            return;
        } 
        else if(tipoComando == TipoComando.ZERAR){
            textoAtual = "";
            textoBuffer = "";
            substituir = false;
            ultimaOperacao = null;
        } 
        else if(tipoComando == TipoComando.SINAL && textoAtual.contains("-")){
            textoAtual = textoAtual.substring(1);
        }
        else if(tipoComando == TipoComando.SINAL && !textoAtual.contains("-")){
            textoAtual = "-" + textoAtual;
        }
        else if(tipoComando == TipoComando.NUMERO || tipoComando == TipoComando.VIRGULA){
            textoAtual = substituir ? texto : textoAtual + texto;
            substituir = false;
        } 
        else if (tipoComando == TipoComando.PORCENTAGEM){
            substituir = true;

            double textoPorcentagem = Double.parseDouble(textoAtual.replace(",", "."));

            double resultadoPorcentagem = textoPorcentagem / 100;

            textoAtual = Double.toString(resultadoPorcentagem).replace(".", ",");
        }
        else {
            substituir = true;
            textoAtual = obterResultadoOperacao();

            textoBuffer = textoAtual;

            ultimaOperacao = tipoComando;
        }

        observers.forEach(o -> o.valorAlterado(getTextoAtual()));
    }

    private String obterResultadoOperacao() {
        if(ultimaOperacao == null || ultimaOperacao == TipoComando.IGUAL) {
            return textoAtual;
        }
        double numeroBuffer = Double.parseDouble(textoBuffer.replace(",", "."));
        double numeroAtual = Double.parseDouble(textoAtual.replace(",", "."));

        double resultado = 0;

        String resultadoTexto = "";
        
        if(ultimaOperacao == TipoComando.SOMA){
            resultado = numeroBuffer + numeroAtual;
        }
        else if(ultimaOperacao == TipoComando.SUB){
            resultado = numeroBuffer - numeroAtual;
        }
        else if(ultimaOperacao == TipoComando.MULT){
            resultado = numeroBuffer * numeroAtual;
        }
        else if(ultimaOperacao == TipoComando.DIV){
            resultado = numeroBuffer / numeroAtual;
        }

        resultadoTexto = Double.toString(resultado).replace(".", ",");

        boolean inteiro = resultadoTexto.endsWith(",0");

        return inteiro ? resultadoTexto.replace(",0", "") : resultadoTexto;
    }

    private TipoComando detectarTipoComando(String texto) {
        if(textoAtual.isEmpty() && texto == "0"){
            return null;
        }

        try {
            Integer.parseInt(texto);
            return TipoComando.NUMERO;
        } catch (NumberFormatException e) {
            if("AC".equals(texto)){
               return TipoComando.ZERAR;
            } 
            else if("/".equals(texto)){
                return TipoComando.DIV;
            }
            else if("x".equals(texto)){
                return TipoComando.MULT;
            }
            else if("-".equals(texto)){
                return TipoComando.SUB;
            }
            else if("+".equals(texto)){
                return TipoComando.SOMA;
            }
            else if("=".equals(texto)){
                return TipoComando.IGUAL;
            }
            else if("±".equals(texto)){
                return TipoComando.SINAL;
            }
            else if("%".equals(texto)){
                return TipoComando.PORCENTAGEM;
            }
            else if(",".equals(texto) && !textoAtual.contains(",")){
                return TipoComando.VIRGULA;
            }
        
        }

        return null;
    }
}
