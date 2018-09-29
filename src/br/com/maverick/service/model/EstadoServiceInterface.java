package br.com.maverick.service.model;

import java.util.List;

import br.com.maverick.model.model.EstadoModel;

public interface EstadoServiceInterface {

	EstadoModel salvarEstado(EstadoModel estadoModel);
	
	void alterar(EstadoModel estadoModel);
	
	void excluir(EstadoModel estadoModel);
	
	List<EstadoModel> getEstados();
	
}
