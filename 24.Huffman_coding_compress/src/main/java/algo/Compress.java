package algo;

import algo.coding.huffman.FrequencyTable;
import algo.coding.huffman.HuffmanCoding;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.Arrays;


public class Compress {
    private static final String COMPRESSED_FILE_EXTENSION = "hcf";

    public static void main(String[] args) {
        Options options = new Options();

        Option compress = Option.builder("c")
                .longOpt("compress")
                .hasArg()
                .optionalArg(false)
                .required()
                .build();

        Option decompress = Option.builder("d")
                .longOpt("decompress")
                .hasArg()
                .optionalArg(false)
                .required()
                .build();

        OptionGroup optionGroup = new OptionGroup();
        optionGroup.addOption(compress);
        optionGroup.addOption(decompress);
        optionGroup.setRequired(true);
        options.addOptionGroup(optionGroup);

        CommandLineParser commandLineParser = new DefaultParser();
        HelpFormatter helpFormatter = new HelpFormatter();

        try {
            CommandLine commandLine = commandLineParser.parse(options, args);

            if (commandLine.hasOption("c")) {
                String compressFilename = commandLine.getOptionValue("c");
                encode(compressFilename, compressFilename + "." + COMPRESSED_FILE_EXTENSION);
            } else if (commandLine.hasOption("d")) {
                String decompressFilename = commandLine.getOptionValue("d");
                String originalFilename = getOriginalFilename(decompressFilename);
                decode(decompressFilename, originalFilename);
            }
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            helpFormatter.printHelp("compress [-c filename | -d filename]", options);
        } catch ( NoSuchFileException | FileNotFoundException e) {
            System.err.println("Файл не найден: " + e.getLocalizedMessage());
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getLocalizedMessage());
        }
    }


    private static void encode(String inFilename, String outFilename) throws IOException {
        final byte[] input = Files.readAllBytes(new File(inFilename).toPath());

        FrequencyTable frequencyTable = FrequencyTable.create(input);
        final byte[] encodedByte = HuffmanCoding.encode(input, frequencyTable);

        PrintStream printStream = new PrintStream(outFilename);
        printStream.write(frequencyTable.serialize());
        printStream.write(encodedByte);
        printStream.close();
    }


    private static void decode(String inFilename, String outFilename) throws IOException {
        final byte[] encodedInput = Files.readAllBytes(new File(inFilename).toPath());

        FrequencyTable frequencyTable = FrequencyTable.deserialize(encodedInput);
        final byte[] encodedBody = Arrays.copyOfRange(encodedInput, frequencyTable.byteSize(), encodedInput.length);
        final byte[] decodedByte = HuffmanCoding.decode(encodedBody, frequencyTable);

        PrintStream printStream = new PrintStream(outFilename);
        printStream.write(decodedByte);
        printStream.close();
    }


    private static String getOriginalFilename(String encodedFilename) throws Exception {
        if (!encodedFilename.endsWith("." + COMPRESSED_FILE_EXTENSION)) {
            throw new Exception("Переданный файл не упакован в формате " + COMPRESSED_FILE_EXTENSION);
        }

        return encodedFilename.substring(0, encodedFilename.lastIndexOf('.'));
    }
}
