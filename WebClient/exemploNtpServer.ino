/*
alterado por Fabiano A. Arndt,
baseados nos créditos abaixo.
 
fabianoallex@gmail.com
www.youtube.com/fabianoallex
*/
 
/*
Udp NTP Client with DNS
I updated the example code to use DNS resolution from the Arduino 1.0 IDE
Basically tries to use the load-balanced NTP servers which are
"magically" returned from DNS queries on pool.ntp.org
If DNS fails, fall back to the hardcoded IP address
for time.nist.gov
bearpaw7 (github), 01DEC2011
This code is also in the public domain.
*/
 
/*
Udp NTP Client
 
Get the time from a Network Time Protocol (NTP) time server
Demonstrates use of UDP sendPacket and ReceivePacket 
For more on NTP time servers and the messages needed to communicate with them, 
see http://en.wikipedia.org/wiki/Network_Time_Protocol
 
created 4 Sep 2010 
by Michael Margolis
modified 17 Sep 2010
by Tom Igoe
 
This code is in the public domain.
 
*/
 
/**********************************************************************************
************************************BIBLIOTECAS************************************
**********************************************************************************/
#include <SPI.h>   
#include <Ethernet.h>
#include <EthernetUdp.h>
#include "Dns.h"
/**********************************************************************************
************************************FIM BIBLIOTECAS********************************
**********************************************************************************/
 
/**********************************************************************************
************************************ETHERNET CONFIG/FUNCTIONS**********************
***********************************************************************************
se for rodar numa rede com firewall, verifique se os ip utilizado está liberado.
Servidores da NTP.br
a.st1.ntp.br 200.160.7.186 e 2001:12ff:0:7::186
b.st1.ntp.br 201.49.148.135
c.st1.ntp.br 200.186.125.195
d.st1.ntp.br 200.20.186.76
a.ntp.br 200.160.0.8 e 2001:12ff::8
b.ntp.br 200.189.40.8
c.ntp.br 200.192.232.8
**********************************************************************************/
 
byte mac[] = {  0x00, 0x00, 0xAA, 0xBB, 0xCC, 0xDD};
unsigned int localPort = 8888;   // local port to listen for UDP packets
IPAddress timeServer(200, 192, 232, 8);  // time.nist.gov NTP server (fallback) - segunda tentativa caso a primeira de erro
 
const int NTP_PACKET_SIZE= 48;    // NTP time stamp is in the first 48 bytes of the message
byte packetBuffer[ NTP_PACKET_SIZE];   // buffer to hold incoming and outgoing packets 
 
//host para a primeira tentativa
const char* host = "ntp02.oal.ul.pt";  // servidor da NTP.br - ver lista acima para todos os servidores da NTP.br
//const char* host = "192.168.200.254";  // servidor interno 01 - caso tenha um servidor de hora interno, pode ser configurado o nome ou ip na variavel host
//const char* host = "192.168.200.253";  // servidor interno 02
 
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
 
/**********************************************************************************
************************************FIM ETHERNET CONFIG/FUNCTIONS******************
**********************************************************************************/
 
/**********************************************************************************
************************************UNIX TIME FUNCTIONS****************************
***********************************************************************************
localTime --> converte o unix time para ano, mes, dia semana, dia, hora, 
              minuto e segundo
              função baseada na biblioteca dateTime, pois não era necessário usar 
              toda a biblioteca.
              (http://playground.arduino.cc/Code/DateTime)
              descrição original: 
                convert the given timep to time components
                this is a more compact version of the C library localtime function
**********************************************************************************/
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
 
/**********************************************************************************
************************************FIM UNIX TIME FUNCTIONS************************
**********************************************************************************/
 
/**********************************************************************************
**************************************** FUNÇÕES FORMATAR DATA/HORA ***************
**********************************************************************************/
String zero(int a){ if(a>=10) {return (String)a+"";} else { return "0"+(String)a;} }
 
String diaSemana(byte dia){
  String str[] = {"Domingo", "Segunda-feira", "Terça-feira", "Quarta-feira", "Quinta-feira", "Sexta-feira", "Sabado"};
  return str[dia];
}
/**********************************************************************************
****************************************FIIM FUNÇÕES FORMATAR DATA/HORA ***********
**********************************************************************************/
 
/**********************************************************************************
**************************************** SETUP / LOOP *****************************
**********************************************************************************/
void setup() {
  pinMode(4, OUTPUT);      //ANTES DE INICIAR O ETHERNET, DESABILITA O SDCARD
  digitalWrite(4, HIGH);
 
  Serial.begin(9600);
  if (Ethernet.begin(mac) == 0) { Serial.println("Failed to configure Ethernet using DHCP"); }
   
  Udp.begin(localPort);
  Dns.begin(Ethernet.dnsServerIP() );
}
 
void loop() {
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
   
  delay(1000); //aguarda um segundo, para receber os dados enviados.
   
  if ( Udp.parsePacket() ) {  
    // We've received a packet, read the data from it
    Udp.read(packetBuffer, NTP_PACKET_SIZE);  // read the packet into the buffer
   
    // the timestamp starts at byte 40 of the received packet and is four bytes,
    // or two words, long. First, esxtract the two words:
    unsigned long highWord = word(packetBuffer[40], packetBuffer[41]);
    unsigned long lowWord = word(packetBuffer[42], packetBuffer[43]);  
     
    // combine the four bytes (two words) into a long integer
    // this is NTP time (seconds since Jan 1 1900):
    unsigned long secsSince1900 = highWord << 16 | lowWord;  
    Serial.print("Segundos desde 1 de Jan. de 1900 = " );
    Serial.println(secsSince1900);     
   
    Serial.print("Unix time = ");
    const unsigned long seventyYears = 2208988800UL;      // Unix time starts on Jan 1 1970. In seconds, that's 2208988800:
    unsigned long epoch = secsSince1900 - seventyYears;  //desconta 70 anos
    // print Unix time:
    Serial.println(epoch);         
 
    byte ano, mes, dia, dia_semana, hora, minuto, segundo;            
    localTime(&epoch, &segundo, &minuto, &hora, &dia, &dia_semana, &mes, &ano); //extrai data e hora do unix time
     
    Serial.print("Ano: ");
    Serial.println(ano+1900);
    Serial.print("Mes: ");
    Serial.println(mes+1);
    Serial.print("Dia da semana: ");
    Serial.println(dia_semana);
    Serial.print("Dia: ");
    Serial.println(dia);
    Serial.print("Hora: ");
    Serial.println(hora);
    Serial.print("minunto: ");
    Serial.println(minuto);
    Serial.print("segundo: ");
    Serial.println(segundo);
     
    String s = diaSemana(dia_semana) + ", " + zero(dia) + "/" + zero(mes+1) + "/" + (ano+1900) + " " + zero(hora) + ":" + zero(minuto) + ":" + zero(segundo);
     
    Serial.println(s);
    Serial.println(" ");
  }
   
  delay(10000); //atualiza novamente em 10 segundos
}
 
 
 
/**********************************************************************************
**************************************** FIM SETUP / LOOP *************************
**********************************************************************************/
