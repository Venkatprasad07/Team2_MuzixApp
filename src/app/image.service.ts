import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ImageService {
  http:HttpClient;
  message:string;
  loggedIn=false;

  imagePostResponse:any;
  imageSuccessResponse:string;
 


  constructor(http:HttpClient) { 
    this.http=http;
  }

  imageUploadAction(uplaodImage: File) {
    console.log('..uploading');
    const imageFormData = new FormData();
    imageFormData.append('image',uplaodImage,uplaodImage.name);
    this.http.post('http://localhost:8085/image/upload',imageFormData,{observe:'response'})
        .subscribe(
          response=>{
            console.log('reponse:' +response);
            if(response.status===200){
              this.imagePostResponse = response;
              this.imageSuccessResponse = this.imagePostResponse.body.message;
            }
            else{
              this.imageSuccessResponse = "Image not uploaded due to error";
            }
          }
        );
  }
}
