/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package botondobleclick;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class BotonDobleClick extends JFrame {

    private JTextField textField;
    private JTextArea textArea;
    private JTextArea consoleArea; 

    private StringBuilder inputText = new StringBuilder();
    private StringBuilder savedText = new StringBuilder();

    private int currentNumber = -1;
    private int clickCount = 0;

    private Timer timer;
    private static final int REINICIO_CLICS_DELAY = 4000; 

    public BotonDobleClick() {
        setTitle("Teléfono Móvil");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textField = new JTextField();
        textField.setEditable(false);  
        textField.setFocusable(false); 

        textArea = new JTextArea();
        textArea.setEditable(false);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 3));

        for (int i = 1; i <= 9; i++) {
            final int number = i;
            JButton button = new JButton(Integer.toString(i));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (timer != null && timer.isRunning()) {
                        timer.stop();
                    }

                    if (currentNumber != number) {
                        currentNumber = number;
                        clickCount = 0;
                    }

                    clickCount++;

                    char letra = obtenerLetra(currentNumber, clickCount);

                    inputText.setLength(0);

                    inputText.append(letra);

                    textField.setText(inputText.toString());
                }
            });
            buttonPanel.add(button);

            JLabel label = new JLabel(getLetrasCorrespondientes(i));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            buttonPanel.add(label);
        }

        JButton button0 = new JButton("0");
        button0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timer != null && timer.isRunning()) {
                    timer.stop();
                }

                if (currentNumber != 0) {
                    currentNumber = 0;
                    clickCount = 0;
                }

                clickCount++;

                char letra = obtenerLetra(0, clickCount);

                inputText.setLength(0);

                if (currentNumber == 0 && clickCount == 1) {
                    inputText.append(" ");
                } else {
                    inputText.append(letra);
                }

                textField.setText(inputText.toString());
            }
        });
        buttonPanel.add(button0);

        JLabel label0 = new JLabel(getLetrasCorrespondientes(0));
        label0.setHorizontalAlignment(SwingConstants.CENTER);
        buttonPanel.add(label0);

        JButton clearButton = new JButton("Borrar");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
                inputText.setLength(0);
            }
        });
        buttonPanel.add(clearButton);

        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savedText.append(inputText.toString());
                consoleArea.append(inputText.toString() + "\n");
                inputText.setLength(0);
                textField.setText(savedText.toString());
            }
        });
        buttonPanel.add(saveButton);

        JButton clearConsoleButton = new JButton("Borrar Consola");
        clearConsoleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consoleArea.setText("");
                savedText.setLength(0);
            }
        });
        buttonPanel.add(clearConsoleButton);

        consoleArea = new JTextArea();
        consoleArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(consoleArea);

        add(textField, BorderLayout.NORTH);
        add(textArea, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.WEST); 
    }

    private char obtenerLetra(int number, int clics) {
        String letrasCorrespondientes = getLetrasCorrespondientes(number);
        return letrasCorrespondientes.charAt((clics - 1) % letrasCorrespondientes.length());
    }

    private String getLetrasCorrespondientes(int number) {
        String[] letras = {" ", "", "2ABC", "3DEF", "4GHI", "5JKL", "6MNO", "7PQRS", "8TUV", "9WXYZ"};
        return letras[number];
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                BotonDobleClick botonDobleClick = new BotonDobleClick();
                botonDobleClick.setVisible(true);
            }
        });
    }
}
