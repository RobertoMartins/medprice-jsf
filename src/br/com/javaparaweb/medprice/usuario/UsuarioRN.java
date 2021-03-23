package br.com.javaparaweb.medprice.usuario;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import br.com.javaparaweb.medprice.medicamento.Medicamento;
import br.com.javaparaweb.medprice.util.DAOException;
import br.com.javaparaweb.medprice.util.DAOFactory;
import br.com.javaparaweb.medprice.util.log.LoggerUtil;
import br.com.javaparaweb.medprice.web.ContextoBean;

public class UsuarioRN {
	private UsuarioDAO usuarioDAO;
	LocalDate data = LocalDate.now();

	public UsuarioRN() {
		this.usuarioDAO = DAOFactory.criarUsuarioDAO();
	}

	public Usuario carregar(Integer codigo) {
		return this.usuarioDAO.carregar(codigo);
	}

	public void salvar(Usuario usuario) throws IOException {
		Integer idUsuario = usuario.getIdUsuario();
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

	public void excluir(Usuario usuario) {
		this.usuarioDAO.excluir(usuario);
	}

	public Usuario buscarPorLogin(String login) {
		return this.usuarioDAO.buscarPorLogin(login);
	}
	
	public void favoritar(Medicamento med, Usuario u) {
		u.getMedicamentos().add(med);
		this.usuarioDAO.salvar(u);
		
	}
	

	public void updatePasswordToken(String token, String email) throws DAOException {
		
		Usuario usuario = this.usuarioDAO.buscarPorLogin(email);
		
		if(usuario != null) {
			usuario.setToken(token);
			this.usuarioDAO.salvar(usuario);
		}else {
			throw new DAOException("Não foi encontrado nenhum usuário com este email " + email);
		}
		
	}
	
	public Usuario get(String token) {
		return this.usuarioDAO.buscaPorPasswordToken(token);
	}

}
