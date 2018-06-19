/* 
	Medicoes.h
	Autor: Hugo Rabock
*/

#ifndef Medicoes_h
#define Medicoes_h

#include "Arduino.h"

class Medicoes
{
	public:
		String realizarMedicao(int tipo);
		String RetornarObjeto(int tipo, String dataHora);
	private:
		String _medirPh(int pin);
		String _medirSalinidade(int pin);
		String _medirTemp(int pin);
		String _medirTransparencia(int pin);
		String _medirMateriaOrganica(int pin);
		String _medirOxigenioDissolvido(int pin);
		String _medirAlcalinidade(int pin);
		String _medirDureza(int pin);
		String _medirAmoniaTotal(int pin);
		String _medirNitrito(int pin);
		String _medirNitrato(int pin);
		String _medidirH2S(int pin);
		String _medirSilicato(int pin);
		String _medirSalinidadeFundo(int pin);
		
};
#endif