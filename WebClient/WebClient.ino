#include <SPI.h>
#include <Ethernet.h>

byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };
char server[] = "192.168.0.12";    // name address for Google (using DNS)
int port = 8080;


IPAddress ip(192, 168, 0, 177);
EthernetClient client;

void setup() {

  Serial.begin(9600);
  if (Ethernet.begin(mac) == 0) {
    Serial.println("Failed to configure Ethernet using DHCP");
    Ethernet.begin(mac, ip);
  }

  delay(1000);
  Serial.println("connecting...");


//  if (client.connect(server, port)) {
//    Serial.println("connected");
//  } else {
//    Serial.println("connection failed");
//  }
//  client.close();
}

void loop() {
  if (Serial.available() > 0) {   
    String conectar = Serial.readString();
    if(conectar == "sim"){
      String url = "/teste/25";
      Serial.println(url);
      delay(1000);
      String conteudo = " { \"texto\" : \"texto\", \"quantidade\" : 10 } ";
      
      enviaGet(url); 
      enviaPost(url, conteudo);
      conectar = "nao";
    }
  }
}

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
      client.flush();  
    

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


