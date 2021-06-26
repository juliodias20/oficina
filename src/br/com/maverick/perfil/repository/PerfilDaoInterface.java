package br.com.maverick.Dao.model;

import java.util.List;

import br.com.maverick.model.model.PerfilModel;

public interface PerfilDaoInterface {

	PerfilModel salvarPerfil(PerfilModel perfilModel);
	
	void alterar(PerfilModel perfilModel);
	
	void excluir(PerfilModel perfilModel);
	
	List<PerfilModel> getPerfis();

	List<PerfilModel> getPerfis(Integer codPerfil);
	
}
