import {Component} from '@angular/core';
import {Feature} from "../object/feature";
import {FeatureService} from "../service/feature.service";
import {MatSlideToggleChange} from "@angular/material/slide-toggle";
import {ActivatedRoute} from "@angular/router";

// noinspection DuplicatedCode
@Component({
  selector: 'app-add-feature',
  templateUrl: './add-feature.component.html',
  styleUrls: ['./add-feature.component.css']
})
export class AddFeatureComponent {

  feature = new Feature('', '', '', false, new Date(), []);
  featureAddedSuccessfully = false;

  constructor(private featureService: FeatureService) {
  }

  onChangeToggleEnabled($event: MatSlideToggleChange): void {
    this.feature.enabled = $event.checked;
  }

  addFeature(): void {
    this.feature.displayName = (document.getElementById('displayName') as HTMLInputElement).value;
    this.feature.technicalName = (document.getElementById('technicalName') as HTMLInputElement).value;
    this.feature.description = (document.getElementById('description') as HTMLInputElement).value;
    this.feature.expiration = new Date((document.getElementById('expiration') as HTMLInputElement).value);
    this.feature.customerIds = (document.getElementById('customerIds') as HTMLInputElement).value.split(",");

    if (this.feature.technicalName.length > 0) {
      this.featureService.addFeature(this.feature)
        .subscribe(() => {
          this.featureAddedSuccessfully = true;
          setTimeout(() => {
            this.featureAddedSuccessfully = false;
          }, 10000);
        })

    }

  }
}
