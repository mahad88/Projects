#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <string.h>
#include <unistd.h>
#include <netinet/tcp.h>
#include <netdb.h> 
#include <fcntl.h>
#define MAX 9000
#define INN 50

void error(char *msg);    // method for error checking
void send_and_recv (char *choice); // method for sending and receiving request
void menu();                       // menu method
int options();                         // method for cases taht give the otions
void menucd();                         // menu for cd
      
int soc;
char * buffSender;

int main(int argc, char *argv[])
{
	  struct sockaddr_in serv_addr;
	  struct hostent *server;
	  int activate=1;
	  int portno;
           
   //check for arguments
	  if (argc < 3) 
	    {
	      fprintf(stderr,"usage %s hostname port\n", argv[0]);
	      exit(0);
	    }

	    portno = atoi(argv[2]);
	    soc = socket(AF_INET, SOCK_STREAM, 0); // creating a socket for the cleint
		    if(soc<0)
		    {
		      error("SOCKET ERROR");
		      exit(1);
		    }

	    int setp=setsockopt(soc, IPPROTO_TCP, TCP_NODELAY, &activate, sizeof(int));
		    if (setp<0)
		    {
		      error("error on setport");
		    }

	    server = gethostbyname(argv[1]);
		    if(server==NULL)
		    {
		      fprintf(stderr,"ERROR, unknown host \n");
		      exit(0);
		    }  

             // creating  a socket 
	    memset(&serv_addr,0, sizeof(serv_addr));
	    serv_addr.sin_family = AF_INET;
	    bcopy((char *)server->h_addr, (char *)&serv_addr.sin_addr.s_addr, server->h_length);
	    serv_addr.sin_port = htons(portno); 

       // connection to the socket 
	    int con=connect(soc,(struct sockaddr *) &serv_addr, sizeof(serv_addr));
		    if(con<0) 
		    { 
		      error("ERROR ON CONNECTION");
		      exit(1);
		   

		    }
	  
	    printf("Connection is ok: Client Side\n");

	    menu();  
	    options();
	    close(soc);
  return 0;	
 
} // close main

