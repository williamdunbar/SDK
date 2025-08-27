package com.vpcp.services;

import com.vpcp.services.model.CountReceiveEdoc;
import com.vpcp.services.model.GetEdocResult;
import com.vpcp.services.model.GetReceivedEdocResult;
import com.vpcp.services.model.GetSendEdocResult;
import com.vpcp.services.model.SendEdocResult;

public interface KnobstickService {
  GetReceivedEdocResult getReceivedEdocList(String paramString);
  
  GetSendEdocResult getSentEdocList(String paramString);
  
  SendEdocResult sendEdoc(String paramString1, String paramString2);
  
  GetEdocResult getEdoc(String paramString);
  
  GetEdocResult downloadEdoc(String paramString);
  
  CountReceiveEdoc countReceiveEdoc(String paramString);
  
  CountReceiveEdoc countReceiveStatus(String paramString);
}


/* Location:              G:\BCY_task\2024\3.DTCB_LGSP\4.VNPT_Adapter\SDKVXP-2.0.1.jar!\com\vpcp\services\KnobstickService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */