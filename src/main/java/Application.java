import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {

        ArrayList<Mountain> mountains = new ArrayList<>();
        mountains.add(new Mountain("Elbrus", "Russia", 5642));
        mountains.add(new Mountain("MontBlanc", "France", 4809));
        mountains.add(new Mountain("Kilimanjaro", "Tanzania", 5881));

        ArrayList<Climber> climbers = new ArrayList<>();
        climbers.add(new Climber("Ivan Ivanov", "1st street", 19));
        climbers.add(new Climber("Pyotr Petrov", "2nd street", 39));
        climbers.add(new Climber("Vasily Vasiliev", "3rd street", 27));
        climbers.add(new Climber("Nikolaj Nikolaev", "4th street", 51));
        climbers.add(new Climber("Dmitrij Dmitriev", "5th street", 34));
        climbers.add(new Climber("Anatolij Anatoliev", "6th street", 28));
        climbers.add(new Climber("Evgenij Evgeniev", "7th street", 41));

        ArrayList<Group> groups = new ArrayList<>();
        groups.add(new Group(mountains.get(0), LocalDate.of(2021, 5, 15), 19, 3));
        groups.add(new Group(mountains.get(1), LocalDate.of(2021, 3, 19), 11, 3));
        groups.add(new Group(mountains.get(2), LocalDate.of(2021, 8, 22), 12, 3));

        groups.get(0).addClimber(climbers.get(0), climbers.get(1), climbers.get(2));
        groups.get(1).addClimber(climbers.get(3), climbers.get(4), climbers.get(5));
        groups.get(2).addClimber(climbers.get(6), climbers.get(0), climbers.get(4));

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("entityManager");
        EntityManager manager = factory.createEntityManager();

        MountainDao mountainDao = new MountainDao(manager);
        manager.getTransaction().begin();
        for (Mountain mountain : mountains) {
            mountainDao.add(mountain);
        }
        manager.getTransaction().commit();

        ClimberDao climberDao = new ClimberDao(manager);
        manager.getTransaction().begin();
        for (Climber climber : climbers) {
            climberDao.add(climber);
        }
        manager.getTransaction().commit();

        GroupDao groupDao = new GroupDao(manager);
        manager.getTransaction().begin();
        for (Group group : groups) {
            groupDao.add(group);
        }
        manager.getTransaction().commit();

        manager.getTransaction().begin();
        List<Climber> climbersWithAgeBetween = climberDao.getByAge(18, 35);
        manager.getTransaction().commit();
        System.out.println("Climbers with ages between 18 and 35:");
        climbersWithAgeBetween.forEach(System.out::println);

        manager.getTransaction().begin();
        List<Group> groupsByMountain = groupDao.getByMountain(mountains.get(2));
        manager.getTransaction().commit();
        System.out.println("Groups going to Kilimanjaro:");
        groupsByMountain.forEach(System.out::println);

        manager.getTransaction().begin();
        List<Mountain> mountainByCountry = mountainDao.getByCountry("Russia");
        manager.getTransaction().commit();
        System.out.println("Mountains in Russia:");
        mountainByCountry.forEach(System.out::println);

        manager.getTransaction().begin();
        List<Group> openGroups = groupDao.getAllOpen(true);
        manager.getTransaction().commit();
        System.out.println("Open groups:");
        openGroups.forEach(System.out::println);



    }
}
