package br.com.maverick.login.service;

import javax.inject.Inject;

import br.com.maverick.usuario.model.UsuarioModel;
import br.com.maverick.usuario.repository.UsuarioDao;

public class LoginService  {

	@Inject
	private UsuarioDao usuarioDao;
	
	public UsuarioModel login(UsuarioModel usuarioModel) {
		UsuarioModel usuario = usuarioDao.getUsuarioByLoginSenha(usuarioModel.getLogin(), usuarioModel.getSenha());
		
		return usuario;
	}

}
