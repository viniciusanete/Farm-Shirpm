package br.com.unigranrio.xavante.service;

import java.util.ArrayList;
import java.util.List;

import br.com.unigranrio.xavante.dto.MedicaoDTO;
import br.com.unigranrio.xavante.dto.MedicaoRetornoDTO;
import br.com.unigranrio.xavante.model.Medicao;
import br.com.unigranrio.xavante.model.Tanque;

public class GerenteTempoReal {
	private List<Tanque> tanques;
	private static GerenteTempoReal gerente; 
	public static GerenteTempoReal getGerente() {
		if(gerente == null)
			gerente = new GerenteTempoReal();
		return gerente;
	}
	
	public GerenteTempoReal() {
		this.tanques = new ArrayList<Tanque>();
	}

	public  List<Tanque> listaTanques(){
		return tanques;
	}
	public  List<MedicaoRetornoDTO> medicaoTanque(Long tanque){
		List<MedicaoRetornoDTO> medicoes = new ArrayList<MedicaoRetornoDTO>();
		for(Tanque tanq : tanques) {
			if (tanq.getId() == tanque) {
				for(Medicao med : tanq.getMedicao()) {
					med.setTanque(tanq);
					medicoes.add(med.devolveDto());
				}
			}
		}
		return medicoes;
	}
	
	public void atribuirMedicaoReal(Tanque tanque) {
		if (tanques.contains(tanque))
			tanques.remove(tanque);
		tanques.add(tanque);
	}

}
 