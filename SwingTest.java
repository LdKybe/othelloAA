import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class SwingTest extends JFrame{

  protected JList list;
  protected DefaultListModel model;

  public static void main(String[] args){
    SwingTest test = new SwingTest("SwingTest");

    test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    test.setVisible(true);
  }

  SwingTest(String title){
    setTitle(title);
    setBounds( 10, 10, 300, 200);

    model = new DefaultListModel();
    list = new JList(model);

    JScrollPane sp = new JScrollPane();
    sp.getViewport().setView(list);
    sp.setPreferredSize(new Dimension(200, 80));

    JButton btn = new JButton("コンポーネント");
    btn.addMouseListener(new myListener());

    JPanel p = new JPanel();
    p.add(btn);
    p.add(sp);

    getContentPane().add(p, BorderLayout.CENTER);
  }

  public class myListener extends MouseAdapter{
    public void mouseClicked(MouseEvent e){
      model.addElement("mouseClicked" + e.getClickCount());
      list.ensureIndexIsVisible(model.getSize() - 1);
    }
  }
}
