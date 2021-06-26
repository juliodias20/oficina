package br.com.maverick.perfil.service;

import java.util.List;

import javax.inject.Inject;

import br.com.maverick.perfil.model.PerfilModel;
import br.com.maverick.perfil.repository.PerfilDao;

public class PerfilService {

	@Inject
	private PerfilDao perfilDao;
	
	public PerfilModel salvarPerfil(PerfilModel perfilModel) {
		perfilDao.salvarPerfil(perfilModel);
		return perfilModel;
	}

	public void alterar(PerfilModel perfilModel) {
		perfilDao.alterar(perfilModel);
	}

	public void excluir(PerfilModel perfilModel) {
		perfilDao.excluir(perfilModel);
	}

	public List<PerfilModel> getPerfis() {
		return perfilDao.getPerfis();
	}

	public List<PerfilModel> getPerfis(Integer codPerfil) {
		return perfilDao.getPerfis(codPerfil);
	}
}
