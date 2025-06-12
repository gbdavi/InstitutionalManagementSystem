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
     * Busca entregas pendentes de avalia��o por um professor.
     */
    public List<EntregaDTO> getEntregasPendentesByProfessor(int matriculaProfessor) {
        return entregaRepository.getEntregasPendentesByProfessor(matriculaProfessor).stream()
                .map(EntregaDTO::new)
                .toList();
    }
}
