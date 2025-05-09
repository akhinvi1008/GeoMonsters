//GeoMonsters.java
//Ritik Shenoy and Anish Khinvasara
//P7 2025


import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Image;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;

import javax.swing.JFrame; 
import javax.swing.JPanel;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBox;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class GeoMonsters extends JFrame
//holds the cards
{
    private CardLayout cards;

    public GeoMonsters()
    {
        super("GeoMonsters");  //made this thing it's own frame
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(500,500);

        cards = new CardLayout();
        setLayout(cards);
        
        HomePanel homePanel = new HomePanel(this, cards);
        GamePanel gamePanel = new GamePanel(this, cards);
        InstructionsPanel instructionsPanel = new InstructionsPanel(this, cards);
        CreditsPanel creditsPanel = new CreditsPanel(this, cards);
        TeamPanel teamPanel = new TeamPanel(this,cards);

        add(homePanel, "Home");
        add(gamePanel, "Game");
        add(instructionsPanel, "Instructions");
        add(creditsPanel, "Credits");
        add(teamPanel, "Team");

        setVisible(true);
    }
    
    public JButton createHomeButton()
    {
        // Create the home button
        Color red = new Color(255, 102, 102);

        JButton button = new JButton("Home");
        button.setFont(new Font("SansSerif", Font.BOLD, 18));
        button.setBackground(red);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(120, 40));

        return button;
    }
    
    public static void main(String[] args)
    {
        new GeoMonsters();
    }
}

class HomePanel extends JPanel implements ActionListener
{
    private Image globeImage;
    private CardLayout cards;
    private GeoMonsters geoFrame;
    
    public HomePanel(GeoMonsters frame, CardLayout cardLayout)
    {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        Color red = new Color(255, 102, 102);
		
		cards = cardLayout;
		geoFrame = frame;
		
        //NORTH: Title and My Team Icon
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BorderLayout());
        northPanel.setBackground(Color.WHITE);
        

        JLabel titleLabel = new JLabel("GeoMonsters", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 48)); //serif lowkey looking a bit weird we can change this
        titleLabel.setForeground(Color.BLACK);
        northPanel.add(titleLabel, BorderLayout.CENTER);
		
        //JPanel topLeftPanel = new JPanel();
        //topLeftPanel.setOpaque(false); 
        //topLeftPanel.setLayout(new GridLayout(5, 1));
        JButton myTeamButton = new JButton("");
        myTeamButton.setPreferredSize(new Dimension(80, 80));  //square
        
        //load the image for the button background 
        //credits to StackOverflow, but more credits will be in the credits place

        String teamIconName = "ButtonBackground.jpg";
        try
        {
            Image img = ImageIO.read(new File(teamIconName));
            Image scaledImg = img.getScaledInstance(68, 68, Image.SCALE_SMOOTH);
            myTeamButton.setIcon(new ImageIcon(scaledImg));  // Set the image as the button icon
        }
        catch (IOException e)
        {
            e.printStackTrace();  
            System.err.println("Error opening file: " + teamIconName);
        }
        
        
        //make it less ugly
        myTeamButton.setFocusPainted(false);
        myTeamButton.setFont(new Font("SansSerif", Font.BOLD, 15));
        myTeamButton.addActionListener(this);
        myTeamButton.setBackground(red);
        //myTeamButton.setForeground(Color.BLACK);
        //topLeftPanel.add(myTeamButton);
        northPanel.add(myTeamButton, BorderLayout.EAST);
        

        add(northPanel, BorderLayout.NORTH);
       
        
        //load the image for the center background
        String globeName = "Globe.jpg";
		try
        {
			//load image
            globeImage = ImageIO.read(new File(globeName));  
        }
        catch (IOException e)
        {
            e.printStackTrace();  
            System.err.println("Error opening file: " + globeName);
        }

        //SOUTH: Buttons Panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setOpaque(false);

		//play button
		JButton playButton = new JButton("PLAY");
		playButton.setFont(new Font("SansSerif", Font.BOLD, 28));
		playButton.setBackground(red);
		playButton.setForeground(Color.BLACK);
		playButton.setFocusPainted(false);
		playButton.setPreferredSize(new Dimension(200, 60));
		playButton.addActionListener(this);  // Replaced lambda with this

		JPanel playPanel = new JPanel();
		playPanel.setOpaque(false);
		playPanel.add(playButton);
		bottomPanel.add(playPanel, BorderLayout.NORTH);

		//Instructions/Credits buttons
		JButton instructionsButton = new JButton("Instructions");
		JButton creditsButton = new JButton("Credits");

		instructionsButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
		creditsButton.setFont(new Font("SansSerif", Font.PLAIN, 18));

		instructionsButton.setBackground(red);
		creditsButton.setBackground(red);
		instructionsButton.setForeground(Color.BLACK);
		creditsButton.setForeground(Color.BLACK);

		instructionsButton.setPreferredSize(new Dimension(140, 40));
		creditsButton.setPreferredSize(new Dimension(140, 40));

		instructionsButton.addActionListener(this);
		creditsButton.addActionListener(this);  
		
		
		//panel for them
		JPanel subButtonsPanel = new JPanel();
		subButtonsPanel.setOpaque(false);
		subButtonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 10));
		subButtonsPanel.add(instructionsButton);
		subButtonsPanel.add(creditsButton);

		bottomPanel.add(subButtonsPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		String command = e.getActionCommand();
		
		
		//need getContentPane because it's a JFrame not a JPanel
		if (command.equals("PLAY")) 
		{
			//Switch to the "Game" card
			cards.show(geoFrame.getContentPane(), "Game");
		} 
		else if (command.equals("Instructions")) 
		{
			//Switch to the "Instructions" card
			cards.show(geoFrame.getContentPane(), "Instructions");
		} 
		else if (command.equals("Credits")) 
		{
			//Switch to the "Credits" card
			cards.show(geoFrame.getContentPane(), "Credits");
		}
		else if (command.equals(""))  //team one just has a picture
		{
			//Switch to the "Team" card
			cards.show(geoFrame.getContentPane(), "Team");
		}
	}

    
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        if (globeImage != null)
        {
			int newWidth = (int)(globeImage.getWidth(this)*0.64);
			int newHeight = (int)(globeImage.getHeight(this)*0.64);
            //image was too big
           
			//center image
            int x = (getWidth() - newWidth) / 2;
            int y = (getHeight() - newHeight) / 2;
            g.drawImage(globeImage, x, y, newWidth, newHeight, this); 
        }
    }
}

