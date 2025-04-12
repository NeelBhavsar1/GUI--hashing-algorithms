import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("Hashing GUI - Neel Bhavsar");

        //setting up basic frame information
        frame.setSize(400, 300); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        //initial label for algorithm selected, can be changed once a subitem is selected on algorithms tab
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
        JTextField hashField = new JTextField(20);
        hashField.setEditable(false); 

        JPanel centerPanel = new JPanel();
        centerPanel.add(hashLabel);
        centerPanel.add(hashField);
        frame.add(centerPanel, BorderLayout.CENTER);

        
        JButton convertButton = new JButton("CONVERT");
        JButton clearButton = new JButton("CLEAR");

        
        clearButton.addActionListener(e -> {
            inputField.setText(""); 
            hashField.setText(""); 
        });

        convertButton.addActionListener(e -> {
            String inputText = inputField.getText();
            hashField.setText("hashed_value_of_" + inputText); // Placeholder hash value, change later
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(convertButton);
        buttonPanel.add(clearButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        /*
         * creating a menu bar with following options: Algorithms, help, credits
         */
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        /*
         * Creating the algorithm menu option with the submenu items for different hashing algorithms
         */
        JMenu algorithmsMenu = new JMenu("Algorithms");
        menuBar.add(algorithmsMenu);
        JMenuItem sha256MenuItem = new JMenuItem("SHA-256");
        JMenuItem md5MenuItem = new JMenuItem("MD5");
        JMenuItem blake2MenuItem = new JMenuItem("Blake2");

        algorithmsMenu.add(sha256MenuItem);
        algorithmsMenu.add(md5MenuItem);
        algorithmsMenu.add(blake2MenuItem);

        //Changes the text displayed on screen when the submenu option for algorithms is selected
        sha256MenuItem.addActionListener(e -> selectedalg.setText("Algorithm selected: SHA-256"));
        md5MenuItem.addActionListener(e -> selectedalg.setText("Algorithm selected: MD5"));
        blake2MenuItem.addActionListener(e -> selectedalg.setText("Algorithm selected: Blake2"));

        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);

        //Created submenu item for help
        JMenuItem helpMenuItem = new JMenuItem("How to use?");
        helpMenu.add(helpMenuItem);

        //checks whether button has clicked, opens up a new screen on how to use the applicatoin
        helpMenuItem.addActionListener(e -> {
            JFrame helpFrame = new JFrame("Help");
            helpFrame.setSize(300, 200);
            helpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JTextArea helpText = new JTextArea(
                "This application allows you to hash passwords using algorithms like SHA-256, MD5, and Blake2.\n\n"
                + "Enter your password in the text field and click CONVERT to generate the hash."
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


        //opens up a small window with credits
        JMenu creditMenu = new JMenu("Credit");
        menuBar.add(creditMenu);

        //interface methods for selecting menu options, 3 need to be produced. only 1 handled for clicking the option
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

        //needed for making the frame visible on screen
        frame.setVisible(true);

        //hello
    }
}
