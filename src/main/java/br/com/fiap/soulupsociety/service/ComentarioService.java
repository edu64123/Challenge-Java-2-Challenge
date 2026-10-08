
package br.com.fiap.soulupsociety.service;

import br.com.fiap.soulupsociety.dao.ComentarioDAO;
import br.com.fiap.soulupsociety.models.Comentario;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ComentarioService {

    private ComentarioDAO comentarioDAO;

    public ComentarioService() {
        comentarioDAO = new ComentarioDAO();
    }

    // Cadastrar comentário
    public void cadastrarComentario(Comentario comentario) {

        if (comentario == null) {
            throw new IllegalArgumentException("Comentário não informado.");
        }

        if (comentario.getId() != null) {
            throw new IllegalArgumentException("O ID é gerado automaticamente.");
        }

        if (comentario.getTextoComentario() == null ||
                comentario.getTextoComentario().isBlank() ||
                comentario.getTextoComentario().trim().length() > 500) {
            throw new IllegalArgumentException("O comentário deve conter entre 1 e 500 caracteres.");
        }

        if (comentario.getConta() == null ||
                comentario.getConta().getId() == null ||
                comentario.getConta().getId() <= 0) {
            throw new IllegalArgumentException("O comentário deve possuir uma conta válida.");
        }

        if (comentario.getPostagem() == null ||
                comentario.getPostagem().getId() == null ||
                comentario.getPostagem().getId() <= 0) {
            throw new IllegalArgumentException("O comentário deve estar associado a uma postagem válida.");
        }

        // Define a data automaticamente
        comentario.setDataComentario(LocalDate.now());

        comentarioDAO.cadastrar(comentario);
    }

    // Consultar comentário pelo ID
    public Comentario consultarPorId(Integer id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }

        Comentario comentario = comentarioDAO.consultarPorId(id);

        if (comentario == null) {
            throw new IllegalArgumentException("Comentário não encontrado.");
        }

        return comentario;
    }

    // Consultar comentários pelo autor
    public List<Comentario> consultarPorAutor(String nomeConta) {

        if (nomeConta == null ||
                nomeConta.isBlank()) {
            throw new IllegalArgumentException("O nome da conta é obrigatório.");
        }

        if (nomeConta.trim().length() < 3 ||
                nomeConta.trim().length() > 50) {
            throw new IllegalArgumentException("O nome da conta deve ter entre 3 e 50 caracteres.");
        }

        return comentarioDAO.consultarPorAutor(nomeConta.trim());
    }

    // Atualizar comentário
    public void atualizarComentario(Comentario comentario) {

        if (comentario == null) {
            throw new IllegalArgumentException("Comentário não informado.");
        }

        if (comentario.getId() == null ||
                comentario.getId() <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }

        if (comentario.getTextoComentario() == null ||
                comentario.getTextoComentario().isBlank() ||
                comentario.getTextoComentario().trim().length() > 500) {
            throw new IllegalArgumentException("O comentário deve conter entre 1 e 500 caracteres.");
        }

        // Verifica se o comentário existe
        consultarPorId(comentario.getId());

        comentarioDAO.atualizarComentario(comentario);
    }

    // Deletar comentário
    public void deletarComentario(Comentario comentario) {

        if (comentario == null ||
                comentario.getId() == null ||
                comentario.getId() <= 0) {
            throw new IllegalArgumentException("Comentário inválido.");
        }

        // Verifica se o comentário existe
        consultarPorId(comentario.getId());

        comentarioDAO.deletarComentario(comentario);
    }
}
