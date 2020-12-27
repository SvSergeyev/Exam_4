import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ClimberDao implements Dao<Climber, Integer> {
    private EntityManager manager;

    public ClimberDao(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void add(Climber climber) {
        manager.persist(climber);
    }

    @Override
    public Climber getByPK(Integer id) {
        return manager.find(Climber.class, id);
    }

    @Override
    public void delete(Climber climber) {
        manager.remove(climber);
    }

    public List<Climber> getByAge(int from, int to) {
//        1. Получение всех участников в возрасте [from; to)
        TypedQuery<Climber> query =
                manager.createNamedQuery("Climber.getByAge", Climber.class);
        query.setParameter("from", from);
        query.setParameter("to", to);
        return query.getResultList();
    }
}
