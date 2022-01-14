package GabrielCharland.runningLog.Workout;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;

@Entity
@Table
public class Workout {
    @Id
    @SequenceGenerator(
            name = "workout_sequence",
            sequenceName = "workout_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "workout_sequence"
    )
    private long id;
    LocalDate date;
    double distance; // km
    Duration time;
    @Transient
    Duration pace;

    // Constructors
    public Workout() {
    }

    public Workout(LocalDate date, double distance, Duration time) {
        this.date = date;
        this.distance = distance;
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getDistance() {
        return distance;
    }

    public Duration getTime() {
        return time;
    }

    public String getFormattedTime() {
        long timeSeconds = time.toSeconds();
        if (timeSeconds / 3600 >= 1) {
            return String.format("%dh %dm", timeSeconds / 3600, (timeSeconds % 3600) / 60);
        } else {
            return String.format("%dm %dsec", (timeSeconds % 3600) / 60, (timeSeconds % 60));
        }
    }

    public Duration getPace() {
        return this.time.dividedBy(((long) (100 * this.distance))).multipliedBy(100);
    }

    public String getFormattedPace() {
        long paceSeconds = getPace().toSeconds();
        if (paceSeconds / 3600 >= 1) {
            return String.format("%dh %dm", paceSeconds / 3600, (paceSeconds % 3600) / 60);
        } else {
            return String.format("%dm %dsec", (paceSeconds % 3600) / 60, (paceSeconds % 60));
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setTime(Duration time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Workout{" +
                "id=" + id +
                ", date=" + date +
                ", distance=" + distance +
                ", time='" + time + "'" +
                ", pace='" + pace + "'" +
                "}";
    }
}
