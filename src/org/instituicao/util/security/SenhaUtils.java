package org.instituicao.util.security;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Implementacao de metodos para criptografia
 * */
public class SenhaUtils {

    private static final int ITERACOES = 65536;
    private static final int COMPRIMENTO_CHAVE = 256;

    /**
     * Gera um hash seguro codificado em Base64 para a senha fornecida utilizando o algoritmo SHA-256 com salt aleatório.
     * @param senha A senha em texto puro que será transformada em hash.
     * @return Uma string codificada em Base64 contendo salt + hash.
     */
    public static String gerarHashSenha(String senha) {
        byte[] salt = gerarSalt();
        byte[] hash = hashSenha(senha.toCharArray(), salt);
        return Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hash);
    }

    /**
     * Verifica se a senha fornecida corresponde ao hash armazenado.
     *
     * @param senha A senha em texto puro fornecida pelo usuário.
     * @param senhaHash O hash completo (salt + hash) em Base64.
     * @return true se a senha for válida, false caso contrário.
     */
    public static boolean verificarSenha(String senha, String senhaHash) {
        String[] partes = senhaHash.split(":");
        byte[] salt = Base64.getDecoder().decode(partes[0]);
        byte[] hashEsperado = Base64.getDecoder().decode(partes[1]);

        byte[] hashReal = hashSenha(senha.toCharArray(), salt);

        if (hashReal.length != hashEsperado.length) return false;

        // Tecnica de comparacao constante de tempo para evitar ataque de timing side-channel
        int diff = 0;
        for (int i = 0; i < hashReal.length; i++) {
            diff |= hashReal[i] ^ hashEsperado[i];
        }

        return diff == 0;
    }

    private static byte[] gerarSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    private static byte[] hashSenha(char[] senha, byte[] salt) {
        try {
            PBEKeySpec spec = new PBEKeySpec(senha, salt, ITERACOES, COMPRIMENTO_CHAVE);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            return skf.generateSecret(spec).getEncoded();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar hash da senha", e);
        }
    }
}
