package br.com.maverick.Dao.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.maverick.model.model.OsModel;

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

	//get todas as OSs
	@Override
	@SuppressWarnings("unchecked")
	public List<OsModel> getOs() {
		Query query = entityManager.createQuery("from OsModel os order by os.numOs desc");
		return query.getResultList();
	}
	
	//get uma OS pelo NUMERO ou STATUS
	@Override
	@SuppressWarnings("unchecked")
	public List<OsModel> getOs(Integer numOs) {
		if(numOs >= 1) {
			Query query = entityManager.createQuery("from OsModel os where os.numOs = :numOs order by os.numOs desc");
			query.setParameter("numOs", numOs);
			return query.getResultList();
		}else if(numOs == 0) {
			Query query = entityManager.createQuery("from OsModel os where os.status = 'PENDENTE' order by os.numOs desc");
			return query.getResultList();
		}else if(numOs == -1) {
			Query query = entityManager.createQuery("from OsModel os where os.status = 'ENCERRADA' order by os.numOs desc");
			return query.getResultList();
		}else {
			return null;
		}
	
	}

	

	

}
