package pl.put.poznan.sqc.logic.analysis;

import pl.put.poznan.sqc.model.Scenario;
import pl.put.poznan.sqc.model.Step;

import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Class, which created .txt file with given scenario with steps number.
 * @author      Radosław Bodus
 * @version     1.0
 * @since       v1.0.1-alpha
 */
public class TextWithStepNumbersDownloader implements ScenarioVisitor {

    /**
     * Main function of this class
     * @param scenario      scenario, which will be changed
     * @return              file with output
     */
    @Override
    public String visitScenario(Scenario scenario) {
        try {
            goThroughSteps(scenario.getSteps());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String fileString = readFileAsString("scenarioFile.txt");
        return fileString;
    }

    /**
     * Function, which created FileWriter, created file in which the scenario will be saved.
     * @param steps         list of steps, which will be turned into .txt file.
     * @throws IOException  arbitrary IOException
     */
    private void goThroughSteps(ArrayList<Step> steps) throws IOException {
        FileWriter fileWriter = new FileWriter("scenarioFile.txt");

        writeLineToFile(steps, "",fileWriter);
        fileWriter.close();
        System.out.println("Successfully scenario wrote to the file.");
    }

    /**
     * Recursive function, which save steps to .txt file in such manner: <p>
     *          - steps in first level: X. Some text <p>
     *          - steps in second level: X.Y. Some text <p>
     *          - etc
     * @param steps             current level of steps, which will be saved with common prefix
     * @param prefix            prefix, which current set of steps will be saved
     * @param fileWriter        writer, which hold information about file, for which scenario is saved
     * @throws IOException      arbitrary IOException
     */
    private void writeLineToFile(ArrayList<Step> steps, String prefix, FileWriter fileWriter) throws IOException {
        Integer currentIndex = 1;

        for(Step step: steps){        
            String line = prefix + currentIndex +  ". " + step.getAction() + "\n";
            fileWriter.write(line);

            if(step.getSteps() != null){
                String nextPrefix = prefix + currentIndex + ".";
                writeLineToFile(step.getSteps(), nextPrefix,fileWriter);
            }
            currentIndex++;
        }
        return;
    }

    public static String readFileAsString(String fileName) {
        String text = "";
        try {
            text = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return text;
    }

}
