package org.usfirst.frc.team2537.robot.auto;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;

public class Distanlines {
    private final JPanel mView = new JPanel(new BorderLayout());

    public Distanlines() {
        JMenuBar aMenuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("FILE");

        JMenuItem load = new JMenuItem("Load Image");
        load.addActionListener(new ActionListener() {

            @SuppressWarnings("unused")
			private BufferedImage frc2016;

            @Override
            public void actionPerformed(ActionEvent pE) {
                JFileChooser aChooser = new JFileChooser();
                aChooser.setFileFilter(new FileFilter() {

                    @Override
                    public String getDescription() {
                        return "Images";
                    }

                    @Override
                    public boolean accept(File pF) {
                        return pF.isDirectory() || pF.getName().endsWith("png");
                    }
                });
                int aShowOpenDialog = aChooser.showOpenDialog(mView);
                if(aShowOpenDialog ==JFileChooser.APPROVE_OPTION) {
                    try {
                        frc2016 = ImageIO.read(aChooser.getSelectedFile());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        aMenuBar.add(fileMenu);


        mView.add(aMenuBar, BorderLayout.NORTH);
    }

    public static final void createAndShowGUI()  {
        JFrame aFrame = new JFrame();
        Distanlines aCode = new Distanlines();
        aFrame.setContentPane(aCode.mView);
        aFrame.pack();
        aFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aFrame.setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();   
            }
        });
    }

}