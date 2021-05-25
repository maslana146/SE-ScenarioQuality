package pl.put.poznan.sqc.logic.analysis;

import pl.put.poznan.sqc.model.Scenario;
import pl.put.poznan.sqc.model.Step;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TextWithStepNumbersDownloader implements ScenarioVisitor {
  
    @Override
    public String visitScenario(Scenario scenario) {
        try {
            goThroughSteps(scenario.getSteps(), "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "200";
    }

    private void goThroughSteps(ArrayList<Step> steps, String prefix) throws IOException {
        FileWriter fileWriter = new FileWriter("scenarioFile.txt");
        writeLineToFile(steps, "",fileWriter);
        fileWriter.close();
        System.out.println("Successfully scenario wrote to the file.");
    }

    private void writeLineToFile(ArrayList<Step> steps, String prefix, FileWriter fileWriter) throws IOException {
        Integer currentIndex = 1;

        for(Step step: steps){
            String line = prefix + currentIndex +  " " + step.getAction() + "\n";
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
