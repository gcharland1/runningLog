package GabrielCharland.runningLog.Workout;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository // Dit à Spring que c'est un Repo
public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    @Query("SELECT MAX(id) FROM Workout")
    Optional<Workout> findLastAddedWorkout();
}

