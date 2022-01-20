package GabrielCharland.runningLog.Workout;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository // Dit à Spring que c'est un Repo
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
}

