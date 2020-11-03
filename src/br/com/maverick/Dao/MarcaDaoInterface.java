package br.com.maverick.Dao;

import java.util.List;

import br.com.maverick.model.MarcaModel;

public interface MarcaDaoInterface {
	
	MarcaModel salvarMarca(MarcaModel marcaModel);
	
	void alterar(MarcaModel marcaModel);
	
	void excluir(MarcaModel marcaModel);
	
	List<MarcaModel> getMarcas();
	

}
