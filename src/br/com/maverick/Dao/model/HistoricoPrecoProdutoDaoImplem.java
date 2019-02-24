package br.com.maverick.Dao.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.maverick.model.model.HistoricoPrecoProduto;

public class HistoricoPrecoProdutoDaoImplem implements HistoricoPrecoProdutoDaoInterface {

	@PersistenceContext(unitName="OficinaPersistenceUnit")
	private EntityManager entityManager;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<HistoricoPrecoProduto> getHistoricoDhref(Integer codProduto, Timestamp dhref) {
		Query query = entityManager.createNativeQuery(""
				+ "SELECT hp1.* "
				+ "FROM tb_produto_historico_preco hp1 "
				+ "WHERE hp1.codproduto = :codProduto "
				+ "AND hp1.dhlanc = (SELECT max(hp2.dhlanc) "
						  		  + "FROM tb_produto_historico_preco hp2 "
								  + "WHERE hp2.codproduto = hp1.codproduto "
								  + "AND hp2.dhlanc <= :dhref");
			  query.setParameter("codProduto", codProduto);
			  query.setParameter("dhref", dhref);
		return query.getResultList();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<HistoricoPrecoProduto> getTodosHistoricoProduto(Integer codProduto) {
		Query query = entityManager.createNativeQuery(""
				+ "SELECT hp1.* "
				+ "FROM tb_produto_historico_preco hp1 "
				+ "WHERE hp1.codproduto = :codProduto");
			  query.setParameter("codProduto", codProduto);
			  
		//List<Object> listResult = query.getResultList();
			  
		List<HistoricoPrecoProduto> listHP = new ArrayList<>();
		
		listHP = (List<HistoricoPrecoProduto>) query.getResultList();
		
		
		
		return listHP;
	}
	
	/*
	@Override
	@SuppressWarnings("unchecked")
	public List<HistoricoPrecoProduto> getTodosHistoricoProduto(Integer codProduto) {
		Query query = entityManager.createQuery("from HistoricoPrecoProduto hp1 where codProduto = :codProduto");
			  query.setParameter("codProduto", codProduto);
	
		return query.getResultList();
	}
	*/
	
	/*
	@Override
	@SuppressWarnings("unchecked")
	public List<HistoricoPrecoProduto> getTodosHistoricoProduto(Integer codProduto) {
		Query query1 = entityManager.createQuery("from HistoricoPrecoProduto hp1 where item = 1");
		Query query2 = entityManager.createQuery("from HistoricoPrecoProduto hp1 where item = 2");
	
		HistoricoPrecoProduto p1 = (HistoricoPrecoProduto) query1.getSingleResult();
		HistoricoPrecoProduto p2 = (HistoricoPrecoProduto) query2.getSingleResult();
		
		
		if(p1.getDhLanc().after(p2.getDhLanc())) {
			System.out.println("a data de p1 é maior!");
		}else if(p1.getDhLanc().before(p2.getDhLanc())) {
			System.out.println("a data de p2 é maior!");
		}
		
		return query1.getResultList();
	}
	*/
	
}
