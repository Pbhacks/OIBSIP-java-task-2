import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame extends JFrame {
    private int generatedNumber;
    private int attempts;
    private int maxAttempts = 10;
    private int wrongAttempts = 0;

    private JTextField guessField;
    private JButton guessButton;
    private JLabel resultLabel;
    private JLabel attemptsLabel;

    public NumberGuessingGame() {
        setTitle("Number Guessing Game");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(4, 1));

        initializeGame();

        guessField = new JTextField();
        guessButton = new JButton("Guess");
        resultLabel = new JLabel("Result: ");
        attemptsLabel = new JLabel("Attempts left: " + attempts);

        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        add(guessField);
        add(guessButton);
        add(resultLabel);
        add(attemptsLabel);
    }

    private void initializeGame() {
        Random random = new Random();
        generatedNumber = random.nextInt(100) + 1;
        attempts = maxAttempts;
    }

    private void checkGuess() {
        try {
            int userGuess = Integer.parseInt(guessField.getText());

            if (userGuess == generatedNumber) {
                resultLabel.setText("Result: Congratulations! You guessed the number!");
                endGame();
            } else if (userGuess < generatedNumber) {
                resultLabel.setText("Result: Too low. Try again.");
            } else {
                resultLabel.setText("Result: Too high. Try again.");
            }

            attempts--;
            attemptsLabel.setText("Attempts left: " + attempts);

            if (userGuess != generatedNumber) {
                wrongAttempts++;
                if (wrongAttempts == 3) {
                    giveHint();
                }
            }

            if (attempts == 0) {
                resultLabel.setText("Result: Sorry, you're out of attempts. The correct number was " + generatedNumber + ".");
                endGame();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.");
        }
    }

    private void giveHint() {
        if (generatedNumber > 50) {
            resultLabel.setText("Hint: The correct number is higher than 50.");
        } else {
            resultLabel.setText("Hint: The correct number is lower than or equal to 50.");
        }
    }

    private void endGame() {
        guessField.setEnabled(false);
        guessButton.setEnabled(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NumberGuessingGame game = new NumberGuessingGame();
            game.setVisible(true);
        });
    }
}