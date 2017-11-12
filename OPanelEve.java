import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

class OPanelEve extends MouseAdapter {

    int number;
    OthelloPanel panel;
    
    OPanelEve(int i, OthelloPanel p) {
	number = i;
	panel = p;
    }
    public  void mouseClicked(MouseEvent e) {
	panel.clickedPanel();
    }
}
