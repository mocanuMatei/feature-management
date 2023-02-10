import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {FeatureComponent} from "./feature/feature.component";
import {FeatureDetailsComponent} from "./feature-details/feature-details.component";
import {AddFeatureComponent} from "./add-feature/add-feature.component";

const routes: Routes = [
  {path: 'features', component: FeatureComponent},
  {path: 'features/:featureId', component: FeatureDetailsComponent},
  {path: 'addFeature', component: AddFeatureComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
