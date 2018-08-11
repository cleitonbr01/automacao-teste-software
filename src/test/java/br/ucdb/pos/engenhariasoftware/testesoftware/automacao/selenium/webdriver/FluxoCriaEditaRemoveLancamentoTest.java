package br.ucdb.pos.engenhariasoftware.testesoftware.automacao.selenium.webdriver;

import br.ucdb.pos.engenhariasoftware.testesoftware.automacao.selenium.webdriver.pageobject.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;

import java.math.*;
import java.time.*;

import static org.testng.Assert.*;


                /**  Fluxo 3: Acessar listagem, criar novo lançamento, validar lançamento criado, editar
                 * lançamento, validar edição, remover lançamento e validar remoção;*/

public class FluxoCriaEditaRemoveLancamentoTest {

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
    public void criaEditaRemoveLancamento() {
        listaLancamentosPage.acessa();
        listaLancamentosPage.novoLancamento();

        LocalDateTime dataHora = LocalDateTime.now();

        final String descricaoLancamento = "Fluxo3 - Lançando " + dataHora.format(
                LancamentoUtil.getDateTimeFormatter());
        final BigDecimal valor = LancamentoUtil.geraValorAleatorio();
        lancamentoPage.cria(descricaoLancamento, valor, dataHora, TipoLancamento.SAIDA);

        listaLancamentosPage.busca(descricaoLancamento);
        assertTrue(listaLancamentosPage.existeLancamento(descricaoLancamento, valor, dataHora,
                TipoLancamento.SAIDA));

        listaLancamentosPage.busca(descricaoLancamento);
        listaLancamentosPage.editaLancamento(0);
        final String novaDescricao = descricaoLancamento + " editado";
        final BigDecimal novoValor = LancamentoUtil.geraValorAleatorio();

        lancamentoPage.edita(novaDescricao, novoValor, TipoLancamento.ENTRADA);

        listaLancamentosPage.busca(novaDescricao);
        assertTrue(listaLancamentosPage.existeLancamento(novaDescricao, novoValor, dataHora,
                TipoLancamento.ENTRADA));

           /* Faz a busca do lançamento e remove o lançamento na posição inicial da tabela  */
        listaLancamentosPage.busca(descricaoLancamento);
        listaLancamentosPage.removeLancamento(0);

           /* Faz a busca novamente e faz uma asserção falsa para garantir que não é verdade, ou seja,
            * que existem lançamentos com esses valores (garante que foi realmente removido o lançamento)*/

         listaLancamentosPage.busca(descricaoLancamento);
         assertFalse(listaLancamentosPage.existeLancamento(novaDescricao, novoValor, dataHora,
                TipoLancamento.ENTRADA));

    }

    @AfterClass
    private void finaliza() {
        driver.quit();
    }

}

