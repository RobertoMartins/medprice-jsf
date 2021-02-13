package br.com.javaparaweb.medprice.usuario;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class UsuarioDAOHibernate implements UsuarioDAO {
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void salvar(Usuario usuario) {
		this.session.save(usuario);
	}

	public void atualizar(Usuario usuario) {
		if (usuario.getPermissao() == null || usuario.getPermissao().size() == 0) {
			Usuario usuarioPermissao = this.carregar(usuario.getIdUsuario());
			usuario.setPermissao(usuarioPermissao.getPermissao());
			this.session.evict(usuarioPermissao);
		}
		this.session.update(usuario);
	
	}

	public void excluir(Usuario usuario) {
		this.session.delete(usuario);
	}

	public Usuario carregar(Integer codigo) {
		return (Usuario) this.session.get(Usuario.class, codigo);
	}

	public List<Usuario> listar() {
		return this.session.createCriteria(Usuario.class).list();
	}

	public Usuario buscarPorLogin(String email) {
		String hql = "select u from Usuario u where u.email = :email";
		Query consulta = this.session.createQuery(hql);
		consulta.setString("login", email);
		return (Usuario) consulta.uniqueResult();
	}
}