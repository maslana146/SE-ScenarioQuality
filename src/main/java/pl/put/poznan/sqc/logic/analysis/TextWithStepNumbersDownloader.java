package pl.put.poznan.sqc.logic.analysis;

import pl.put.poznan.sqc.model.Scenario;
import pl.put.poznan.sqc.model.Step;

import java.io.FileWriter;
import java.io.IOException;

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
     * @return              true, if downloading end with success, false otherwise
     */
    @Override
    public Boolean visitScenario(Scenario scenario) {
        try {
            goThroughSteps(scenario.getSteps());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

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


}
