
package Config;

import java.nio.file.Path;
import java.nio.file.Paths;


public class RutasArchivos {
    static final String BASE = "src/resources/data";
    static final String FILE_CSV = "dispositivos.csv";
    static final String FILE_BIN = "dispositivos.dat";

    
    public static Path getRutaCSV(){
        return Paths.get(BASE, FILE_CSV);
    }
    
    public static Path getRutaBin(){
        return Paths.get(BASE, FILE_BIN);
    }
    
    public static String getRutaCSVString(){
        return getRutaCSV().toString();
    }
    
    public static String getRutaBINString(){
        return getRutaBin().toString();
    }
}
