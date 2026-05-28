import { Component } from '@angular/core';
import { HttpserviceService } from '../httpservice.service';

@Component({
  selector: 'app-cricketteam',
  templateUrl: './cricketteam.component.html'
})
export class CricketteamComponent {

  endpoint: any = "http://localhost:8080/cricket/save"

  form: any = {
    data: {},
    inputerror: {},
    message: ''
  }
  constructor(public httpservice: HttpserviceService) { }
  save() {
    let self = this;
    this.httpservice.post(this.endpoint, this.form.data, (response: any) => {
      console.log('response === >', response);
      if (response.success == false && response.result.inputerror) {
        self.form.inputerror = response.result.inputerror;
      } else if (response.success == true) {
        self.form.message = response.result.message;
      }
    });
  }
}
