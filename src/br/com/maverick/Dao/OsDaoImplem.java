package br.com.maverick.Dao;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.maverick.model.OsModel;

public class OsDaoImplem implements OsDaoInterface {

	@PersistenceContext(unitName="OficinaPersistenceUnit")
	private EntityManager entityManager;
	
	@Override
	public OsModel salvarOs(OsModel osModel) {
		entityManager.persist(osModel);
		return osModel;
	}

	@Override
	public void alterar(OsModel osModel) {
		OsModel osModelMerge = entityManager.merge(osModel);
		entityManager.persist(osModelMerge);
	}
	
	//metodo que atualiza o valor total da Ordem de Servi�o, conforme os itens da OS s�o inseridos/alterados/removidos;
	@Override
	public void alterar(Integer numOs, float valorTotal) {
		Query query = entityManager.createQuery("update OsModel os set os.valorTotal = :valorTotal where os.numOs = :numOs");
			  query.setParameter("valorTotal", valorTotal);
			  query.setParameter("numOs", numOs);
			  query.executeUpdate();
	}
	
	@Override
	public void excluir(OsModel osModel) {
		OsModel osModelMerge = entityManager.merge(osModel);
		entityManager.remove(osModelMerge);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<OsModel> getOsEncerradas() {
		Query query = entityManager.createQuery("from OsModel os where os.status = 'ENCERRADA' ");
		return query.getResultList();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<OsModel>getOsPendentes() {
		Query query = entityManager.createQuery("from OsModel os where os.status = 'PENDENTE' ");
		return query.getResultList();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<OsModel>getOsPendente(Integer numOs) {
		Query query = entityManager.createQuery("from OsModel os where os.status = 'PENDENTE' and os.numOs = :numOs ");
		query.setParameter("numOs", numOs);
		return query.getResultList();
	}
	
	//get todas as OS encerradas com filtro de parceiro
	@SuppressWarnings("unchecked")
	public List<OsModel> getOsEncerradas(Integer codParceiro){	
		Query query = entityManager.createQuery("from OsModel os where os.parceiroModel.codParceiro = :codParceiro and os.status = 'ENCERRADA' ");
		query.setParameter("codParceiro", codParceiro);
		
		return query.getResultList();
	}
	
	//get todas as OS com filtro de placa
	@SuppressWarnings("unchecked")
	public List<OsModel> getOsEncerradas(String placa){
		Query query = entityManager.createQuery("from OsModel os where os.placaCarro = :placa and os.status = 'ENCERRADA' ");
		query.setParameter("placa", placa.toUpperCase());
		
		return query.getResultList();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<OsModel> getOsEncerradas(Integer codParceiro, String placa) {
		Query query = entityManager.createQuery("from OsModel os where os.parceiroModel.codParceiro = :codParceiro and os.placaCarro = :placa and os.status = 'ENCERRADA' ");
		query.setParameter("codParceiro", codParceiro);
		query.setParameter("placa", placa.toUpperCase());

		return query.getResultList();
	}


	@SuppressWarnings("unchecked")
	//get todas as OS com filtros de periodo entre
	public List<OsModel> getOsEncerradas(Date dtIni, Date dtFim){
		Query query = entityManager.createQuery("from OsModel os where os.dhAbertura between :dtIni and :dtFim and os.status = 'ENCERRADA' ");
		query.setParameter("dtIni", dtIni);
		query.setParameter("dtFim", dtFim);
		
		return query.getResultList();
	}
	
	//get todas as OS com filtros de parceiro e de periodo entre
	@SuppressWarnings("unchecked")
	public List<OsModel> getOs(Integer numOs, Date dtIni, Date dtFim, Integer codParceiro){
		Query query = entityManager.createQuery("from OsModel os where os.dhAbertura between :dtIni and :dtFim and parceiroModel.codParceiro = :codParceiro and os.status = 'ENCERRADA' ");
		query.setParameter("dtIni", dtIni);
		query.setParameter("dtFim", dtFim);
		query.setParameter("codParceiro", codParceiro);
		
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<OsModel> getOsEncerradas(Integer codParceiro, Date dtIni, Date dtFim) {
		Query query = entityManager.createQuery("from OsModel os where os.parceiroModel.codParceiro = :codParceiro "
																+ "and os.dhAbertura between :dtIni and :dtFim and os.status = 'ENCERRADA' ");
		query.setParameter("codParceiro", codParceiro);
		query.setParameter("dtIni", dtIni);
		query.setParameter("dtFim", dtFim);
		
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<OsModel> getOsEncerradas(String placa, Date dtIni, Date dtFim) {
		Query query = entityManager.createQuery("from OsModel os where os.placaCarro = :placa and os.dhAbertura between :dtIni and :dtFim and os.status = 'ENCERRADA' ");
		query.setParameter("placa", placa.toUpperCase());
		query.setParameter("dtIni", dtIni);
		query.setParameter("dtFim", dtFim);

		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<OsModel> getOsEncerradas(Integer codParceiro, String placa, Date dtIni, Date dtFim) {
		Query query = entityManager.createQuery("from OsModel os where os.parceiroModel.codParceiro = :codParceiro "
																+ "and os.placaCarro = :placa "
																+ "and os.dhAbertura between :dtIni and :dtFim "
																+ "and os.status = 'ENCERRADA' ");
		query.setParameter("codParceiro", codParceiro);
		query.setParameter("placa", placa);
		query.setParameter("dtIni", dtIni);
		query.setParameter("dtFim", dtFim);
		
		return query.getResultList();
	}

}
