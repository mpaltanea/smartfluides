package fr.osb.smartf.agent.dispatcher.controller;

import java.util.Arrays;
import java.util.Optional;

/**
 * Created by szagoret on 03.06.2016.
 */

/**
 * Expected import types
 */

public enum ImportFileFormat {
    CSV("csv", "importCsvFile"), XML("xml", "importXmlFile"), JSON("json", "importJsonFile");

    private String extensionValue;
    private String methodName;

    ImportFileFormat(String extensionValue, String methodName) {
        this.extensionValue = extensionValue;
        this.methodName = methodName;
    }

    public static ImportFileFormat getExtensionType(String fileName) {

        /**
         * Split filename to get extension, this regex return only
         * last occurrence after dot symbol
         */
        String[] splittedFileName = fileName.split("\\.(?=[^\\.]+$)");

        if (splittedFileName.length == 2) {

            String fileExtension = splittedFileName[1];

            /**
             * Iterate all possibles extension values and select matched extension
             * if extension is unwanted then throw an exeption
             * else return extension type
             */
            Optional<ImportFileFormat> extensionType = Arrays.asList(values()).stream().filter(
                    extension -> extension.extensionValue.equals(fileExtension)).findFirst();
            if (extensionType.isPresent()) {
                return extensionType.get();
            } else {
                throw new IllegalArgumentException("File " + fileName + " has unexpected extension");
            }

        } else {
            throw new IllegalArgumentException("File " + fileName + " name has invalid extension");
        }
    }

    public String getMethodName() {
        return methodName;
    }
}