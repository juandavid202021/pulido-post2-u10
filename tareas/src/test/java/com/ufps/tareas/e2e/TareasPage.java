package com.ufps.tareas.e2e;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.List;

public class TareasPage {

    private final WebDriver driver;
    private final By btnNueva   = By.id("btn-nueva");
    private final By listItems  = By.cssSelector(".tarea-item");
    private final By heading    = By.cssSelector("h1");

    public TareasPage(WebDriver driver) {
        this.driver = driver;
    }

    public int contarTareas() {
        return driver.findElements(listItems).size();
    }

    public String obtenerTituloPagina() {
        return driver.getTitle();
    }

    public String obtenerEncabezado() {
        return driver.findElement(heading).getText();
    }

    public NuevaTareaPage irANuevaTarea() {
        driver.findElement(btnNueva).click();
        return new NuevaTareaPage(driver);
    }
}