import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Feature} from "../object/feature";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class FeatureService {

  FEATURE_PATH = '/v1/features';

  constructor(private http: HttpClient) {
  }

  getAllFeatures(enabled?: boolean): Observable<Feature[]> {
    let params = new HttpParams();

    if (typeof enabled === 'boolean') {
      params = params.append('enabled', enabled);
    }

    return this.http.get<Feature[]>(environment.backendURL + this.FEATURE_PATH, {params});
  }

  getFeatureById(id: string): Observable<Feature> {
    return this.http.get<Feature>(environment.backendURL + this.FEATURE_PATH + '/' + id);
  }

  addFeature(feature: Feature): Observable<Feature> {
    return this.http.post<Feature>(environment.backendURL + this.FEATURE_PATH, feature);
  }

  updateFeature(feature: Feature): Observable<Feature> {
    return this.http.put<Feature>(environment.backendURL + this.FEATURE_PATH, feature);
  }
}
