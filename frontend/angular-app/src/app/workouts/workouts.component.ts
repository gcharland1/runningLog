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
      workouts => { this.workouts = this.formatWorkouts(workouts) }
    );
  }

  addWorkout(date: string, distance: string, time: string) {

    let formattedTime = this.formatTimeToIso(time);

    const newWorkout: Workout = { date: date, distance: parseFloat(distance), time: formattedTime };
    this.workoutService.addWorkout(newWorkout).subscribe(
      (workout) => this.workouts.push(this.formatWorkout(workout))
    );
  }

  deleteWorkout(workout: Workout): void {
    if (workout.id != null) {
      this.workoutService.deleteWorkout(workout.id).subscribe();
      this.workouts =  this.workouts.filter(w => w != workout);
    }
  }

  formatWorkout(workout: Workout): Workout {
    if ( workout.time.startsWith('PT') ) {
      console.log(`String ${workout.time} starts with PT`);
      workout.time = this.formatTimeToHuman(workout.time);
    }

    if (workout.pace != null) {
      workout.pace = this.formatTimeToHuman(workout.pace);
    }
    return workout;
  }

  formatWorkouts(workouts: Workout[]): Workout[] {
    for (let w of workouts) {
      w = this.formatWorkout(w);
    }
    return workouts;
  }

  formatTimeToHuman(time: string): string {
    time = time.replace('PT', '');
    time = time.replace('H', ':');
    time = time.replace('M', ':');
    time = time.replace('S', '');

    var timeArr = time.split(':');
    for (let i=0; i<timeArr.length; i++) {
      timeArr[i] = timeArr[i].split('.')[0];
      if (i > 0){
        if (timeArr[i].length < 2) {
          timeArr[i] = '0' + timeArr[i];
        }
      }
    }
    time = timeArr.join(':');

    return time;
  }

  formatTimeToIso(time: string): string {
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
