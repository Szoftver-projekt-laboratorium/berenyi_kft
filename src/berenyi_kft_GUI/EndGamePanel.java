package berenyi_kft_GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import berenyi_kft.State;

public class EndGamePanel extends JPanel {
	
	private static final String wonString =
			"You won by building the spacebase! Congratulations!";
	private static final String lostString =
			"You lost the game, try again!";
	
	private static final int endImagePreferredWidth = 500;
	
	private Cards cards;
	//private JTextArea resultField;
	private JButton backToMenuButton;
	// private Image img;
	private Image endImg;
	private JPanel resultPanel;
	private JLabel resultLabel;
	
	private class ButtonListener implements ActionListener {		
		public void actionPerformed(ActionEvent ae) {
			JButton pressedButton = (JButton)ae.getSource();
			if (pressedButton == backToMenuButton) {
				cards.show(Cards.menuPanelID);
			}
		}
	}
	
	public String getResultText() {
		if(cards.getGamePanel().getController().getState()==State.LOST)
			return lostString;
		return wonString;
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
		resultLabel=new JLabel(message);
		resultLabel.setFont(titleFont);
		resultLabel.setForeground(Color.YELLOW);
		this.add(resultLabel, BorderLayout.NORTH);
		resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		/*resultField = new JTextArea(7, 15);
		resultField.setEditable(false);
		resultField.setAlignmentX(CENTER_ALIGNMENT);
		resultField.setFont(font);
		resultField.setWrapStyleWord(true);*/
		
		backToMenuButton = new JButton("Back to Menu");
		backToMenuButton.setFont(font);
		backToMenuButton.addActionListener(bl);
		backToMenuButton.setForeground(Color.YELLOW);
		backToMenuButton.setBackground(color);
		backToMenuButton.setBorder(buttonBorder);
		buttonPanel.add(backToMenuButton);
		
		resultPanel = new JPanel();
		//resultPanel.setOpaque(true);
		resultPanel.setBackground(new Color(0, 0, 0, 0));
		
		this.add(resultPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
		
		/*String path = "src\\berenyi_kft_GUI\\Icons\\start_16_9.png";
		try {
			img=ImageIO.read(new File(path));
		} catch(Exception e) {
			e.printStackTrace();
		}*/
	}
	
	/*@Override
    public Dimension getPreferredSize() {
        return (new Dimension(img.getWidth(null), img.getHeight(null)));
    }*/
	
	@Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(Cards.bgImage, 0, 0, this);
        
        if(cards.getGamePanel().getController().getState()==State.LOST) {
        	String path = "src\\berenyi_kft_GUI\\Icons\\gameFailed.png";
    		try {
    			endImg=ImageIO.read(new File(path));
    			endImg = endImg.getScaledInstance(
    					endImagePreferredWidth, -1, Image.SCALE_DEFAULT);
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
		}
        
        else {
        	String path2 = "src\\berenyi_kft_GUI\\Icons\\gameWon.png";
    		try {
    			endImg=ImageIO.read(new File(path2));
    			endImg = endImg.getScaledInstance(
    					endImagePreferredWidth, -1, Image.SCALE_DEFAULT);
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
        }
        g.drawImage(endImg, 400, 100, resultPanel);
        resultLabel.setText(this.getResultText());
    }
	
	public EndGamePanel(Cards cards) {
		this.cards = cards;
		this.initComponents();
		this.setOpaque(false);
		this.setVisible(true);
	}
	
}
