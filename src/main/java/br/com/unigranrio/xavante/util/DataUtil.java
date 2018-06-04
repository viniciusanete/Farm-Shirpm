package br.com.unigranrio.xavante.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DataUtil {
	public static final String DATA_INICIAL = "dataInicial";
	public static final String DATA_FINAL = "dataFinal";
	

	public static Date retornarData() {
		Date dataAtual = new Date();
		String dataFormat;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		dataFormat = sdf.format(dataAtual);
		try{
			dataAtual = sdf.parse(dataFormat);
		}catch (Exception e) {
		
		}
		return dataAtual;
	}
	
	public static Date incrementarDias(Integer dias) {
		Date dataAtual = new Date();
		String dataFormat;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dataAtual);
		calendar.add(Calendar.DAY_OF_MONTH, dias);
		dataAtual = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		dataFormat = sdf.format(dataAtual);
		try{
			dataAtual = sdf.parse(dataFormat);
		}catch (Exception e) {
		
		}
		return dataAtual;	
	}
	
	
	public static Date incrementarDias(Date date, Integer dias) {
	
		String dataFormat;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, dias);
		date = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		dataFormat = sdf.format(date);
		try{
			date = sdf.parse(dataFormat);
		}catch (Exception e) {
		
		}
		return date;	
	}
	
	
	public static Date retornarDataAmanha() {
		Date dataAtual = new Date();
		String dataFormat;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dataAtual);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		dataAtual = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		dataFormat = sdf.format(dataAtual);
		try{
			dataAtual = sdf.parse(dataFormat);
		}catch (Exception e) {
		
		}
		return dataAtual;
	}
	public static Date retornarDataHora() {
		Date dataAtual = new Date();
		return dataAtual;
	}
	

	public static Date convertData(String data) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try{
			return sdf.parse(data);
		}catch (Exception e) {
			return null;
		}
	}
	
	public static Date convertDataHora(String data) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		try{
			return sdf.parse(data);
		}catch (Exception e) {
			return null;
		}
	}
	
	public static boolean validarData(String data) {
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");	
			sdf.setLenient(false);
			sdf.parse(data);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	public static Map<String, Date> retornarRangeDatas(String data){
		if(data == null) 
			data = "";
		String datas[] = new String[2];
		datas = data.split("-");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
		Date dataInicio;
		Date dataFim;
		Map<String, Date> dataRetorno = new HashMap<>();
		
		
		if(!data.equals("")) {
			try{
				sdf.setLenient(false);
				dataInicio = sdf.parse(datas[0]);
				dataFim = sdf.parse(datas[1]);
			}catch(Exception e) {
				e.printStackTrace();
				return null;
			}
			if(dataInicio.compareTo(dataFim) < 0) {
				dataFim = incrementarDias(dataFim, 1);
				dataRetorno.put(DataUtil.DATA_INICIAL, dataInicio);
				dataRetorno.put(DataUtil.DATA_FINAL, dataFim);
			}else if(dataInicio.compareTo(dataFim) > 0)  {
				dataInicio = incrementarDias(dataInicio, 1);
				dataRetorno.put(DataUtil.DATA_INICIAL,dataFim);
				dataRetorno.put(DataUtil.DATA_FINAL,dataInicio);
			}else {
				dataFim = incrementarDias(dataFim, 1);
				dataRetorno.put(DataUtil.DATA_INICIAL, dataInicio);
				dataRetorno.put(DataUtil.DATA_FINAL, dataFim);
			}
			return  dataRetorno;
			}
		else
			dataRetorno.put(DataUtil.DATA_INICIAL, retornarData());
			dataRetorno.put(DataUtil.DATA_FINAL, retornarDataAmanha());
			return dataRetorno;
		
	}
}
