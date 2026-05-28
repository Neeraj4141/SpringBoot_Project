import { Component, OnInit } from '@angular/core';

import { ActivatedRoute } from '@angular/router';
import { HttpserviceService } from '../httpservice.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent {

  form: any = {
    data: {},
    inputerror: {},
    message: '',
  }

  fileToUpload: any = null;

  constructor(private httpService: HttpserviceService, private activatedRoute: ActivatedRoute) {
    this.activatedRoute.params.subscribe((params: any) => {
      this.form.data.id = params["id"];
      console.log('id===>', this.form.data.id)
      if (this.form.data.id) {
        this.display();
      }
    })
  }

  display() {
    var self = this
    this.httpService.get('http://localhost:8080/user/get/' + this.form.data.id, function (res: any) {
      console.log(res)
      self.form.data = res.result.data;
      self.form.data.dob = res.result.data.dob.substring(0, 10);
      if (res.result.data.imageId) {
        self.form.data.imageId = res.result.data.imageId;
      }
    });

  }

  save() {
    var self = this
    this.httpService.post('http://localhost:8080/user/save', this.form.data, function (response: any) {
      console.log('res => ', response);

      if (response.success == false && response.result.inputerror) {
        self.form.inputerror = response.result.inputerror;
      }

      if (response.success == true && response.result.message) {
        self.form.message = response.result.message;
      }

      if (response.success == true && response.result.data.id) {
        self.form.data.id = response.result.data.id;
      }

      if (self.fileToUpload != null) {
        self.uploadFile();
      }

    });
  }

  onFileSelect(event: any) {
    this.fileToUpload = event.target.files.item(0);
    console.log('file===>', this.fileToUpload);
  }

  uploadFile() {

    let self = this;

    const formData = new FormData();

    formData.append('file', this.fileToUpload);

    return this.httpService.post("http://localhost:8080/user/profilePic/" + this.form.data.id, formData, function (res: any) {

      console.log("imageId = " + res.result.imageId);
      self.form.data.imageId = res.result.imageId;
      self.fileToUpload = null;

    });
  }

} 1