package br.com.javaparaweb.medprice.comentario;

import java.util.List;

import br.com.javaparaweb.medprice.usuario.Usuario;
import br.com.javaparaweb.medprice.util.DAOFactory;

public class ComentarioRN {
	private ComentarioDAO comentarioDAO;

	public ComentarioRN() {
		this.comentarioDAO = DAOFactory.criarComentarioDAO();
	}

	public List<Comentario> listar(Usuario usuario) {
		return this.comentarioDAO.listar(usuario);
	}
	
	public void adicionar(Comentario comentario){
		comentarioDAO.salvar(comentario);
	}

	public void excluir(Comentario comentario) {
		this.comentarioDAO.excluir(comentario);
	}

	public void excluir(Usuario usuario) {
		List<Comentario> lista = this.listar(usuario);
		for (Comentario comentario : lista) {
			this.comentarioDAO.excluir(comentario);
		}
	}

	public Comentario carregar(Integer comentario) {
		return this.comentarioDAO.carregar(comentario);
	}
}
