package br.com.maverick.service.model;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.maverick.Dao.model.CidadeDaoInterface;
import br.com.maverick.model.model.CidadeModel;

public  class CidadeServiceImplem implements CidadeServiceInterface{

	@Inject
	private CidadeDaoInterface cidadeDaoInterface;

	@Override
	@Transactional
	public CidadeModel salvarCidade(CidadeModel cidadeModel) {
		return cidadeDaoInterface.salvarCidade(cidadeModel);
	}

	@Override
	@Transactional
	public void alterar(CidadeModel cidadeModel) {
		cidadeDaoInterface.alterar(cidadeModel);		
	}

	@Override
	@Transactional
	public void excluir(CidadeModel cidadeModel) {
		cidadeDaoInterface.excluir(cidadeModel);		
	}

	@Override
	@Transactional
	public List<CidadeModel> getCidades() {
		return cidadeDaoInterface.getCidades();
	}
	
}
