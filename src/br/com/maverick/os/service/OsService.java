package br.com.maverick.os.service;

import java.sql.Date;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.maverick.os.model.OsModel;
import br.com.maverick.os.repository.OsDao;

public class OsService {

	@Inject
	private OsDao osDao;
	
	@Transactional
	public OsModel salvarOs(OsModel osModel) {
		osDao.salvarOs(osModel);
		return osModel;
	}

	@Transactional
	public void alterar(OsModel osModel) {
		osDao.alterar(osModel);
	}

	@Transactional
	public void alterar(Integer numOs, float valorTotal) {
		osDao.alterar(numOs,valorTotal);
	}
	
	@Transactional
	public void excluir(OsModel osModel) {
		osDao.excluir(osModel);
		
	}
	
	public List<OsModel> getOsEncerradas() {
		return osDao.getOsEncerradas();
	}

	public List<OsModel> getOsPendentes() {
		return osDao.getOsPendentes();
	}
	
	public List<OsModel> getOsPendente(Integer numOs) {
		return osDao.getOsPendente(numOs);
	}
	
	public List<OsModel> getOsEncerradas(Integer codParceiro){
		return osDao.getOsEncerradas(codParceiro);
	}
	
	public List<OsModel> getOsEncerradas(Date dtIni, Date dtFim){
		return osDao.getOsEncerradas(dtIni, dtFim);
	}
	
	public List<OsModel> getOsEncerradas(String placa){
		return osDao.getOsEncerradas(placa);
	}

	public List<OsModel> getOsEncerradas(Integer codParceiro, String placa) {
		return osDao.getOsEncerradas(codParceiro, placa);
	}

	public List<OsModel> getOsEncerradas(Integer codParceiro, Date dtIni, Date dtFim) {
		return osDao.getOsEncerradas(codParceiro, dtIni, dtFim);
	}

	public List<OsModel> getOsEncerradas(String placa, Date dtIni, Date dtFim) {
		return osDao.getOsEncerradas(placa, dtIni, dtFim);
	}

	public List<OsModel> getOsEncerradas(Integer codParceiro, String placa, Date dtIni, Date dtFim) {
		return osDao.getOsEncerradas(codParceiro, placa, dtIni, dtFim);
	}

}
