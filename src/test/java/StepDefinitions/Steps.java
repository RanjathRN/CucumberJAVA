package StepDefinitions;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Steps {

    WebDriver driver;

    @Given("^User is on the bookstore website homepage$")
    public void user_is_on_the_bookstore_website_homepage() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.waterstones.com");
        try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        
        
    }

    @When("^User searches for a book with title \"(.*)\"$")
    public void user_searches_for_book(String bookTitle) {
        WebElement searchBox = driver.findElement(By.xpath("//*[@class='input input-search']"));
        searchBox.sendKeys(bookTitle);
        WebElement searchButton = driver.findElement(By.xpath("//*[@class='input-search-button icon']"));
        searchButton.click();
        driver.manage().window().maximize();
        try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Then("^Search results should display the book with title \"(.*)\"$")
    public void search_results_display_book(String bookTitle) {
        
        List <WebElement> searchResult = driver.findElements(By.xpath("//a[contains(@class,'title link-invert dotdotdot')]"));
        //List <WebElement> searchResult = driver.findElements(By.xpath("//a[@class='title link-invert dotdotdot']"));
        System.out.println(searchResult.get(1).getText());
        assertTrue("Invalid search result",searchResult.get(1).getText().equalsIgnoreCase("The Great Gatsby"));
        assertTrue("Search result does not display the expected book", searchResult.get(1).isDisplayed());
        
        
    }

    @When("^User navigates to the details page of a book$")
    public void user_navigates_to_book_details_page() {
    	List <WebElement> bookLink = driver.findElements(By.xpath("//a[contains(@class,'title link-invert dotdotdot')]"));
        bookLink.get(1).click();
    }

    @When("^User adds the book to the shopping cart$")
    public void user_adds_book_to_cart() {
        WebElement addToCartButton = driver.findElement(By.xpath("//button[@type='submit' and contains(text(),'Add to basket')]"));
        addToCartButton.click();
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Then("^The book should be successfully added to the shopping cart$")
    public void book_added_to_cart_successfully() {
    	
        WebElement successMessage = driver.findElement(By.xpath("//*[contains(text(),'This item has been added to your basket')]"));
        assertTrue("Book was not added to the shopping cart successfully", successMessage.isDisplayed());
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}