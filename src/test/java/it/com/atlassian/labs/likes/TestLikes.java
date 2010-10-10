package it.com.atlassian.labs.likes;

import com.atlassian.webdriver.Link;
import com.atlassian.webdriver.confluence.ConfluenceTestedProduct;
import com.atlassian.webdriver.confluence.page.DashboardPage;
import com.atlassian.webdriver.product.TestedProductFactory;
import org.junit.Test;
import org.openqa.selenium.By;
import webdriver.browsers.WebDriverBrowserAutoInstall;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class TestLikes
{
    @Test
    public void testLikes()
    {
        ConfluenceTestedProduct conf = TestedProductFactory.create(ConfluenceTestedProduct.class, WebDriverBrowserAutoInstall.getDriver());
        DemoPage demo = conf.gotoLoginPage()
                .loginAsAdmin()
                .gotoPage(new Link<DemoPage>(By.id("spacelink-ds"), DemoPage.class))
                .gotoAddComment()
                .submitComment("Bob");

        int commentId = demo.getCommentIds().get(0);
        demo.likeComment(commentId);
        assertEquals(1, demo.getCommentLikes(commentId));
    }

}
