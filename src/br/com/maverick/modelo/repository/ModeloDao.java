<<<<<<< Updated upstream:src/br/com/maverick/perfil/repository/model/ModeloDaoImplem.java
package br.com.maverick.Dao.model;
=======
package br.com.maverick.modelo.repository;
>>>>>>> Stashed changes:src/br/com/maverick/modelo/repository/ModeloDao.java

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

<<<<<<< Updated upstream:src/br/com/maverick/perfil/repository/model/ModeloDaoImplem.java
import br.com.maverick.model.model.ModeloModel;
public class ModeloDaoImplem implements ModeloDaoInterface{
=======
import br.com.maverick.modelo.model.ModeloModel;
public class ModeloDao {
>>>>>>> Stashed changes:src/br/com/maverick/modelo/repository/ModeloDao.java

	@PersistenceContext(unitName="OficinaPersistenceUnit")
	private EntityManager entityManager;
	
	public ModeloModel salvarModelo(ModeloModel modeloModel) {
		entityManager.persist(modeloModel);
		return modeloModel;
	}
	
	public void alterar(ModeloModel modeloModel) {
		ModeloModel modeloModelMerge = entityManager.merge(modeloModel);
		entityManager.persist(modeloModelMerge);
	}

	public void excluir(ModeloModel modeloModel) {
		ModeloModel modeloModelMerge = entityManager.merge(modeloModel);
		entityManager.remove(modeloModelMerge);		
	}

	@SuppressWarnings("unchecked")
	public List<ModeloModel> getModelos() {
		Query query = entityManager.createQuery("from ModeloModel");
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<ModeloModel> getModelos(Integer codModelo){
		Query query = entityManager.createQuery("from ModeloModel m where m.codModelo = :id");
			  query.setParameter("id", codModelo);
		return query.getResultList();
	}
	
	
	

}
