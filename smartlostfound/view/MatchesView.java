package smartlostfound.view;

import smartlostfound.model.Match;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MatchesView extends JPanel {
    private final DefaultTableModel tableModel;
    private final JButton refreshButton;

    public MatchesView() {
        setLayout(new BorderLayout());

        String[] columns = {"Match ID", "Lost Report ID", "Found Report ID", "Confidence", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);

        refreshButton = new JButton("Refresh Match Pool");
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(refreshButton, BorderLayout.SOUTH);
    }

    public void setRefreshListener(Runnable listener) {
        refreshButton.addActionListener(e -> listener.run());
    }

    public void displayMatches(List<Match> matches) {
        tableModel.setRowCount(0);
        for (Match match : matches) {
            tableModel.addRow(new Object[]{
                    match.getMatchId(),
                    match.getLostReportId(),
                    match.getFoundReportId(),
                    match.getScore() + "%",
                    match.getStatus()
            });
        }
    }
}
