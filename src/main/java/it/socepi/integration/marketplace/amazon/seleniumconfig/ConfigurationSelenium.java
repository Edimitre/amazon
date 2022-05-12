package it.socepi.integration.marketplace.amazon.seleniumconfig;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;


@Configuration
@ComponentScan
public class ConfigurationSelenium {





    @Bean(name = "driver")
    public WebDriver driver(){

        System.setProperty("webdriver.chrome.driver", "/home/spring/Downloads/chromedriver");

        ChromeOptions options = new ChromeOptions();

        options.addArguments("start-maximized");
        options.addArguments("--disable-notifications");


        WebDriver driver = new ChromeDriver(options);


        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        return driver;
    }


}



