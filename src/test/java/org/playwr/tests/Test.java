package org.playwr.tests;

import com.microsoft.playwright.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;

public class Test {
    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    @org.testng.annotations.Test
    public void ceshi() {

        //实例化Playwright对象
        try (Playwright playwright = Playwright.create()) {
            //启动浏览器默认无头模式，
            //Browser browser = playwright.webkit().launch();//打开浏览器但是不打开ui可视化页面
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
            //打开新页面

            BrowserContext browserContext = null;

            logger.info("开始获取登录信息");

            try {

                browserContext = browser.newContext(new Browser.NewContextOptions().setStorageStatePath(Paths.get("auth.json")));

                logger.info("登录信息获取成功");

            }catch (Exception exception){

                logger.warn("登录信息获取失败------------------------------------------------");

            }

            Page page = browserContext.newPage();

//            打开网页
            page.navigate("https://ceshiren.com");
//            截图
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example.png")));

        }catch (Exception e){


        }
    }
}
