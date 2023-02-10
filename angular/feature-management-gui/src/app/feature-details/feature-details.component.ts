import {Component} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {FeatureService} from "../service/feature.service";
import {Feature} from "../object/feature";
import {MatSlideToggleChange} from "@angular/material/slide-toggle";

// noinspection DuplicatedCode
@Component({
  selector: 'app-feature-details',
  templateUrl: './feature-details.component.html',
  styleUrls: ['./feature-details.component.css']
})
export class FeatureDetailsComponent {

  feature!: Feature
  featureEditedSuccessfully = false;

  constructor(private route: ActivatedRoute,
              private featureService: FeatureService){}

  ngOnInit(): void {
    const featureIdFromRoute = String(this.route.snapshot.paramMap.get('featureId'));
    this.featureService.getFeatureById(featureIdFromRoute)
      .subscribe(feature => this.feature = feature);
  }

  onChangeToggle($event: MatSlideToggleChange): void{
    this.feature.enabled = $event.checked;
  }

  editFeature(): void{
    this.feature.displayName = (document.getElementById('displayName') as HTMLInputElement).value;
    this.feature.technicalName = (document.getElementById('technicalName') as HTMLInputElement).value;
    this.feature.description = (document.getElementById('description') as HTMLInputElement).value;
    this.feature.expiration = new Date((document.getElementById('expiration') as HTMLInputElement).value);
    this.feature.customerIds = (document.getElementById('customerIds') as HTMLInputElement).value.split(",");

    this.featureService.updateFeature(this.feature)
      .subscribe(updatedFeature => {
        this.feature = updatedFeature;
        this.featureEditedSuccessfully = true;
        setTimeout(() => this.featureEditedSuccessfully = false, 10000);
      })
  }

}
