
package br.com.fiap.soulupsociety.service;

import br.com.fiap.soulupsociety.dao.DesafioDAO;
import br.com.fiap.soulupsociety.models.Desafio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesafioService {

    private DesafioDAO desafioDAO;

    public DesafioService() {
        desafioDAO = new DesafioDAO();
    }

    // Cadastro de Desafio feito por um moderador
    public void cadastrarDesafio(Desafio desafio) {

        if (desafio == null) {
            throw new IllegalArgumentException("Desafio não informado.");
        }

        if (desafio.getId() != null) {
            throw new IllegalArgumentException("O ID é gerado automaticamente.");
        }

        if (desafio.getNome() == null ||
                desafio.getNome().isBlank() ||
                desafio.getNome().trim().length() < 5 ||
                desafio.getNome().trim().length() > 100) {
            throw new IllegalArgumentException("O nome deve ter entre 5 e 100 caracteres.");
        }

        if (desafio.getDescricaoDesafio() == null ||
                desafio.getDescricaoDesafio().isBlank() ||
                desafio.getDescricaoDesafio().trim().length() < 10 ||
                desafio.getDescricaoDesafio().trim().length() > 500) {
            throw new IllegalArgumentException("A descrição deve ter entre 10 e 500 caracteres.");
        }

        if (desafio.getQtdPontos() < 10 ||
                desafio.getQtdPontos() > 500) {
            throw new IllegalArgumentException("A quantidade de pontos deve estar entre 10 e 500.");
        }

        desafioDAO.cadastrar(desafio);
    }

    // Lista disponibilizada aos usuários para ver desafios
    public List<Desafio> listarDesafios() {
        return desafioDAO.listarDesafios();
    }

    // Consultar desafios pelo ID
    public Desafio consultarPorid(Integer id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }

        Desafio desafio = desafioDAO.consultarPorId(id);

        if (desafio == null) {
            throw new IllegalArgumentException("Desafio não encontrado.");
        }

        return desafio;
    }

    // Atualização de desafio feita pelo moderador
    public void atualizar(Desafio desafio) {

        if (desafio == null) {
            throw new IllegalArgumentException("Desafio não informado.");
        }

        if (desafio.getId() == null || desafio.getId() <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }

        if (desafio.getNome() == null ||
                desafio.getNome().isBlank() ||
                desafio.getNome().trim().length() < 5 ||
                desafio.getNome().trim().length() > 100) {
            throw new IllegalArgumentException("O nome deve ter entre 5 e 100 caracteres.");
        }

        if (desafio.getDescricaoDesafio() == null ||
                desafio.getDescricaoDesafio().isBlank() ||
                desafio.getDescricaoDesafio().trim().length() < 10 ||
                desafio.getDescricaoDesafio().trim().length() > 500) {
            throw new IllegalArgumentException("A descrição deve ter entre 10 e 500 caracteres.");
        }

        if (desafio.getQtdPontos() < 10 ||
                desafio.getQtdPontos() > 1000) {
            throw new IllegalArgumentException("A quantidade de pontos deve estar entre 10 e 1000.");
        }

        consultarPorid(desafio.getId());

        desafioDAO.atualizar(desafio);
    }

    // Exclusão de desafio pelo moderador
    public void deletar(Integer id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }

        consultarPorid(id);

        desafioDAO.deletar(id);
    }
}
