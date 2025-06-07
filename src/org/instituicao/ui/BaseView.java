package org.instituicao.ui;

import java.util.Scanner;

public abstract class BaseView {
    protected final Scanner scanner = new Scanner(System.in);
    private static final String ansiReset = "\u001B[0m";
    private static final String ansiCyan = "\u001B[36m";
    private static final String ansiRed = "\u001B[31m";

    /**
     * Exibir cabe�alho formatado para usu�rio.
     * @param titulo t�tulo do cabe�alho.
     */
    public void exibirCabecalho(String titulo) {
        System.out.println("\n" + "-".repeat(titulo.length() + 8));
        System.out.println("*** " + titulo + " ***");
        System.out.println("-".repeat(titulo.length() + 8));
    }

    /**
     * Exibir mensagem informativa formatada para usu�rio.
     * @param info mensagem.
     */
    public void exibirInfo(String prefixo, String info) {
        System.out.println(ansiCyan + prefixo + "[INFO] " + info + ansiReset);
    }

    public void exibirInfo(String info) {
        exibirInfo("", info);
    }

    /**
     * Exibir mensagem de erro formatada para usu�rio.
     * @param erro mensagem.
     */
    public void exibirErro(String prefixo, String erro) {
        System.out.println(ansiRed + prefixo + "[ERRO] " + erro + ansiReset);
    }

    public void exibirErro(String erro) {
        exibirErro("", erro);
    }

    public String solicitarCampo(String nomeCampo) {
        System.out.print(nomeCampo + ": ");
        return scanner.nextLine();
    }
}
