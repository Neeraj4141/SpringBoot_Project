import { Component } from '@angular/core';
import { HttpserviceService } from '../httpservice.service';

@Component({
  selector: 'app-atm',
  templateUrl: './atm.component.html',
  styleUrls: ['./atm.component.css']
})

export class AtmComponent {

  endpoint = "http://localhost:8080/Atm/save"

  form: any = {
    data: {},
    inputerror: {},
    message: ''
  }
  constructor(public httpService: HttpserviceService) { }
  save() {
    let self = this;
    this.httpService.post(this.endpoint, this.form.data, (response: any) => {
      console.log('response === >', response);
      if (response.success == false && response.result.inputerror) {
        self.form.inputerror = response.result.inputerror;
      } else if (response.success == true) {
        self.form.message = response.result.message;
      }
    });
  }


}
