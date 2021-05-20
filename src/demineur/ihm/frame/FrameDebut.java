package demineur.ihm.frame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import demineur.ihm.Difficulte;
import demineur.ihm.frame.component.OptionSliders;
import demineur.ihm.frame.component.Settings;

public class FrameDebut extends JFrame 
{
	
	public static final Color DARK6 = new Color(24, 24, 64);
	public static final Color DARK5 = new Color(44, 44, 84);
	public static final Color DARK4 = new Color(64, 64, 122);
	public static final Color DARK3 = new Color(71, 71, 135);
	public static final Color DARK3B = new Color(91,91,155);
	public static final Color DARK2 = new Color(142, 141, 251);
	public static final Color DARK1 = new Color(34, 112, 147);
	public static final Color WHITE = new Color(190,190,190);
	
	private boolean isInOption;

	private JSlider volume;
	private JSlider longueur;
	private JSlider hauteur;
	private JSlider time;
	
	private CardLayout card;
	
	private Icon iconOptionHover = new ImageIcon(FrameDebut.class.getResource("/demineur/assets/img/options2.png"));
	private Icon iconOption = new ImageIcon(FrameDebut.class.getResource("/demineur/assets/img/options.png"));
	private JLabel option;
	
	private JPanel centerMenu;
	private JPanel menuOption;
	private JPanel menuContent;
	
	private DefaultListModel<String> listModel ;
	private JList<String>            liste ;
	private JPanel contentPane;
	
	private Font font;
	
	
	/** Constructeur permettant la création des différentes fenêtre du menu principal
	 * 
	 */
	public FrameDebut()
	{
		super("Démineur - Menu");
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.isInOption = false;
		
		this.font = new Font(Font.SANS_SERIF, 15, 100);
		this.font = this.font.deriveFont(30f);
		
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0,0));
		this.setContentPane(contentPane);
		
		this.setPreferredSize(new Dimension(310,400));
		
		/* Panel Nord */
		JPanel nord = new JPanel();
		nord.setBackground(DARK6);
		nord.setPreferredSize(new Dimension(600,60));
		JLabel dem = new JLabel("Jeu du Démineur",JLabel.CENTER);
		dem.setFont(font);
		dem.setForeground(Color.WHITE);
		nord.add(dem);
		
		/* Panel West */
		JPanel west = new JPanel();
		west.setLayout(new BorderLayout(0,0));
		
		BufferedImage img;

			JPanel leftSide = new JPanel();
			leftSide.setBackground(DARK5);
			leftSide.setLayout(new GridLayout(3,1));
			west.add(leftSide);
	        option = new JLabel("",JLabel.CENTER);
	        
	        option.setIcon(this.iconOption);
	        leftSide.add(option,BorderLayout.NORTH);
	        
	        option.addMouseListener(new MouseListener() {
				@Override
				public void mousePressed(MouseEvent e) 
				{
					option();
				}
				
				@Override
				public void mouseExited(MouseEvent e) 
				{
					option.setIcon(iconOption);
				}
				
				@Override
				public void mouseEntered(MouseEvent e) 
				{
					option.setIcon(iconOptionHover);
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {}
				@Override
				public void mouseReleased(MouseEvent e) {}
			});


	        
	        
		west.setBackground(DARK5);
		west.setPreferredSize(new Dimension(60,600));
		
		/* Panel Center */
		JPanel center = new JPanel();
		center.setLayout(new BorderLayout(0,0));
		center.setBackground(DARK4);
		
		this.centerMenu = new JPanel();
		card = new CardLayout();
		this.centerMenu.setLayout(card);
		
			//Center menu
			menuContent = new JPanel();
			menuContent.setLayout(new BorderLayout(0,0));
			
			this.listModel = new DefaultListModel<String>();
			ArrayList<String> difs = new ArrayList<String>(Arrays.asList(Difficulte.DEBUTANT.getLabel(),Difficulte.AMATEUR.getLabel(),Difficulte.MOYEN.getLabel(),Difficulte.HABILE.getLabel(),Difficulte.EXPERT.getLabel()));
			for(String dif : difs ) {
				this.listModel.addElement(dif);
			}
			this.liste     = new JList<String>(listModel);
			this.liste.setSelectedIndex(0);
			this.liste.setForeground(Color.WHITE);
			//this.liste.setBackground( Color.CYAN );
			this.liste.setFont(this.font);
			this.liste.setBackground(DARK3);
		
			DefaultListCellRenderer renderer =  (DefaultListCellRenderer)liste.getCellRenderer();  
			renderer.setHorizontalAlignment(JLabel.CENTER);
			
			menuContent.add(liste,BorderLayout.CENTER);
			
			//Title
			JTextField title = new JTextField("Choix du Niveau");
		
			title.setEditable(false);
			title.setForeground(WHITE);
			title.setBackground(DARK3);
			title.setBorder(BorderFactory.createEmptyBorder());
			title.setFont(this.font);
			menuContent.add(title,BorderLayout.NORTH);
			
			//Bouton
			JButton jouer = new JButton("Jouer !");
			jouer.setBackground(DARK3B);
			jouer.setFont(this.font);
			jouer.setForeground(Color.WHITE);
			jouer.setBorder(BorderFactory.createRaisedSoftBevelBorder());
			jouer.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println(liste);
					FrameJeu fj = new FrameJeu((Difficulte) Difficulte.fromString((String)liste.getSelectedValue()),Settings.getHauteurOption(),Settings.getLargeurOption(),Settings.getTimeConstraint());
					fj.setVisible(true);
					dispose();
				}
			});
			menuContent.add(jouer,BorderLayout.SOUTH);

		centerMenu.add("1",menuContent);
		
		
			JSlider[] sliders = new JSlider[3];
			
			//Center menu
			menuOption = new JPanel();
			menuOption.setLayout(new GridLayout(5,1));
			menuOption.setBackground(DARK3);
			JLabel optionTitle = new JLabel("Options",JLabel.CENTER);
			optionTitle.setForeground(WHITE);
			optionTitle.setBackground(DARK3);
			optionTitle.setBorder(BorderFactory.createEmptyBorder());
			optionTitle.setFont(this.font);
			menuOption.add(optionTitle);
			
			volume = OptionSliders.getVolumeSlider();
			longueur = OptionSliders.getHauteurSlider();
			hauteur = OptionSliders.getLargeurSlider();
			time = OptionSliders.getTimeSlider();
			
			menuOption.add(volume);
			menuOption.add(longueur);
			menuOption.add(hauteur);
			menuOption.add(time);
			
			
		centerMenu.add("2",menuOption);
		centerMenu.setBackground(DARK3);
		
		center.add(centerMenu,BorderLayout.WEST);
		//------------------
		
		contentPane.add(nord,BorderLayout.NORTH);
		contentPane.add(west,BorderLayout.WEST);
		contentPane.add(center,BorderLayout.CENTER);
		this.pack();
	}
	
	/** Permet de passer dans les options / retourner sur la vue menu
	 * Sauvegarde lors d'un passage des options -> menu
	 * Charge lors d'un passage menu -> option
	 */
	public void option()
	{
		if(this.isInOption)
		{
			Settings.saveOption(this.volume.getValue(), this.longueur.getValue(), this.hauteur.getValue(),this.time.getValue());
		} else {
			try {
				this.volume.setValue(Settings.getVolumeOption());
				this.longueur.setValue(Settings.getLargeurOption());
				this.hauteur.setValue(Settings.getHauteurOption());
				this.time.setValue(Settings.getTimeConstraint());
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		card.next(this.centerMenu);
		this.isInOption = !this.isInOption;
	}
}
