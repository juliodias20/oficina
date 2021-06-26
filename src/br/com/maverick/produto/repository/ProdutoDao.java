<<<<<<< Updated upstream:src/br/com/maverick/perfil/repository/model/ProdutoDaoImplem.java
package br.com.maverick.Dao.model;
=======
package br.com.maverick.produto.repository;
>>>>>>> Stashed changes:src/br/com/maverick/produto/repository/ProdutoDao.java

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

<<<<<<< Updated upstream:src/br/com/maverick/perfil/repository/model/ProdutoDaoImplem.java
import br.com.maverick.model.model.ProdutoModel;
=======
import br.com.maverick.produto.model.ProdutoModel;
>>>>>>> Stashed changes:src/br/com/maverick/produto/repository/ProdutoDao.java

public class ProdutoDao {

	@PersistenceContext(unitName="OficinaPersistenceUnit")
	private EntityManager entityManager;
	
	public ProdutoModel salvarProduto(ProdutoModel produtoModel) {
		entityManager.persist(produtoModel);
		return produtoModel;
	}

	public void alterar(ProdutoModel produtoModel) {
		ProdutoModel produtoModelMerge = entityManager.merge(produtoModel);
		entityManager.persist(produtoModelMerge);
	}
	
	public void excluir(ProdutoModel produtoModel) {
		ProdutoModel produtoModelMerge = entityManager.merge(produtoModel);
		entityManager.remove(produtoModelMerge);
	}

	//get todos os produtos
	@SuppressWarnings("unchecked")
	public List<ProdutoModel> getProdutos() {
		Query query = entityManager.createQuery("from ProdutoModel");
		return query.getResultList();
	}
	
	//get um produto por ID
	@SuppressWarnings("unchecked")
	public List<ProdutoModel> getProdutos(Integer codProduto){
		Query query = entityManager.createQuery("from ProdutoModel p where p.codProduto = :id");
			  query.setParameter("id", codProduto);
		return query.getResultList();
	}
}