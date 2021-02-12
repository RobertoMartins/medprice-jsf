package br.com.javaparaweb.medprice.usuario;

import br.com.javaparaweb.medprice.util.DAOFactory;

import java.util.List;

public class UsuarioRN {
	private UsuarioDAO usuarioDAO;

	public UsuarioRN() {
		this.usuarioDAO = DAOFactory.criarUsuarioDAO();
	}
	
	public Usuario carregar(Integer codigo) {
		return this.usuarioDAO.carregar(codigo);
	}
	
	public void salvar(Usuario usuario) {
		Integer idUsuario = usuario.getIdUsuario();
		System.out.println(usuario.getIdUsuario());
		if (idUsuario == null || idUsuario == 0) {

		usuario.getPermissao().add("ROLE_USUARIO");

			this.usuarioDAO.salvar(usuario);
		} else {
			this.usuarioDAO.atualizar(usuario);
		}
	}

	public List<Usuario> listar() {
		return this.usuarioDAO.listar();
	}

	// public void excluir(Usuario usuario) {
	// this.usuarioDAO.excluir(usuario);
	// }

}
