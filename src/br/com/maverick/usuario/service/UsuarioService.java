package br.com.maverick.usuario.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.maverick.usuario.model.UsuarioModel;
import br.com.maverick.usuario.repository.UsuarioDao;

public class UsuarioService {

	@Inject
	private UsuarioDao usuarioDao;
	
	@Transactional
	public UsuarioModel salvarUsuario(UsuarioModel usuarioModel) {
		usuarioDao.salvarUsuario(usuarioModel);
		return usuarioModel;
	}

	@Transactional
	public void alterar(UsuarioModel usuarioModel) {
		usuarioDao.alterar(usuarioModel);
	}

	@Transactional
	public void excluir(UsuarioModel usuarioModel) {
		usuarioDao.excluir(usuarioModel);
	}

	public List<UsuarioModel> getUsuarios() {
		return usuarioDao.getUsuarios();
	}

	public List<UsuarioModel> getUsuarios(Integer codUsuario) {
		return usuarioDao.getUsuarios(codUsuario);
	}

}
