package br.com.maverick.Dao.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.maverick.model.model.ProdutoModel;

public class ProdutoDaoImplem implements ProdutoDaoInterface {

	@PersistenceContext(unitName="OficinaPersistenceUnit")
	private EntityManager entityManager;
	
	@Override
	public ProdutoModel salvarProduto(ProdutoModel produtoModel) {
		entityManager.persist(produtoModel);
		return produtoModel;
	}

	@Override
	public void alterar(ProdutoModel produtoModel) {
		ProdutoModel produtoModelMerge = entityManager.merge(produtoModel);
		entityManager.persist(produtoModelMerge);
	}

	@Override
	public void excluir(ProdutoModel produtoModel) {
		ProdutoModel produtoModelMerge = entityManager.merge(produtoModel);
		entityManager.remove(produtoModelMerge);
		
	}

	//get todos os produtos
	@Override
	@SuppressWarnings("unchecked")
	public List<ProdutoModel> getProdutos() {
		Query query = entityManager.createQuery("from ProdutoModel");
		return query.getResultList();
	}
	
	
	//get um produto por ID
	@Override
	@SuppressWarnings("unchecked")
	public List<ProdutoModel> getProdutos(Integer codProduto){
		Query query = entityManager.createQuery("from ProdutoModel p where p.codProduto = :id");
			  query.setParameter("id", codProduto);
		return query.getResultList();
	}

	
	
	
}