class GamePanel extends JPanel implements ActionListener
{
    private CardLayout cards;
    private GeoMonsters geoFrame;
   
    public GamePanel(GeoMonsters frame, CardLayout cardLayout)
    {
        setLayout(new BorderLayout());
		
		
		cards = cardLayout;
		geoFrame = frame;
		
        //Placeholder for right now
        JLabel label = new JLabel("This is the Game Panel", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 24));
        add(label, BorderLayout.CENTER);

        //home button
        JButton homeButton = geoFrame.createHomeButton();
        
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        bottomPanel.setOpaque(false);
        bottomPanel.add(homeButton);
        add(bottomPanel, BorderLayout.SOUTH);

        homeButton.addActionListener(this);
        
    }
    
    public void actionPerformed(ActionEvent e)
    {
		cards.show(geoFrame.getContentPane(), "Home");
	}
}

class InstructionsPanel extends JPanel implements ActionListener
{
    private CardLayout cards;
    private GeoMonsters geoFrame;
   
    public InstructionsPanel(GeoMonsters frame, CardLayout cardLayout)
    {
        setLayout(new BorderLayout());
		
		
		cards = cardLayout;
		geoFrame = frame;
		
		//JTextArea for the instructions
		JTextArea instructionsTextArea = new JTextArea();
		instructionsTextArea.setText("Instructions\n---we can write here ---\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		instructionsTextArea.setFont(new Font("SansSerif", Font.PLAIN, 18));
		instructionsTextArea.setEditable(false);
		instructionsTextArea.setLineWrap(true);
		JScrollPane scrollPane = new JScrollPane(instructionsTextArea);
		scrollPane.setPreferredSize(new Dimension(600, 400));  //set a preferred size for the scroll panel
		
		add(scrollPane, BorderLayout.CENTER);
		
        // Home button
        JButton homeButton = geoFrame.createHomeButton();
        
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        bottomPanel.setOpaque(false);
        bottomPanel.add(homeButton);
        add(bottomPanel, BorderLayout.SOUTH);

        homeButton.addActionListener(this);
        
    }
    
    public void actionPerformed(ActionEvent e)
    {
		cards.show(geoFrame.getContentPane(), "Home");
	}
}


class CreditsPanel extends JPanel implements ActionListener
{
    private CardLayout cards;
    private GeoMonsters geoFrame;G
   
    public CreditsPanel(GeoMonsters frame, CardLayout cardLayout)
    {
        setLayout(new BorderLayout());
		
		
		cards = cardLayout;
		geoFrame = frame;
		
		//JTextArea for the credits
		JTextArea creditsTextArea = new JTextArea();
		creditsTextArea.setText("Credits\n\nStackOverflow - adding ImageIcon to JButton, setOpaque\nOracle - SwingConstants\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		creditsTextArea.setFont(new Font("SansSerif", Font.PLAIN, 18));
		creditsTextArea.setEditable(false);
		creditsTextArea.setLineWrap(true);
		JScrollPane scrollPane = new JScrollPane(creditsTextArea);
		scrollPane.setPreferredSize(new Dimension(600, 400));  //set a preferred size for the scroll panel

		add(scrollPane, BorderLayout.CENTER);

        // Home button
        JButton homeButton = geoFrame.createHomeButton();
        
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        bottomPanel.setOpaque(false);
        bottomPanel.add(homeButton);
        add(bottomPanel, BorderLayout.SOUTH);

        homeButton.addActionListener(this);
        
    }
    
    public void actionPerformed(ActionEvent e)
    {
		cards.show(geoFrame.getContentPane(), "Home");
	}
}

class TeamPanel extends JPanel implements ActionListener
{
    private CardLayout cards;
    private GeoMonsters geoFrame;
   
    public TeamPanel(GeoMonsters frame, CardLayout cardLayout)
    {
        setLayout(new BorderLayout());
		
		
		cards = cardLayout;
		geoFrame = frame;
		
        //Placeholder for right now
        JLabel label = new JLabel("This is the Team Panel", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 24));
        add(label, BorderLayout.CENTER);

        // Home button
        JButton homeButton = geoFrame.createHomeButton();
        
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        bottomPanel.setOpaque(false);
        bottomPanel.add(homeButton);
        add(bottomPanel, BorderLayout.SOUTH);

        homeButton.addActionListener(this);
        
    }
    
    public void actionPerformed(ActionEvent e)
    {
		cards.show(geoFrame.getContentPane(), "Home");
	}
}