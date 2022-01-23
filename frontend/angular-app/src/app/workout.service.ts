import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';

import { Workout } from './workout';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class WorkoutService {

  constructor(private http: HttpClient, private messageService: MessageService) { }

  private workoutsUrl = 'http://0.0.0.0:8080/api/workout'

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error); // log to console instead
      this.log(error);
      return of(result as T);
    };
  }

  getWorkouts(): Observable<Workout[]> {
    const workouts = this.http.get<Workout[]>(this.workoutsUrl).pipe(
      tap(_ => this.log('fetched workouts')),
      catchError(this.handleError<Workout[]>('getWorkouts', []))
    );

    return workouts;
  }

  addWorkout(workout: Workout): Observable<Workout> {
    return this.http.post<Workout>(this.workoutsUrl, workout, this.httpOptions).pipe(
      tap((newWorkout: Workout) => { this.log(`Added workout with id=${newWorkout.id}`)}),
      catchError(this.handleError<Workout>('addWorkout'))
    );
  }

  deleteWorkout(workoutId: number): Observable<Workout> {
    const url = `${this.workoutsUrl}/${workoutId}`;
    return this.http.delete<Workout>(url, this.httpOptions).pipe(
      tap(_ => { this.log(`Deleted workout with id=${workoutId}`) }),
      catchError(this.handleError<Workout>('deleteWorkout'))
    );
  }

  log(message: string): void {
    const messageString = "Workout Service: " + message;
    this.messageService.addMessage(messageString)
    console.log(messageString);
  }

}
