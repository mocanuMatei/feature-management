export class Feature{
  id: string | undefined;

  technicalName: string;
  displayName: string;
  description: string;
  enabled: boolean;
  created: Date | undefined;
  expiration: Date;

  customerIds: string[];


  constructor(technicalName: string, displayName: string, description: string, enabled: boolean, expiration: Date, customerIds: string[]) {
    this.technicalName = technicalName;
    this.displayName = displayName;
    this.description = description;
    this.enabled = enabled;
    this.expiration = expiration;
    this.customerIds = customerIds;
  }
}
