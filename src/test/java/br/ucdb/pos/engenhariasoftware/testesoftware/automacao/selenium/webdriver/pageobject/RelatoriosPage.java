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


            /* Faz a checagem do Fluxo 4 (relatórios) - garante que está na página atual do relatório.
             * Verifica a URL e procura por textos que só existe nessa página para garantir que ela existe*/

            public boolean isPageAtual() {
        String pagina = driver.getPageSource();
        return driver.getCurrentUrl().contains("/dashboard") && pagina.contains(
                "Lançamentos por Categoria") && pagina.contains("Dashboard");
    }

}

