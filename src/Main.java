import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
    DataStreams  dataStreams = new DataStreams();
    String textFilePath = dataStreams.chooseFilePath(DataStreams.defaultDir,"Select a text file");
    Stream<String> lines =  dataStreams.textFileToStream(textFilePath);
    dataStreams.textFileStreamOutput(lines);
    }
}
