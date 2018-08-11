package br.ucdb.pos.engenhariasoftware.testesoftware.automacao.selenium.webdriver.pageobject;

import java.math.*;
import java.time.format.*;


public class LancamentoUtil {


        /**
         * Isolei o Gera Valor aqui para poder ser chamado nas classes, de forma obter melhor visualização */
    public static BigDecimal geraValorAleatorio() {

        boolean aplicaVariante = (System.currentTimeMillis() % 3) == 0;
        int fator = 10;
        long mim = 30;
        long max = 900;
        if (aplicaVariante) {
            mim /= fator;
            max /= fator;
        }
        return new BigDecimal((1 + (Math.random() * (max - mim)))).setScale(2,
                RoundingMode.HALF_DOWN);
    }

        /** Este método transforma basicamente data em texto.
         * Além disso, adicionei o segundo (:ss) para garantir que esse lançamento fosse único dentro da listagem*/

    public static DateTimeFormatter getDateTimeFormatter() {
        return DateTimeFormatter.ofPattern("dd.MM.yy HH:mm:ss");
    }
}
