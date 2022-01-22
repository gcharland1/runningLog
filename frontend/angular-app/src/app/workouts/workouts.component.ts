import { Component, OnInit } from '@angular/core';

import { Workout } from '../workout';
import { WorkoutService } from '../workout.service';

@Component({
  selector: 'app-workouts',
  templateUrl: './workouts.component.html',
  styleUrls: ['./workouts.component.css']
})
export class WorkoutsComponent implements OnInit {
  workouts: Workout[] = [];

  constructor(private workoutService: WorkoutService) { }

  ngOnInit(): void {
    this.getWorkouts();
  }

  getWorkouts() {
    this.workoutService.getWorkouts().subscribe(
      workouts => { this.workouts = workouts }
    );
  }

  addWorkout(date: string, distance: string, time: string) {

    let formattedTime = this.formatTime(time);
    const newWorkout: Workout = { date: date, distance: parseFloat(distance), time: formattedTime };
    this.workoutService.addWorkout(newWorkout).subscribe(
      (workout) => this.workouts.push(workout)
    );
  }

  formatTime(time: string): string {
    let formattedTime = "";

    let timeUnit = ['S', 'M', 'H'];
    let n = 0;

    for (let t of time.split(':').reverse()) {
      formattedTime = parseFloat(t) + timeUnit[n] + formattedTime;
      n += 1;
    }
    return "PT" + formattedTime;
  }

  validateInput(date: string, distance: string, time:string): boolean {
    return true;
  }

  /*
   * Add method to add new workout
   *    - New method in workoutService,
   *    - New input form in html
   *    - Read Date, Time and distance individually and pass them to service
   *    - subscribe to service to push new Workout in this.workouts
   */

}
