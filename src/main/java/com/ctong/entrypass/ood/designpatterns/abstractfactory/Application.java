package com.ctong.entrypass.ood.designpatterns.abstractfactory;

import com.ctong.entrypass.ood.designpatterns.abstractfactory.factories.GuiFactory;
import com.ctong.entrypass.ood.designpatterns.abstractfactory.products.checkboxes.Checkbox;
import com.ctong.entrypass.ood.designpatterns.abstractfactory.products.buttons.Button;

/**
 * Provider
 * Application picks the factory type and creates it in run time (usually at
 * initialization stage), depending on the configuration or environment
 * variables.
 */
public class Application {

    private final Button button;
    private final Checkbox checkbox;

    public Application(final GuiFactory guiFactory) {
        this.button = guiFactory.createButton();
        this.checkbox = guiFactory.createCheckbox();
    }

    public void paint() {
        button.paint();
        checkbox.paint();
    }
}
