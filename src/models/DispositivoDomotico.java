
package models;


public class DispositivoDomotico implements CSVConvertible, Comparable<DispositivoDomotico>{
    private static final long serialVersionUID = 1L;
    private int codigo;
    private String nombreModelo;
    private Categoria categoria;
    private int consumoWatts;
    private int anioFabricacion;

    public DispositivoDomotico(int codigo, String nombreModelo, Categoria categoria, int consumoWatts, int anioFabricacion) {
        this.codigo = codigo;
        this.nombreModelo = nombreModelo;
        this.categoria = categoria;
        this.consumoWatts = consumoWatts;
        this.anioFabricacion = anioFabricacion;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombreModelo() {
        return nombreModelo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public int getConsumoWatts() {
        return consumoWatts;
    }

    public int getAnioFabricacion() {
        return anioFabricacion;
    }
    
    

    public String toCSV(){
        return codigo + "," + nombreModelo + "," + categoria + "," + consumoWatts + "," + anioFabricacion;
    }
    
        
    public static String toHeader(){
        return "codigo,nombreModelo,categoria,consumoWatts,añoFabricacion\n";
    }
    
    @Override
    public String toString() {
        return "DispositivoDomotico{" + "codigo=" + codigo + ", nombreModelo=" + nombreModelo + ", categoria=" + categoria + ", consumoWatts=" + consumoWatts + ", anioFabricacion=" + anioFabricacion + '}';
    }
    
    public static DispositivoDomotico fromCSV(String linea){
        if(linea.endsWith("\n"))
            linea = linea.substring(0, linea.length() - 1);
        
        String [] datosCSV = linea.split(",");
        return new DispositivoDomotico(Integer.parseInt(datosCSV[0]), datosCSV[1], Categoria.valueOf(datosCSV[3]), Integer.parseInt(datosCSV[4]), Integer.parseInt(datosCSV[5]));
    }
    
    @Override
        public int compareTo(DispositivoDomotico o) {
            int cmp = Integer.compare(o.anioFabricacion, this.anioFabricacion); // DESC
                if (cmp != 0) return cmp;

        return Integer.compare(this.consumoWatts, o.consumoWatts); // ASC
    }
    
}


//. Clase DispositivoDomotico 
//Debe: 
//Implementar CSVConvertible 
//• Implementar Comparable<DispositivoDomotico> 
//• Usar como orden natural: 
//1. más moderno primero (anioFabricacion descendente) 
//2. a igual año, menor consumo (consumoWatts) primero 
//• Definir: 
//o toCSV() → con separador coma ok
//o static String toHeaderCSV() ok
//o static DispositivoDomotico fromCSV(String linea) ok
//o toString() descriptivo ok