package smartlostfound.view;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JPanel {
    private final JTextField idField;
    private final JTextField nameField;
    private final JTextField emailField;
    private final JButton loginButton;

    public LoginView() {
        setName("login");
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 244, 248));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Smart Lost & Found System Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(new Color(44, 62, 80));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        add(new JLabel("User ID (e.g., U001):"), gbc);

        idField = new JTextField(15);
        gbc.gridx = 1;
        add(idField, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        add(new JLabel("Name:"), gbc);

        nameField = new JTextField(15);
        gbc.gridx = 1;
        add(nameField, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        add(new JLabel("Email:"), gbc);

        emailField = new JTextField(15);
        gbc.gridx = 1;
        add(emailField, gbc);

        loginButton = new JButton("Login / Register");
        loginButton.setBackground(new Color(52, 152, 219));
        loginButton.setForeground(Color.WHITE);
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(loginButton, gbc);
    }

    public String getUserId() {
        return idField.getText().trim();
    }

    public String getName() {
        return nameField.getText().trim();
    }

    public String getEmail() {
        return emailField.getText().trim();
    }

    public void setLoginListener(Runnable listener) {
        loginButton.addActionListener(e -> listener.run());
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void clearFields() {
        idField.setText("");
        nameField.setText("");
        emailField.setText("");
    }
}
