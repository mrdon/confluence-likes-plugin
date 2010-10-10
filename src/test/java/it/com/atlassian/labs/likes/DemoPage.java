package it.com.atlassian.labs.likes;

import com.atlassian.webdriver.confluence.ConfluenceTestedProduct;
import com.atlassian.webdriver.confluence.page.ConfluenceAbstractPage;
import com.atlassian.webdriver.utils.ByJquery;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class DemoPage extends ConfluenceAbstractPage<DemoPage>
{

    @FindBy(id = "add-comment-bottom")
    private WebElement addCommentLink;

    public DemoPage(ConfluenceTestedProduct testedProduct)
    {
        super(testedProduct, null);
    }

    public AddCommentPage gotoAddComment()
    {
        addCommentLink.click();
        return new AddCommentPage(getTestedProduct()).get(true);
    }

    public List<Integer> getCommentIds()
    {
        List<Integer> result = new ArrayList<Integer>();
        for (WebElement e : getDriver().findElements(By.className("comment")))
        {
            result.add(Integer.parseInt(e.getAttribute("id").substring("comment-".length())));
        }
        return result;
    }

    public int getCommentLikes(int commentId)
    {
        WebElement e = getDriver().findElement(ByJquery.$("div#comment-" + commentId+" .date"));
        String likes = e.getText().replaceFirst(".*- ([0-9]+) likes", "$1");
        return Integer.parseInt(likes);
    }

    public DemoPage likeComment(final int commentId)
    {
        final int likes = getCommentLikes(commentId);
        getDriver().findElement(By.id("like-comment-" + commentId)).click();

        wait.until(new ExpectedCondition<Boolean>()
        {
            public Boolean apply(WebDriver o)
            {
                return getCommentLikes(commentId) == likes + 1;
            }
        });
        return this;
    }


}
