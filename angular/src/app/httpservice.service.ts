import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HttpserviceService {

  constructor(public httpClient: HttpClient) { }

  post(endpoint: any, formData: any, callback: any) {
    this.httpClient.post(endpoint, formData).subscribe((response: any) => {
      callback(response); // return response as a collback function parameter
    });
  }

  get(endpoint: any, callback: any) {
    this.httpClient.get(endpoint).subscribe((response: any) => {
      callback(response); // return response as a collback function parameter
    });
  }

}