package main;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Create a Swing app using Spring to create the Components.
 * Not because it's useful, but just because you can.
 * Goes far beyond: also demonstrates AppContext's methods for I18N.
 */
public class SpringIntl {

	public static void main(String[] args) {
		ApplicationContext factory = 
			new ClassPathXmlApplicationContext("beans.xml");
		
		final JFrame jf = new JFrame("SpringIntl");
		jf.setSize(500, 300);
		jf.setLayout(new FlowLayout());
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final String labelText = factory.getMessage("quitLabel.text", null, null);
		final JLabel quitLabel = new JLabel(labelText);
		jf.add(quitLabel);
		
		final String quitButtonText = factory.getMessage("quitButton.text", null, "Quit", null);
		final JButton quitButton = new JButton(quitButtonText);
		jf.add(quitButton);
		
		jf.setVisible(true); // Can't do this with DI to avoid "flashing"
	}
}