package br.com.maverick.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.maverick.model.SubOsModel;

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
	public void excluir(Integer numOs, Integer codProduto) {
		System.out.println("numOS: "+numOs);
		System.out.println("codProduto: "+codProduto);
		Query query = entityManager.createQuery("delete from SubOsModel sos where sos.numOs = :numOs and sos.produtoModel.codProduto = :codProduto");
			  query.setParameter("numOs", numOs);
			  query.setParameter("codProduto", codProduto);
			  query.executeUpdate();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SubOsModel> getSubOs() {
		Query query = entityManager.createQuery("from SubOsModel sos");
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
		Query query = entityManager.createQuery("from SubOsModel sos where sos.numOs = :numOs and sos.produtoModel.codProduto = :codProduto");
			  query.setParameter("numOs", numOs);
			  query.setParameter("codProduto", codProduto);
		return query.getResultList();
	}

	

}
