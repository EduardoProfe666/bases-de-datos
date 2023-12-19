package visual.models.tables;

import services.TSServices.AuxEscalafon;

public class EscalafonYearTableModel extends MainModelTableModel<AuxEscalafon> {
	private static final long serialVersionUID = 1L;

	public EscalafonYearTableModel() {
		super(new String[] {"No.", "Grupo", "Nombre y Apellidos", "Sexo", "Municipio", "Promedio Acumulado"});
	}
 
	@Override
	public void add(AuxEscalafon t) {
		this.addRow(new Object[] {
				t.getNumOrder(), 
				t.getStd().getGroup().getNumGroup(), 
				t.getStd().getNames()+" "+t.getStd().getLastNames(),
				t.getStd().getSex()=='F'? "Femenino" : "Masculino",
				t.getStd().getMunicipal(),
				(Math.floor(t.getAvg()*100))/100
		});
	}
}
