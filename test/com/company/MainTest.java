package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ltp on 2016/4/15.
 */
public class MainTest {
    WebDriver driver;
    ArrayList<String> info;

    @Before
    public void setUp() throws Exception {
        info = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("G:\\各种文档\\2016 春\\软件测试\\LIB2\\selenium\\info.csv"));
        String temp = reader.readLine();
        while (temp != null) {
            info.add(temp);
            temp = reader.readLine();
        }
        driver = new FirefoxDriver();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void testMain() throws Exception {
        Iterator iterator = info.iterator();
        while(iterator.hasNext()){
            String temp =(String) iterator.next();
            String[] information = temp.split(",");
                            driver.get("http://www.ncfxy.com");
                // 用下面代码也可以实现
                // driver.navigate().to("http://www.baidu.com");
                (new WebDriverWait(driver, 1)).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return d.getTitle().toLowerCase().startsWith("软件");
                    }
                });
                // 获取 网页的 title
                System.out.println("id is: " + information[0]);

                // 通过 id 找到 input 的 DOM
                WebElement element = driver.findElement(By.id("name"));

                // 输入关键字
                element.sendKeys(information[0]);

                element = driver.findElement(By.id("pwd"));
                element.sendKeys(information[0].substring(information[0].length() - 6, information[0].length()));
                // 提交 input 所在的  form
                element.submit();

                // 通过判断 title 内容等待搜索页面加载完毕，Timeout 设置10秒
                (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return d.getTitle().toLowerCase().startsWith("登录结果");
                    }
                });
                List<WebElement> getEmail = driver.findElements(By.tagName("td"));
            try {
                assertEquals(information[1], getEmail.get(1).getText());
            }catch (org.junit.ComparisonFailure f){
                System.out.print("fail in id "+information[0]+" where email should be "+getEmail.get(1).getText()+" but email list is "+information[1]+"\n");
            }
                // 显示搜索结果页面的 title
                System.out.println("email is: " + getEmail.get(1).getText());
        }

    }
}