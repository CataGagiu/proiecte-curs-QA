package com.demoblaze;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;

public class LoginTest
{   WebDriver driver;
    @Parameters({"browserParam"})
    @BeforeTest(alwaysRun = true)

    public void deschidefereastra(@Optional("chrome") String browser)
    {
        //open page
        String url = "https://www.demoblaze.com/index.html#carouselExampleIndicators";
        switch (browser)
        {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                driver = new ChromeDriver();
        }

        driver.get(url);
        driver.manage().window().maximize();
    }
//    @AfterTest
//
//    // inchide fereastra
//    public void inchidefereastra()
//    {
//        driver.quit();
//    }

    @Test
    public void loginTestPozitiv()
    {
        // creaza obiect pt a intarzia executia
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //asteapta incarcarea paginii
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html")));

        // cauta elementul de logare
        WebElement linkautentificare= driver.findElement(By.id("login2"));

        // clic pe link
        linkautentificare.click();

        // asteapta pana fereastra de logare este afisata
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logInModalLabel")));

        // creaza obiectul care contine locatia ferestrei de login username
        WebElement introduUsername=driver.findElement(By.id("loginusername"));

        //da clic pe camp
        introduUsername.click();

        // introdu username-ul
        introduUsername.sendKeys("CataG");

        // creaza obiectul localizand fereastra pt parola
        WebElement introduParola=driver.findElement(By.id("loginpassword"));

        // da clic pe camp
        introduParola.click();

        // introdu parola
        introduParola.sendKeys("0&!#6rWmOauZ%8E9");

        // creaza obiect cu locatia butonului de login
        WebElement butonLogin=driver.findElement(By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[2]"));

        // apasa butonul de login
        butonLogin.click();

        //Asteapta incarcarea paginii dupa logare
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"nameofuser\"]")));

        // creaza obiect cu locatia mesajului afisat de logare
        WebElement welcomeCataG= driver.findElement(By.xpath("//*[@id=\"nameofuser\"]"));

        //verifica daca mesajul a aparut corect
        Assert.assertTrue(welcomeCataG.isDisplayed());

    }

    @Test
    public void loginTestUsernameGresit()
    {
        // creaza obiect pt a intarzia executia
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //asteapta incarcarea paginii
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html")));

        // cauta elementul de logare
        WebElement linkautentificare= driver.findElement(By.id("login2"));

        // clic pe link
        linkautentificare.click();

        // asteapta pana fereastra de logare este afisata
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logInModalLabel")));

        // creaza obiectul care contine locatia ferestrei de login username
        WebElement introduUsername=driver.findElement(By.id("loginusername"));

        //da clic pe camp
        introduUsername.click();

        // introdu username-ul gresit
        introduUsername.sendKeys("user_gresit");

        // creaza obiectul localizand fereastra pt parola
        WebElement introduParola=driver.findElement(By.id("loginpassword"));

        // da clic pe camp
        introduParola.click();

        // introdu parola
        introduParola.sendKeys("0&!#6rWmOauZ%8E9");

        // creaza obiect cu locatia butonului de login
        WebElement butonLogin=driver.findElement(By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[2]"));

        // apasa-l
        butonLogin.click();

        //asteptarea pana apare mesajul de eroare
        wait.until(ExpectedConditions.alertIsPresent());

        //comuta la fereastra cu alerta
        Alert alert= driver.switchTo().alert();

        //verificare mesaj de eroare aparut cu "Wrong password" intr-o fereastra noua
        String mesajDeEroareAfisat=alert.getText();
        Assert.assertEquals(mesajDeEroareAfisat,"User does not exist.","Mesajul este gresit");

    }

    @Test
    public void loginTestParolaGresita()
    {
        // creaza obiect pt a intarzia executia
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // cauta elementul de logare
        WebElement linkautentificare= driver.findElement(By.id("login2"));

        // clic pe link
        linkautentificare.click();

        // asteapta pana fereastra de logare este afisata
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logInModalLabel")));

        // creaza obiectul care contine locatia ferestrei de login username
        WebElement introduUsername=driver.findElement(By.id("loginusername"));

        //da clic pe camp
        introduUsername.click();

        // introdu username-ul gresit
        introduUsername.sendKeys("CataG");

        // creaza obiectul localizand fereastra pt parola
        WebElement introduParola=driver.findElement(By.id("loginpassword"));

        // da clic pe camp
        introduParola.click();

        // introdu parola
        introduParola.sendKeys("parola_gresita");

        // creaza obiect cu locatia butonului de login
        WebElement butonLogin=driver.findElement(By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[2]"));

        // apasa-l
        butonLogin.click();

        //asteptarea pana apare mesajul de eroare
        wait.until(ExpectedConditions.alertIsPresent());

        //comuta la fereastra cu alerta
        Alert alert= driver.switchTo().alert();

        //verificare mesaj de eroare aparut cu "Wrong password" intr-o fereastra noua
        String mesajDeEroareAfisat=alert.getText();
        Assert.assertEquals(mesajDeEroareAfisat,"Wrong password.","Mesajul este gresit");
    }
    @Test
    public void loginTestFaraCredentiale()
    {
        // creaza obiect pt a intarzia executia
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // cauta elementul de logare
        WebElement linkautentificare= driver.findElement(By.id("login2"));

        // clic pe link
        linkautentificare.click();

        // asteapta pana fereastra de logare este afisata
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logInModalLabel")));

        // creaza obiect cu locatia butonului de login
        WebElement butonLogin=driver.findElement(By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[2]"));

        // apasa-l
        butonLogin.click();

        //asteptarea pana apare mesajul de eroare
        wait.until(ExpectedConditions.alertIsPresent());

        //comuta la fereastra cu alerta
        Alert alert= driver.switchTo().alert();

        //verificare mesaj de eroare aparut cu "Wrong password" intr-o fereastra noua
        String mesajDeEroareAfisat=alert.getText();
        Assert.assertEquals(mesajDeEroareAfisat,"Please fill out Username and Password.","Mesajul este gresit");
    }
}
