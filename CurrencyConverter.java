import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter extends JFrame implements ActionListener {

    private JComboBox<String> fromCurrency, toCurrency;
    private JTextField amountField;
    private JLabel resultLabel;
    private Map<String, Double> exchangeRates;

    public CurrencyConverter() {
        setTitle("💱 Currency Converter");
        setSize(400, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // 🌈 Set modern background and layout
        getContentPane().setBackground(new Color(40, 44, 52));
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.fill = GridBagConstraints.HORIZONTAL;

        // 💰 Title
        JLabel title = new JLabel("Currency Converter", SwingConstants.CENTER);
        title.setFont(new Font("Poppins", Font.BOLD, 22));
        title.setForeground(new Color(0, 200, 255));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        add(title, c);

        // 🪙 Amount label + field
        JLabel amountLabel = new JLabel("Enter Amount:");
        amountLabel.setForeground(Color.WHITE);
        c.gridwidth = 1;
        c.gridy = 1;
        c.gridx = 0;
        add(amountLabel, c);

        amountField = new JTextField();
        c.gridx = 1;
        add(amountField, c);

        // 🌍 From Currency
        JLabel fromLabel = new JLabel("From:");
        fromLabel.setForeground(Color.WHITE);
        c.gridy = 2;
        c.gridx = 0;
        add(fromLabel, c);

        fromCurrency = new JComboBox<>(new String[]{"USD", "INR", "EUR", "GBP", "JPY", "AUD"});
        c.gridx = 1;
        add(fromCurrency, c);

        // 💶 To Currency
        JLabel toLabel = new JLabel("To:");
        toLabel.setForeground(Color.WHITE);
        c.gridy = 3;
        c.gridx = 0;
        add(toLabel, c);

        toCurrency = new JComboBox<>(new String[]{"USD", "INR", "EUR", "GBP", "JPY", "AUD"});
        c.gridx = 1;
        add(toCurrency, c);

        // 🔘 Convert Button
        JButton convertButton = new JButton("Convert");
        convertButton.setBackground(new Color(0, 200, 255));
        convertButton.setForeground(Color.WHITE);
        convertButton.setFont(new Font("Poppins", Font.BOLD, 16));
        convertButton.setFocusPainted(false);
        convertButton.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        convertButton.addActionListener(this);
        c.gridy = 4;
        c.gridx = 0;
        c.gridwidth = 2;
        add(convertButton, c);

        // 📊 Result Label
        resultLabel = new JLabel("Converted Amount: ", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        resultLabel.setForeground(Color.WHITE);
        c.gridy = 5;
        add(resultLabel, c);

        // 💹 Load exchange rates
        loadRates();
    }

    private void loadRates() {
        // ⚙️ Base: USD
        exchangeRates = new HashMap<>();
        exchangeRates.put("USD", 1.0);
        exchangeRates.put("INR", 83.0);
        exchangeRates.put("EUR", 0.93);
        exchangeRates.put("GBP", 0.79);
        exchangeRates.put("JPY", 151.5);
        exchangeRates.put("AUD", 1.52);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double amount = Double.parseDouble(amountField.getText());
            String from = (String) fromCurrency.getSelectedItem();
            String to = (String) toCurrency.getSelectedItem();

            double usdAmount = amount / exchangeRates.get(from);
            double converted = usdAmount * exchangeRates.get(to);

            resultLabel.setText(String.format("Converted: %.2f %s", converted, to));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "⚠️ Please enter a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CurrencyConverter().setVisible(true);
        });
    }
}
