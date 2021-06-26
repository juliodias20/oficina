package br.com.maverick.parceiro.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.maverick.parceiro.model.ParceiroModel;
import br.com.maverick.parceiro.repository.ParceiroDao;

public class ParceiroService {

	@Inject
	private ParceiroDao parceiroDao;

	@Transactional
	public ParceiroModel salvarParceiro(ParceiroModel parceiroModel) {
		return parceiroDao.salvarParceiro(parceiroModel);
	}

	@Transactional
	public void alterar(ParceiroModel parceiroModel) {
		parceiroDao.alterar(parceiroModel);
	}

	@Transactional
	public void excluir(ParceiroModel parceiroModel) {
		parceiroDao.excluir(parceiroModel);
	}

	public List<ParceiroModel> getParceiros() {
		return parceiroDao.getParceiros();		 
	}
	
	public List<ParceiroModel> getParceiros(Integer codParceiro) {
		return parceiroDao.getParceiros(codParceiro);
	}
	
	/*busca parceiro por CPF(se for pessoa fisica) ou por CNPJ (se for pessoa jurifica*/
	public List<ParceiroModel> getParceiros(String parametro, String tipoParametro) {
		return parceiroDao.getParceiros(parametro, tipoParametro);
	}
	
}
