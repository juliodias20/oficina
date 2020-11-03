package br.com.maverick.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.maverick.model.ModeloModel;
public class ModeloDaoImplem implements ModeloDaoInterface{

	@PersistenceContext(unitName="OficinaPersistenceUnit")
	private EntityManager entityManager;
	
	@Override
	public ModeloModel salvarModelo(ModeloModel modeloModel) {
		entityManager.persist(modeloModel);
		return modeloModel;
	}

	@Override
	public void alterar(ModeloModel modeloModel) {
		ModeloModel modeloModelMerge = entityManager.merge(modeloModel);
		entityManager.persist(modeloModelMerge);
	}

	@Override
	public void excluir(ModeloModel modeloModel) {
		ModeloModel modeloModelMerge = entityManager.merge(modeloModel);
		entityManager.remove(modeloModelMerge);		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ModeloModel> getModelos() {
		Query query = entityManager.createQuery("from ModeloModel");
		return query.getResultList();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<ModeloModel> getModelos(Integer codModelo){
		Query query = entityManager.createQuery("from ModeloModel m where m.codModelo = :id");
			  query.setParameter("id", codModelo);
		return query.getResultList();
	}
	
	
	

}
