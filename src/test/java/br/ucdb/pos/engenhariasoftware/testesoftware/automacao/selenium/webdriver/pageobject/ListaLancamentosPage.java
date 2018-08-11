package br.ucdb.pos.engenhariasoftware.testesoftware.automacao.selenium.webdriver.pageobject;

import org.openqa.selenium.*;

import java.math.*;
import java.text.*;
import java.time.*;
import java.time.format.*;


public class ListaLancamentosPage {

    private WebDriver driver;

    public ListaLancamentosPage(final WebDriver driver) {
        this.driver = driver;
    }

    public void acessa() {
        driver.get("http://localhost:8080/lancamentos");
    }

    public boolean isPageAtual() {
        String pagina = driver.getPageSource();
        return driver.getCurrentUrl().contains("/lancamentos") && pagina.contains(
                "Lançamentos") && pagina.contains("Recarregar");
    }

    public void novoLancamento() {
        driver.findElement(By.id("novoLancamento")).click();
    }

    public void abreRelatorios() {
        driver.findElement(By.cssSelector(".form-group a[title=Gráfico]")).click();
    }


            /**Acha o campo com o item busca, limpa o texto do campo, envia o texto de busca, depois
             * submete este formulário e aguarda a busca retornar**/

    public void busca(String texto) {
        WebElement busca = driver.findElement(By.id("itemBusca"));
        busca.clear();
        busca.sendKeys(texto);
        busca.submit();
        try {
            Thread.sleep(500);  /*500 milisegundos até a buscar retornar */
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void recarrega() {
        driver.findElement(By.id("bth-search")).click();
    }

    public void editaLancamento(int posicao) {
        WebElement webElement = driver.findElements(By.cssSelector("#tabelaLancamentos tbody tr"))
                .get(posicao);
        webElement.findElement(By.partialLinkText("Editar")).click();
    }

    public void removeLancamento(int posicao) {
        WebElement webElement = driver.findElements(By.cssSelector("#tabelaLancamentos tbody tr"))
                .get(posicao);
        webElement.findElement(By.partialLinkText("Excluir")).click();
    }

    public boolean existeLancamento(final String descricaoLancamento,
                                    final BigDecimal valorLancamento, LocalDateTime dataHora, TipoLancamento tipo) {

        DateTimeFormatter formatoDataLancamento = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String lancamentos = driver.getPageSource();
        DecimalFormat df = getDecimalFormat();
        return (lancamentos.contains(descricaoLancamento) && lancamentos.contains(
                df.format(valorLancamento)) && lancamentos.contains(
                dataHora.format(formatoDataLancamento)) && lancamentos.contains(
                tipo.getDescricao()));
    }

    public boolean validaTotalEntrada(BigDecimal valor) {
        DecimalFormat format = getDecimalFormat();
        WebElement entrada = driver.findElements(By.cssSelector("#tabelaLancamentos tfoot tr"))
                .get(1);
        return entrada.getText().contains(" " + format.format(valor));
    }

    public boolean validaTotalSaida(BigDecimal valor) {
        DecimalFormat format = getDecimalFormat();
        WebElement saida = driver.findElements(By.cssSelector("#tabelaLancamentos tfoot tr"))
                .get(0);
        return saida.getText().contains(" " + format.format(valor));
    }

    private DecimalFormat getDecimalFormat() {
        return new DecimalFormat("#,###,##0.00");
    }
}

