import javax.swing.*;
import java.awt.*;
import java.util.stream.Stream;
import java.util.List;

public class DataStreamsGUI {

    private JFrame frame;
    private JTextArea originalTextArea;
    private JTextArea filteredTextArea;
    private JTextField searchField;
    private JButton loadButton;
    private JButton searchButton;
    private JButton quitButton;
    DataStreams  dataStreams = new DataStreams();
    private List<String> textFileList;

    public DataStreamsGUI() {
        // --- Frame setup ---

        frame = new JFrame("DataStreams");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout(10, 10));

        // --- Text Areas (side by side) ---
        originalTextArea = new JTextArea();
        filteredTextArea = new JTextArea();
        originalTextArea.setEditable(false);
        filteredTextArea.setEditable(false);


        JScrollPane originalScroll = new JScrollPane(originalTextArea);
        JScrollPane filteredScroll = new JScrollPane(filteredTextArea);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, originalScroll, filteredScroll);
        splitPane.setResizeWeight(0.5);

        // --- Top panel: Search field ---
        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        searchField = new JTextField();
        topPanel.add(new JLabel("Search: "), BorderLayout.WEST);
        topPanel.add(searchField, BorderLayout.CENTER);

        // --- Bottom panel: Buttons ---
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        loadButton = new JButton("Load File");
        searchButton = new JButton("Search File");
        quitButton = new JButton("Quit");

        searchButton.setEnabled(false);

        setupLoadFileButton(loadButton);
        setupSearchFileButton(searchButton);

        buttonPanel.add(loadButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(quitButton);

        // --- Add to frame ---
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(splitPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // --- Quit button ---
        quitButton.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }

    private void setupLoadFileButton(JButton button){
        button.addActionListener(e -> {
            String textFilePath = dataStreams.chooseFilePath(DataStreams.defaultDir,"Select a text file");
            if (textFilePath == null) return; // do nothing
            Stream<String> lines =  dataStreams.textFileToStream(textFilePath);
            textFileList = dataStreams.textFileStreamOutput(lines);
            originalTextArea.setText(String.join("\n", textFileList));
            searchButton.setEnabled(true);

        });
    }

    private void setupSearchFileButton(JButton button){
        button.addActionListener(e -> {
            String filteredOutput = dataStreams.filteredTextOutput(textFileList, searchField.getText());
            filteredTextArea.setText(filteredOutput);
        });
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(DataStreamsGUI::new);
    }
}
