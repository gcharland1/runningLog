import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  messages: String[] = [];

  constructor() { }

  addMessage(message: string) {
    this.messages.push(message);
  }

  clearMessages() {
      this.messages = [];
  }
}
