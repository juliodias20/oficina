package br.com.maverick.Dao.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.maverick.model.model.PerfilModel;

public class PerfilDaoImplem implements PerfilDaoInterface {

	@PersistenceContext(unitName="OficinaPersistenceUnit")
	private EntityManager entityManager;
	
	@Override
	public PerfilModel salvarPerfil(PerfilModel perfilModel) {
		entityManager.persist(perfilModel);
		return perfilModel;
	}

	@Override
	public void alterar(PerfilModel perfilModel) {
		PerfilModel perfilModelMerge = entityManager.merge(perfilModel);
		entityManager.persist(perfilModelMerge);		
	}

	@Override
	public void excluir(PerfilModel perfilModel) {
		PerfilModel perfilModelMerge = entityManager.merge(perfilModel);
		entityManager.remove(perfilModelMerge);	
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<PerfilModel> getPerfis() {
		Query query = entityManager.createQuery("from PerfilModel");
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<PerfilModel> getPerfis(Integer codPerfil) {
		Query query = entityManager.createQuery("from PerfilModel where p.codPerfil = :id");
			  query.setParameter("id", codPerfil);
		return query.getResultList();
	}
	
}
