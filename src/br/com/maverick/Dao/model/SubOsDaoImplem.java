package br.com.maverick.Dao.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.maverick.model.model.SubOsModel;

public class SubOsDaoImplem implements SubOsDaoInterface {

	@PersistenceContext(unitName="OficinaPersistenceUnit")
	private EntityManager entityManager;
	
	@Override
	public SubOsModel salvarSubOs(SubOsModel subOsModel) {
		entityManager.persist(subOsModel);
		return subOsModel;
	}

	@Override
	public void alterar(SubOsModel subOsModel) {
		SubOsModel subOsModelMerge = entityManager.merge(subOsModel);
		entityManager.persist(subOsModelMerge);
	}

	@Override
	public void excluir(SubOsModel subOsModel) {
		SubOsModel subOsModelMerge = entityManager.merge(subOsModel);
		entityManager.remove(subOsModelMerge);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SubOsModel> getSubOs() {
		Query query = entityManager.createQuery("from SubOsModel");
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SubOsModel> getSubOs(Integer numOs) {
		Query query = entityManager.createQuery("from SubOsModel sos where sos.numOs = :numOs");
		  	  query.setParameter("numOs", numOs);
		return query.getResultList();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<SubOsModel> getSubOs(Integer numOs, Integer codProduto) {
		Query query = entityManager.createQuery("from SubOsModel sos where sos.numOs = :numOs and sos.codProduto = :codProduto");
			  query.setParameter("numOs", numOs);
			  query.setParameter("codProduto", codProduto);
		return query.getResultList();
	}

	

}
