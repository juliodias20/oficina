<<<<<<< Updated upstream:src/br/com/maverick/perfil/repository/model/UsuarioDaoImplem.java
package br.com.maverick.Dao.model;
=======
package br.com.maverick.usuario.repository;
>>>>>>> Stashed changes:src/br/com/maverick/usuario/repository/UsuarioDao.java

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

<<<<<<< Updated upstream:src/br/com/maverick/perfil/repository/model/UsuarioDaoImplem.java
import br.com.maverick.model.model.UsuarioModel;
=======
import br.com.maverick.usuario.model.UsuarioModel;
>>>>>>> Stashed changes:src/br/com/maverick/usuario/repository/UsuarioDao.java

public class UsuarioDao {

	@PersistenceContext(unitName="OficinaPersistenceUnit")
	private EntityManager entityManager;
	
	public UsuarioModel salvarUsuario(UsuarioModel usuarioModel){
		usuarioModel.setSenha(generetedMD5(usuarioModel.getSenha()));
		entityManager.persist(usuarioModel);
		return usuarioModel;
	}

	public void alterar(UsuarioModel usuarioModel) {
		usuarioModel.setSenha(generetedMD5(usuarioModel.getSenha()));
		UsuarioModel usuarioModelMerge = entityManager.merge(usuarioModel);
		entityManager.persist(usuarioModelMerge);	
	}

	public void excluir(UsuarioModel usuarioModel) {
		UsuarioModel usuarioModelMerge = entityManager.merge(usuarioModel);
		entityManager.remove(usuarioModelMerge);
	}

	//getTodos
	@SuppressWarnings("unchecked")
	public List<UsuarioModel> getUsuarios() {
		Query query = entityManager.createQuery("from UsuarioModel");
		return query.getResultList();
	}
	
	//getById
	@SuppressWarnings("unchecked")
	public List<UsuarioModel> getUsuarios(Integer codUsuario) {
		Query query = entityManager.createQuery("from UsuarioModel u where u.codUsuario = :id");
			  query.setParameter("id", codUsuario);
		return query.getResultList();
	}

	public UsuarioModel getUsuarioByLoginSenha(String login, String senha) {
		
		Query query = entityManager.createQuery("from UsuarioModel u where u.login = :login and u.senha = :senha");
			  query.setParameter("login", login);
			  query.setParameter("senha", generetedMD5(senha));
		return (UsuarioModel)query.getSingleResult();
	}

	private String generetedMD5(String valor) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");

			md.update(valor.getBytes());
			byte[] digest = md.digest();
			StringBuffer sb = new StringBuffer();

			for (byte b : digest)
				sb.append(String.format("%02x", b & 0xff));

			return sb.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
