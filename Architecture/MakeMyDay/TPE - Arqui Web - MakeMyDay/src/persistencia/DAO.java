package persistencia;

import java.util.List;

public interface DAO<E> {
	E persist(E entity);
    E findById(int id);
    List<E> findAll();
}
