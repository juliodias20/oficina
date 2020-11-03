package br.com.maverick.service;

import java.util.List;

import br.com.maverick.model.MarcaModel;

public interface MarcaServiceInterface {
	
	MarcaModel salvarMarca(MarcaModel marcaModel);
	
	void alterar(MarcaModel marcaModel);
	
	void excluir(MarcaModel marcaModel);
	
	List<MarcaModel> getMarcas();

}
