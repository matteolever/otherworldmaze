package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;
import enums.CellEnum;

/**
 * the start View of the whole game.
 *
 */
public class StartView extends JPanel {
	
	private static final long serialVersionUID = -4810210219893519108L;
	private Controller controller;
	private JPanel createMazePanel;

	private JButton startButton;
	private JButton editButton;
	private JButton selectButton;
	
    private Color BG = new Color(34, 44, 77);

	public StartView(Controller controller) {
		this.controller = controller;
		initView();
	}

	public void initView() {
		
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(500,500));
		this.setBackground(Color.WHITE);
		this.setOpaque(true);

		JLabel nameLabel = new JLabel("The Ghost of Orsay");
		nameLabel.setFont(nameLabel.getFont().deriveFont(Font.BOLD, 20f));
		nameLabel.setBorder(BorderFactory.createEmptyBorder(100,0,50,0));
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		
		
		ImageIcon icon = new ImageIcon(CellEnum.PLAYER.getSrc());
		Image img = icon.getImage() ;
		Image newimg = img.getScaledInstance( 200,200, java.awt.Image.SCALE_SMOOTH ) ;
		icon = new ImageIcon(newimg);

		nameLabel.setIcon(icon);
		//label.setBounds(0, 0, CellView.CELLSIZE.width, CellView.CELLSIZE.height + 10);
		//label.setPreferredSize(new Dimension(CellView.CELLSIZE.width, CellView.CELLSIZE.height + 10));
		
		this.startButton = new JButton("Start Game");
		this.startButton.addMouseListener(this.startButtonListener);
		this.startButton.setFont(new Font("Sans Serif", Font.PLAIN, 16));
		this.startButton.setHorizontalAlignment(JButton.CENTER);
		this.startButton.setVerticalAlignment(JButton.CENTER);
		this.startButton.setBorder(BorderFactory.createEmptyBorder(50,0,50,0));
		this.startButton.setForeground(BG);
		this.startButton.setOpaque(false);
		this.startButton.setContentAreaFilled(false);
		this.startButton.setBorderPainted(false);

		this.add(nameLabel, BorderLayout.NORTH);
		this.add(this.startButton, BorderLayout.CENTER);
		
		this.createMazePanel = new JPanel();
		this.createMazePanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		this.createMazePanel.setLayout(new GridLayout(1,0));
		this.createMazePanel.setOpaque(false);
		
		this.editButton = new JButton("Create Game Board");
		this.editButton.addMouseListener(this.createButtonListener);
		this.editButton.setFont(new Font("Sans Serif", Font.PLAIN, 12));
		this.editButton.setHorizontalAlignment(JButton.CENTER);
		this.editButton.setVerticalAlignment(JButton.CENTER);
		this.editButton.setForeground(BG);
		this.editButton.setOpaque(false);
		this.editButton.setContentAreaFilled(false);
		this.editButton.setBorderPainted(false);
		
		this.selectButton = new JButton("Select a Maze");
		this.selectButton.addMouseListener(this.selectButtonListener);
		this.selectButton.setFont(new Font("Sans Serif", Font.PLAIN, 12));
		this.selectButton.setHorizontalAlignment(JButton.CENTER);
		this.selectButton.setVerticalAlignment(JButton.CENTER);
		this.selectButton.setForeground(BG);
		this.selectButton.setOpaque(false);
		this.selectButton.setContentAreaFilled(false);
		this.selectButton.setBorderPainted(false);

		this.createMazePanel.add(this.editButton);
		this.createMazePanel.add(this.selectButton);
		this.add(this.createMazePanel, BorderLayout.SOUTH);

	}

	private MouseAdapter startButtonListener = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			StartView.this.controller.startMaze(null);
		}
	};

	private MouseAdapter createButtonListener = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			StartView.this.controller.startEdit();
		}
	};
	
	private MouseAdapter selectButtonListener = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			StartView.this.controller.startSelect();
		}
	};

	public JButton getStartButton() {
		return this.startButton;
	}

	public JButton getEditButton() {
		return this.editButton;
	}

}
