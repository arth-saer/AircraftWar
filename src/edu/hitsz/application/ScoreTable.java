package edu.hitsz.application;

import edu.hitsz.dao.ScoreDao;
import edu.hitsz.dao.ScoreDaoImpByFile;
import edu.hitsz.dao.ScoreData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

public class ScoreTable extends JPanel{
    private final int difficult;
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel buttonPanel;
    private JButton deleteButton;
    private JTable scoresTable;
    private JLabel tableTitle;
    private JScrollPane tableScrollPanel;
    private JLabel difficultLabel;

    private final String[] columnName = {"排名", "玩家名", "分数", "时间"};
    private String[][] tableData;
    private DefaultTableModel model;

    public ScoreTable(int difficult){

        this.difficult = difficult;
        if(difficult == 1){
            difficultLabel.setText("难度: EASY");
        }
        else if(difficult == 2){
            difficultLabel.setText("难度: REGULAR");
        }
        else if(difficult == 3){
            difficultLabel.setText("难度: HARD");
        }
        tableScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //tableScrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        ScoreDao scoreDao = new ScoreDaoImpByFile("data/scoredata.ser");
        updateModel(scoreDao);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = scoresTable.getSelectedRow();
                if(row != -1){
                    int ifDelete = JOptionPane.showConfirmDialog(mainPanel, "是否确认删除选中的记录");
                    if(ifDelete == 0)
                    {
                        scoreDao.doDelete(new ScoreData(difficult, Integer.parseInt(tableData[row][2]), tableData[row][1], tableData[row][3]));
                        model.removeRow(row);
                    }
                }
            }
        });
    }
    private String[][] toTableData(List<ScoreData> scoreDatas){

        scoreDatas.sort(Comparator.comparingInt(ScoreData::getScore).reversed());
        String[][] tableData = new String[scoreDatas.size()][];
        for(int i = 0; i < scoreDatas.size(); i++){
            String[] s = {(i+1) + "", scoreDatas.get(i).getName(), scoreDatas.get(i).getScore() + "", scoreDatas.get(i).getDate()};
            tableData[i] = s;
        }
        //System.out.println(scoreDatas.size());
        return tableData;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void addScore(int score){

        ScoreDao scoreDao = new ScoreDaoImpByFile("data/scoredata.ser");
        String playerName = JOptionPane.showInputDialog(this,"当前得分为:" + score + "\n" + "请输入你的名字记录得分","输入",1);
        if(playerName != null){
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd HH:mm");
            String formattedDateTime = currentDateTime.format(formatter);
            scoreDao.doAdd(new ScoreData(difficult, score, playerName, formattedDateTime));
        }
        updateModel(scoreDao);

    }
    public void updateModel(ScoreDao scoreDao){
        this.tableData = toTableData(scoreDao.getScores(this.difficult));
        this.model = new DefaultTableModel(this.tableData, this.columnName){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        this.scoresTable.setModel(this.model);
        this.tableScrollPanel.setViewportView(this.scoresTable);
    }
}
