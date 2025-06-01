package org.instituicao.util.id;

public class IdSequencialGenerator implements IdGenerator {
    private int idCounter;

    public IdSequencialGenerator(int idInicial) {
        idCounter = idInicial;
    }

    @Override
    public int gerarProximoId() {
        return idCounter++;
    }
}
