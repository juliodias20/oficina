package br.com.maverick.service.model;

import java.util.List;

import br.com.maverick.model.model.MarcaModel;

public interface MarcaServiceInterface {
	
	MarcaModel salvarMarca(MarcaModel marcaModel);
	
	void alterar(MarcaModel marcaModel);
	
	void excluir(MarcaModel marcaModel);
	
	List<MarcaModel> getMarcas();

}
