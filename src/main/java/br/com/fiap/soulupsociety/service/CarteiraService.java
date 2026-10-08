
package br.com.fiap.soulupsociety.service;

import br.com.fiap.soulupsociety.dao.CarteiraDAO;
import br.com.fiap.soulupsociety.models.Carteira;
import org.springframework.stereotype.Service;

@Service
public class CarteiraService {

    private CarteiraDAO carteiraDAO;

    public CarteiraService() {
        carteiraDAO = new CarteiraDAO();
    }

    // Criar carteira
    public void criarCarteira(Carteira carteira) {

        if (carteira == null) {
            throw new IllegalArgumentException("Carteira não informada.");
        }

        if (carteira.getId() == null || carteira.getId() <= 0) {
            throw new IllegalArgumentException("ID da carteira inválido.");
        }

        if (carteira.getConta() == null ||
                carteira.getConta().getId() == null ||
                carteira.getConta().getId() <= 0) {
            throw new IllegalArgumentException("A carteira deve possuir uma conta válida.");
        }

        if (carteira.getQuantidadePontos() < 0) {
            throw new IllegalArgumentException("A quantidade de pontos não pode ser negativa.");
        }

        if (carteira.getValesDesconto() < 0) {
            throw new IllegalArgumentException("A quantidade de vales não pode ser negativa.");
        }

        if (carteira.getQuantidadePassagens() < 0) {
            throw new IllegalArgumentException("A quantidade de passagens não pode ser negativa.");
        }

        if (carteiraDAO.consultarPorId(carteira.getId()) != null) {
            throw new IllegalArgumentException("Já existe uma carteira com este ID.");
        }

        if (carteiraDAO.consultarPorConta(carteira.getConta().getId()) != null) {
            throw new IllegalArgumentException("Esta conta já possui uma carteira.");
        }

        carteiraDAO.criarCarteira(carteira);
    }

    // Consultar carteira pelo ID
    public Carteira consultarPorId(Integer id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }

        Carteira carteira = carteiraDAO.consultarPorId(id);

        if (carteira == null) {
            throw new IllegalArgumentException("Carteira não encontrada.");
        }

        return carteira;
    }

    // Consultar carteira pela conta
    public Carteira consultarPorConta(Integer idConta) {

        if (idConta == null || idConta <= 0) {
            throw new IllegalArgumentException("ID da conta inválido.");
        }

        Carteira carteira = carteiraDAO.consultarPorConta(idConta);

        if (carteira == null) {
            throw new IllegalArgumentException("Esta conta não possui uma carteira.");
        }

        return carteira;
    }

    // Adicionar pontos
    public void adicionarPontos(Integer idCarteira, Integer quantidade) {

        if (quantidade == null || quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade de pontos deve ser maior que zero.");
        }

        Carteira carteira = consultarPorId(idCarteira);

        if (quantidade > Integer.MAX_VALUE - carteira.getQuantidadePontos()) {
            throw new IllegalArgumentException("Limite máximo de pontos excedido.");
        }

        carteiraDAO.adicionarPontos(idCarteira, quantidade);
    }

    // Remover pontos
    public void removerPontos(Integer idCarteira, Integer quantidade) {

        if (quantidade == null || quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade de pontos deve ser maior que zero.");
        }

        Carteira carteira = consultarPorId(idCarteira);

        if (carteira.getQuantidadePontos() < quantidade) {
            throw new IllegalArgumentException("Saldo de pontos insuficiente.");
        }

        carteiraDAO.removerPontos(idCarteira, quantidade);
    }

    // Adicionar passagem
    public void adicionarPassagem(Integer idCarteira) {

        Carteira carteira = consultarPorId(idCarteira);

        if (carteira.getQuantidadePassagens() == Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Limite máximo de passagens atingido.");
        }

        carteiraDAO.adicionarPassagem(idCarteira);
    }

    // Deletar carteira
    public void deletarCarteira(Integer id) {

        consultarPorId(id);

        carteiraDAO.deletarCarteira(id);
    }
}
