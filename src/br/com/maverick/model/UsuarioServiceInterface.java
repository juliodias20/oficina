package br.com.maverick.service.model;

import java.util.List;

import br.com.maverick.model.model.UsuarioModel;


public interface UsuarioServiceInterface {
	
	UsuarioModel salvarUsuario(UsuarioModel usuarioModel);
	
	void alterar(UsuarioModel usuarioModel);
	
	void excluir(UsuarioModel usuarioModel);
	
	List<UsuarioModel> getUsuarios();

	List<UsuarioModel> getUsuarios(Integer codUsuario);

}
