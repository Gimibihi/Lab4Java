package Lab4Bijeikis;

import Lab4Bijeikis.Gui.Lab4Window;

import java.util.Locale;

public class GuiVykdymas {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US); // Suvienodiname skaičių formatus
        KompTestai.atvaizdzioTestas();
        Lab4Window.createAndShowGUI();
    }
}
