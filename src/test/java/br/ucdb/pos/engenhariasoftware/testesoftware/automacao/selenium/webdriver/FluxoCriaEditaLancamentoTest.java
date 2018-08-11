package br.ucdb.pos.engenhariasoftware.testesoftware.automacao.selenium.webdriver;

import br.ucdb.pos.engenhariasoftware.testesoftware.automacao.selenium.webdriver.pageobject.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;

import java.math.*;
import java.time.*;

import static org.testng.Assert.*;

           /** Fluxo 2: Acessar listagem, criar novo lançamento, validar lançamento criado, editar
            * lançamento e validar edição;*/

public class FluxoCriaEditaLancamentoTest {

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
    public void criaEditaLancamento() {
        listaLancamentosPage.acessa();
        listaLancamentosPage.novoLancamento();

        LocalDateTime dataHora = LocalDateTime.now();

        final String descricaoLancamento = "Fluxo2 - Lançando " + dataHora.format(
                LancamentoUtil.getDateTimeFormatter());
        final BigDecimal valor = LancamentoUtil.geraValorAleatorio();
        lancamentoPage.cria(descricaoLancamento, valor, dataHora, TipoLancamento.SAIDA);

        listaLancamentosPage.busca(descricaoLancamento);
        assertTrue(listaLancamentosPage.existeLancamento(descricaoLancamento, valor, dataHora,
                TipoLancamento.SAIDA));
        listaLancamentosPage.busca(descricaoLancamento);



        /* edita o lançamento na posição 0, ou seja, o primeiro lançamento da tabela */
        listaLancamentosPage.editaLancamento(0);


        final String novaDescricao = descricaoLancamento + " editado";
        final BigDecimal novoValor = LancamentoUtil.geraValorAleatorio();


        /*muda o valor do tipo para ENTRADA */
        lancamentoPage.edita(novaDescricao, novoValor, TipoLancamento.ENTRADA);


        /* Aqui ele busca passando os valores novos pra certificar que está tudo  ok  */
        listaLancamentosPage.busca(novaDescricao);
        assertTrue(listaLancamentosPage.existeLancamento(novaDescricao, novoValor, dataHora,
                TipoLancamento.ENTRADA));
    }

    @AfterClass
    private void finaliza() {
        driver.quit();
    }

}


