import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
public class GroupOfClimbers extends BaseIdentify {
    @Transient
    private int count;

    @Transient
    LocalDate dateOfClimbingEnding;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Mountain mountain;

    @ManyToMany
    @JoinTable(name = "climbers_group",
        joinColumns = @JoinColumn(name = "groupOfCl_id"),
        inverseJoinColumns = @JoinColumn(name = "climber_id"))
    private List<Climber> climbers;

    @Column(nullable = false)
    private boolean isOpen;

    @Column(nullable = false)
    private LocalDate dateOfClimbing;

    @Column(nullable = false)
    private int climbingDurationInDays;

    private int groupSize;

    public GroupOfClimbers() {
    }

    public GroupOfClimbers(Mountain mountain, LocalDate dateOfClimbing,
                           int climbingDurationInDays, int groupSize) {
        setOpen();
        setMountain(mountain);
        setDateOfClimbing(dateOfClimbing);
        setClimbingDurationInDays(climbingDurationInDays);
        setGroupSize(groupSize);
        climbers = new ArrayList<>();
    }

    private void setOpen() {
        isOpen = true;
    }

    private void setClose() {
        isOpen = false;
    }

    private void setMountain(Mountain mountain) {
        if (mountain == null) throw new IllegalArgumentException("Не задана гора для восхождения.");
        this.mountain = mountain;
    }

    private void setDateOfClimbing(LocalDate dateOfClimbing) {
        if (dateOfClimbing == null)
            throw new IllegalArgumentException("Некорректная дата восхождения");
        else {
            if (dateOfClimbing.isBefore(LocalDate.now())) {
                throw new IllegalArgumentException("Некорректная дата восхождения");
            } else {
                this.dateOfClimbing = dateOfClimbing;
            }
        }
    }

    private void setGroupSize(int groupSize) {
        if (groupSize < 1) throw new IllegalArgumentException("Размер группы не может быть меньше 1.");
        else this.groupSize = groupSize;
    }

    private void setClimbingDurationInDays(int climbingDurationInDays) {
        if (climbingDurationInDays < 0) {
            throw new IllegalArgumentException("Длительность восхождения должна быть больше 0");
        } else {
            this.climbingDurationInDays = climbingDurationInDays;
        }
    }

    public void addClimber(Climber... climbers) {
        for (Climber climber : climbers) {
            if (climber != null && isOpen) {
                if (count <= groupSize) {
                    this.climbers.add(climber);
                    climber.addGroup(this);
                    count++;
                } else {
                    setClose();
                    System.out.println("Group was closed");
                }
            }
            else if (climber == null) {
                throw new IllegalArgumentException("Climber cannot be null");
            }
            else {
                throw new IllegalArgumentException("Group recruitment is closed");
            }
        }
    }

    @Override
    public String toString() {
        return "GroupOfClimbers{" +
                "dateOfClimbingEnding=" + dateOfClimbingEnding +
                ", mountain=" + mountain +
                ", climbers=" + climbers +
                ", isOpen=" + isOpen +
                ", dateOfClimbing=" + dateOfClimbing +
                ", climbingDurationInDays=" + climbingDurationInDays +
                ", groupSize=" + groupSize +
                '}';
    }
}
