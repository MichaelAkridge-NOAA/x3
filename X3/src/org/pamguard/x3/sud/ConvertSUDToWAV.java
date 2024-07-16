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
        sudParams.setVerbose(false);
        sudParams.setFileSave(false, true, true, false);
        sudParams.setSudEnable(true, true, true);

        // Create output directory if it doesn't exist
        File outputDir = new File(outputFilePath).getParentFile();
        if (outputDir != null && !outputDir.exists()) {
            outputDir.mkdirs();
        }

        SudFileExpander sudFileExpander = new SudFileExpander(new File(inputFilePath), sudParams);

        try {
            sudFileExpander.processFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        long time1 = System.currentTimeMillis();
        System.out.println("Processing time: " + (time1 - time0));
    }
}
