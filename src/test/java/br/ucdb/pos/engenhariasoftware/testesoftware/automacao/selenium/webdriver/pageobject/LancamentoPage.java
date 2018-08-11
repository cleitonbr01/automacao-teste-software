package br.ucdb.pos.engenhariasoftware.testesoftware.automacao.selenium.webdriver.pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.math.*;
import java.time.*;
import java.time.format.*;


public class LancamentoPage {

    private WebDriver driver;

    public LancamentoPage(final WebDriver driver) {
        this.driver = driver;
    }

    public void cria(final String descricaoLancamento, final BigDecimal valorLancamento,
                     LocalDateTime dataHora, TipoLancamento tipo) {

        alteraTipo(tipo);

        alteraDescricao(descricaoLancamento);

        alteraData(dataHora);

        alteraValor(valorLancamento);

        alteraCategoria();

        salva();
    }

    public void edita(String descricaoLancamento, BigDecimal valorLancamento, TipoLancamento tipo) {
        alteraTipo(tipo);

        alteraDescricao(descricaoLancamento);

        alteraValor(valorLancamento);

        salva();
    }

    private void alteraCategoria() {
        Select categoria = new Select(driver.findElement(By.id("categoria")));
        categoria.selectByIndex(1);
    }

    private void alteraTipo(TipoLancamento tipo) {
        if (tipo == TipoLancamento.SAIDA) {
            driver.findElement(By.id("tipoLancamento2")).click(); // informa lançamento: SAÍDA
        } else {
            driver.findElement(By.id("tipoLancamento1")).click(); // informa lançamento: ENTRADA
        }
    }

    private void alteraDescricao(String descricaoLancamento) {
        WebElement descricao = driver.findElement(By.id("descricao"));
        descricao.click();
        descricao.clear();
        descricao.sendKeys(descricaoLancamento);
    }

    private void alteraData(LocalDateTime dataHora) {
        DateTimeFormatter formatoDataLancamento = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        WebElement dataLancamento = driver.findElement(By.name("dataLancamento"));
        dataLancamento.clear();
        dataLancamento.sendKeys(dataHora.format(formatoDataLancamento));
        driver.findElement(By.cssSelector(".panel-heading h4")).click();
    }

    private void alteraValor(BigDecimal valorLancamento) {
        WebElement valor = driver.findElement(By.id("valor"));
        valor.clear();
        valor.sendKeys(String.valueOf(valorLancamento));
    }

    public boolean contemMensagemErro(String mensagem) {
        return driver.findElement(By.cssSelector(".alert-danger")).getText().contains(mensagem);
    }

    private void salva() {
        driver.findElement(By.id("btnSalvar")).click();
    }

    public void cancela() {
        driver.findElement(By.id("cancelar")).click();
    }
}


