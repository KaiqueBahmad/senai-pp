package Tela;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Paths;
import java.util.Vector;

import classes.NewMessagesAdapter;
import classes.persistence.MessageBox;
import classes.persistence.Mensagem;
import classes.request.CSVRequest;
import classes.request.FileEnterSeparatedRequest;
import classes.request.ListRequest;

public class Tela extends JFrame {
    private JTextArea messageDisplay;
    private JTextField inputField;
    private JComboBox<String> inputTypeSelector;
    private JTextField delimiterField;
    private MessageBox messageBox;
    private NewMessagesAdapter adapter;
    private DefaultListModel<String> listModel;
    private JList<String> itemList;
    private JPanel listPanel;
    private JPanel csvPanel;
    private JPanel filePanel;

    public Tela() {
        messageBox = new MessageBox();
        adapter = new NewMessagesAdapter();
        
        setTitle("Sistema de Mensagens");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLayout(new BorderLayout());

        createDisplayArea();
        createInputArea();
        
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createDisplayArea() {
        messageDisplay = new JTextArea();
        messageDisplay.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(messageDisplay);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void createInputArea() {
        JPanel inputPanel = new JPanel(new BorderLayout());

        String[] inputTypes = {"Lista", "CSV", "Arquivo"};
        inputTypeSelector = new JComboBox<>(inputTypes);
        
        JPanel selectorPanel = new JPanel();
        selectorPanel.add(new JLabel("Tipo de entrada:"));
        selectorPanel.add(inputTypeSelector);

        createListPanel();
        createCSVPanel();
        createFilePanel();
        
        JPanel cardPanel = new JPanel(new CardLayout());
        cardPanel.add(listPanel, "Lista");
        cardPanel.add(csvPanel, "CSV");
        cardPanel.add(filePanel, "Arquivo");
        
        inputPanel.add(selectorPanel, BorderLayout.NORTH);
        inputPanel.add(cardPanel, BorderLayout.CENTER);
        
        add(inputPanel, BorderLayout.SOUTH);

        inputTypeSelector.addActionListener(e -> {
            CardLayout cl = (CardLayout) cardPanel.getLayout();
            cl.show(cardPanel, (String) inputTypeSelector.getSelectedItem());
        });
    }

    private void createListPanel() {
        listPanel = new JPanel(new BorderLayout());
        listModel = new DefaultListModel<>();
        itemList = new JList<>(listModel);
        JScrollPane listScroller = new JScrollPane(itemList);
        
        JPanel inputControlPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());
        
        inputField = new JTextField();
        JButton addButton = new JButton("Adicionar");
        JButton removeButton = new JButton("Remover");
        JButton submitListButton = new JButton("Enviar Lista");
        
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(submitListButton);
        
        inputControlPanel.add(inputField, BorderLayout.CENTER);
        inputControlPanel.add(buttonPanel, BorderLayout.EAST);
        
        listPanel.add(listScroller, BorderLayout.CENTER);
        listPanel.add(inputControlPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> {
            String text = inputField.getText().trim();
            if (!text.isEmpty()) {
                listModel.addElement(text);
                inputField.setText("");
            }
        });

        removeButton.addActionListener(e -> {
            int selectedIndex = itemList.getSelectedIndex();
            if (selectedIndex != -1) {
                listModel.remove(selectedIndex);
            }
        });

        submitListButton.addActionListener(e -> {
            if (listModel.getSize() > 0) {
                Vector<String> messages = new Vector<>();
                for (int i = 0; i < listModel.getSize(); i++) {
                    messages.add(listModel.getElementAt(i));
                }
                
                ListRequest listRequest = new ListRequest();
                listRequest.setMensagens(messages);
                java.util.List<Mensagem> newMessages = adapter.readMessages(listRequest);
                
                messageBox.getMensagens().addAll(newMessages);
                updateDisplay();
                listModel.clear();
            }
        });
    }

    private void createCSVPanel() {
        csvPanel = new JPanel(new BorderLayout());
        
        JPanel delimiterPanel = new JPanel();
        delimiterField = new JTextField(",", 3);
        delimiterPanel.add(new JLabel("Delimitador:"));
        delimiterPanel.add(delimiterField);
        
        JTextField csvInput = new JTextField();
        JButton submitCSVButton = new JButton("Enviar CSV");
        
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(csvInput, BorderLayout.CENTER);
        inputPanel.add(submitCSVButton, BorderLayout.EAST);
        
        csvPanel.add(delimiterPanel, BorderLayout.NORTH);
        csvPanel.add(inputPanel, BorderLayout.SOUTH);
        
        submitCSVButton.addActionListener(e -> {
            String input = csvInput.getText().trim();
            if (!input.isEmpty()) {
                CSVRequest csvRequest = new CSVRequest();
                csvRequest.setContent(input);
                csvRequest.setDelimiter(delimiterField.getText());
                java.util.List<Mensagem> newMessages = adapter.readMessages(csvRequest);
                messageBox.getMensagens().addAll(newMessages);
                updateDisplay();
                csvInput.setText("");
            }
        });
    }

    private void createFilePanel() {
        filePanel = new JPanel(new BorderLayout());
        
        JTextField filePathField = new JTextField();
        filePathField.setEditable(false);
        JButton chooseFileButton = new JButton("Escolher Arquivo");
        JButton submitFileButton = new JButton("Enviar Arquivo");
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(chooseFileButton);
        buttonPanel.add(submitFileButton);
        
        filePanel.add(filePathField, BorderLayout.CENTER);
        filePanel.add(buttonPanel, BorderLayout.EAST);
        
        chooseFileButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                filePathField.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }
        });
        
        submitFileButton.addActionListener(e -> {
            String path = filePathField.getText();
            if (!path.isEmpty()) {
                FileEnterSeparatedRequest fileRequest = new FileEnterSeparatedRequest();
                fileRequest.setPath(Paths.get(path));
                java.util.List<Mensagem> newMessages = adapter.readMessages(fileRequest);
                messageBox.getMensagens().addAll(newMessages);
                updateDisplay();
                filePathField.setText("");
            }
        });
    }

    private void updateDisplay() {
        StringBuilder display = new StringBuilder();
        for (Mensagem msg : messageBox.getMensagens()) {
            display.append(msg.getConteudo()).append("\n");
        }
        messageDisplay.setText(display.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Tela());
    }
}