import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class QuoteServeTests {

    @Test
    public void searchScopeAuthor() {
        FirefoxDriver driver = new FirefoxDriver();
        driver.get("https://cs.gmu.edu:8443/offutt/servlet/quotes.quoteserve");
        WebElement searchQuotes = driver.findElement(By.name("searchText"));
        WebElement scope = driver.findElement(By.id("author"));
        WebElement submit = driver.findElement(By.xpath("//input[@name='submit' and @value='search']"));
        // Enter something to search for
        searchQuotes.sendKeys("all");
        scope.click();
        submit.click();
        Assert.assertTrue(driver.getPageSource().contains("It ain\'t over till it\'s over."));
        driver.quit();
    }

    @Test
    public void searchScopeQuotes() {
        FirefoxDriver driver = new FirefoxDriver();
        driver.get("https://cs.gmu.edu:8443/offutt/servlet/quotes.quoteserve");
        WebElement searchQuotes = driver.findElement(By.name("searchText"));
        WebElement scope = driver.findElement(By.id("quote"));
        WebElement submit = driver.findElement(By.xpath("//input[@name='submit' and @value='search']"));
        // Enter something to search for
        searchQuotes.sendKeys("all");
        scope.click();
        submit.click();
        Assert.assertTrue(driver.getPageSource().contains("Prediction is very difficult, especially about the future."));
        driver.quit();
    }

    @Test
    public void searchQuotesEmpty() {
        FirefoxDriver driver = new FirefoxDriver();
        driver.get("https://cs.gmu.edu:8443/offutt/servlet/quotes.quoteserve");
        WebElement searchQuotes = driver.findElement(By.name("searchText"));
        WebElement scope = driver.findElement(By.id("both"));
        WebElement submit = driver.findElement(By.xpath("//input[@name='submit' and @value='search']"));
        // Enter something to search for
        searchQuotes.sendKeys("");
        scope.click();
        submit.click();
        Assert.assertTrue(!driver.getPageSource().contains("<dl>"));
        driver.quit();
    }

    @Test
    public void searchQuotesInvalid() {
        FirefoxDriver driver = new FirefoxDriver();
        driver.get("https://cs.gmu.edu:8443/offutt/servlet/quotes.quoteserve");
        WebElement searchQuotes = driver.findElement(By.name("searchText"));
        WebElement scope = driver.findElement(By.id("both"));
        WebElement submit = driver.findElement(By.xpath("//input[@name='submit' and @value='search']"));
        // Enter something to search for
        searchQuotes.sendKeys("\"\"");
        scope.click();
        submit.click();
        Assert.assertTrue(driver.getPageSource().contains("did not match any quotes."));
        driver.quit();
    }

    @Test
    public void searchQuotesValidNoMatch() {
        FirefoxDriver driver = new FirefoxDriver();
        driver.get("https://cs.gmu.edu:8443/offutt/servlet/quotes.quoteserve");
        WebElement searchQuotes = driver.findElement(By.name("searchText"));
        WebElement scope = driver.findElement(By.id("both"));
        WebElement submit = driver.findElement(By.xpath("//input[@name='submit' and @value='search']"));
        // Enter something to search for
        searchQuotes.sendKeys("no match");
        scope.click();
        submit.click();
        Assert.assertTrue(driver.getPageSource().contains("did not match any quotes."));
        driver.quit();
    }

    @Test
    public void searchQuotesValidWithMatch() {
        FirefoxDriver driver = new FirefoxDriver();
        driver.get("https://cs.gmu.edu:8443/offutt/servlet/quotes.quoteserve");
        WebElement searchQuotes = driver.findElement(By.name("searchText"));
        WebElement scope = driver.findElement(By.id("both"));
        WebElement submit = driver.findElement(By.xpath("//input[@name='submit' and @value='search']"));
        searchQuotes.sendKeys("diagnose");
        scope.click();
        submit.click();
        Assert.assertTrue(driver.getPageSource().contains("Before you diagnose yourself with depression or low self esteem, first be sure that you are not, in fact, surrounded by assholes."));
        driver.quit();
    }

    @Test
    public void clickGetAnotherRandomQuote() {
        FirefoxDriver driver = new FirefoxDriver();
        driver.get("https://cs.gmu.edu:8443/offutt/servlet/quotes.quoteserve");
        String before = driver.getPageSource();
        WebElement randomQuoteButton = driver.findElement(By.xpath("//input[@name='submit' and @value='Get Another Random Quote']"));
        randomQuoteButton.click();
        String after = driver.getPageSource();
        Assert.assertTrue(!before.equals(after) && !driver.getPageSource().contains("<dl>"));
    }

    @Test
    public void searchRecentUser() {
        FirefoxDriver driver = new FirefoxDriver();
        driver.get("https://cs.gmu.edu:8443/offutt/servlet/quotes.quoteserve");
        WebElement searchQuotes = driver.findElement(By.name("searchText"));
        WebElement submit = driver.findElement(By.xpath("//input[@name='submit' and @value='search']"));
        searchQuotes.sendKeys("test123");
        submit.click();
        Assert.assertTrue(driver.getPageSource().contains("test123"));
        driver.quit();
    }

    @Test
    public void searchCommunityUser() {
        FirefoxDriver driver = new FirefoxDriver();
        driver.get("https://cs.gmu.edu:8443/offutt/servlet/quotes.quoteserve");
        WebElement searchQuotes = driver.findElement(By.name("searchText"));
        WebElement submit = driver.findElement(By.xpath("//input[@name='submit' and @value='search']"));
        searchQuotes.sendKeys("test123");
        submit.click();
        Assert.assertTrue(driver.getPageSource().contains("test123"));
        driver.quit();
    }

    @Test
    public void resetScopeAuthorWithText() {
        FirefoxDriver driver = new FirefoxDriver();
        driver.get("https://cs.gmu.edu:8443/offutt/servlet/quotes.quoteserve");
        WebElement searchQuotes = driver.findElement(By.name("searchText"));
        WebElement reset = driver.findElement(By.xpath("//input[@name='reset' and @value='reset']"));
        WebElement scope = driver.findElement(By.id("author"));
        searchQuotes.sendKeys("text that shouldn't be here once I click reset");
        scope.click();
        reset.click();
        Assert.assertTrue(!searchQuotes.getText().equals("text that shouldn't be here once I click reset"));
        driver.quit();
    }

    @Test
    public void resetScopeQuoteWithText() {
        FirefoxDriver driver = new FirefoxDriver();
        driver.get("https://cs.gmu.edu:8443/offutt/servlet/quotes.quoteserve");
        WebElement searchQuotes = driver.findElement(By.name("searchText"));
        WebElement reset = driver.findElement(By.xpath("//input[@name='reset' and @value='reset']"));
        WebElement scope = driver.findElement(By.id("quote"));
        searchQuotes.sendKeys("text that shouldn't be here once I click reset");
        scope.click();
        reset.click();
        Assert.assertTrue(!searchQuotes.getText().equals("text that shouldn't be here once I click reset"));
        driver.quit();
    }

    @Test
    public void resetScopeBothWithText() {
        FirefoxDriver driver = new FirefoxDriver();
        driver.get("https://cs.gmu.edu:8443/offutt/servlet/quotes.quoteserve");
        WebElement searchQuotes = driver.findElement(By.name("searchText"));
        WebElement reset = driver.findElement(By.xpath("//input[@name='reset' and @value='reset']"));
        WebElement scope = driver.findElement(By.id("both"));
        searchQuotes.sendKeys("text that shouldn't be here once I click reset");
        scope.click();
        reset.click();
        Assert.assertTrue(!searchQuotes.getText().equals("text that shouldn't be here once I click reset"));
        driver.quit();
    }

    @Test
    public void resetScopeAuthorNoText() {
        FirefoxDriver driver = new FirefoxDriver();
        driver.get("https://cs.gmu.edu:8443/offutt/servlet/quotes.quoteserve");
        WebElement searchQuotes = driver.findElement(By.name("searchText"));
        WebElement reset = driver.findElement(By.xpath("//input[@name='reset' and @value='reset']"));
        WebElement scope = driver.findElement(By.id("author"));
        searchQuotes.sendKeys("");
        reset.click();
        scope.click();
        Assert.assertTrue(searchQuotes.getText().equals(""));
        driver.quit();
    }
}