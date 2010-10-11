package it.com.atlassian.labs.likes;

import com.atlassian.webdriver.AtlassianWebDriver;
import com.atlassian.webdriver.component.user.User;
import com.atlassian.webdriver.confluence.ConfluenceTestedProduct;
import com.atlassian.webdriver.confluence.page.ConfluenceLoginPage;
import com.atlassian.webdriver.confluence.page.DashboardPage;
import com.atlassian.webdriver.product.TestedProductFactory;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import webdriver.browsers.WebDriverBrowserAutoInstall;
import webdriver.browsers.WebDriverBrowserAutoInstall;

import static org.junit.Assert.assertTrue;

/**
 *
 */
public class TestDashboard
{
    @Test
    public void testLoginOld()
    {
        ConfluenceTestedProduct conf = TestedProductFactory.create(ConfluenceTestedProduct.class);
        DashboardPage dashboard = conf.gotoLoginPage()
                .loginAsAdmin();
        assertTrue(dashboard.isLoggedIn());
    }

    @Test
    public void testLogin()
    {
        ConfluenceTestedProduct conf = TestedProductFactory.create(ConfluenceTestedProduct.class,
                WebDriverBrowserAutoInstall.getDriver());
        
        DashboardPage dashboard = conf.gotoLoginPage()
                .loginAsAdmin();
        assertTrue(dashboard.isLoggedIn());
    }
}
