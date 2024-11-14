package src;

import com.google.gson.Gson;

import java.io.File;

public class Main {

    public static void main(String[] args){
        File configFile = new File("Config.json");
        Configuration configObj;

        if (configFile.exists()) {
            configObj = Configuration.loadConfigFile(configFile);
        }

        else {
            configObj = new Configuration();
            configObj.getUserInput();
            Configuration.saveConfigFile(configFile,configObj);
        }



    }
}
