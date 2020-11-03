package br.com.maverick.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.maverick.Dao.ParceiroDaoInterface;
import br.com.maverick.model.ParceiroModel;

public class ParceiroServiceImplem implements ParceiroServiceInterface {

	@Inject
	private ParceiroDaoInterface parceiroDaoInterface;

	@Override
	@Transactional
	public ParceiroModel salvarParceiro(ParceiroModel parceiroModel) {
		return parceiroDaoInterface.salvarParceiro(parceiroModel);
	}

	@Override
	@Transactional
	public void alterar(ParceiroModel parceiroModel) {
		parceiroDaoInterface.alterar(parceiroModel);
	}

	@Override
	@Transactional
	public void excluir(ParceiroModel parceiroModel) {
		parceiroDaoInterface.excluir(parceiroModel);
	}

	@Override
	public List<ParceiroModel> getParceiros() {
		return parceiroDaoInterface.getParceiros();		 
	}
	
	@Override
	public List<ParceiroModel> getParceiros(Integer parametro) {
		return parceiroDaoInterface.getParceiros(parametro);
	}
	
	@Override
	public List<ParceiroModel> getParceiros(String parametro, String tipoParametro) {
		return parceiroDaoInterface.getParceiros(parametro, tipoParametro);
	}
	
}
