package br.com.calc.modelo;

@FunctionalInterface
public interface MemoriaObserver {

    void valorAlterado(String novoValor);
}
