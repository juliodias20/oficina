package br.com.maverick.service;

import java.util.List;

import javax.inject.Inject;

import br.com.maverick.Dao.PerfilDaoInterface;
import br.com.maverick.model.PerfilModel;

public class PerfilServiceImplem implements PerfilServiceInterface {

	@Inject
	private PerfilDaoInterface perfilDaoInterface;
	
	@Override
	public PerfilModel salvarPerfil(PerfilModel perfilModel) {
		perfilDaoInterface.salvarPerfil(perfilModel);
		return perfilModel;
	}

	@Override
	public void alterar(PerfilModel perfilModel) {
		perfilDaoInterface.alterar(perfilModel);
		
	}

	@Override
	public void excluir(PerfilModel perfilModel) {
		perfilDaoInterface.excluir(perfilModel);
		
	}

	@Override
	public List<PerfilModel> getPerfis() {
		return perfilDaoInterface.getPerfis();
	}

	@Override
	public List<PerfilModel> getPerfis(Integer codPerfil) {
		return perfilDaoInterface.getPerfis(codPerfil);

	}

}
