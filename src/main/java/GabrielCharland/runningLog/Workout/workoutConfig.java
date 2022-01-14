package GabrielCharland.runningLog.Workout;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class workoutConfig {

    @Bean
    CommandLineRunner commandLineRunner(WorkoutRepository repository) {
        return args -> {
            Workout quartier = new Workout(
                    LocalDate.of(2022, Month.JANUARY, 12),
                    5.18,
                    Duration.parse("PT25M41S")
            );

            Workout beckett = new Workout(
                    LocalDate.of(2021, Month.DECEMBER, 04),
                    13.7,
                    Duration.parse("PT1H41M07S")
            );

            repository.saveAll(List.of(quartier, beckett));
        };
    }
}
