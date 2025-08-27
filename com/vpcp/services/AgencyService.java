package com.vpcp.services;

import com.vpcp.services.model.DeleteAgencyResult;
import com.vpcp.services.model.GetAgenciesResult;
import com.vpcp.services.model.RegisterAgencyResult;

public interface AgencyService {
  GetAgenciesResult getAgenciesList(String paramString);
  
  RegisterAgencyResult registerAgency(String paramString1, String paramString2);
  
  DeleteAgencyResult deleteAgency(String paramString);
}


/* Location:              G:\BCY_task\2024\3.DTCB_LGSP\4.VNPT_Adapter\SDKVXP-2.0.1.jar!\com\vpcp\services\AgencyService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */