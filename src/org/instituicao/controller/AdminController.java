package org.instituicao.controller;

import org.instituicao.dto.FuncionarioDTO;
import org.instituicao.service.AdminService;

/**
 * Controller para dados de admin
 * Equivalente a rota base /admins
 */
public class AdminController {
    private final AdminService adminService;

    public AdminController() {
        this.adminService = new AdminService();
    }

    /**
     * Retorna informacoes do admin caso login realizado com sucesso
     * Equivalente a rota /admins/login
     * @param emailCorporativo email corporativo do admin.
     * @param senha senha do admin.
     * @return
     */
    public FuncionarioDTO login(String emailCorporativo, String senha) {
        if (emailCorporativo == null || senha == null || emailCorporativo.length() == 0 || senha.length() == 0) {
            return null;
        }
        return adminService.login(emailCorporativo, senha);
    }
}
