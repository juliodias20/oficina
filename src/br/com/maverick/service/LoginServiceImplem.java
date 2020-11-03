package br.com.maverick.service;

import javax.inject.Inject;

import br.com.maverick.Dao.UsuarioDaoInterface;
import br.com.maverick.model.UsuarioModel;

public class LoginServiceImplem implements LoginServiceInterface {

	@Inject
	private UsuarioDaoInterface usuarioDaoInterface;
	
	@Override
	public UsuarioModel login(UsuarioModel usuarioModel) {
		UsuarioModel usuario = usuarioDaoInterface.getUsuarioByLoginSenha(usuarioModel.getLogin(), usuarioModel.getSenha());
		
		return usuario;
	}

}
