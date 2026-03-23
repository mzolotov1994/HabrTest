package packageTest;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PageTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.habr.com");
        setLicenseIfExist();
    }

    public void setLicenseIfExist(){
        List<WebElement> buttonsConsent = driver.findElements(By.cssSelector(".fc-button-label"));
        if (!buttonsConsent.isEmpty()) {
            buttonsConsent.get(0).click();
        }
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    @Tag("Авторизация")
    @DisplayName("Проверка отображения почты в форме ввода email")
    public void findEmail() {

        String text = "example@gmail.com";
        WebElement loginButton = driver.findElement(By.cssSelector("a.tm-header-user-menu__login"));
        loginButton.click();

        WebElement searchPageField = driver.findElement(By.xpath("//input[contains(@name,'email')]"));
        searchPageField.sendKeys(text);
        assertTrue(searchPageField.isDisplayed(), "Email не отображается в поле");
    }

    @Test
    @Tag("Главная страница")
    @DisplayName("Наличие заголовка 'Моя лента' на главной странице")
    public void findMyRibbon() {
        WebElement searchPageElements = driver.findElement(By.cssSelector("h1.tm-section-name__text"));
        assertTrue(searchPageElements.isDisplayed(), "Заголовок отсутствует");
    }

    @Test
    @Tag("Поиск")
    @DisplayName("Поиск элемента")
    public void findElements() {
        List<WebElement> searchPageElements = driver.findElements(By.xpath("//span[contains(text(),'Фронтенд')]"));
        assertFalse(searchPageElements.isEmpty(), "Message"); //альтернатива .isEmpty() это .size() > 0
    }

}