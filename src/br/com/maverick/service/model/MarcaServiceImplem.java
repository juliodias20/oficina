package br.com.maverick.service.model;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.maverick.Dao.model.MarcaDaoInterface;
import br.com.maverick.model.model.MarcaModel;

public class MarcaServiceImplem implements MarcaServiceInterface {

	@Inject
	private MarcaDaoInterface marcaDaoInterface;
	
	@Override
	@Transactional
	public MarcaModel salvarMarca(MarcaModel marcaModel) {
		return marcaDaoInterface.salvarMarca(marcaModel);
	}

	@Override
	@Transactional
	public void alterar(MarcaModel marcaModel) {
		marcaDaoInterface.alterar(marcaModel);		
	}

	@Override
	@Transactional
	public void excluir(MarcaModel marcaModel) {
		marcaDaoInterface.excluir(marcaModel);		
	}

	@Override
	public List<MarcaModel> getMarcas() {
		return marcaDaoInterface.getMarcas();
	}

	
	
}
