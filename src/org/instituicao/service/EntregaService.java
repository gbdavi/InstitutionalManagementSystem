package org.instituicao.service;

import org.instituicao.dto.EntregaDTO;
import org.instituicao.repository.EntregaRepository;

import java.util.List;

public class EntregaService {
    private final EntregaRepository entregaRepository;

    public EntregaService() {
        this.entregaRepository = new EntregaRepository();
    }

    /**
     * Retorna as entregas pendentes de avaliação por um professor no banco de dados.
     */
    public List<EntregaDTO> getEntregasPendentesByProfessor(int matriculaProfessor) {
        return entregaRepository.getEntregasPendentesByProfessor(matriculaProfessor).stream()
            .map(EntregaDTO::new)
            .toList();
    }
}
