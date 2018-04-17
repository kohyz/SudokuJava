import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;



public class StartMenu  {

	Difficulty diff;
    JFrame frame = new JFrame("Sudoku");
	
    public StartMenu() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new TestPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    @SuppressWarnings("serial")
	public class TestPane extends JPanel {

        private List<String> menuItems;
        private String selectMenuItem;
        private String focusedItem;

        private MenuItemPainter painter;
        private Map<String, Rectangle> menuBounds;

        public TestPane() {
            setBackground(Color.WHITE);
            
            JPanel panel = new JPanel(new BorderLayout());
    		panel.setLayout(new FlowLayout());
    		
            painter = new SimpleMenuItemPainter();
                      
            JLabel label = new JLabel("Sudoku");
            panel.add(label);
            
            menuItems = new ArrayList<>(25);
            menuItems.add("Start Game");
           // menuItems.add("Options");
            menuItems.add("Exit");
          
            
            selectMenuItem = menuItems.get(0);
     
           

            MouseAdapter ma = new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    String newItem = null;
                    for (String text : menuItems) {
                        Rectangle bounds = menuBounds.get(text);
                        if (bounds.contains(e.getPoint())) {                   
                            newItem = text;
                            
                            switch(newItem){
                            	case "Start Game":
                            		runGui2();
                    				frame.dispose();
                            	break;                            	
                            	case "Exit":
                            		System.exit(0);
                            	break;
                            }
                   
                            
                            break;
                        }
                    }
                    if (newItem != null && !newItem.equals(selectMenuItem)) {
                        selectMenuItem = newItem;
                        repaint();
                    }
                }

                @Override
                public void mouseMoved(MouseEvent e) {
                    focusedItem = null;
                    for (String text : menuItems) {
                        Rectangle bounds = menuBounds.get(text);
                        if (bounds.contains(e.getPoint())) {
                            focusedItem = text;
                            repaint();
                            break;
                        }
                    }
                }

            };

            addMouseListener(ma);
            addMouseMotionListener(ma);

            InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
            ActionMap am = getActionMap();

            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "arrowDown");
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "arrowUp");

            am.put("arrowDown", new MenuAction(1));
            am.put("arrowUp", new MenuAction(-1));

        }

        @Override
        public void invalidate() {
            menuBounds = null;
            super.invalidate();
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(200, 200);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            if (menuBounds == null) {
                menuBounds = new HashMap<>(menuItems.size());
                int width = 0;
                int height = 0;
                for (String text : menuItems) {
                    Dimension dim = painter.getPreferredSize(g2d, text);
                    width = Math.max(width, dim.width);
                    height = Math.max(height, dim.height);
                }

                int x = (getWidth() - (width + 10)) / 2;

                int totalHeight = (height + 10) * menuItems.size();
                totalHeight += 5 * (menuItems.size() - 1);

                int y = (getHeight() - totalHeight) / 2;

                for (String text : menuItems) {
                    menuBounds.put(text, new Rectangle(x, y, width + 10, height + 10));
                    y += height + 10 + 5;
                }

            }
            for (String text : menuItems) {
                Rectangle bounds = menuBounds.get(text);
                boolean isSelected = text.equals(selectMenuItem);
                boolean isFocused = text.equals(focusedItem);
                painter.paint(g2d, text, bounds, isSelected, isFocused);
            }
            g2d.dispose();
        }

        public class MenuAction extends AbstractAction {

            private final int delta;

            public MenuAction(int delta) {
                this.delta = delta;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                int index = menuItems.indexOf(selectMenuItem);
               
                
                if (index < 0) {
                    selectMenuItem = menuItems.get(0);

                   // runGui2();
                  
                    
                }
                index += delta;
                if (index < 0) {
                    selectMenuItem = menuItems.get(menuItems.size() - 1);
                } else if (index >= menuItems.size()) {
                    selectMenuItem = menuItems.get(0);
                } else {
                    selectMenuItem = menuItems.get(index);
                }
                repaint();
            }

        }

    }

    public interface MenuItemPainter {

        public void paint(Graphics2D g2d, String text, Rectangle bounds, boolean isSelected, boolean isFocused);

        public Dimension getPreferredSize(Graphics2D g2d, String text);

    }

    public class SimpleMenuItemPainter implements MenuItemPainter {

        public Dimension getPreferredSize(Graphics2D g2d, String text) {
            return g2d.getFontMetrics().getStringBounds(text, g2d).getBounds().getSize();
        }

        @Override
        public void paint(Graphics2D g2d, String text, Rectangle bounds, boolean isSelected, boolean isFocused) {
            Font font=new Font("Tahoma", Font.BOLD, 12);
        	FontMetrics fm = g2d.getFontMetrics();
            g2d.setFont(font);
           
        	Color  btn1  = new Color(234, 207, 125);
        	Color  btn2  = new Color(215,92,72);
        	Color  btn3  = new Color(190, 44, 69);
            if (isSelected) {
                paintBackground(g2d, bounds, btn3, Color.WHITE);
            } else if (isFocused) {
                paintBackground(g2d, bounds, btn2, Color.white);
            } else {
                paintBackground(g2d, bounds, btn1, Color.WHITE);
            }
            int x = bounds.x + ((bounds.width - fm.stringWidth(text)) / 2);
            int y = bounds.y + ((bounds.height - fm.getHeight()) / 2) + fm.getAscent();
            g2d.setColor(isSelected ? Color.WHITE : Color.WHITE);
            g2d.drawString(text, x, y);
        }

        protected void paintBackground(Graphics2D g2d, Rectangle bounds, Color background, Color foreground) {
            g2d.setColor(background);
            g2d.fill(bounds);
            g2d.setColor(foreground);
            g2d.draw(bounds);
        }

    }

	public static void runGui2() {
		new Difficulty();
	}
}