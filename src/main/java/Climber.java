import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/*
1. Получение всех участников в возрасте [from; to)
*/
@Entity
@Table(name = "climbers")
@NamedQueries({
        @NamedQuery(name = "Climber.getByAge", query = "SELECT c FROM Climber c WHERE c.age >= :from AND c.age < :to")
})
public class Climber extends BaseIdentify {
    /*
    ####Альпинист:
 - имя (не менее 3 символов);
 - адрес проживания (не менее 5 символов);
 - возраст (не менее 18 лет);
 - коллекция групп (при необходимости);
     */

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private int age;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "climbers")
    private List<Group> groups;

    public Climber() {
    }

    public Climber(String fullName, String address, int age) {
        setFullName(fullName);
        setAddress(address);
        setAge(age);
        this.groups = new ArrayList<>();
    }

    public void addGroup(Group group) {
        this.groups.add(group);
    }

    //    public void setGroup(Group group) {
//        Objects.requireNonNull(group, "group cannot be null");
//        this.group = group;
//    }

    private void setFullName(String fullName) {
        if (fullName != null && fullName.trim().length() >= 3) this.fullName = fullName;
        else throw new IllegalArgumentException("Имя не может быть короче трех символов.");
    }

    private void setAddress(String address) {
        if (address != null && address.trim().length() >= 5) this.address = address;
        else throw new IllegalArgumentException("Адрес не может быть короче пяти символов.");
    }

    private void setAge(int age) {
        if (age >= 18) this.age = age;
        else throw new IllegalArgumentException("Возраст не может быть меньше 18 лет");
    }
}
