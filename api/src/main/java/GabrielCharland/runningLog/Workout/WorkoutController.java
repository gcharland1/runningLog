package GabrielCharland.runningLog.Workout;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path="api/workout")
public class WorkoutController {

    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @CrossOrigin(origins = "http://0.0.0.0:4200/")
    @GetMapping
    public List<Workout> getWorkouts() {
        return workoutService.getWorkouts();
    }

    @CrossOrigin(origins = "http://0.0.0.0:4200/")
    @PostMapping
    public Workout addNewWorkout(@RequestBody Workout workout) {
        workoutService.addNewWorkout(workout);
        List<Workout> workouts = this.workoutService.getWorkouts();
        return workouts.get(workouts.size() - 1);
    }

    @CrossOrigin(origins = "http://0.0.0.0:4200/")
    @DeleteMapping(path="{workoutId}")
    public void deleteWorkout(@PathVariable("workoutId") long workoutId) {
        workoutService.deleteWorkout(workoutId);
    }

    @CrossOrigin(origins = "http://0.0.0.0:4200/")
    @PutMapping(path="{workoutId}")
    public void updateWorkout(@PathVariable("workoutId") long workoutId,
                              @RequestParam(required = false) LocalDate date,
                              @RequestParam(required = false) double distance,
                              @RequestParam(required = false) Duration time) {
        workoutService.updateWorkout(workoutId, date, distance, time);

    }

}
