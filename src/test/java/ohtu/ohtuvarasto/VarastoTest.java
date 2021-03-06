package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ylitäyttöEiOnnistu() {
        varasto.lisaaVarastoon(11);
        assertEquals(0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    @Test
    public void otetaanLiikaa() {
        varasto.lisaaVarastoon(8);
        varasto.otaVarastosta(9);
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    @Test
    public void lisataanNegatiivinen() {
        varasto.lisaaVarastoon(-2);
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    @Test
    public void otetaanNegatiivinen() {
        varasto.lisaaVarastoon(1);
        varasto.otaVarastosta(-1);
        assertEquals(9, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    @Test
    public void tehdäänNegatiivinen() {
        Varasto varasto2 = new Varasto(-1);
        assertEquals(0, varasto2.paljonkoMahtuu(), vertailuTarkkuus);
    }
    @Test
    public void tehdäänNegatiivinen2() {
        Varasto varasto2 = new Varasto(-1, -1);
        assertEquals(0, varasto2.paljonkoMahtuu(), vertailuTarkkuus);
    }
    @Test
    public void tehdäänNegatiivinen3() {
        Varasto varasto2 = new Varasto(-1, -2);
        assertEquals(0, varasto2.paljonkoMahtuu(), vertailuTarkkuus);
    }
    @Test
    public void alkusaldoNegatiivinen() {
        Varasto varasto2 = new Varasto(5, -5);
        assertEquals(5, varasto2.paljonkoMahtuu(), vertailuTarkkuus);
    }
    @Test
    public void alkusaldoLiianSuuri() {
        Varasto varasto2 = new Varasto(5, 10);
        assertEquals(0, varasto2.paljonkoMahtuu(), vertailuTarkkuus);
    }
    @Test
    public void alkusaldoSopiva() {
        Varasto varasto2 = new Varasto(10, 5);
        assertEquals(5, varasto2.paljonkoMahtuu(), vertailuTarkkuus);
    }
    @Test
    public void tulostusOk() {
        varasto.lisaaVarastoon(5);
        assertEquals(true, varasto.toString().equals("saldo = 5.0, vielä tilaa 5.0"));
    }

}
