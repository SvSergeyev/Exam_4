import java.sql.SQLException;
import java.util.List;

public interface Dao<T, PK> {

    void add(T t);
    T getByPK(PK id);
    void delete(T t);
}
