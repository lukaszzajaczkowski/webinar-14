import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.Keys;


public class ZadaniaDomowe extends BaseTest {


    //zadanie 1

    @Test
    public void ShowPageName() {
        driver.get("https://testuj.pl/");
        System.out.println("Tytuł strony: " + driver.getCurrentUrl());

    }

    //zadanie 2

    @Test
    public void ShowNumberOfLinks() {
        driver.get("https://www.bbc.com");
        int numberOfLinks = driver.findElements(By.tagName("a")).size();
        System.out.println("Na stronie jest " + numberOfLinks + " linków");
        }

    @Test
    public void ShowNumberOfImgs() {
        driver.get("https://www.bbc.com");
        int numberOfImgs = driver.findElements(By.tagName("img")).size();
        System.out.println("Na stronie jest " + numberOfImgs + " obrazków");
        }


    //zadanie 3

    @Test
    public void LoginAsAdmin() {
        driver.get("https://opensource-demo.orangehrmlive.com/");

        WebElement usernameInput = driver.findElement(By.id("txtUsername"));
        usernameInput.sendKeys("Admin");

        WebElement passwordInput = driver.findElement(By.id("txtPassword"));
        passwordInput.sendKeys("admin123");

        WebElement loginButton = driver.findElement(By.id("btnLogin"));
        loginButton.click();

        WebElement successAlert = driver.findElement(By.id("welcome"));
        String successText = successAlert.getText();

        Assert.assertTrue(successText.contains("Welcome"));
    }


    //zadanie 4

    @Test
    public void InvalidCredentials() {
        driver.get("https://opensource-demo.orangehrmlive.com/");

        WebElement usernameInput = driver.findElement(By.id("txtUsername"));
        usernameInput.sendKeys("WrongUsername");

        WebElement passwordInput = driver.findElement(By.id("txtPassword"));
        passwordInput.sendKeys("WrongPassword");

        WebElement loginButton = driver.findElement(By.id("btnLogin"));
        loginButton.click();

        WebElement failAlert = driver.findElement(By.id("spanMessage"));
        String failText = failAlert.getText();

        Assert.assertTrue(failText.contains("Invalid credentials"));
    }


    //zadanie 5

    @Test
    public void AssertPrice() {
        driver.get("http://demo.guru99.com/payment-gateway/index.php");

        WebElement quantity = driver.findElement(By.name("quantity"));

        Select select = new Select(quantity);
        select.selectByValue("3");

        WebElement buyNow = driver.findElement(By.cssSelector(".button"));
        buyNow.click();

        WebElement fullPrice = driver.findElement(By.xpath("/html/body/section/div/form/div[1]/div/font[2]"));
        String price = fullPrice.getText();

        Assert.assertTrue(price.contains("60"));
    }


    //zadanie 6

    @Test
    public void DragAndDrop() {
        driver.get("https://demo.guru99.com/test/drag_drop.html");

        WebElement drag1 = driver.findElement(By.id("fourth"));
        WebElement drop1 = driver.findElement(By.id("amt7"));
        WebElement drag2 = driver.findElement(By.id("credit2"));
        WebElement drop2 = driver.findElement(By.id("bank"));
        WebElement drag3 = driver.findElement(By.id("credit1"));
        WebElement drop3 = driver.findElement(By.id("loan"));
        WebElement drop4 = driver.findElement(By.id("amt8"));


        Actions dragAndDrop = new Actions(driver);
        dragAndDrop.dragAndDrop(drag1, drop1).build().perform();
        dragAndDrop.dragAndDrop(drag2, drop2).build().perform();
        dragAndDrop.dragAndDrop(drag3, drop3).build().perform();
        dragAndDrop.dragAndDrop(drag1, drop4).build().perform();


        WebElement message = driver.findElement(By.id("equal"));
        if (message.isDisplayed()) {
            System.out.println("Drag and drop success");
        } else {
            System.out.println("Drag and drop failed");
            }
    }


        //zadanie 7

    @Test
    public void CountLinks() {
        driver.get("https://google.com");

        JavascriptExecutor js = (JavascriptExecutor)driver;

        WebElement agreeButton = driver.findElement(By.id("L2AGLb"));

        js.executeScript("arguments[0].scrollIntoView();", agreeButton);
        agreeButton.click();

        WebElement field = driver.findElement(By.name("q"));
        field.sendKeys("Java");

        WebElement searchButton = driver.findElement(By.xpath("//div[@class='FPdoLc lJ9FBc']//input[@name='btnK']"));
        searchButton.click();

        int numberOfLinks = driver.findElements(By.tagName("a")).size();
        System.out.println("Na stronie jest " + numberOfLinks + " linków");
    }


        //zadanie 8

    @Test
    public void TesterSearch() {
        driver.get("https://www.pracuj.pl");

        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"gp-cookie-agreements\"]/div/div/div[1]/div[3]/button")));

        WebElement cookies = driver.findElement(By.xpath("//*[@id=\"gp-cookie-agreements\"]/div/div/div[1]/div[3]/button"));
        cookies.click();


        WebElement searchBar = driver.findElement(By.xpath("//label[text()='stanowisko, firma, słowo kluczowe']/../input"));
        searchBar.sendKeys("tester", Keys.ENTER);

        WebElement placeBar = driver.findElement(By.xpath("/html/body/main/div[1]/div/div/div[3]/form/div[1]/div[3]/div/div/input"));
        placeBar.sendKeys("Warszawa", Keys.ENTER);




        WebElement searchButton = driver.findElement(By.cssSelector(".slider--1Afpe"));
        searchButton.click();
    }

    /**
     * Zadanie domowe
     * 1. wchodzimy na stronę http://sampleshop.inqa.pl/
     * 2. klikamy "Kontakt z nami" - nie korzystamy z By.linkText()
     *
     * Uzupełniamy formularz:
     * 1. Temat = webmaster
     * 2. podajemy e-mail
     * 3. wpisujemy treść
     *
     * Klikamy wyślij
     * Sprawdzamy czy wiadomość została wysłana
     */

    @Test
    public void ContactForm() {
        driver.get("http://sampleshop.inqa.pl/");

        WebElement ContactWithUs = driver.findElement(By.cssSelector("#contact-link > [href='http://sampleshop.inqa.pl/kontakt']"));
        ContactWithUs.click();

        WebElement topic = driver.findElement(By.cssSelector("[name='id_contact']"));
        Select select = new Select(topic);
        select.selectByValue("2");

        WebElement emailAdd = driver.findElement(By.cssSelector("[name='from']"));
        emailAdd.sendKeys("test@test.pl");

        WebElement msg = driver.findElement(By.cssSelector("[name='message']"));
        msg.sendKeys("test 123");

        WebElement sendButton = driver.findElement(By.cssSelector("[name='submitMessage']"));
        sendButton.click();

        WebElement successMsg = driver.findElement(By.cssSelector(".alert li"));

        if (successMsg.isDisplayed()) {
            System.out.println("Message sent with success");
        } else {
            System.out.println("Message not send");
        }




    }
}


