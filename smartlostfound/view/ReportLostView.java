package smartlostfound.view;

import javax.swing.*;
import java.awt.*;

public class ReportLostView extends JPanel {
    private final JTextField categoryField;
    private final JTextField descriptionField;
    private final JTextField locationField;
    private final JButton submitButton;

    public ReportLostView() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        categoryField = new JTextField(15);
        descriptionField = new JTextField(15);
        locationField = new JTextField(15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Category (e.g. Wallet, Phone):"), gbc);
        gbc.gridx = 1;
        add(categoryField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Description (Color, Brand):"), gbc);
        gbc.gridx = 1;
        add(descriptionField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Last Seen Location:"), gbc);
        gbc.gridx = 1;
        add(locationField, gbc);

        submitButton = new JButton("File Lost Report");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(submitButton, gbc);
    }

    public String getCategory() {
        return categoryField.getText().trim();
    }

    public String getDescription() {
        return descriptionField.getText().trim();
    }

    public String getItemLocation() {
        return locationField.getText().trim();
    }

    public void setSubmitListener(Runnable listener) {
        submitButton.addActionListener(e -> listener.run());
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void clearFields() {
        categoryField.setText("");
        descriptionField.setText("");
        locationField.setText("");
    }
}
