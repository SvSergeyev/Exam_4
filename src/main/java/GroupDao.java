import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class GroupDao implements Dao<Group, Integer> {
    private EntityManager manager;

    public GroupDao(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void add(Group group) {
        manager.persist(group);
    }

    @Override
    public Group getByPK(Integer id) {
        return manager.find(Group.class, id);
    }

    @Override
    public void delete(Group group) {
        manager.remove(group);
    }

    public List<Group> getByMountain(Mountain mountain) {
//        2. Получение списка Групп по названию Горы
        TypedQuery<Group> query =
                manager.createNamedQuery("Group.getByMountain", Group.class);
        query.setParameter("mountain", mountain);
        return query.getResultList();
    }

    public List<Group> getAllOpen(Boolean condition) {
//        4. Получение групп, набор в которые еще открыт.
        TypedQuery<Group> query =
                manager.createNamedQuery("Group.getAllOpen", Group.class);
        query.setParameter("isOpen", condition);
        return query.getResultList();
    }
}
