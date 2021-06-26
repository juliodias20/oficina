package br.com.maverick.Dao;

import java.sql.Date;
import java.util.List;

import br.com.maverick.model.OsModel;

public interface OsDaoInterface {

	OsModel salvarOs(OsModel osModel);
	
	void alterar(OsModel osModel);
	
	void alterar(Integer numOs, float valorTotal);

	void excluir(OsModel osModel);
	
	List<OsModel> getOsPendentes();
	
	List<OsModel> getOsPendente(Integer numOs);

	List<OsModel> getOsEncerradas();

	List<OsModel> getOsEncerradas(Integer codParceiro);

	List<OsModel> getOsEncerradas(String placa);
	
	List<OsModel> getOsEncerradas(Date dtIni, Date dtFim);
	
	List<OsModel> getOsEncerradas(Integer codParceiro, String placa);

	List<OsModel> getOsEncerradas(Integer codParceiro, Date dtIni, Date dtFim);

	List<OsModel> getOsEncerradas(String placa, Date dtIni, Date dtFim);

	List<OsModel> getOsEncerradas(Integer codParceiro, String placa, Date dtIni, Date dtFim);

	

	

	

	
	
}
