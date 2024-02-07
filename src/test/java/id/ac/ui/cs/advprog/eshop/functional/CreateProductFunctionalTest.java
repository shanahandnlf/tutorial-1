package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setUpTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }
    @Test
    void pageTitle_isCorrect(ChromeDriver driver) throws Exception{
        driver.get(baseUrl);
        driver.findElement(By.tagName("a")).click();
        driver.findElement(By.tagName("a")).click();
        String pageTitle = driver.getTitle();
        assertEquals("Create New Product", pageTitle);
    }

    @Test
    void makeNewProduct_isCorrect(ChromeDriver driver) throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.tagName("a")).click();
        driver.findElement(By.tagName("a")).click();

        WebElement nameField = driver.findElement(By.name("productName"));
        nameField.clear();
        nameField.sendKeys("Nama Testing");

        WebElement quantityField = driver.findElement(By.name("productQuantity"));
        quantityField.clear();
        quantityField.sendKeys("500");

        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();

        String source = driver.getPageSource();

        assertTrue(source.contains("Nama Testing") && source.contains("500"));

    }
}
