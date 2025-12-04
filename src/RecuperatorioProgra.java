import java.util.List;
import models.Almacenable;
import models.Categoria;
import models.DispositivoDomotico;
import models.Inventario;


    public static void main(String[] args) {

    try {
        Almacenable<DispositivoDomotico> inv = new Inventario<>();

        inv.agregar(new DispositivoDomotico(1001, "AirPure S1", Categoria.AMBIENTAL, 35, 2023));
        inv.agregar(new DispositivoDomotico(1002, "AssistGo Mini", Categoria.ASISTENCIA, 50, 2019));
        inv.agregar(new DispositivoDomotico(1003, "SecureHome A7", Categoria.SEGURIDAD, 42, 2021));
        inv.agregar(new DispositivoDomotico(1004, "AirPure X2", Categoria.AMBIENTAL, 28, 2022));
        inv.agregar(new DispositivoDomotico(1005, "AssistGo Pro", Categoria.ASISTENCIA, 60, 2020));
        inv.agregar(new DispositivoDomotico(1006, "SafeWatch L3", Categoria.SEGURIDAD, 33, 2024));

        System.out.println("=== Inventario original ===");
        for (DispositivoDomotico d : inv.obtenerTodos()) {
            System.out.println(d);
        }

        // 1) Orden natural
        inv.ordenar();
        System.out.println("\n=== Orden natural ===");
        for (DispositivoDomotico d : inv.obtenerTodos()) {
            System.out.println(d);
        }

        // 2) Ordenar por nombreModelo (Comparator)
        inv.ordenar((a, b) -> a.getNombreModelo().compareToIgnoreCase(b.getNombreModelo()));
        System.out.println("\n=== Ordenados por nombre de modelo ===");
        for (DispositivoDomotico d : inv.obtenerTodos()) {
            System.out.println(d);
        }

        // 3) Filtrar consumo <= 40W (Predicate)
        System.out.println("\n=== Dispositivos con consumo <= 40W ===");
        List<DispositivoDomotico> filtrados =
                inv.filtrar(d -> d.getConsumoWatts() <= 40);

        for (DispositivoDomotico d : filtrados) {
            System.out.println(d);
        }

        // 4) Transformación: reducir 15% a ASISTENCIA (Function)
        System.out.println("\n=== Transformación (ASISTENCIA con -15% consumo) ===");

        List<DispositivoDomotico> transformados =
                inv.transformar(d -> {
                    if (d.getCategoria() == Categoria.ASISTENCIA) {
                        int nuevoConsumo = (int) (d.getConsumoWatts() * 0.85);
                        return new DispositivoDomotico(d.getCodigo(),d.getNombreModelo(),d.getCategoria(),nuevoConsumo,d.getAnioFabricacion());}
                    return d;
                });

        for (DispositivoDomotico d : transformados) {
            System.out.println(d);
        }

        // 5) Contar fabricados desde 2020 (Predicate)
        int recientes = inv.contar(d -> d.getAnioFabricacion() >= 2020);
        System.out.println("\nCantidad fabricados desde 2020: " + recientes);

        // 6) Persistencia
        inv.guardarEnBinario("data/inventario.bin");
        inv.guardarEnCSV("data/inventario.csv");
        inv.guardarEnJSON("data/inventario.json");

        // 7) Crear nuevo inventario y cargar desde CSV
        Almacenable<DispositivoDomotico> invCSV = new Inventario<>();

        invCSV.cargarDesdeCSV("data/inventario.csv",
                linea -> DispositivoDomotico.fromCSV(linea)
        );

        System.out.println("\n=== Inventario cargado desde CSV ===");
        for (DispositivoDomotico d : invCSV.obtenerTodos()) {
            System.out.println(d);
        }

    } catch (Exception e) {
        System.err.println("Error: " + e.getMessage());
    }
}
