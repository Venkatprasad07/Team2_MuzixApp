import { Component, OnInit } from '@angular/core';
import { ImageService } from '../image.service';


@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent implements OnInit {
  uplaodImage : File;
  dbImage:any;
  postResponse:any;
  successResponse:string;
  image:any;
  selected:boolean=false;

  constructor(public service:ImageService) { }

  ngOnInit(): void {
  }
  public onImageUpload(event:any){
    this.uplaodImage = event.target.files[0];
    this.selected = true;
  }
  public imageUploadAction(){
    console.log(this.uplaodImage.name );
    this.service.imageUploadAction(this.uplaodImage);
  }
 5.2
 

}
