import {Component} from '@angular/core';
import {Feature} from "../object/feature";
import {FeatureService} from "../service/feature.service";

@Component({
  selector: 'app-feature',
  templateUrl: './feature.component.html',
  styleUrls: ['./feature.component.css']
})
export class FeatureComponent {

  enabledFeatures = new Array<Feature>();
  disabledFeatures = new Array<Feature>();

  constructor(private featureService: FeatureService) {
  }

  ngOnInit(): void {
    this.featureService.getAllFeatures(undefined).subscribe(features => {
      features.forEach(feature => {
        if (feature.enabled) {
          this.enabledFeatures.push(feature);
        } else {
          this.disabledFeatures.push(feature);
        }
      })
    });
  }
}
