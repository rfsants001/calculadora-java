package br.com.calc.modelo;

import java.util.ArrayList;
import java.util.List;

public class Memoria {

    private static final Memoria instance = new Memoria();

    private final List<MemoriaObserver> observers = new ArrayList<>();

    private String textoAtual = "";

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

    public void processComando(String valor) {
        if("AC".equals(valor)){
            textoAtual = "";
        }else {
            textoAtual += valor;
        }

        observers.forEach(o -> o.valorAlterado(getTextoAtual()));
    }
}
