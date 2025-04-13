import javax.swing.*;
import java.awt.*;
import java.security.*;

public class App {
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("Hashing GUI - Neel Bhavsar");

        // Setting up basic frame information
        frame.setSize(400, 300); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Initial label for algorithm selected, can be changed once a submenu is selected
        JLabel selectedalg = new JLabel("Algorithm selected: None");
        selectedalg.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel inputLabel = new JLabel("Enter password: ");
        JTextField inputField = new JTextField(20);

        JPanel northPanel = new JPanel(new GridLayout(2, 1));
        JPanel inputPanel = new JPanel(); 
        inputPanel.add(inputLabel);
        inputPanel.add(inputField);

        northPanel.add(selectedalg); 
        northPanel.add(inputPanel); 
        frame.add(northPanel, BorderLayout.NORTH);

        JLabel hashLabel = new JLabel("Hashed result: ");
        JTextArea hashField = new JTextArea(5, 20); 
        hashField.setEditable(false); 

        JScrollPane scrollPane = new JScrollPane(hashField);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel centerPanel = new JPanel();
        centerPanel.add(hashLabel);
        centerPanel.add(scrollPane); 
        frame.add(centerPanel, BorderLayout.CENTER);

        JButton convertButton = new JButton("CONVERT");
        JButton clearButton = new JButton("CLEAR");

        // Clear button functionality
        clearButton.addActionListener(e -> {
            inputField.setText(""); 
            hashField.setText(""); 
        });

        // Convert button functionality with hashing logic
        convertButton.addActionListener(e -> {
            String inputText = inputField.getText();
            String selectedAlgorithm = selectedalg.getText().replace("Algorithm selected: ", "");

            try {
                String hashedValue = hashPassword(inputText, selectedAlgorithm);
                hashField.setText(hashedValue);
            } catch (NoSuchAlgorithmException ex) {
                hashField.setText("error beep boop");
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(convertButton);
        buttonPanel.add(clearButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        //creating a menu bar with options: Algorithms, Help, Credit
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        //algorithms menu with submenu items
        JMenu algorithmsMenu = new JMenu("Algorithms");
        menuBar.add(algorithmsMenu);
        JMenuItem sha256MenuItem = new JMenuItem("SHA-256");
        JMenuItem md5MenuItem = new JMenuItem("MD5");

        algorithmsMenu.add(sha256MenuItem);
        algorithmsMenu.add(md5MenuItem);

        //hanging displayed text when a submenu option is selected
        sha256MenuItem.addActionListener(e -> selectedalg.setText("Algorithm selected: SHA-256"));
        md5MenuItem.addActionListener(e -> selectedalg.setText("Algorithm selected: MD5"));

        //Help menu
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);

        JMenuItem helpMenuItem = new JMenuItem("How to use?");
        helpMenu.add(helpMenuItem);

        //Help menu functionality
        helpMenuItem.addActionListener(e -> {
            JFrame helpFrame = new JFrame("Help");
            helpFrame.setSize(300, 200);
            helpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JTextArea helpText = new JTextArea(
                "This application allows you to hash passwords using algorithms like SHA-256 and MD5.\n\nEnter your password in the text field and click CONVERT to generate the hash."
            );
            helpText.setLineWrap(true);
            helpText.setWrapStyleWord(true);
            helpText.setEditable(false);

            JButton exitButton = new JButton("Close");
            exitButton.addActionListener(event -> helpFrame.dispose());

            helpFrame.add(new JScrollPane(helpText), BorderLayout.CENTER);
            helpFrame.add(exitButton, BorderLayout.SOUTH);
            helpFrame.setVisible(true);

        });

        //Credit menu
        JMenu creditMenu = new JMenu("Credit");
        menuBar.add(creditMenu);

        //need to implement all the interface methods for this event

        creditMenu.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuSelected(javax.swing.event.MenuEvent e) {
                JFrame creditFrame = new JFrame("Credit");
                creditFrame.setSize(300, 200);
                creditFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JLabel creditLabel = new JLabel("Made by Neel Bhavsar", SwingConstants.CENTER);
                JButton exitButton = new JButton("Close");
                exitButton.addActionListener(event -> creditFrame.dispose());

                creditFrame.add(creditLabel, BorderLayout.CENTER);
                creditFrame.add(exitButton, BorderLayout.SOUTH);
                creditFrame.setVisible(true);
            }

            public void menuDeselected(javax.swing.event.MenuEvent e) {

            }
            public void menuCanceled(javax.swing.event.MenuEvent e) {
            }
        });

        //makes frame visible
        frame.setVisible(true);
    }
    


    
    //method for hashing passwords
    private static String hashPassword(String input, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        byte[] hashBytes = md.digest(input.getBytes());

        String outputt = "";
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) outputt += '0';
            outputt += hex;
        }

        return outputt;
    }
}
