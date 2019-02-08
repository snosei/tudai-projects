package persistencia;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class EntityManagerFactory implements ServletContextListener {
	private static javax.persistence.EntityManagerFactory emf;

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		emf = Persistence.createEntityManagerFactory("TPE-MakeMyDay-JPA");
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		emf.close();
	}

	public static EntityManager createEntityManager() {
		if (emf == null) {
			throw new IllegalStateException("Context is not initialized yet.");
		}
		return emf.createEntityManager();
	}
}