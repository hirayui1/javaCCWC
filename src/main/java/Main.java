import java.io.*;

public class Main {
    public static void main(String[] args) {

        boolean noCommand = false;
        if (args.length > 0 && args.length <= 2) {
            String fileName;
            if (args[0].endsWith(".txt")) {
                fileName = args[0];
                noCommand = true;
            } else if (args.length > 1 && args[0].contains("-") && args[1].endsWith(".txt")) {
                fileName = args[1];
            } else {
                fileName = "";
            }

            if (noCommand) {
                System.out.println("\t" +
                        readBytes(args) +
                        " " +
                        readLines(args) +
                        " " +
                        readWordCount(args) +
                        " " +
                        args[0]);
            } else {
                switch (args[0]) {
                    case "-c" ->
                            System.out.println("\t" + readBytes(args) + " " + fileName);
                    case "-l" ->
                            System.out.println("\t" + readLines(args) + " " + fileName);
                    case "-w" ->
                            System.out.println("\t" + readWordCount(args) + " " + fileName);
                    case "-m" ->
                            System.out.println("\t" + readCharacterCount(args) + " " + fileName);

                    default ->
                            System.out.println("Unsupported CLI command. Program usage: ccwc {command} {fileName.txt}");
                }
            }
        } else System.out.println("Unsupported CLI command. Program usage: ccwc {command} {fileName.txt}");
    }

    // decides if stdin or txt or no txt
    private static FileInputStream getFileInputStream(String[] args) throws IOException {
        if (args.length >= 2 && args[0].contains("-") && args[1].endsWith(".txt")) {
            return new FileInputStream(args[1]);
        } else if (args[0].endsWith(".txt")) {
            return new FileInputStream(args[0]);
        } else return new FileInputStream(FileDescriptor.in);
    }

    // decides if stdin or txt or no txt
    private static BufferedReader getBufferedReader(String[] args) throws IOException {
        if (args.length >= 2 && args[0].contains("-") && args[1].endsWith(".txt")){
            return new BufferedReader(new InputStreamReader(new FileInputStream(args[1])));
        } else if (args[0].endsWith(".txt")) {
            return new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
        } else return new BufferedReader(new InputStreamReader(System.in));
    }

    private static int readCharacterCount(String[] args) {

        int charCount = 0;
        try (BufferedReader br = getBufferedReader(args)) {
            int chars;
            while ((chars = br.read()) != -1) {
                charCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return charCount;
    }

    private static int readWordCount(String[] args) {
        int wordCount = 0;
        try (BufferedReader br = getBufferedReader(args)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    if (!word.isEmpty()) {
                        wordCount++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordCount;
    }

    private static int readLines(String[] args) {
        int lineCount = 0;
        try (BufferedReader br = getBufferedReader(args)) {
            while (br.readLine() != null) {
                lineCount++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lineCount;
    }

    // read and print each line into a String array
    public static int readBytes(String[] args) {
        int byteCount = 0;
        try (FileInputStream fil = getFileInputStream(args)) {
            int nextByte;
            while ((nextByte = fil.read()) != -1) {
                byteCount++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return byteCount;
    }
}
