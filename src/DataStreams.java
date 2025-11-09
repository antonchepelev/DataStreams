import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataStreams {
    static File defaultDir = new File("C:\\Users\\anton\\IdeaProjects\\DataStreams\\");

    protected  String chooseFilePath(File defaultDir, String dialogTitle) {
        JFileChooser chooser = new JFileChooser(defaultDir);
        chooser.setDialogTitle(dialogTitle);
        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getAbsolutePath();
        }
        return null;
    }

    protected String filteredTextOutput(List<String> list, String keyword) {

        return list.stream()
                .filter(line -> line.trim().toLowerCase()
                        .contains(keyword.toLowerCase()))
                .collect(Collectors.joining("\n"));
    }


    protected List<String> textFileStreamOutput(Stream<String> lines) {
        if (lines == null) return List.of(); // safe check

        return lines.collect(Collectors.toList()); // collect lines into a List
    }




    protected  Stream<String> textFileToStream(String filePath) {
        try {
            return Files.lines(Paths.get(filePath));
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return Stream.empty(); // Return an empty stream if error occurs
        }
    }


}
