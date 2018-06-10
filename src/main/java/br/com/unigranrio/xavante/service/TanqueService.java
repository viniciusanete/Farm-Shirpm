package br.com.unigranrio.xavante.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.unigranrio.xavante.dao.TanqueDAO;
import br.com.unigranrio.xavante.model.Tanque;

@Service
public class TanqueService {

	public Tanque cadastrarTanque(Tanque tanque) {
		TanqueDAO tanquedao = new TanqueDAO();
		return tanquedao.CadastrarTanque(tanque);
	}

	public List<Tanque> buscarTanques() {
		TanqueDAO tanquedao = new TanqueDAO();
		return tanquedao.buscarTodos();
		
	}
}
