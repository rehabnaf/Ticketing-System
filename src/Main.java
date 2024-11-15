package src;

import com.google.gson.Gson;

import java.io.File;

public class Main {

    public static void main(String[] args){
        File configFile = new File("Config.json");
        Configuration configObj;

        if (configFile.exists()) {
            configObj = Configuration.loadConfigFile(configFile);
            // Loads the configuration file that was saved when the program was run for the first time
        }

        else {
            configObj = new Configuration();
            configObj.getUserInput();
            Configuration.saveConfigFile(configFile,configObj);
            // Saves the configuration file so that we don't have to ask for user input everytime we run the program
        }



    }
}
