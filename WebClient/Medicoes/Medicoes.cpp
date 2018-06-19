/* 
	Medicao.h
	Autor: Hugo Rabock
*/

#include "Arduino.h"
#include "Medicoes.h"
	
		
	String Medicoes::RetornarObjeto(int tipo, String dataHora)
	{
		String retorno;
		if (dataHora == "")
			retorno = "{ \"dataMedicao\": \"01/01/2001 00:00:00:\", \"registro\": "+ realizarMedicao(tipo) + ", \"tipo\": "+ String(tipo)+ " }";
		else		
			retorno = "{ \"dataMedicao\": \"" + dataHora +"\", \"registro\": "+ realizarMedicao(tipo) + ", \"tipo\": "+ String(tipo)+ " }";
		return retorno;
	}
	
	String Medicoes::realizarMedicao(int tipo)
	{
		String medida;
		switch(tipo){
			case 0:
				medida = _medirPh(A0);
				break;
			case 1:
				medida = _medirSalinidadeFundo(A7);
				break;
			case 2: 
				medida = _medirSalinidade(A2);
				break;
			case 3:
				medida = _medirTemp(A2);
				break;
			case 4:
				medida = _medirTransparencia(A3);
				break;
			case 5: 
				medida = _medirMateriaOrganica(A3);
				break;
			case 6:
				medida = _medirOxigenioDissolvido(A5);
				break;
			case 7:
				medida = _medirAlcalinidade(A5);
				break;
			case 8:
				medida = _medirDureza(A4);
				break;
			case 9:
				medida = _medirAmoniaTotal(A4);
				break;
			case 10: 
				medida = _medirNitrito(A4);
				break;
			case 11:
				medida = _medirNitrato(A6);
				break;
			case 12: 
				medida = _medidirH2S(A6);
				break;
			case 13:
				medida = _medirSilicato(A7);	
				break;				
			default: 
				medida = "0.1";
				
		}
		return medida;
	};
	String Medicoes::_medirTemp(int pin)
	{
		//return String(float(analogRead(pin)*5/(1023))/0.01);
		return String(rand() %20 + 25);
	};
	
	String Medicoes::_medirPh(int pin)
	{
		return  String(rand() %4 + 6);
	};
	
	String Medicoes::_medirSalinidade(int pin)
	{
		return  String(rand() %7 + 19);
	};
	
	String Medicoes::_medirTransparencia(int pin)
	{
		return  String(rand() %7 + 29);
	};
	
	String Medicoes::_medirMateriaOrganica(int pin)
	{
		return  "\"Medicao de materia organica\"";
	};
	
	String Medicoes::_medirOxigenioDissolvido(int pin)
	{
		return  String(rand() %4 + 5);
	};
	
	String Medicoes::_medirAlcalinidade(int pin)
	{
		return  String(rand() %150 + 100);
	};
	
	String Medicoes::_medirDureza(int pin)
	{
		return  String(rand() %2200 + 100);
	};	
	
	String Medicoes::_medirAmoniaTotal(int pin)
	{
		return  String(rand() %2 + 0.1);
	};	
	
	String Medicoes::_medirNitrito(int pin)
	{
		return  String(rand() %8 + 0.2);
	};
	
	String Medicoes::_medirNitrato(int pin)
	{
		return  String(rand() %11 );
	};	
	
	String Medicoes::_medidirH2S(int pin)
	{
		return  String(rand() %2 );
	};
	
	String Medicoes::_medirSilicato(int pin)
	{
		return  String(rand() %2);
	};
	
	String Medicoes::_medirSalinidadeFundo(int pin)
	{
		return  String(rand() %7 + 19);
	};
	