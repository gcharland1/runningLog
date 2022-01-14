package GabrielCharland.runningLog.Home;

import GabrielCharland.runningLog.Workout.Workout;
import GabrielCharland.runningLog.Workout.WorkoutService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path="/")
public class HomeController {

    private final WorkoutService workoutService;

    public HomeController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping
    public String getWorkouts(Model model) {
        List<Workout> workouts = workoutService.getWorkouts();
        model.addAttribute("title", "My recorded workouts");
        model.addAttribute("allWorkouts", workouts);
        return "home";
    }
}
