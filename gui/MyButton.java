package gui;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;


public class MyButton extends JButton implements ActionListener {
	private static final long serialVersionUID = 1L;
	MyButton (String label,Color forground,Color background){
		super(label);
		this.setName(label);
		this.setBackground(background);
	
	this.setOpaque(true);
	this.setBorderPainted(false);
	this.setBorder (BorderFactory.createLineBorder(Color.blue));
	this.setFont(new Font("Courier",Font.PLAIN,18));
	
	this.setForeground(forground);
	this.setCursor(new Cursor(Cursor.HAND_CURSOR));
	
	addActionListener(this);
	}
	
	
	



@Override
public void actionPerformed(ActionEvent ev) {
	JButton btn = (JButton) ev.getSource();
	if(btn.getName().equals("="))Calculator.initiateConversion();
	else if (btn.getName().equals("C"))Calculator.clearTextField();
	else Calculator.appendToTextField(btn.getName());
	}

}



