package main;

import gui.Calculator;
public class MainClass {
	
	public static void main(String[] args) {
        
        javax.swing.SwingUtilities.invokeLater(() -> {
            new Calculator();
        });
    }
	

}
