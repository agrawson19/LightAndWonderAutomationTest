package Ui.pages;

import Ui.commonComponents.PaymentABO;
import Ui.commonComponents.UIMethods;
import io.cucumber.java.sl.In;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.asserts.Assertion;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartPage {
    private static final Logger logger = LogManager.getLogger(LoginPage.class);

    UIMethods uiMethods = new UIMethods();

//    public By amazonLogo = By.xpath("//div[@id='nav-logo']");
//    public By btnAddItemTocard = By.id("add-to-cart-button");
//    public By labelItemPrice = By.className("a-price-whole");
//    public By labelItemAddedToCard = By.xpath("//div[@id='attachDisplayAddBaseAlert']//h4[contains(text(),'Added to Cart')]");
//    public By lnkClose = By.xpath("//a[@id='attach-close_sideSheet-link']");
//    public By linkCartItem = By.xpath("//a[contains(@aria-label,'items in cart')]");

    public By labelShoppingCart = By.xpath("//div//*[contains(text(),'Shopping Cart')]");
    public By labeltotalPrice = By.xpath("//div[@class='sc-badge-price-to-pay']//span[contains(@class,'sc-price')]");
    public By labelSubtotalPrice = By.xpath("//span[@id='sc-subtotal-amount-buybox']//span[contains(@class,'sc-price')]");

    public ShoppingCartPage verifIseletedItemInShoppingCart(){
        logger.info("Verifying selectd item added to cart:");
        new LoginPage().verifyElementDispalyed(labelShoppingCart);
        logger.info("selectd item added to cart sucessfully :");
        return  this;
    }

    public String getSubTotalPaymentPrice(){
        uiMethods.waitForElementToBeVisible(labelSubtotalPrice);
        String totalPrice =  uiMethods.getText(labelSubtotalPrice, "Sub total item price").trim().replace(".00", "").replaceAll(",","");
        return totalPrice;
    }

    public String getTotalPaymentPrice(){
        List<String> price = new ArrayList<>();
        uiMethods.waitForElementToBeVisible(labeltotalPrice);
        for(WebElement ele :uiMethods.findElements(labeltotalPrice)){
            price.add(ele.getText().trim().replace(".00", ""));
        }
        Integer finalPrice =0;
        for(String priceVal :price){
             finalPrice=finalPrice+ Integer.parseInt(priceVal.replaceAll(",",""));
        }
        String totalPrice = String.valueOf(finalPrice);
        return totalPrice;
    }
    public ShoppingCartPage verifTotalPaymentPriceIsIdentical(){
        logger.info("Verifying total item price is identical to the product page price");
        String itemPrice = new PaymentABO().getPaymentPrice();

        if( new PaymentABO().getOtherPaymentPrice()!=null){
            String otherItemPrice = new PaymentABO().getOtherPaymentPrice();
            itemPrice= String.valueOf(Integer.parseInt(itemPrice)+Integer.parseInt(otherItemPrice));
        }
        Assertions.assertThat(getTotalPaymentPrice())
                .as("verify total item price is identical to the product page price")
                .containsIgnoringCase(itemPrice);
        logger.info("Total item price is identical to the product page price found sucessfully");
        return  this;
    }

    public ShoppingCartPage verifSubTotalPaymentPriceIsIdentical(){
        logger.info("Verifying sub total item price is identical to the product page price");
        String itemPrice = new PaymentABO().getPaymentPrice();
        if( new PaymentABO().getOtherPaymentPrice()!=null){
            String otherItemPrice = new PaymentABO().getOtherPaymentPrice();
            itemPrice=String.valueOf( Integer.parseInt(itemPrice)+Integer.parseInt(otherItemPrice));
        }
        Assertions.assertThat(getSubTotalPaymentPrice())
                .as("verify sub total item price is identical to the product page price")
                .containsIgnoringCase(itemPrice);
        logger.info("Sub total item price is identical to the product page price found sucessfully");
        return  this;
    }

}
