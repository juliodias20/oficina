package br.com.maverick.service.model;

import javax.inject.Inject;

import br.com.maverick.Dao.model.UsuarioDaoInterface;
import br.com.maverick.model.model.UsuarioModel;

public class LoginServiceImplem implements LoginServiceInterface {

	@Inject
	private UsuarioDaoInterface usuarioDaoInterface;
	
	@Override
	public UsuarioModel login(UsuarioModel usuarioModel) {
		UsuarioModel usuario = usuarioDaoInterface.getUsuarioByLoginSenha(usuarioModel.getLogin(), usuarioModel.getSenha());
		
		return usuario;
	}

}
