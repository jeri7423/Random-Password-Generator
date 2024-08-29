import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.Random;
import javax.swing.*;

public class PasswordGenerator extends JFrame {
    private JTextField passwordField;
    private JButton generateButton, copyButton;

    public PasswordGenerator() {
        setTitle("Random Password Generator");
        setSize(500, 360);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Title label
        JLabel titleLabel = new JLabel("Random Password Generator");
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 24));
        titleLabel.setForeground(new Color(1, 90, 150));
        titleLabel.setBounds(50, 20, 400, 50);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel);

        // Password field with custom placeholder
        passwordField = new CustomTextField("Create password");
        passwordField.setBounds(50, 100, 400, 50);
        passwordField.setFont(new Font("Poppins", Font.PLAIN, 22));
        passwordField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        passwordField.setEditable(false);
        add(passwordField);

        // Generate button
        generateButton = new CustomButton("Generate");
        generateButton.setBounds(50, 190, 155, 50);
        generateButton.setFont(new Font("Poppins", Font.PLAIN, 15));
        generateButton.setBackground(new Color(12, 129, 238));
        generateButton.setForeground(Color.WHITE);
        add(generateButton);

        // Copy button
        copyButton = new CustomButton("Copy");
        copyButton.setBounds(295, 190, 155, 50);
        copyButton.setFont(new Font("Poppins", Font.PLAIN, 15));
        copyButton.setBackground(new Color(12, 129, 238));
        copyButton.setForeground(Color.WHITE);
        add(copyButton);

        // Action listeners
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordField.setText(generatePassword());
            }
        });

        copyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordField.selectAll();
                passwordField.copy();
                JOptionPane.showMessageDialog(null, "Password copied to clipboard!");
            }
        });
    }

    private String generatePassword() {
        String chars = "0123456789abcdefghijklmnopqrstuvwxyz!@#$%^&*()ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int passwordLength = 12;
        StringBuilder password = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i <= passwordLength; i++) {
            int randomIndex = random.nextInt(chars.length());
            password.append(chars.charAt(randomIndex));
        }

        return password.toString();
    }

    // Custom JTextField with curved edges and placeholder
    class CustomTextField extends JTextField {
        private String placeholder;

        public CustomTextField(String placeholder) {
            this.placeholder = placeholder;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (getText().isEmpty()) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setPaint(Color.GRAY);
                g2.drawString(placeholder, getInsets().left, g.getFontMetrics().getMaxAscent() + getInsets().top);
                g2.dispose();
            }
        }

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setPaint(new Color(13, 152, 245));
            g2.draw(new RoundRectangle2D.Double(1, 1, getWidth() - 2, getHeight() - 2, 20, 20));
            g2.dispose();
        }
    }

    // Custom JButton with curved edges
    class CustomButton extends JButton {
        public CustomButton(String text) {
            super(text);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            g2.setColor(getForeground());
            super.paintComponent(g);
            g2.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PasswordGenerator().setVisible(true));
    }
}
