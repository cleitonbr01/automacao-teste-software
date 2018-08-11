package br.ucdb.pos.engenhariasoftware.testesoftware.automacao.selenium.webdriver.pageobject;

import org.openqa.selenium.*;


public class RelatoriosPage {

    private WebDriver driver;

    public RelatoriosPage(final WebDriver driver) {
        this.driver = driver;
    }

    public void acessa() {
        driver.get("http://localhost:8080/dashboard/");
    }

    public boolean isPageAtual() {
        String pagina = driver.getPageSource();
        return driver.getCurrentUrl().contains("/dashboard") && pagina.contains(
                "Lançamentos por Categoria") && pagina.contains("Dashboard");
    }

}

