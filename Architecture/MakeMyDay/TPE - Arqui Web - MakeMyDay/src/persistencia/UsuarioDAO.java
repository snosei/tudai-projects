package persistencia;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import entidades.Usuario;

public class UsuarioDAO extends BaseJPADAO<Usuario> {

	private static UsuarioDAO uDao;

	private UsuarioDAO() {
		super(Usuario.class);
	}

	public static UsuarioDAO getInstance() {
		if (uDao == null)
			uDao = new UsuarioDAO();
		return uDao;
	}

	public static Usuario login(Usuario usuario) {
		EntityManager em = EntityManagerFactory.createEntityManager();
		Usuario usuarioBD = new Usuario();
		try {
			String jpql = "SELECT u FROM Usuario u WHERE u.nombre = ?1 AND u.password = ?2 ";
			Query query = em.createQuery(jpql, Usuario.class);
			query.setParameter(1, usuario.getNombre());
			query.setParameter(2, usuario.getPassword());
			usuarioBD = (Usuario) query.getSingleResult();
			usuarioBD.setEsValido(true);
		} catch (NoResultException e) {
			usuario.setEsValido(false);
		} catch (Exception e) {
			usuario.setEsValido(false);
		} finally {
			em.close();
		}
		return usuarioBD;
	}

	public void update(Usuario u, int id) {
		EntityManager entityManager = EntityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Usuario uOriginal = entityManager.find(Usuario.class, id);
		uOriginal.setNombre(u.getNombre());
		uOriginal.setPassword(u.getPassword());
		uOriginal.setApellido(u.getApellido());
		uOriginal.setDNI(u.getDNI());
		uOriginal.setFechaNacimiento(u.getFechaNacimiento());
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public Usuario findUsuarioByName(String userName) {
		EntityManager em = EntityManagerFactory.createEntityManager();
		Usuario userDB = null;
		try {
			String jpql = "SELECT u FROM Usuario u WHERE u.nombre = ?1";
			Query query = em.createQuery(jpql, Usuario.class);
			query.setParameter(1, userName);
			userDB = (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("no result");
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return userDB;
	}
}
