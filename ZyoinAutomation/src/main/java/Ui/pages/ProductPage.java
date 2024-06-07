package Ui.pages;

import Ui.commonComponents.PaymentABO;
import Ui.commonComponents.UIMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductPage {
    private static final Logger logger = LogManager.getLogger(LoginPage.class);

    UIMethods uiMethods = new UIMethods();

    public By amazonLogo = By.xpath("//div[@id='nav-logo']");
    public By btnAddItemTocard = By.id("add-to-cart-button");
    public By labelItemPrice = By.xpath("//div[@id='corePriceDisplay_desktop_feature_div']//span[@class='a-price-whole']");
    public By labelItemAddedToCard = By.xpath("//div[@id='attachDisplayAddBaseAlert']//h4[contains(text(),'Added to Cart')]");
   public By lnkClose = By.xpath("//a[contains(@id,'close')]");
    public By linkCartItem = By.xpath(  "//*[@id='nav-cart']");



    public By labelShoppingCart = By.xpath("//div//*[contains(text(),'Shopping Cart')]");

    public By btnSkip = By.xpath(  "//span[@id='attachSiNoCoverage']//span[contains(text(),'Skip')]");



    public ProductPage verifyProductPageLoadedSucessfully(){
        logger.info("verify product page loaded after selecting item");
        uiMethods.switchToWindowAndCloseCurrentWindow();
        return  this;
    }
    public ProductPage addItemToCart(){
        String itemPrice = getSelectedItemPrice();
        new PaymentABO().setPaymentPrice(itemPrice);
        List<WebElement> price = uiMethods.findElements(btnAddItemTocard);
        if(price.size()>1){
            uiMethods.click(price.get(1));
            clickSkipIfPresent();
        }
        else {
        uiMethods.click(price.get(0));
            clickSkipIfPresent();}
        logger.info("added selected item to cart");
        return this;
    }

    public String getSelectedItemPrice(){
       String itemPrice =  uiMethods.getText(labelItemPrice, "itemPrice").replaceAll(",","");
       return itemPrice;
    }

    public void clickSkipIfPresent(){
                if(uiMethods.findElements(btnSkip).size()>1){
            uiMethods.click(btnSkip,"click skip button");
            uiMethods.delay(2000);
        }

    }
    public ProductPage verifItemAddedToCart(){

        clickSkipIfPresent();
        if(uiMethods.findElements(labelItemAddedToCard).size()>0){
            new LoginPage().verifyElementDispalyed(labelItemAddedToCard, "item Added to cart ");
            logger.info("selectd item added to cart sucessfully :");
            if (uiMethods.isElementDisplayed(lnkClose, "close side sheet ")) {
                uiMethods.click(lnkClose, "close link");
            }
        }
        else {
            uiMethods.delay(2000);
            if (uiMethods.findElements(lnkClose).size()>0) {
                uiMethods.click(lnkClose, "close link");
            }
        }
        return  this;
    }

    public ShoppingCartPage openCartFromTopPanel(){
        logger.info("open card from top panel and verify item is added");
        uiMethods.scrollIntoView(uiMethods.findElement(linkCartItem));
        uiMethods.delay(1000);
        if(uiMethods.isElementDisplayed(linkCartItem,"click link cartItem")){
            uiMethods.click(linkCartItem);
        }
        return new ShoppingCartPage();
    }

    public ProductPage addMoreItemsToCart(){
        String itemPrice = getSelectedItemPrice();
        new PaymentABO().setOtherPaymentPrice(itemPrice);
        List<WebElement> price = uiMethods.findElements(btnAddItemTocard);
        if(price.size()>1){
            uiMethods.click(price.get(1));
            clickSkipIfPresent();
        }
        else {
            uiMethods.click(price.get(0));
            clickSkipIfPresent();}
        logger.info("added selected item to cart");
        return this;
    }

}
