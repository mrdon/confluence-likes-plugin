package it.com.atlassian.labs.likes;

import com.atlassian.webdriver.confluence.ConfluenceTestedProduct;
import com.atlassian.webdriver.confluence.page.ConfluenceAbstractPage;
import com.atlassian.webdriver.utils.ByJquery;
import com.atlassian.webdriver.utils.element.ElementLocated;
import org.openqa.selenium.By;
import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;

/**
 *
 */
public class AddCommentPage extends ConfluenceAbstractPage<AddCommentPage>
{

    @FindBy(name = "confirm")
    private WebElement confirmButton;

    public AddCommentPage(ConfluenceTestedProduct testedProduct)
    {
        super(testedProduct, null);
    }

    public DemoPage submitComment(String text)
    {
        getDriver().findElement(ByJquery.$("li#markupTab a")).click();

        final RenderedWebElement textArea = (RenderedWebElement) getDriver().findElement(By.id("markupTextarea"));
        wait.until(new ExpectedCondition<Boolean>()
        {
            public Boolean apply(WebDriver o)
            {
                return textArea.isDisplayed();
            }
        });

        textArea.sendKeys(text);
        confirmButton.click();
        return new DemoPage(getTestedProduct()).get(true);
    }
}
