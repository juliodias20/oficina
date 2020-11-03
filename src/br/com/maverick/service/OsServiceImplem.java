package br.com.maverick.service;

import java.sql.Date;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.maverick.Dao.OsDaoInterface;
import br.com.maverick.model.OsModel;

public class OsServiceImplem implements OsServiceInterface {

	@Inject
	private OsDaoInterface osDaoInterface;
	
	@Override
	@Transactional
	public OsModel salvarOs(OsModel osModel) {
		osDaoInterface.salvarOs(osModel);
		return osModel;
	}

	@Override
	@Transactional
	public void alterar(OsModel osModel) {
		osDaoInterface.alterar(osModel);
	}

	@Override
	@Transactional
	public void alterar(Integer numOs, float valorTotal) {
		osDaoInterface.alterar(numOs,valorTotal);
	}
	
	@Override
	@Transactional
	public void excluir(OsModel osModel) {
		osDaoInterface.excluir(osModel);
		
	}
	
	@Override
	public List<OsModel> getOsEncerradas() {
		return osDaoInterface.getOsEncerradas();
	}

	@Override
	public List<OsModel> getOsPendentes() {
		return osDaoInterface.getOsPendentes();
	}
	
	@Override
	public List<OsModel> getOsPendente(Integer numOs) {
		return osDaoInterface.getOsPendente(numOs);
	}
	
	@Override
	public List<OsModel> getOsEncerradas(Integer codParceiro){
		return osDaoInterface.getOsEncerradas(codParceiro);
	}
	
	@Override
	public List<OsModel> getOsEncerradas(Date dtIni, Date dtFim){
		return osDaoInterface.getOsEncerradas(dtIni, dtFim);
	}
	
	@Override
	public List<OsModel> getOsEncerradas(String placa){
		return osDaoInterface.getOsEncerradas(placa);
	}

	@Override
	public List<OsModel> getOsEncerradas(Integer codParceiro, String placa) {
		return osDaoInterface.getOsEncerradas(codParceiro, placa);
	}

	@Override
	public List<OsModel> getOsEncerradas(Integer codParceiro, Date dtIni, Date dtFim) {
		return osDaoInterface.getOsEncerradas(codParceiro, dtIni, dtFim);
	}

	@Override
	public List<OsModel> getOsEncerradas(String placa, Date dtIni, Date dtFim) {
		return osDaoInterface.getOsEncerradas(placa, dtIni, dtFim);
	}

	@Override
	public List<OsModel> getOsEncerradas(Integer codParceiro, String placa, Date dtIni, Date dtFim) {
		return osDaoInterface.getOsEncerradas(codParceiro, placa, dtIni, dtFim);
	}

	

	

	

}
