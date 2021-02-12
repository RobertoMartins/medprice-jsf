package br.com.javaparaweb.medprice.comentario;

import java.util.List;
import br.com.javaparaweb.medprice.usuario.Usuario;

public interface ComentarioDAO {
	public Comentario salvar(Comentario comentario);

	public void excluir(Comentario comentario);

	public Comentario carregar(Integer comentario);

	public List<Comentario> listar(Usuario usuario);
}
