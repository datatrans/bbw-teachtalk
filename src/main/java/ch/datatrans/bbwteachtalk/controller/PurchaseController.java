package ch.datatrans.bbwteachtalk.controller;

import ch.datatrans.bbwteachtalk.service.PurchaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * @author dominik.mengelt@gmail.com
 */
@Controller
@RequestMapping("/payment")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping()
    public ModelAndView pay(@Valid Basket basket) {
        String paymentId = purchaseService.initializePurchase(basket);
        return new ModelAndView("redirect:https://pay.sandbox.datatrans.com/v1/start/" + paymentId);
    }

    @GetMapping("/success")
    public String success(@RequestParam("datatransTrxId") String transactionId, Model model) {
        model.addAttribute("transactionId", transactionId);

        // to be on the safe side, lets check here if the user really has paid.
        if (purchaseService.hasPaid(transactionId)) {
            return "success";
        }

        return "error";
    }

    @GetMapping("/cancel")
    public String cancel(@RequestParam("datatransTrxId") String transactionId, Model model) {
        model.addAttribute("transactionId", transactionId);
        return "cancel";
    }

    @GetMapping("/error")
    public String error(@RequestParam("datatransTrxId") String transactionId, Model model) {
        model.addAttribute("transactionId", transactionId);
        return "error";
    }

    @PostMapping("/listener")
    @ResponseBody
    public void paymentSuccessfulListener(@RequestBody WebhookResponse webhookResponse) {

        // only checking if status=settled here.
        // more detailed status information would be available in the 'detail' object
        // of course in real world scenarios much more is needed to do a "proper" status validation
        if("settled".equals(webhookResponse.getStatus())) {
            purchaseService.updatePurchase(webhookResponse.getRefno(),
                    webhookResponse.getPaymentMethod());
        }

        // status was not "settled". implement other business logic to handle this case

    }

}
