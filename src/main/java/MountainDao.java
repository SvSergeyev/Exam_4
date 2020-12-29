import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class MountainDao implements Dao<Mountain, Integer> {
    private EntityManager manager;

    public MountainDao(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void add(Mountain mountain) {
        manager.persist(mountain);
    }

    @Override
    public Mountain getByPK(Integer id) {
        return manager.find(Mountain.class, id);
    }

    @Override
    public void delete(Mountain mountain) {
        manager.remove(mountain);
    }

    public List<Mountain> getByCountry(String country) {
//        3. Получение Гор по названию страны
        TypedQuery<Mountain> query =
                manager.createQuery("SELECT m FROM Mountain m WHERE m.country = :country", Mountain.class);
        query.setParameter("country", country.toLowerCase());
        return query.getResultList();
    }

}
