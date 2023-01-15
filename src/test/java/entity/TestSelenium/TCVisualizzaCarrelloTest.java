// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class TCVisualizzaCarrelloTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void tCVisualizzaCarrelloOk() {
    driver.get("http://localhost:8080/dressapp/show-products");
    driver.manage().window().setSize(new Dimension(974, 1032));
    driver.findElement(By.cssSelector(".col-md-3:nth-child(1) .btn")).click();
    driver.findElement(By.cssSelector(".navbar-toggler")).click();
    driver.findElement(By.linkText("Carrello")).click();
    {
      List<WebElement> elements = driver.findElements(By.cssSelector("td:nth-child(1)"));
      assert(elements.size() > 0);
    }
  }
  @Test
  public void tCVisualizzaCarrelloNonAutenticato() {
    driver.get("http://localhost:8080/dressapp/index.jsp");
    driver.manage().window().setSize(new Dimension(974, 1032));
    driver.findElement(By.cssSelector(".navbar-toggler-icon")).click();
    driver.findElement(By.linkText("Carrello")).click();
    assertThat(driver.getTitle(), is("DressApp - Login"));
    assertThat(driver.findElement(By.cssSelector(".sign-in-container h1")).getText(), is("Login"));
  }
  @Test
  public void tCVisualizzaCarrelloVuoto() {
    driver.get("http://localhost:8080/dressapp/show-products");
    driver.manage().window().setSize(new Dimension(974, 1032));
    driver.findElement(By.cssSelector(".navbar-toggler")).click();
    driver.findElement(By.linkText("Carrello")).click();
    assertThat(driver.findElement(By.cssSelector("h3")).getText(), is("Totale: € 0"));
  }
}
