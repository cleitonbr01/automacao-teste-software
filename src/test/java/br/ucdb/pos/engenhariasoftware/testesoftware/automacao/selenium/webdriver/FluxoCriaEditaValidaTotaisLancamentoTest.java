package br.ucdb.pos.engenhariasoftware.testesoftware.automacao.selenium.webdriver;

import br.ucdb.pos.engenhariasoftware.testesoftware.automacao.selenium.webdriver.pageobject.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;

import java.math.*;
import java.time.*;

import static org.testng.Assert.*;

                /** Fluxo 4: Acessar  listagem, criar novo lançamento, validar lançamento criado, validar
                 *totais de saída e entrada e acessar relatórios;*/

public class FluxoCriaEditaValidaTotaisLancamentoTest {

    private WebDriver driver;

    private ListaLancamentosPage listaLancamentosPage;

    private LancamentoPage lancamentoPage;

            /* Essa classe relatoriosPage foi adicionada ao projeto para fazer a validação do relatório */
    private RelatoriosPage relatoriosPage;

    @BeforeClass
    private void inicialliza() {
        driver = new WebdriverTestBuilder().initialize().getDriver();
        listaLancamentosPage = new ListaLancamentosPage(driver);
        lancamentoPage = new LancamentoPage(driver);
        relatoriosPage = new RelatoriosPage(driver);
    }

    @Test
    public void criaEditaLancamento() {
        listaLancamentosPage.acessa();
        listaLancamentosPage.novoLancamento();

        LocalDateTime dataHora = LocalDateTime.now();

        final String descricaoLancamento = "Fluxo4 - Lançando " + dataHora.format(
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

        listaLancamentosPage.busca(novaDescricao);

             /* Aqui estou buscando somente por um lançamento de entrada.
              * A saída tem que ser zero, sendo uma asserção que está sendo feita*/
        assertTrue(listaLancamentosPage.validaTotalEntrada(novoValor));
        assertTrue(listaLancamentosPage.validaTotalSaida(BigDecimal.ZERO));

              /* Método que abre o relatório */
        listaLancamentosPage.abreRelatorios();

              /* Verifica que a página de relatório é atual, garantindo que ela existe */
        assertTrue(relatoriosPage.isPageAtual());

    }

    @AfterClass
    private void finaliza() {
        driver.quit();
    }

}


