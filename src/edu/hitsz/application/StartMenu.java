package edu.hitsz.application;

import edu.hitsz.prop.BaseProp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class StartMenu {
    private JPanel mainPanel;
    private JButton easy;
    private JButton regular;
    private JButton hard;
    private JComboBox<String> voiceSwitch;
    private JLabel voiceSwitchLabel;
    private boolean voiceSwitchFlag;

    public StartMenu() {

        voiceSwitch.addItem("关");
        voiceSwitch.addItem("开");

        voiceSwitch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voiceSwitchFlag = Objects.equals((String) voiceSwitch.getSelectedItem(), "开");
                BaseProp.voiceSwitch = voiceSwitchFlag;
                Game.voiceSwitch = voiceSwitchFlag;
            }
        });

        easy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game game = new EasyGame();
                Main.cardPanel.add(game);
                Main.cardLayout.last(Main.cardPanel);
                game.action();

            }
        });
        regular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Game game = new RegularGame();
                Main.cardPanel.add(game);
                Main.cardLayout.last(Main.cardPanel);
                game.action();

            }
        });
        hard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Game game = new HardGame();
                Main.cardPanel.add(game);
                Main.cardLayout.last(Main.cardPanel);
                game.action();

            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
