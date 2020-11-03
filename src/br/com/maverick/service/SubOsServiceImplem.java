package br.com.maverick.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.maverick.Dao.SubOsDaoInterface;
import br.com.maverick.model.SubOsModel;

public class SubOsServiceImplem implements SubOsServiceInterface {

	@Inject
	private SubOsDaoInterface subOsDaoInterface;
	
	@Override
	@Transactional
	public SubOsModel salvarSubOs(SubOsModel subOsModel) {
		subOsDaoInterface.salvarSubOs(subOsModel);
		return subOsModel;
	}

	@Override
	@Transactional
	public void alterar(SubOsModel subOsModel) {
		subOsDaoInterface.alterar(subOsModel);
	}

	@Override
	@Transactional
	public void excluir(Integer numOs, Integer codProduto) {
		subOsDaoInterface.excluir(numOs, codProduto);
	}

	@Override
	public List<SubOsModel> getSubOs() {
		return subOsDaoInterface.getSubOs();
	}

	@Override
	public List<SubOsModel> getSubOs(Integer numOs){
		return subOsDaoInterface.getSubOs(numOs);
	}
	
	@Override
	public List<SubOsModel> getSubOs(Integer numOs, Integer codProduto) {
		return subOsDaoInterface.getSubOs(numOs, codProduto);
	}

}
