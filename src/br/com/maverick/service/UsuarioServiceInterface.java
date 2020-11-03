package br.com.maverick.service;

import java.util.List;

import br.com.maverick.model.UsuarioModel;


public interface UsuarioServiceInterface {
	
	UsuarioModel salvarUsuario(UsuarioModel usuarioModel);
	
	void alterar(UsuarioModel usuarioModel);
	
	void excluir(UsuarioModel usuarioModel);
	
	List<UsuarioModel> getUsuarios();

	List<UsuarioModel> getUsuarios(Integer codUsuario);

}
