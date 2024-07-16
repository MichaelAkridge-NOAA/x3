package org.pamguard.x3.sud;

import java.io.File;
import java.io.IOException;

/**
 * Basic test for opening a .sud file and converting it to .wav
 */
public class ConvertSUDToWAV {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: java ConvertSUDToWAV <input_sud_file> <output_wav_file>");
            System.exit(1);
        }

        String inputFilePath = args[0];
        String outputFilePath = args[1];

        System.out.println("Input SUD file: " + inputFilePath);
        System.out.println("Output WAV file: " + outputFilePath);

        long time0 = System.currentTimeMillis();

        SudParams sudParams = new SudParams();
        sudParams.setVerbose(true); // Enable verbose for debugging
        sudParams.setFileSave(true, true, true, true); // Enable file saving for all types
        sudParams.setSudEnable(true, true, true);
        
        // Print out the file save settings for debugging
        System.out.println("File save settings: ");
        System.out.println("Save WAV: " + sudParams.isFileSave(ISudarDataHandler.WAV_FTYPE, "wav"));
        System.out.println("Save CSV: " + sudParams.isFileSave(ISudarDataHandler.CSV_FTYPE, "csv"));
        System.out.println("Save XML: " + sudParams.isFileSave(ISudarDataHandler.XML_FTYPE, "xml"));
        System.out.println("Save Clicks: " + sudParams.isFileSave(ISudarDataHandler.TXT_FTYPE, "bcl"));

        // Create output directory if it doesn't exist
        File outputDir = new File(outputFilePath).getParentFile();
        if (outputDir != null && !outputDir.exists()) {
            outputDir.mkdirs();
            System.out.println("Created output directory: " + outputDir.getAbsolutePath());
        }

        SudFileExpander sudFileExpander = new SudFileExpander(new File(inputFilePath), sudParams);

        try {
            sudFileExpander.processFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        long time1 = System.currentTimeMillis();
        System.out.println("Processing time: " + (time1 - time0));

        // Check if the output file was created
        File outputFile = new File(outputFilePath);
        if (outputFile.exists()) {
            System.out.println("Output file created: " + outputFile.getAbsolutePath());
        } else {
            System.out.println("Output file not created: " + outputFile.getAbsolutePath());
        }
    }
}
