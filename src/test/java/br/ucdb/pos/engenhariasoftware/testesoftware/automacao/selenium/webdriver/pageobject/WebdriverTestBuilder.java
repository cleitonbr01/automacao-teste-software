package br.ucdb.pos.engenhariasoftware.testesoftware.automacao.selenium.webdriver.pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;


public class WebdriverTestBuilder {

    private WebDriver driver;

              /** Criei essa classe separada para servir de apoio a toda as inicializações dos fluxos de Teste */

    public WebdriverTestBuilder initialize() {
        boolean windows = System.getProperty("os.name").toUpperCase().contains("WIN");
        System.setProperty("webdriver.gecko.driver",
                System.getProperty("user.dir") + "/src/test/resources/drivers/geckodriver" + (
                        windows ?
                                ".exe" :
                                ""));

        driver = new FirefoxDriver();
        return this;
    }

    public WebDriver getDriver() {
        return driver;
    }

}
