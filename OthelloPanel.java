import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.*;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;


public class OthelloPanel extends JPanel {
    Gamen gamen;
    int panelNum;
    
    OthelloPanel(int i, Gamen g){
	addMouseListener(new OPanelEve(i, this));
	gamen = g;
	panelNum = i;
    }
    
    public void paintComponent(Graphics g){
	Graphics2D g2 = (Graphics2D)g;

	g2.setBackground(Color.GREEN);
        g2.clearRect(0, 0, getWidth()-1, getHeight()-1);
    }

    public void toBlack() {
	Graphics g = this.getGraphics();
	g.setColor(Color.BLACK);
	g.fillOval(5, 5, 65, 65);
    }

    public void toWhite() {
	Graphics g = this.getGraphics();
	g.setColor(Color.WHITE);
	g.fillOval(5, 5, 65, 65);
    }

    public void toBlank() {
	Graphics2D g = (Graphics2D)this.getGraphics();
	g.setBackground(Color.GREEN);
        g.clearRect(0, 0, getWidth()-1, getHeight()-1);
    }

    public void clickedPanel() {
	gamen.clicked(panelNum);
    }
}
