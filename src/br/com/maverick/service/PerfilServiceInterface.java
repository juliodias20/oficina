package br.com.maverick.service;

import java.util.List;

import br.com.maverick.model.PerfilModel;

public interface PerfilServiceInterface {

	PerfilModel salvarPerfil(PerfilModel perfilModel);
	
	void alterar(PerfilModel perfilModel);
	
	void excluir(PerfilModel perfilModel);
	
	List<PerfilModel> getPerfis();

	List<PerfilModel> getPerfis(Integer codPerfil);
	
}
