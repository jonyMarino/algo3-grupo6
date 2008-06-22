package controlador;

import programaAuto.ProgramaAuto;

public class ControladorJuego {
	
	private ProgramaAuto programaAuto;
	
	public ControladorJuego() {
		this.setProgramaAuto(null);
	}

	public ProgramaAuto getProgramaAuto() {
		return programaAuto;
	}

	public void setProgramaAuto(ProgramaAuto programaAuto) {
		this.programaAuto = programaAuto;
	}
	

}
