package ch.datatrans.bbwteachtalk.controller;

/**
 * @author dominik.mengelt@gmail.com
 */
public class WebhookResponse {

    private String refno;
    private String status;
    private String paymentMethod;

    public String getRefno() {
        return refno;
    }

    public void setRefno(String refno) {
        this.refno = refno;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}




/*

Sample payload of a webhook call:

{
  "type" : "payment",
  "status" : "settled",
  "currency" : "CHF",
  "refno" : "gkllDbCGnqQIzV78RDij",
  "paymentMethod" : "VIS",
  "detail" : {
    "authorize" : {
      "amount" : 450,
      "date" : "2019-01-25T14:52:15.513+00:00",
      "acquirerAuthorizationCode" : "155230"
    },
    "settle" : {
      "amount" : 450,
      "date" : "2019-01-25T14:52:30.000+00:00"
    }
  }
}

 */