package br.com.maverick.service.model;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.maverick.Dao.model.ModeloDaoInterface;
import br.com.maverick.model.model.ModeloModel;

public class ModeloServiceImplem implements ModeloServiceInterface{

	@Inject
	private ModeloDaoInterface modeloDaoInterface;
	
	@Override
	@Transactional
	public ModeloModel salvarModelo(ModeloModel modeloModel) {
		return modeloDaoInterface.salvarModelo(modeloModel);
	}

	@Override
	@Transactional
	public void alterar(ModeloModel modeloModel) {
		modeloDaoInterface.alterar(modeloModel);
		
	}

	@Override
	@Transactional
	public void excluir(ModeloModel modeloModel) {
		modeloDaoInterface.excluir(modeloModel);
		
	}

	@Override
	public List<ModeloModel> getModelos() {
		return modeloDaoInterface.getModelos();
	}

	@Override
	public List<ModeloModel> getModelos(Integer codModelo) {
		return modeloDaoInterface.getModelos(codModelo);
	}

}
