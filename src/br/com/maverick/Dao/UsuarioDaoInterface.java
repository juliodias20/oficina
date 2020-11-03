package br.com.maverick.Dao;

import java.util.List;

import br.com.maverick.model.UsuarioModel;

public interface UsuarioDaoInterface {

	UsuarioModel salvarUsuario(UsuarioModel usuarioModel);
	
	void alterar(UsuarioModel usuarioModel);
	
	void excluir(UsuarioModel usuarioModel);
	
	UsuarioModel getUsuarioByLoginSenha(String login, String senha) ;
	
	List<UsuarioModel> getUsuarios();

	List<UsuarioModel> getUsuarios(Integer codUsuario);
}
