package berenyi_kft_GUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class EndGamePanel extends JPanel {
	
	private static final String wonString =
			"You won!";
	private static final String lostString =
			"You lost!";
	
	private Cards cards;
	private JTextArea resultField;
	private JButton backToMenuButton;
	private BufferedImage img;
	
	private class ButtonListener implements ActionListener {		
		public void actionPerformed(ActionEvent ae) {
			JButton pressedButton = (JButton)ae.getSource();
			if (pressedButton == backToMenuButton) {
				cards.show(Cards.menuPanelID);
			}
		}
	}
	
	public void showResult(boolean won) {
		resultField.setText(won ? wonString : lostString);
	}
	
	public void initComponents() {
		this.setLayout(new BorderLayout());
		Font font = new Font("teko semibold", Font.BOLD, 20);
		Font titleFont = new Font("teko semibold", Font.BOLD, 40);
		
		ButtonListener bl = new ButtonListener();
		
		Border buttonBorder = new LineBorder(Color.YELLOW, 3);
		
		Color color = new Color(9, 28, 87);
		
		JPanel buttonPanel=new JPanel();
		buttonPanel.setOpaque(true);
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setBackground(new Color(0, 0, 0, 0));
		buttonPanel.setOpaque(true);
		buttonPanel.setBackground(new Color(0, 0, 0, 0));
		this.add(buttonPanel, BorderLayout.SOUTH);
		
		String message="You won!";
		JLabel resultLabel=new JLabel(message);
		resultLabel.setFont(titleFont);
		resultLabel.setForeground(Color.YELLOW);
		this.add(resultLabel, BorderLayout.NORTH);
		resultLabel.setHorizontalAlignment(SwingConstants.CENTER);

		resultField = new JTextArea(7, 15);
		resultField.setEditable(false);
		resultField.setAlignmentX(CENTER_ALIGNMENT);
		resultField.setFont(font);
		resultField.setWrapStyleWord(true);
		
		backToMenuButton = new JButton("Back to Menu");
		backToMenuButton.setFont(font);
		backToMenuButton.addActionListener(bl);
		backToMenuButton.setForeground(Color.YELLOW);
		backToMenuButton.setBackground(color);
		backToMenuButton.setBorder(buttonBorder);
		buttonPanel.add(backToMenuButton);
		
		JPanel resultPanel = new JPanel();
		resultPanel.add(resultField);
		resultPanel.setOpaque(true);
		resultPanel.setBackground(new Color(0, 0, 0, 0));
		
		this.add(resultPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
		
		String path = "src\\berenyi_kft_GUI\\Icons\\start.png";
		try {
			img=ImageIO.read(new File(path));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
    public Dimension getPreferredSize()
    {
        return (new Dimension(img.getWidth(), img.getHeight()));
    }
	
	@Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this);
    }
	
	public EndGamePanel(Cards cards) {
		this.cards = cards;
		this.initComponents();
		this.setVisible(true);
	}
	
}
