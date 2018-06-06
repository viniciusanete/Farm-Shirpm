#include <Medicoes.h>
#include <SPI.h>
#include <Ethernet.h>
#include <EthernetUdp.h>
#include "Dns.h"

byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };
char server[] = "192.168.0.12";    // name address for Google (using DNS)
int port = 8080;
String arduinoCodigo = "AR1";


IPAddress ip(192, 168, 0, 177);
EthernetClient client;
Medicoes medicao;
String medidaString;
String Controle;



void enviaPost(String url, String conteudo){

   if(client.connect(server, port)){
      client.println("POST "+url + " HTTP/1.1");
      client.print("Host: ");
      client.print(server);
      client.println(":8080");
      client.println("Content-Type: application/json");
      client.println("Cache-Control: no-cache");
      client.println("Connection:close");
      client.print("Content-Length:");
      client.println(conteudo.length());
      client.println();
      client.print(conteudo);
      Serial.println("inserido");

      Serial.println("POST "+url + " HTTP/1.1");
      Serial.print("Host: ");
      Serial.print(server);
      Serial.println(":8080");
      Serial.println("Content-Type: application/json");
      Serial.println("Cache-Control: no-cache");
      Serial.println("Connection:close");
      Serial.print("Content-Length:");
      Serial.println(conteudo.length());
      Serial.println();
      Serial.print(conteudo);  
    

   }
   else
   Serial.println("erro na conexão");
   client.stop();
}

void enviaGet(String url){

   if(client.connect(server, port)){
      client.println("GET "+url + " HTTP/1.1");
      client.print("Host: ");
      client.print(server);
      client.println(":8080");
      client.println("Content-Type: application/json");
      client.println("Cache-Control: no-cache");
      client.println("Connection:close");
      client.println();
      Serial.println("inserido");
      client.flush();  
   }
   else
   Serial.println("erro na conexão");
   client.stop();
}

/* ------------------ UDP ---------------------------------*/
unsigned int localPort = 8888; 
IPAddress timeServer(200, 192, 232, 8); 
 
const int NTP_PACKET_SIZE= 48;    
byte packetBuffer[ NTP_PACKET_SIZE]; 
const char* host = "ntp02.oal.ul.pt"; 
 
EthernetUDP Udp;
DNSClient Dns;
IPAddress rem_add;
 
// send an NTP request to the time server at the given address 
unsigned long sendNTPpacket(IPAddress& address) {
  // set all bytes in the buffer to 0
  memset(packetBuffer, 0, NTP_PACKET_SIZE); 
 
  // Initialize values needed to form NTP request
  // (see URL above for details on the packets)
  packetBuffer[0] = 0b11100011;   // LI, Version, Mode
  packetBuffer[1] = 0;    // Stratum, or type of clock
  packetBuffer[2] = 6;    // Polling Interval
  packetBuffer[3] = 0xEC;  // Peer Clock Precision
 
  // 8 bytes of zero for Root Delay & Root Dispersion
  packetBuffer[12] = 49; 
  packetBuffer[13] = 0x4E;
  packetBuffer[14] = 49;
  packetBuffer[15] = 52;
  
  // all NTP fields have been given values, now
  // you can send a packet requesting a timestamp: 
  Udp.beginPacket(address, 123); //NTP requests are to port 123
  Udp.write(packetBuffer,NTP_PACKET_SIZE);
  Udp.endPacket(); 
}
 
#define LEAP_YEAR(_year) ((_year%4)==0)
static  byte monthDays[] = {31, 28, 31, 30 , 31, 30, 31, 31, 30, 31, 30, 31};
 
void localTime(unsigned long *timep, byte *psec, byte *pmin, byte *phour, byte *pday, byte *pwday, byte *pmonth, byte *pyear) {
  unsigned long long epoch =* timep;
  byte year;
  byte month, monthLength;
  unsigned long days;
   
  *psec  =  epoch % 60;
  epoch  /= 60; // now it is minutes
  *pmin  =  epoch % 60;
  epoch  /= 60; // now it is hours
  *phour =  epoch % 24;
  epoch  /= 24; // now it is days
  *pwday =  (epoch+4) % 7;
   
  year = 70;  
  days = 0;
  while((unsigned)(days += (LEAP_YEAR(year) ? 366 : 365)) <= epoch) { year++; }
  *pyear=year; // *pyear is returned as years from 1900
   
  days  -= LEAP_YEAR(year) ? 366 : 365;
  epoch -= days; // now it is days in this year, starting at 0
   
  for (month=0; month<12; month++) {
    monthLength = ( (month==1) && LEAP_YEAR(year) ) ? 29 : monthDays[month];  // month==1 -> february
    if (epoch >= monthLength) { epoch -= monthLength; } else { break; }
  }
   
  *pmonth = month;  // jan is month 0
  *pday   = epoch+1;  // day of month
}
 
String zero(int a){ if(a>=10) {return (String)a+"";} else { return "0"+(String)a;} }
 
String diaSemana(byte dia){
  String str[] = {"Domingo", "Segunda-feira", "Terça-feira", "Quarta-feira", "Quinta-feira", "Sexta-feira", "Sabado"};
  return str[dia];
}

String montaDataHora(byte dia, byte mes, byte ano, byte hora, byte minuto, byte segundo)
{
  return String(dia)+"/"+String(mes+1)+"/"+String(ano+1900)+" "+ String(hora-3)+ ":"+ String(minuto)+":"+ String(segundo);
}
void setup() {


  Serial.begin(9600);
  if (Ethernet.begin(mac) == 0) {
    Serial.println("Failed to configure Ethernet using DHCP");
    Ethernet.begin(mac, ip);
  }

  Udp.begin(localPort);
  Dns.begin(Ethernet.dnsServerIP() );

  delay(1000);
  Serial.println("connecting...");
}


void loop() {
    Controle = "nao";
    Controle = Serial.readString();
    if(Controle == "sim"){

     if(Dns.getHostByName(host, rem_add) == 1 ){
      Serial.println("DNS resolve...");  
      Serial.print(host);
      Serial.print(" = ");
      Serial.println(rem_add);
      sendNTPpacket(rem_add);
    } else {
      Serial.println("DNS fail...");
      Serial.print("time.nist.gov = ");
      Serial.println(timeServer); // caso a primeira tentativa não retorne um host válido
      sendNTPpacket(timeServer);  // send an NTP packet to a time server
    }
    delay(1000); 
   
    if ( Udp.parsePacket() ) {  
      Udp.read(packetBuffer, NTP_PACKET_SIZE);  // read the packet into the buffer
     unsigned long highWord = word(packetBuffer[40], packetBuffer[41]);
     unsigned long lowWord = word(packetBuffer[42], packetBuffer[43]);  
     unsigned long secsSince1900 = highWord << 16 | lowWord; 
     const unsigned long seventyYears = 2208988800UL;  
     unsigned long epoch = secsSince1900 - seventyYears;  //desconta 70 anos
     byte ano, mes, dia, dia_semana, hora, minuto, segundo;            
     localTime(&epoch, &segundo, &minuto, &hora, &dia, &dia_semana, &mes, &ano);
   
    Serial.println("Medição registrada");
    medidaString = medicao.RetornarObjeto(0, montaDataHora(dia, mes, ano, hora,minuto, segundo));
    enviaPost("/auth/medicao/arduino/"+arduinoCodigo ,medidaString);
    }
  }
}




