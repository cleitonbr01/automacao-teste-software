package br.ucdb.pos.engenhariasoftware.testesoftware.automacao.selenium.webdriver;

import br.ucdb.pos.engenhariasoftware.testesoftware.automacao.selenium.webdriver.pageobject.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;

import java.math.*;
import java.time.*;

import static org.testng.Assert.*;



                    /** Fluxo 1: Acessar listagem, criar novo lançamento e validar lançamento criado;*/

public class FluxoCriaLancamentoTest {

    private WebDriver driver;

    private ListaLancamentosPage listaLancamentosPage;

    private LancamentoPage lancamentoPage;

    @BeforeClass
    private void inicialliza() {
        driver = new WebdriverTestBuilder().initialize().getDriver();
        listaLancamentosPage = new ListaLancamentosPage(driver);
        lancamentoPage = new LancamentoPage(driver);
    }

    @Test
    public void criaLancamento() {
        listaLancamentosPage.acessa();
        listaLancamentosPage.novoLancamento();

        LocalDateTime dataHora = LocalDateTime.now();
        final String descricaoLancamento =
                "Fluxo1 - Lançando " + dataHora.format(LancamentoUtil.getDateTimeFormatter());
        final BigDecimal valor = LancamentoUtil.geraValorAleatorio();
        lancamentoPage.cria(descricaoLancamento, valor, dataHora, TipoLancamento.SAIDA);


                  /* Faz a busca na barra de busca */
        listaLancamentosPage.busca(descricaoLancamento);
        assertTrue(listaLancamentosPage.existeLancamento(descricaoLancamento, valor, dataHora,
                TipoLancamento.SAIDA));
    }

    @AfterClass
    private void finaliza() {
        driver.quit();
    }

}


