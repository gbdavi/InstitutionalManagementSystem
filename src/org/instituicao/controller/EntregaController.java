package org.instituicao.controller;

import org.instituicao.dto.AvaliacaoDTO;
import org.instituicao.dto.EntregaDTO;
import org.instituicao.service.EntregaService;

import java.util.List;

public class EntregaController {
    private final EntregaService entregaService;

    public EntregaController() {
        this.entregaService = new EntregaService();
    }

    /**
     * Retorna as entregas pendentes de avaliação por um professor no banco de dados.
     * Equivalente a rota /entregas (byProfessor e pendente via queryParams).
     */
    public List<EntregaDTO> getEntregasPendentesByProfessor(int matriculaProfessor) {
        return entregaService.getEntregasPendentesByProfessor(matriculaProfessor);
    }
}
