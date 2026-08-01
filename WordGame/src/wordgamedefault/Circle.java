package wordgamedefault;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
import javax.swing.JPanel;

public class Circle extends JPanel implements ActionListener{
	private int y = 150;
	private int velocity = 2;
	private final int DIAMETER = 60;
	private Timer timer;
	
	public Circle()
	{
		timer = new Timer(10, this);
		timer.start();
		this.setBackground(Color.pink);
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.GREEN);
		g.fillOval(125, y, DIAMETER,DIAMETER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		y += velocity;
		
		if (y <= 0 || y >= getHeight() - DIAMETER)
		{
			velocity = -velocity;
		}
		
		repaint();
	}
	
	
}
