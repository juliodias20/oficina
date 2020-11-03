package br.com.maverick.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.maverick.Dao.UsuarioDaoInterface;
import br.com.maverick.model.UsuarioModel;

public class UsuarioServiceImplem implements UsuarioServiceInterface {

	@Inject
	private UsuarioDaoInterface usuarioDaoInterface;
	
	@Override
	@Transactional
	public UsuarioModel salvarUsuario(UsuarioModel usuarioModel) {
		usuarioDaoInterface.salvarUsuario(usuarioModel);
		return usuarioModel;
	}

	@Override
	@Transactional
	public void alterar(UsuarioModel usuarioModel) {
		usuarioDaoInterface.alterar(usuarioModel);
	}

	@Override
	@Transactional
	public void excluir(UsuarioModel usuarioModel) {
		usuarioDaoInterface.excluir(usuarioModel);
	}

	@Override
	public List<UsuarioModel> getUsuarios() {
		return usuarioDaoInterface.getUsuarios();
	}

	@Override
	public List<UsuarioModel> getUsuarios(Integer codUsuario) {
		return usuarioDaoInterface.getUsuarios(codUsuario);
	}

}
