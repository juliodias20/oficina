<<<<<<< Updated upstream:src/br/com/maverick/perfil/repository/model/EstoqueDaoImplem.java
package br.com.maverick.Dao.model;
=======
package br.com.maverick.estoque.repository;
>>>>>>> Stashed changes:src/br/com/maverick/estoque/repository/EstoqueDao.java

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

<<<<<<< Updated upstream:src/br/com/maverick/perfil/repository/model/EstoqueDaoImplem.java
import br.com.maverick.model.model.EstoqueModel;
=======
import br.com.maverick.estoque.model.EstoqueModel;
>>>>>>> Stashed changes:src/br/com/maverick/estoque/repository/EstoqueDao.java

public class EstoqueDao {

	@PersistenceContext(unitName="OficinaPersistenceUnit")
	private EntityManager entityManager;
	
	public EstoqueModel salvarEstoque(EstoqueModel estoqueModel) {
		entityManager.persist(estoqueModel);
		return estoqueModel;
	}

	public void alterar(EstoqueModel estoqueModel) {
		EstoqueModel estoqueModelMerge = entityManager.merge(estoqueModel);
		entityManager.persist(estoqueModelMerge);		
	}
	
	public void alterar(Integer codProduto, Integer qtdEstoque) {
		Query query = entityManager.createQuery("update EstoqueModel est set est.qtdEstoque = :qtdEstoque where est.produtoModel.codProduto = :codProduto");
			  query.setParameter("qtdEstoque", qtdEstoque);
			  query.setParameter("codProduto", codProduto);
			  query.executeUpdate();
	}

	public void excluir(EstoqueModel estoqueModel) {
		EstoqueModel estoqueModelMerge = entityManager.merge(estoqueModel);
		entityManager.remove(estoqueModelMerge);
	}

	@SuppressWarnings("unchecked")
	public List<EstoqueModel> getEstoques() {
		Query query = entityManager.createQuery("from EstoqueModel es");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<EstoqueModel> getEstoquesPorProduto(Integer codProduto) {
		Query query = entityManager.createQuery("from EstoqueModel es where codProduto = :codProduto");
			  query.setParameter("codProduto", codProduto);
		return query.getResultList();
	}
}
