package view;

import business.Server;
import domain.Candidato;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Grafica {

    private List<Candidato> Postulados;
    private List<Candidato> Ganadores;
    private List<Candidato> Perdedores;
    private Server server;

    public void graficar(Server pServer) {
        this.server = pServer;
        Postulados = server.getPostulantes();
        Ganadores = server.getGanadores();
        Perdedores = server.getPerdedores();

        DefaultCategoryDataset ds = new DefaultCategoryDataset();

        for (Candidato candidato : Ganadores) {
            ds.addValue(candidato.mis_votos.size(), candidato.getNombre(), candidato.getAgrupacion());
        }
        for (Candidato candidato : Postulados) {
            ds.addValue(candidato.mis_votos.size(), candidato.getNombre(), candidato.getAgrupacion());
        }
        for (Candidato candidato : Perdedores) {
            ds.addValue(candidato.mis_votos.size(), candidato.getNombre(), candidato.getAgrupacion());
        }
        JFreeChart jf = ChartFactory.createBarChart3D("STV", "Candidatos", "Votos", ds, PlotOrientation.HORIZONTAL, true, true, true);
        ChartFrame f = new ChartFrame("STV", jf);
        f.setSize(800, 600);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
