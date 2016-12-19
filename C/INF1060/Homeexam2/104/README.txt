candidate number 104

 My program has two c files
1.servers.c
2 clients.c
3. answers on the theory questions the theory 
4.makefile

let me begin explaining  clients.c
This file has 5 methods and main
The main  pretty much create the socket for the client and 
it has the standard method like which connnects to the socket 
 The other method are 
1. error () which i use to print our error messages
2.void  receive_and_send () which request and receives data from
  the server .
3.menu() print out the menu
4. option() .it has the case that i input the  iput that the server will undersatand 
send back data to client
5. menucd() is is the menu of cd which is option 3


servers.c
 it has  8 methods plus the main
  the main method has the standard menthods like creating socket ,bind,listen,select and accept
 select and accept and in aternal loop .This is important because the server will receicng data accept
new client to connectand it will take data as long as their is a request from the client.
inside this while loop i call in receive_and_send ().
the other method i have commented on them in the code
* receive_and_send () method has several send and receive methods. 
inside this method the server will send back data to the client if request i among ls,pwd, cd, filetype, printing and q
some of this option have several request in themselves.
 if client  input 1 the  server will send list of file and folders
 if client  input 2 the  server will send the current working dir
 if client  input 3 the  server will send first print the cd menu which has nothing to do with the server
in cd option there are the more possible request in it
       1 if client  input 1 the  server will change the client to one level up in the path
       2.if client  input 2 the  server will change the client to the absolute directory
       3. client  input 3 the  server will send the list of folders and if he chooses on then 
the folder will be changed .. 
 After cd , if the client inputs number 4 ,the the server will send the list of all the 
available files if the client choose one of the files then the server will send back the file
 type . file is chosen by writhing its name
if the clients inputs number 5 ,then the server will send the list of all th available files .
 if the client chooses one of the files then the file will be printed on the clients side. all 
printable characters will be printed and the  unprintable will be replaced by a dot
file is chosen by writing its name
 the client can quite by inputting q and it will quite suceesfully.

NOTE if the client input unknown thing when in the main menu then nothing is sent to the server 
the client will appear to hanging BUT NOTE  ITS NOT HANGING . if you in out numbers 1-5, q and ?
 then will give back the request


i have freed all the memory.


LIMITATION. I has a limitation for option 3 ,4 and 5, because of the interaction
it the has to serve one client before it serves the others .I only realized when i was too late
I had an idea  to reduce the many interation in 3,4 and  5 interation but i was to late.
som impact on cd also 





       
