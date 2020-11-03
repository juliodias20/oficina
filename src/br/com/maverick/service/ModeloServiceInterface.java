package br.com.maverick.service;

import java.util.List;

import br.com.maverick.model.ModeloModel;

public interface ModeloServiceInterface {

	ModeloModel salvarModelo(ModeloModel modeloModel);
	
	void alterar(ModeloModel modeloModel);
	
	void excluir(ModeloModel modeloModel);
	
	List<ModeloModel> getModelos();
	
	List<ModeloModel> getModelos(Integer codModelo);
	
}
