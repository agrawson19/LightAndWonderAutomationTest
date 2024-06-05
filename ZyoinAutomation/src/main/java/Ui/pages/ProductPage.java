package Ui.pages;

import Ui.commonComponents.UIMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class ProductPage {
    private static final Logger logger = LogManager.getLogger(LoginPage.class);

    UIMethods uiMethods = new UIMethods();

    public By amazonLogo = By.xpath("//div[@id='nav-logo']");
    public By btnAddItemTocard = By.id("add-to-cart-button");
    public By labelItemPrice = By.className("a-price-whole");
    public By labelItemAddedToCard = By.xpath("//div[@id='attachDisplayAddBaseAlert']//h4[contains(text(),'Added to Cart')]");
    public By lnkClose = By.xpath("//a[@id='attach-close_sideSheet-link']");



    public ProductPage verifyProductPageLoadedSucessfully(){
        logger.info("verify product page loaded after selecting item");
        uiMethods.switchToWindowAndCloseCurrentWindow();
        return  this;
    }
    public ProductPage addItemToCard(){
        uiMethods.click(btnAddItemTocard,"addItemToCart");
        logger.info("added selected item to cart");
        return this;
    }

    public String getSelectedItemPrice(){
       String itemPrice =  uiMethods.getText(labelItemPrice, "itemPrice");
       return itemPrice;
    }

    public ProductPage verifItemAddedToCart(){
        new LoginPage().verifyElementDispalyed(labelItemAddedToCard,"item Added to cart ");
        logger.info("selectd item added to cart sucessfully :");
        if(uiMethods.isElementDisplayed(lnkClose,"close side sheet ")){
            uiMethods.click(lnkClose,"close link");
        }

        return  this;
    }

}