void error(char *msg)
{
	  perror(msg);
	  exit(1);

}
   
 /*This method send request to server and receives feedback 
it send specific request to server like from no 1-5 and q for quitting.
 for request nummber 3-5  there is several request.  i have explained more in my 
README file*/
void send_and_recv (char *choice)
{
    
	  char buffer[MAX];
	  char innText[MAX];

	  int i;
	  char filName[INN];
	  char strInput[INN];
	  char cdInput[INN];
	  char  strfolder[INN];
	  buffSender = choice;

	  while(1)
	    {
		      memset(buffer,0,sizeof(buffer));
		      memset(innText,0,sizeof(innText));
		      printf(" cmd (? for help)>");
		      fgets(buffer,MAX,stdin);
		      
	             
		      strcpy(buffer,buffSender);
		      i = send(soc,buffer,strlen(buffer),0);  //  send the first request to the server 
			      if (i< 0)
				{
				  error("ERROR On requesting from the server");
				}
		      
		      if(strcmp(buffer, "Menu") == 0)    // prints the menu and my menu is just on the clients side
			{
			  menu();
			  continue;
			}
		      else if(strcmp(buffer, "q") == 0)  // the clients quits
			      {
				  exit(0);
			      }

                       /*cd is request 3 and in the first instance it give the menu.
                         with the cd menu their three options -1,2 and 3 
                       i will explain each under thsi */
		      else if (strcmp(buffer,"cd")==0)  
			{
				  int lsrecv = recv(soc,buffer,sizeof(buffer),0);
					  if (lsrecv < 0) 
					    {
					      error("ERROR reading from serve( cd) ");
					    }
				  printf("\n");
				  menucd();
				  printf("%s",buffer);
				  //bzero(buffer,256);
				  memset(buffer,0,sizeof(buffer));
			  
			  if(fgets(cdInput,100,stdin) != NULL)
		  
			   strtok(cdInput,"\n");

                               /*  this option recieves   the directory list upon the first request 
                                    then the client get an option of selecting among the available ones
                                 and finally after making a choice directory is changed*/
				  if(strcmp(cdInput, "3") == 0)
				    {

				     strcpy(buffer,cdInput);

				     int sfile = send(soc,buffer,sizeof(buffer),0);
					     if (sfile < 0)
					     {
					     error("ERROR on requesting from server(cdtype)");
					     }

				     memset(buffer,0,sizeof(buffer));

				      int lsrecv = recv(soc,buffer,sizeof(buffer),0);
                                         
					      if (lsrecv < 0)
						{
						  error("ERROR reading from server (cdtype)");
						}
				      printf("%s\n",buffer);
					  
				      printf("choose a folder>");
				      if(fgets(strfolder,100,stdin) != NULL)
			  
					memset(buffer,0,sizeof(buffer));
				      strcpy(buffer,strfolder);
				      int choscd = send(soc,buffer,sizeof(buffer),0);
					      if (choscd < 0)
						{
						  error("ERROR on  requesting from server the changed dir");
						} 

				    }
                                   /* This option is ".."*/     
                                    if(strcmp(cdInput, "1") == 0)
				    {
				   	 strcpy(buffer,cdInput);

				         int sfile1 = send(soc,buffer,sizeof(buffer),0);
						  if (sfile1< 0)
						    {
						      error("ERROR on receiving from the server (..& /)");
						    }
                                     } 
                                         /*this is  "/"*/
                                     if(strcmp(cdInput, "2") == 0)
				     {
				     	strcpy(buffer,cdInput);

				   	int sfile2= send(soc,buffer,sizeof(buffer),0);
						 if (sfile2< 0)
						    {
						      error("ERROR on receiving from the server (..& /)");
						    }
                                      } 


			}

         /* This is option 5 which print a file . upon the first request the client gets all the files available
          then he/she can make a choice. if he request un available file then the server will inform him that the 
             file is not a available*/
	      else if(strcmp(buffer, "printing") == 0)
		{
	      
			  int lsrecv = recv(soc,buffer,sizeof(buffer),0);
			  if (lsrecv < 0)
			    {
			      error("ERROR on receiving ls cat ");
			    }
			  printf("%s\n",buffer);
			  //bzero(buffer,256);
			     
			  printf("input a file>" );
		      
			  if(fgets(strInput,100,stdin) != NULL)
		  
			  strcpy(buffer,strInput);
			  int sfile = send(soc,buffer,sizeof(buffer),0);
			  if (sfile < 0)
			    {
			      error("ERROR on requesting specific file (cat)");
			    }

			  
		    
	      
		}

        /* This is option 4 which tells the  a filetype . upon the first request the client gets all the files available
          then he/she can make a choice. if he request un available file then the server will inform him that the 
             file is not a available*/
	      else if(strcmp(buffer, "filetype") == 0)
		{
		  // receiving the of the files
		  int  rw = recv(soc,buffer,sizeof(buffer),0);
		  if (rw < 0)
		    {
		      error("ERROR on receiving from ls(4)");
		    }
		  printf("%s\n",buffer);
		  bzero(buffer,256);

		  // sending the file for info
		  printf("input a file> " );
		  if(fgets(filName,100,stdin) != NULL)
		    strcpy(buffer,filName);
		  printf("%s\n", buffer);
		   
		  int sfile = send(soc,buffer,sizeof(buffer),0);
		  if (sfile < 0)
			    {
			      error("ERROR requesting a specific file(4)");
			    }

		}
	      int  i = recv(soc,buffer,sizeof(buffer),0);
	      if (i < 0) 
		error("ERROR on receiving ");
	      // buffer[i] = '\0';
	   
	      printf("%s\n",buffer);
	      if(strcmp(buffer,"q")==0)
		{
		  printf("");
		  exit(0);
	     
		}

	      printf("cmd (? for help)>");
	       
	      options();

	    }// end of  while


}// end of send_and_receive


void menu() 
{
 
  printf("cmd (? for help)> ?\n");
  printf("! You are connected to the server\n");
  printf("! Please press a key:\n");
  printf("! [1] list content of current directory (ls)\n");
  printf("! [2] print name of current directory (pwd)\n");
  printf("! [3] change current directory (cd)\n");
  printf("! [4] get file information\n");
  printf("! [5] display file (cat)\n");
  printf("! [?] this menu\n");
  printf("! [q] quit\n");

 
}




int options()
{
  char input;
  while(1)
    { 

      scanf( "%c", &input );
      //}
      switch ( input ) 
	{
       
	case '1':             
	  printf( " This is the list of the files: \n" );
	  buffSender = "ls";
	  send_and_recv(buffSender);
	  break;
	case '2':          
	  printf( " This is the working directory : \n" );
	  buffSender = "pwd";
       
	  send_and_recv(buffSender);
	  break;
	case '3':         
	  buffSender = "cd";
	  send_and_recv(buffSender);
	
	  break;
	case '4':        	
	  buffSender = "filetype";
	  send_and_recv(buffSender);
	  break;
	case '5':        
	  buffSender = "printing";
	  send_and_recv(buffSender);
	  break;
	case 'q':        
	  printf(" The client quitted \n" );
	  buffSender = "q";
	  send_and_recv(buffSender);
	  exit(0);
	  //break;
	case '?':        
	  buffSender = "?";
	 
	  menu(); 
      
	default:            
	
	  buffSender = "none";
	 
	  continue;

	}
      return 0;
        
    }
}

void menucd()

{
  printf("[1] ..     the parent directory\n");
  printf("[2] / a new absolute directory\n");
  printf("[3]   a new directory relative to the current position\n");
 

}
