package br.com.maverick.parceiro.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.maverick.parceiro.model.ParceiroModel;

public class ParceiroDao {

	@PersistenceContext(unitName="OficinaPersistenceUnit")
	private EntityManager entityManager;

	public ParceiroModel salvarParceiro(ParceiroModel parceiroModel) {
		entityManager.persist(parceiroModel);
		return parceiroModel;
	}

	public void alterar(ParceiroModel parceiroModel) {
		ParceiroModel parceiroModelMerge = entityManager.merge(parceiroModel);
		entityManager.persist(parceiroModelMerge);		
	}

	public void excluir(ParceiroModel parceiroModel) {
		ParceiroModel parceiroModelMerge = entityManager.merge(parceiroModel);
		entityManager.remove(parceiroModelMerge);
	}

	@SuppressWarnings("unchecked")
	public List<ParceiroModel> getParceiros() {
		Query query = entityManager.createQuery("from ParceiroModel");
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<ParceiroModel> getParceiros(Integer codParceiro){
			
		Query query = entityManager.createQuery("from ParceiroModel c where c.codParceiro = :codParceiro");
			  query.setParameter("codParceiro", codParceiro);
			 
		return query.getResultList();	
	}
	
	@SuppressWarnings("unchecked")
	public List<ParceiroModel> getParceiros(String parametro, String tipoParametro){
		if(tipoParametro.equals("cpf")) {
			Query query = entityManager.createQuery("from ParceiroModel c where c.cpf = :cpf");
				  query.setParameter("cpf", parametro);
			return query.getResultList();
		}else if(tipoParametro.equals("cnpj")) {
			Query query = entityManager.createQuery("from ParceiroModel c where c.cnpj = :cnpj");
				  query.setParameter("cnpj", parametro);
			return query.getResultList();
		}else if (tipoParametro.equals("codParceiro")) {
			Query query = entityManager.createQuery("from ParceiroModel c where c.codParceiro = :codParceiro");
				  query.setParameter("codParceiro", parametro);
			return query.getResultList();
		}else {
			System.out.println("caiu no else");
			return null;
		}
	}
	
	
}
