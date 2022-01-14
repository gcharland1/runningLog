package GabrielCharland.runningLog.Workout;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(path="strava")
public class WorkoutController {

    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping
    public String getWorkouts(Model model) {
        List<Workout> workouts = workoutService.getWorkouts();
        model.addAttribute("allWorkouts", workouts);
        return "viewWorkouts";
    }

    /*
    @GetMapping
    public List<Workout> getWorkouts() {
        return workoutService.getWorkouts();
    }

    @PostMapping
    public void addNewWorkout(@RequestBody Workout workout) {
        workoutService.addNewWorkout(workout);
    }

    @DeleteMapping(path="{workoutId}")
    public void deleteWorkout(@PathVariable("workoutId") long workoutId) {
        workoutService.deleteWorkout(workoutId);
    }

    @PutMapping(path="{workoutId}")
    public void updateWorkout(@PathVariable("workoutId") long workoutId,
                              @RequestParam(required = false) LocalDate date,
                              @RequestParam(required = false) double distance,
                              @RequestParam(required = false) Duration time) {
        workoutService.updateWorkout(workoutId, date, distance, time);

    }
    */
}
