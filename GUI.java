import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/* The GUI class creates an interface where the user can interact with the bot.
 * It displays the chat box for the user to type into, the chat log for the user to
 * view the bot's responses as well as previous user inputs, and the bot's emotion
 * represented through pictures.
 */
public class GUI {

    JFrame frame; // Main Frame
    JPanel textPanel; // Panel that includes the chat log and chat box
    JPanel botPanel; // Panel that includes the avatar
    JPanel mainPanel; // Panel that includes all the panels and organizes them
    JTextArea chatLog; // Log of user and bot interactions
    JTextField chatBox; // Where the user types in order to talk to the bot
    JLabel avatar; // Bot's emotions visually displayed
    Bot bot; // Bot
    Behaviors behaviors; // Bot's behaviors

    // Constructs the whole the interface
    public GUI() {
        avatar = new JLabel();
        avatar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        bot = new Bot();
        behaviors = new Behaviors();
        frame = new JFrame("Chat Bot");
        textPanel = new JPanel(new GridBagLayout());
        textPanel.setBackground(Color.LIGHT_GRAY);
        botPanel = new JPanel(new GridBagLayout());
        botPanel.setBackground(Color.LIGHT_GRAY);
        mainPanel = new JPanel(new GridBagLayout());
        chatLog = new JTextArea();
        chatBox = new JTextField();

        frame.setSize(900, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chatArea();
        botArea();
        mainArea();
        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
        frame.pack();
    }
    // Creates the text areas for the interface (text box for user input and chat log for bot response)
    public void chatArea() {
        GridBagConstraints c = new GridBagConstraints();
        textPanel.setPreferredSize(new Dimension(500,750)); // Page Start
        Border border = BorderFactory.createLineBorder(Color.black);

        JLabel chat = new JLabel("Chat: ");
        JLabel log = new JLabel("Log: ");
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0,10,0,0);
        textPanel.add(log, c);
        c.gridy = 1;
        textPanel.add(chat, c);

        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(20,20,15,0);
        chatLog.setBorder(border);
        chatLog.setEditable(false);
        JScrollPane jsc = new JScrollPane(chatLog);
        jsc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jsc.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        textPanel.add(jsc, c);


        c.gridy = 1;
        c.weighty = 0.05;
        c.insets = new Insets(0,20,10,0);
        chatBox.setBorder(border);
        chatBox.setFont(new Font("Serif", Font.PLAIN, 20));
        chatBox.setText(" ");
        KeyAdapter listener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Processes user input once the 'enter' key is hit
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (bot.botUnderstands) {
                        String currText = chatLog.getText();
                        String newText = "  User: " + chatBox.getText() + "\n"
                                + "  Cpt. C.O.D.E: " + bot.response(chatBox.getText()) + "\n";
                        String newLog = currText + "\n" + newText;
                        chatLog.setText(newLog);
                        if (!(bot.botUnderstands)) {
                            avatar.setIcon(behaviors.confused());
                            frame.pack();
                        }
                        botArea();
                    } else {
                        String currText = chatLog.getText();
                        String newText = "  Cpt. C.O.D.E: Ah okay! Thank you for expanding my knowledge!";
                        String newLog = currText + "\n" + newText;
                        chatLog.setText(newLog);
                        bot.learn(bot.responseToLearn, chatBox.getText());
                        avatar.setIcon(behaviors.happy());
                        frame.pack();
                    }
                    // Bot changes emotions based on what the user said
                    avatar.setIcon(behaviors.getBehavior(bot.mood));
                    frame.pack();
                    chatBox.setText(" ");
                }
            }
        };
        chatBox.addKeyListener(listener);
        textPanel.add(chatBox, c);
        // Bottom-left corner logo
        c.gridy = 2;
        c.anchor = GridBagConstraints.LAST_LINE_START;
        c.insets = new Insets(20,5,5,0);
        ImageIcon icon =
                new ImageIcon(getClass().getResource("cptncode.png"));
        JLabel background = new JLabel(icon);
        textPanel.add(background, c);

    }
    // Creates the avatar visual
    public void botArea() {
        GridBagConstraints c = new GridBagConstraints();
        botPanel.setSize(400, 750);
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.insets = new Insets(20,10,50,10);
        c.fill = GridBagConstraints.BOTH;
        avatar.setIcon(behaviors.normalState());
        botPanel.add(avatar, c);
    }
    // Organizes the other panels and sets the background colors
    public void mainArea() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        textPanel.setBackground(Color.orange);
        botPanel.setBackground(Color.orange);
        mainPanel.setBackground(Color.orange);
        mainPanel.add(textPanel, c);
        c.gridx = 1;
        mainPanel.add(botPanel, c);
    }

}
