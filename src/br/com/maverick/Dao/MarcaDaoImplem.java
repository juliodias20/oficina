package br.com.maverick.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.maverick.model.MarcaModel;

public class MarcaDaoImplem implements MarcaDaoInterface {

	@PersistenceContext(unitName="OficinaPersistenceUnit")
	private EntityManager entityManager;
	
	@Override
	public MarcaModel salvarMarca(MarcaModel marcaModel) {
		entityManager.persist(marcaModel);
		return marcaModel;
	}

	@Override
	public void alterar(MarcaModel marcaModel) {
		MarcaModel marcaModelMerge = entityManager.merge(marcaModel);
		entityManager.persist(marcaModelMerge);	
	}

	@Override
	public void excluir(MarcaModel marcaModel) {
		MarcaModel marcaModelMerge = entityManager.merge(marcaModel);
		entityManager.remove(marcaModelMerge);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<MarcaModel> getMarcas() {
		Query query = entityManager.createQuery("from MarcaModel");
		return query.getResultList();
	}

}
