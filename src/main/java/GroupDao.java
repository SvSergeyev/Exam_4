import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class GroupDao implements Dao<GroupOfClimbers, Integer> {
    private EntityManager manager;

    public GroupDao(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void add(GroupOfClimbers groupOfClimbers) {
        manager.persist(groupOfClimbers);
    }

    @Override
    public GroupOfClimbers getByPK(Integer id) {
        return manager.find(GroupOfClimbers.class, id);
    }

    @Override
    public void delete(GroupOfClimbers groupOfClimbers) {
        manager.remove(groupOfClimbers);
    }

    public List<GroupOfClimbers> getByMountain(String mountain) {
//        2. Получение списка Групп по названию Горы
        TypedQuery<GroupOfClimbers> query =
                manager.createQuery("SELECT g FROM GroupOfClimbers g WHERE g.mountain.name = :mountainName", GroupOfClimbers.class);
        query.setParameter("mountainName", mountain);
        return query.getResultList();
    }

    public List<GroupOfClimbers> getAllOpen(Boolean isOpen) {
//        4. Получение групп, набор в которые еще открыт.
        TypedQuery<GroupOfClimbers> query =
                manager.createQuery("SELECT g FROM GroupOfClimbers g WHERE g.isOpen = :isOpen", GroupOfClimbers.class);
        query.setParameter("isOpen", isOpen);
        return query.getResultList();
    }
}
