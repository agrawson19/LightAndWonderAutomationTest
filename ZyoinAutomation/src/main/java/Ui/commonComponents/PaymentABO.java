package Ui.commonComponents;

public class PaymentABO {
    private static String paymentPrice;
    private static String otherItemPrice;

    public  void setPaymentPrice(String paymentPrice){
        this.paymentPrice = paymentPrice;

    }
    public  String getPaymentPrice(){
        return this.paymentPrice;

    }

    public  void setOtherPaymentPrice(String otherItemPrice){
        this.otherItemPrice = otherItemPrice;

    }
    public  String getOtherPaymentPrice(){
        return this.otherItemPrice;

    }
}
