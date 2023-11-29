/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab4Bijeikis.Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ResourceBundle;

/**
 *
 * @author darius
 */
public class Menu extends JMenuBar implements ActionListener {

    private static final ResourceBundle MESSAGES = ResourceBundle.getBundle("Lab4Bijeikis.Gui.messages");

    private JMenu jMenu1;
    private JMenu jMenu2;
    private JMenu jMenu3;
    private JMenuItem jMenuItem11;
    private JMenuItem jMenuItem12;
    private JMenuItem jMenuItem13;
    private JMenuItem jMenuItem21;
    private JMenuItem jMenuItem31;
    private JMenuItem jMenuItem32;
    private JMenuItem jMenuItem33;
    private final Lab4Window window;

    public Menu(Lab4Window lab4Panel) {
        this.window = lab4Panel;
        initComponents();
    }

    private void initComponents() {
        // Sukuriama meniu juosta
        jMenu1 = new JMenu(MESSAGES.getString("menu1"));
        super.add(jMenu1);
        jMenuItem11 = new JMenuItem(MESSAGES.getString("menuItem11"));
        jMenuItem11.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        jMenuItem12 = new JMenuItem(MESSAGES.getString("menuItem12"));
        jMenuItem12.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        jMenuItem13 = new JMenuItem(MESSAGES.getString("menuItem13"));
        jMenuItem13.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));

        jMenu1.add(jMenuItem11);
        jMenu1.add(jMenuItem12);
        jMenu1.addSeparator();
        jMenu1.add(jMenuItem13);

        jMenu2 = new JMenu(MESSAGES.getString("menu2"));
        super.add(jMenu2);
        jMenuItem21 = new JMenuItem(MESSAGES.getString("menuItem21"));
        jMenuItem21.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.SHIFT_MASK));
        jMenu2.add(jMenuItem21);

        jMenu3 = new JMenu(MESSAGES.getString("menu3"));
        super.add(jMenu3);
        jMenuItem31 = new JMenuItem(MESSAGES.getString("menuItem31"));
        jMenuItem32 = new JMenuItem(MESSAGES.getString("menuItem32"));
        jMenuItem33 = new JMenuItem(MESSAGES.getString("menuItem33"));

        jMenu3.add(jMenuItem31);
        jMenu3.add(jMenuItem32);
        jMenu3.add(jMenuItem33);

        jMenuItem11.addActionListener(this);
        jMenuItem12.addActionListener(this);
        jMenuItem13.addActionListener(this);
        jMenuItem21.addActionListener(this);
        jMenuItem31.addActionListener(this);
        jMenuItem32.addActionListener(this);
        jMenuItem33.addActionListener(this);


    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            Object command = ae.getSource();
            KsSwing.setFormatStartOfLine(true);
            if (command.equals(jMenuItem11)) {
                fileChooseMenu();
            } else if (command.equals(jMenuItem12)) {
                KsSwing.ounerr(window.getTaEvents(), MESSAGES.getString("notImplemented"));
            } else if (command.equals(jMenuItem13)) {
                System.exit(0);
            } else if (command.equals(jMenuItem21)) {
                JOptionPane.showOptionDialog(window,
                        MESSAGES.getString("author"),
                        MESSAGES.getString("menuItem21"),
                        JOptionPane.OK_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        new String[]{"OK"},
                        null);
            }
            else if (command.equals(jMenuItem31)){
                String key = JOptionPane.showInputDialog(window,"Iveskite norima rakta",MESSAGES.getString("menuItem31"),
                        JOptionPane.PLAIN_MESSAGE);
                if(key == null || key.isEmpty()){
                    JOptionPane.showOptionDialog(window,"Neivesti jokie duomenys",MESSAGES.getString("menuItem31"),
                            JOptionPane.OK_OPTION,JOptionPane.ERROR_MESSAGE,null,new String[]{"OK"},null);
                }
                window.remove(key);
            }else if (command.equals(jMenuItem32)){
                String key = JOptionPane.showInputDialog(window,"Iveskite norima rakta",MESSAGES.getString("menuItem32"),
                        JOptionPane.PLAIN_MESSAGE);
                if(key == null || key.isEmpty()){
                    JOptionPane.showOptionDialog(window,"Neivesti jokie duomenys",MESSAGES.getString("menuItem32"),
                            JOptionPane.OK_OPTION,JOptionPane.ERROR_MESSAGE,null,new String[]{"OK"},null);
                }
                window.findByKey(key);
            }else if (command.equals(jMenuItem33)){
                String value = JOptionPane.showInputDialog(window,"Iveskite norima reiksme",MESSAGES.getString("menuItem33"),
                        JOptionPane.PLAIN_MESSAGE);
                if(value == null || value.isEmpty()){
                    JOptionPane.showOptionDialog(window,"Neivesti jokie duomenys",MESSAGES.getString("menuItem33"),
                            JOptionPane.OK_OPTION,JOptionPane.ERROR_MESSAGE,null,new String[]{"OK"},null);
                }
                window.containsValue(value);
            }

        } catch (MyException e) {
            KsSwing.ounerr(window.getTaEvents(), e.getMessage());
        } catch (Exception e) {
            KsSwing.ounerr(window.getTaEvents(), MESSAGES.getString("systemError"));
            e.printStackTrace(System.out);
        }
    }

    private void fileChooseMenu() {
        JFileChooser fc = new JFileChooser(".");

        // Nuimamas "all Files" filtras
        // fc.setAcceptAllFileFilterUsed(false);
        // Papildoma mūsų sukurtais filtrais
        fc.addChoosableFileFilter(new MyFilter());
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            window.getTaEvents().setBackground(Color.white);
            File file = fc.getSelectedFile();
            window.mapGeneration(file.getAbsolutePath());
        }
    }

    /**
     * Privati klasė, aprašanti failų pasirinkimo dialogo filtrus
     */
    private class MyFilter extends javax.swing.filechooser.FileFilter {

        @Override
        public boolean accept(File file) {
            String filename = file.getName();
            // Rodomos tik direktorijos ir txt failai
            return file.isDirectory() || filename.endsWith(".txt");
        }

        @Override
        public String getDescription() {
            return "*.txt";
        }
    }

}
