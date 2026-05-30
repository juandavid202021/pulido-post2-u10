package com.ufps.tareas.e2e;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class TareasE2ETest {

    private WebDriver driver;
    private TareasPage tareasPage;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions opts = new ChromeOptions();
        opts.addArguments("--headless", "--no-sandbox",
                "--disable-dev-shm-usage");
        driver = new ChromeDriver(opts);
        driver.get("http://localhost:8080/tareas");
        tareasPage = new TareasPage(driver);
    }

    @Test
    void paginaTareas_tituloContieneTareas() {
        assertThat(tareasPage.obtenerTituloPagina())
                .containsIgnoringCase("Tareas");
    }

    @Test
    void paginaTareas_encabezadoVisible() {
        assertThat(tareasPage.obtenerEncabezado())
                .isEqualTo("Gestión de Tareas");
    }

    @Test
    void btnNueva_navegaAFormulario() {
        NuevaTareaPage nuevaPage = tareasPage.irANuevaTarea();
        assertThat(nuevaPage.obtenerEncabezado())
                .isEqualTo("Nueva Tarea");
    }

    @Test
    void crearTarea_aparaceEnLista() {
        NuevaTareaPage nuevaPage = tareasPage.irANuevaTarea();
        nuevaPage.ingresarTitulo("Tarea Selenium");
        TareasPage resultado = nuevaPage.guardar();
        assertThat(resultado.contarTareas()).isGreaterThan(0);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }
}