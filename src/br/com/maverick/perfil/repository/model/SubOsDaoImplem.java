<<<<<<< Updated upstream:src/br/com/maverick/perfil/repository/model/SubOsDaoImplem.java
package br.com.maverick.Dao.model;
=======
package br.com.maverick.osItem.repository;
>>>>>>> Stashed changes:src/br/com/maverick/osItem/repository/SubOsDao.java

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

<<<<<<< Updated upstream:src/br/com/maverick/perfil/repository/model/SubOsDaoImplem.java
import br.com.maverick.model.model.SubOsModel;
=======
import br.com.maverick.osItem.model.SubOsModel;
>>>>>>> Stashed changes:src/br/com/maverick/osItem/repository/SubOsDao.java

public class SubOsDao {

	@PersistenceContext(unitName="OficinaPersistenceUnit")
	private EntityManager entityManager;
	
	public SubOsModel salvarSubOs(SubOsModel subOsModel) {
		entityManager.persist(subOsModel);
		return subOsModel;
	}

	public void alterar(SubOsModel subOsModel) {
		SubOsModel subOsModelMerge = entityManager.merge(subOsModel);
		entityManager.persist(subOsModelMerge);
	}

	public void excluir(Integer numOs, Integer codProduto) {
		System.out.println("numOS: "+numOs);
		System.out.println("codProduto: "+codProduto);
		Query query = entityManager.createQuery("delete from SubOsModel sos where sos.numOs = :numOs and sos.produtoModel.codProduto = :codProduto");
			  query.setParameter("numOs", numOs);
			  query.setParameter("codProduto", codProduto);
			  query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<SubOsModel> getSubOs() {
		Query query = entityManager.createQuery("from SubOsModel sos");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<SubOsModel> getSubOs(Integer numOs) {
		Query query = entityManager.createQuery("from SubOsModel sos where sos.numOs = :numOs");
		  	  query.setParameter("numOs", numOs);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<SubOsModel> getSubOs(Integer numOs, Integer codProduto) {
		Query query = entityManager.createQuery("from SubOsModel sos where sos.numOs = :numOs and sos.produtoModel.codProduto = :codProduto");
			  query.setParameter("numOs", numOs);
			  query.setParameter("codProduto", codProduto);
		return query.getResultList();
	}
	
}
