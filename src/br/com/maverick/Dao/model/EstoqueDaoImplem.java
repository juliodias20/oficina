package br.com.maverick.Dao.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.maverick.model.model.EstoqueModel;

public class EstoqueDaoImplem implements EstoqueDaoInterface {

	@PersistenceContext(unitName="OficinaPersistenceUnit")
	private EntityManager entityManager;
	
	@Override
	public EstoqueModel salvarEstoque(EstoqueModel estoqueModel) {
		entityManager.persist(estoqueModel);
		return estoqueModel;
	}

	@Override
	public void alterar(EstoqueModel estoqueModel) {
		EstoqueModel estoqueModelMerge = entityManager.merge(estoqueModel);
		entityManager.persist(estoqueModelMerge);		
	}
	
	@Override
	public void alterar(Integer codProduto, Integer qtdEstoque) {
		Query query = entityManager.createQuery("update EstoqueModel est set est.qtdEstoque = :qtdEstoque where est.produtoModel.codProduto = :codProduto");
			  query.setParameter("qtdEstoque", qtdEstoque);
			  query.setParameter("codProduto", codProduto);
			  query.executeUpdate();
		
	}

	@Override
	public void excluir(EstoqueModel estoqueModel) {
		EstoqueModel estoqueModelMerge = entityManager.merge(estoqueModel);
		entityManager.remove(estoqueModelMerge);
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<EstoqueModel> getEstoques() {
		Query query = entityManager.createQuery("from EstoqueModel es");
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<EstoqueModel> getEstoquesPorProduto(Integer codProduto) {
		Query query = entityManager.createQuery("from EstoqueModel es where codProduto = :p_codProduto");
			  query.setParameter("p_codProduto", codProduto);
		return query.getResultList();
	}

}
