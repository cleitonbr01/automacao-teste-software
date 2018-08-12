package br.ucdb.pos.engenhariasoftware.testesoftware.automacao.selenium.webdriver;

import br.ucdb.pos.engenhariasoftware.testesoftware.automacao.selenium.webdriver.pageobject.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;

import static org.testng.Assert.*;

                /** Fluxo 5:  Acessar listagem, validar a exibição de mensagens de campos obrigatório
                 * para todos os campos, voltar para tela de listagem com botão Cancelar e clicar no
                 * botão recarregar.*/

public class FluxoValidaMensagensEBotaoCancelarTest {

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

        lancamentoPage.salva();

        assertTrue(lancamentoPage.contemMensagemErro("A descrição deve ser informada"));
        assertTrue(lancamentoPage.contemMensagemErro("O valor deve ser informado"));
        assertTrue(lancamentoPage.contemMensagemErro("A data deve ser informada"));
        assertTrue(lancamentoPage.contemMensagemErro("A categoria deve ser informada"));

        lancamentoPage.cancela();

        listaLancamentosPage.recarrega();

        assertTrue(listaLancamentosPage.isPageAtual());
    }

    @AfterClass
    private void finaliza() {
        driver.quit();
    }

}


