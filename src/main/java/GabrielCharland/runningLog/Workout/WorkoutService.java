package GabrielCharland.runningLog.Workout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;
import java.util.Objects;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    @Autowired
    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public List<Workout> getWorkouts() {
        return this.workoutRepository.findAll();
    }

    public void addNewWorkout(Workout workout) {
        workoutRepository.save(workout);
    }

    public void deleteWorkout(long workoutId) {
        if (!workoutRepository.existsById(workoutId)) {
            throw new IllegalStateException("Workout with id " + workoutId + " doen't exist");
        }
        workoutRepository.deleteById(workoutId);
    }

    @Transactional
    public void updateWorkout(long workoutId, LocalDate date, double distance, Duration time) {
        Workout workout = workoutRepository.findById(workoutId).orElseThrow(
                () -> new IllegalStateException("Workout with id " + workoutId + " doen't exist")
        );

        if (date != null && !(Objects.equals(date, workout.date))) {
            workout.date = date;
        }

        if ((distance > 0) && !(Objects.equals(distance, workout.distance))) {
            workout.distance = distance;
        }

        if (time != null && !(Objects.equals(time, workout.time))) {
            workout.time = time;
        }
    }

}
