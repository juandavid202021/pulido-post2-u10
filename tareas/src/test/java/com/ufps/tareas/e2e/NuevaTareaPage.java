package com.ufps.tareas.e2e;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NuevaTareaPage {

    private final WebDriver driver;
    private final By inputTitulo  = By.id("titulo");
    private final By btnGuardar   = By.id("btn-guardar");
    private final By heading      = By.cssSelector("h1");

    public NuevaTareaPage(WebDriver driver) {
        this.driver = driver;
    }

    public String obtenerEncabezado() {
        return driver.findElement(heading).getText();
    }

    public void ingresarTitulo(String titulo) {
        driver.findElement(inputTitulo).sendKeys(titulo);
    }

    public TareasPage guardar() {
        driver.findElement(btnGuardar).click();
        return new TareasPage(driver);
    }
}