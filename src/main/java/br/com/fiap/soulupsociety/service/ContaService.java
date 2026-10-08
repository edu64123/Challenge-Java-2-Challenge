
package br.com.fiap.soulupsociety.service;

import br.com.fiap.soulupsociety.dao.ContaDAO;
import br.com.fiap.soulupsociety.models.Conta;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ContaService {

    private ContaDAO contaDAO;

    public ContaService() {
        contaDAO = new ContaDAO();
    }

    // Validar os dados da conta
    private void validarConta(Conta conta) {

        if (conta == null) {
            throw new IllegalArgumentException("Conta não informada.");
        }

        if (conta.getNomeConta() == null ||
                conta.getNomeConta().isBlank() ||
                conta.getNomeConta().trim().length() < 3 ||
                conta.getNomeConta().trim().length() > 50) {
            throw new IllegalArgumentException("O nome da conta deve ter entre 3 e 50 caracteres.");
        }

        if (conta.getEmail() == null ||
                conta.getEmail().isBlank()) {
            throw new IllegalArgumentException("O e-mail é obrigatório.");
        }

        if (!conta.getEmail().matches("^[\\w.+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Formato de e-mail inválido.");
        }

        if (conta.getEmail().length() > 254) {
            throw new IllegalArgumentException("O e-mail deve ter no máximo 254 caracteres.");
        }

        if (conta.getDataCriacao() != null &&
                conta.getDataCriacao().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data de criação não pode ser futura.");
        }

        if (conta.getBio() != null &&
                conta.getBio().length() > 500) {
            throw new IllegalArgumentException("A biografia deve ter no máximo 500 caracteres.");
        }

        if (conta.getTipoConta() == null) {
            throw new IllegalArgumentException("O tipo de conta é obrigatório.");
        }

        if (conta.getUsuario() == null) {
            throw new IllegalArgumentException("A conta deve estar associada a um usuário.");
        }
    }

    // Cadastrar conta
    public void cadastrarConta(Conta conta) {

        validarConta(conta);

        if (conta.getId() != null) {
            throw new IllegalArgumentException("O ID deve ser gerado automaticamente.");
        }

        if (contaDAO.consultarPorEmail(conta.getEmail()) != null) {
            throw new IllegalArgumentException("Este e-mail já está cadastrado.");
        }

        if (conta.getDataCriacao() == null) {
            conta.setDataCriacao(LocalDate.now());
        }

        contaDAO.cadastrar(conta);
    }

    // Consultar conta pelo ID
    public Conta consultarPorId(Integer id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }

        Conta conta = contaDAO.consultarPorId(id);

        if (conta == null) {
            throw new IllegalArgumentException("Conta não encontrada.");
        }

        return conta;
    }

    // Consultar conta pelo e-mail
    public Conta consultarPorEmail(String email) {

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("O e-mail é obrigatório.");
        }

        Conta conta = contaDAO.consultarPorEmail(email);

        if (conta == null) {
            throw new IllegalArgumentException("Conta não encontrada.");
        }

        return conta;
    }

    // Atualizar conta
    public void atualizarConta(Conta conta) {

        validarConta(conta);

        if (conta.getId() == null || conta.getId() <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }

        Conta contaAtual = consultarPorId(conta.getId());

        Conta contaEmail = contaDAO.consultarPorEmail(conta.getEmail());

        if (contaEmail != null &&
                !contaEmail.getId().equals(conta.getId())) {
            throw new IllegalArgumentException("Este e-mail já pertence a outra conta.");
        }

        // Impede alteração da data original de criação
        conta.setDataCriacao(contaAtual.getDataCriacao());

        contaDAO.atualizarConta(conta);
    }

    // Deletar conta
    public void deletarConta(Integer id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }

        consultarPorId(id);

        contaDAO.deletarConta(id);
    }
}
