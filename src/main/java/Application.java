import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

public class Application {
    public static void main(String[] args) {

        Mountain mountain1 = new Mountain("Elbrus", "Russia", 5642);
        Mountain mountain2 = new Mountain("MontBlanc", "France", 4809);
        Mountain mountain3 = new Mountain("Kilimanjaro", "Tanzania", 5881);

        Climber climber1 = new Climber("Ivan Ivanov", "1st street", 19);
        Climber climber2 = new Climber("Pyotr Petrov", "2nd street", 39);
        Climber climber3 = new Climber("Vasily Vasiliev", "3rd street", 27);
        Climber climber4 = new Climber("Nikolaj Nikolaev", "4th street", 51);
        Climber climber5 = new Climber("Dmitrij Dmitriev", "5th street", 34);
        Climber climber6 = new Climber("Anatolij Anatoliev", "6th street", 28);
        Climber climber7 = new Climber("Evgenij Evgeniev", "7th street", 41);

        GroupOfClimbers group1 = new GroupOfClimbers(mountain1, LocalDate.of(2021, 5, 15), 19, 3);
        GroupOfClimbers group2 = new GroupOfClimbers(mountain2, LocalDate.of(2021, 3, 19), 11, 3);
        GroupOfClimbers group3 = new GroupOfClimbers(mountain3, LocalDate.of(2021, 8, 22), 12, 3);

        group1.addClimber(climber1, climber2, climber3);
        group2.addClimber(climber4, climber5, climber6);
        group3.addClimber(climber7, climber4, climber2);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("entityManager");
        EntityManager manager = factory.createEntityManager();

        MountainDao mountainDao = new MountainDao(manager);
        ClimberDao climberDao = new ClimberDao(manager);
        GroupDao groupDao = new GroupDao(manager);

        manager.getTransaction().begin();
        mountainDao.add(mountain1);
        mountainDao.add(mountain2);
        mountainDao.add(mountain3);
        climberDao.add(climber1);
        climberDao.add(climber2);
        climberDao.add(climber3);
        climberDao.add(climber4);
        climberDao.add(climber5);
        climberDao.add(climber6);
        climberDao.add(climber7);
        groupDao.add(group1);
        groupDao.add(group2);
        groupDao.add(group3);
        manager.getTransaction().commit();

        List<Climber> climbersWithAgeBetween = climberDao.getByAge(18, 35);
        System.out.println("Climbers with ages between 18 and 35:");
        climbersWithAgeBetween.forEach(System.out::println);

        List<GroupOfClimbers> groupsByMountain = groupDao.getByMountain("Elbrus");
        System.out.println("Groups going to Elbrus:");
        groupsByMountain.forEach(System.out::println);

        List<Mountain> mountainByCountry = mountainDao.getByCountry("Russia");
        System.out.println("Mountains in Russia:");
        mountainByCountry.forEach(System.out::println);

        List<GroupOfClimbers> openGroupOfClimbers = groupDao.getAllOpen(true);
        System.out.println("Open groups:");
        openGroupOfClimbers.forEach(System.out::println);
    }
}
