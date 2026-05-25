import { Component } from '@angular/core';
import { HttpserviceService } from '../httpservice.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html'
})
export class SignupComponent {

  endpoint: any = "http://localhost:8080/Auth/signup"

  form: any = {
    data: {},
    inputerror: {},
    message: ''
  }

  constructor(public httpService: HttpserviceService) { }

  signUp() {
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