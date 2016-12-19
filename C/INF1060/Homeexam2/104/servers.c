
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h> 
#include <sys/socket.h>
#include <netinet/in.h>
#include <netinet/tcp.h>
#include <strings.h>
#include <string.h>
#include <unistd.h>
#include <sys/uio.h>
#include <dirent.h> 
#include <sys/time.h> 
#include<netdb.h>
#include <arpa/inet.h>
#include <sys/stat.h>
#include <errno.h>
#include <fcntl.h>


#define MAXI 9000
#define MAX_CLIENTS  100
#define MAXIN 200



void error(char * msg); // this method checks error
int recv_and_send (int sock);  // recv and send data to clienst
char *lsFiles();                  // read ls
char * lsDir();                   // read directory ls
char *changeDir(char *dir);       // for changint the directory
char * readFile(char *file);        // reading file
char *filType(char *finf);          // identify file type
int checkFilePresence(const char *fil); // check if file exists


int clientsock[MAX_CLIENTS];
int sd;
int i;
unsigned int clilen;
struct sockaddr_in serv_addr, cli_addr;


int main(int argc, char *argv[])
{
	  int  portno,  activate = 1 ;
	  int  max_sd;
	  int newsock;
	  int sock1;
	  int n;
 
    
	  // set socket descriptor
	  fd_set readfds;
	    
	  if (argc < 2) 
	    {
	      fprintf(stderr,"ERROR, no port provided\n");
	      exit(1);
	    }

	  //initialize socket to 0
	  for (i = 0; i< MAX_CLIENTS; i++) 
	    {
	      clientsock[i] = 0;
	    }

	  // create socket
	  sock1 = socket(AF_INET, SOCK_STREAM, 0);
	  if(sock1<0)
	    {
	      error("erorr in opening socket");
	      exit(1);
	    }
              
	 int setp= setsockopt(sock1, IPPROTO_TCP, TCP_NODELAY, &activate, sizeof(int));
	 if(setp<0)
	 {
	   
	   error("error on setsocketpot");
	}

         
                // address information
	  memset(&serv_addr,0, sizeof(serv_addr));
	  portno = atoi(argv[1]);
	  serv_addr.sin_family = AF_INET;
	  serv_addr.sin_addr.s_addr = INADDR_ANY;
	  serv_addr.sin_port = htons(portno);
	     
	  // bind socket 

	  n=bind(sock1, (struct sockaddr *) &serv_addr, sizeof(serv_addr));
	  if(n<0) 
	{ 
	    error("error in binding");
	    exit(1);
	  }
     

	  // get the server name

	  char hostname[1024];
	  hostname[1023] = '\0';
	   gethostname(hostname, 1023);
	    struct hostent* h;
	  h = gethostbyname(hostname);
	  printf("h_name: %s\n", h->h_name);

            // listen to 5
	  n=listen(sock1,5);
	  if(n<0)
	    {
	      error("error  on listening ");
	      exit(1);
	    }
    


	       
	  clilen = sizeof(serv_addr);
	  printf("%s\n","Server running...waiting for connections.");

	   //  main loop
  while (1) 
    {
    
         
	      FD_ZERO(&readfds);// clear socket
	      FD_SET(sock1, &readfds);// add to set
	      max_sd = sock1;
		      
	      //add socket to set

	      for ( i = 0 ; i< MAX_CLIENTS ; i++) 
		{
		  sd = clientsock[i]; // socket descripor

		  // if valid  then add it to list
		  if(sd > 0)
		    {
		      FD_SET( sd , &readfds); 
		    }
		      //highest needed for the select 
		  if(sd > max_sd)
		    {
		      max_sd = sd;
		    }
		}
            // wait for socket activity
	      int selc = select( max_sd + 1 , &readfds , NULL , NULL , NULL);
	      if ((selc == -1) && (errno!=EINTR)) 
		{
		  error("erorr on select");
		}
	      else if (selc==0)
		{ 
		  error("select");
		  continue;
	     
		}
   
	      if (FD_ISSET(sock1, &readfds)) 
		{
		  newsock = accept(sock1, (struct sockaddr *) &serv_addr, &clilen);
		  if(newsock<0)
		    {
		      error("error on accept");
		      exit(1);
	            }
		   
		  fcntl(sock1, F_SETFL, O_NONBLOCK);
		  //  fcntl(sock1, F_SETFL, O_ASYNC);
		  //inform user of socket number - used in send and receive commands
		  printf("SOCKET , SOCKET FD IS %d , IP IS : %s , PORT : %d \n" , newsock , inet_ntoa(cli_addr.sin_addr) , ntohs(cli_addr.sin_port));
		  
              
		  //add new socket to array of sockets
		  for (i= 0; i< MAX_CLIENTS; i++) 
		    {
		      //if position is empty
		      if( clientsock[i] == 0 )
		        {
			  clientsock[i] = newsock;
			  printf("socket  %d\n" , i);
		             
			  break;
		        }
		    }

	       }

	     // for other sockets
	      for (i = 0; i < MAX_CLIENTS; i++) 
		{
		  sd = clientsock[i];
		 
		      
		  if (FD_ISSET( sd , &readfds)) 
		    {  
		      

		      recv_and_send(sd);
		   
		    }
		}


    }// close for while

   

  return 0;

}// close for main




void error(char *msg) 
{
  perror(msg);
  exit(1);

}


/*This method recieves  request from the client and give back data to clients
 it handles specific request from the clients like ls, pwd ,cd, filetype, and printing and  quitting
of a specific client. function like cd, filetype, and printing several other functions in themselves..
 i have explained more in my README file*/


int recv_and_send(int sock)
{

	  unsigned int blength;
	  unsigned int bl;
	 
	  char buffer[MAXI]="";
	  char buffTemp[MAXI];
	  char bufferls [MAXI];
	  char bufferpwd[MAXI]= "" ;
	  char  bufferquit[MAXI];
	  int k;
 
 
	  memset(buffer, 0, sizeof(buffer));
	  memset(buffTemp, 0, sizeof(buffTemp));
	  memset(bufferpwd,0,sizeof(bufferpwd));
	  memset(bufferls,0,sizeof(bufferls));
	  memset(bufferquit,0,sizeof(bufferquit));

	int  jrec = recv(sock,buffer,MAXI,0);  // receiving every first request
		  if (jrec < 0)
		  {
		     error("error on reading");
		   }
		  printf("This is the request>%s\n",buffer);
		  
           // send all the ls 
	  if(strcmp(buffer, "ls") == 0)
	    {
	      char*rz=lsFiles(); // method that read all files
	      strcpy(buffer, rz);
	      free(rz); // free memory for lsFiles buffers

		      int sen = send(sock,buffer,sizeof(buffer),0);
		      if (sen < 0)
			{
			  error("error on sending");
			}

	    }
           // send the current dir
	  else if(strcmp(buffer, "pwd") == 0)
	    {

	      if (getcwd(bufferpwd, sizeof(bufferpwd)) != NULL)
		{
		  
		  int send2 = send(sock,bufferpwd,sizeof(bufferpwd),0);
		  if (send2 < 0)
		    {
		      error("error on sending  pwd");
		    }
		}
	      

	    }
     /* this option has 3 fuction - 1 takes as to parent dir ,2
    goes to absolute dir and 3 prints all the folder and offers clients 
   change to one of the available */ 
	  else if (strcmp(buffer,"cd")==0)
	    {
	      
	      char * uu=" new dir (? for help)> ";
		   
	      strcat(buffer,uu);
	     
	      int send3 = send(sock,buffer,sizeof(bufferls),0);
		      if (send3< 0)
			{
			  error("error  on sending");
			}
                    memset(buffer, 0, sizeof(buffer));
	      int cdrecv  = recv(sock,buffer,sizeof(buffer),0);
		      if (cdrecv< 0){
			error("error   reading cd request\n");
		      }

	      strtok(buffer, "\n");

              // this option takes us one step backward in our path
	      if(strcmp(buffer, "1") == 0)
		{
		  chdir("..");
		  char * nn= " the parent  directory";
		  memset(buffer, 0, sizeof(buffer));
		  strcpy(buffer, nn);
		  int  sendch = send(sock,buffer,sizeof(buffer),0);
			  if (sendch< 0)
			    {
			      error("error on sending  ..");
			    }
		}

  
                // this takes us to the absolute dir
	     if(strcmp(buffer, "2") == 0)
		{
		  chdir("/");
		  char * nn= "absolute directory";
		  memset(buffer, 0, sizeof(buffer));
		  strcpy(buffer, nn);
		  int  sendch = send(sock,buffer,sizeof(buffer),0);
		  if (sendch< 0)
		    {
		      error("error on sending /");
		    }
		}

           /* this option first gives client all the available folders and
               he can change to any*/

	      if(strcmp(buffer, "3") == 0)
		{
                  
                  memset(buffer, 0, sizeof(buffer));
	    
		  char *bb= lsDir();  // method reads all the folders available
		 
		  strcat(buffer,bb);
		  free(bb) ; // free memory on lsDir folders
		  int send3 = send(sock,buffer,sizeof(bufferls),0);
			  if (send3< 0)
			    {
			      error("error on sending dir ls");
			    }
		  memset(buffer, 0, sizeof(buffer));

		  int fdrecv  = recv(sock,buffer,sizeof(buffer),0);
			  if (fdrecv< 0)
			    {
			      error("error recieving cd change \n");
			    }

                 //memset(buffer, 0, sizeof(buffer));

		  strtok(buffer, "\n");
		  char *pp= changeDir(buffer);
		  strcpy(buffer,pp);
		  send(sock, buffer, sizeof(buffer), 0);

		}
	      /*else{

		char * lq= "unknown request on cd";
		strcpy(buffer,lq);
		send(sock, buffer, sizeof(buffer), 0);

	       }*/

   	 }
       /*This option  send to clients all the files and the client and choose one file
          to identify its type .if the file is not availlable the client will b informed*/
	   
	  else if(strcmp(buffer, "filetype") == 0)
	    {
	      
	      char*hus=lsFiles();
	      strcpy(buffer, hus);
	      free(hus);
	      int flist = send(sock,buffer,sizeof(buffer),0);
		      if (flist < 0)
			{ 
			  error("error on sending ls ");
			}
	      

	      int frequest  = recv(sock,buffer,sizeof(buffer),0);
		      if (frequest < 0)
			{
			  error("error on reading request filetype ");
			}
		     
	      strtok(buffer,"\n");
	    
	      char *tt = filType(buffer);// method that identifies the file type
	      memset(buffer, 0, sizeof(buffer));
	      strcpy(buffer, tt);
	      free(tt);
	      int srequest = send(sock,buffer,sizeof(buffer),0);
		      if (srequest < 0)
			{
			  error("error on sending filetype");
			}
	      
          }

      /*This  option sends first all the files then the cleint can choose a file to print out
          if the file is not availlable the client will b informed */
	  else if(strcmp(buffer, "printing") == 0)
	    {
	    
	     
	      char*hu=lsFiles();
	      strcpy(buffer, hu);
	      free(hu);
	      int flist = send(sock,buffer,sizeof(buffer),0);
		      if (flist < 0) 
			{ 
			  error("error sending ls list ");
			}
	      

	      int rfile = recv(sock,buffer,sizeof(buffer),0);
		      if (rfile< 0)
			{
			  error("error on printing file ");
			}
	   
	      strtok(buffer,"\n");
	      char *tt = readFile(buffer); // method that print out the files 
	      
	      strcpy(buffer, tt);
	      free (tt);
	      int rfile2 = send(sock,buffer,sizeof(buffer),0);
	      if (rfile2 < 0) 
		{
		  error("error on sending printing file");
		}

	    }

/* this method close each client that will disonnect from the server*/
	  else if(strcmp(buffer, "q") == 0)
	    {
	      char *s = "q";
	      strcat(bufferquit, s);
	      int send4 = send(sock,bufferquit,sizeof(buffer),0);
		      if (send4 < 0) {
			error("error printing q");
		      }
	   
	      getpeername(sd , (struct sockaddr*)&cli_addr , (socklen_t*)&clilen);
	      printf("client disconnected , IP %s , PORT %d \n" , inet_ntoa(cli_addr.sin_addr) , ntohs(cli_addr.sin_port));                 
	    
	      close(sock);
	      clientsock[i] = 0; 
	      return -1;
	    }
	  else
	    {
	      
	      return ;
	     
	    }
    
}
        // this checks existance of the file
	int checkFilePresence(const char *fil)
	{
		 struct stat struc;
		 int x = stat(fil, &struc);
		 return x == 0;


	}

     /*  this method reads all the available files and dir in the current working dir*/
	char *lsFiles() 
	{
		  char *buffTemp ;  
		  buffTemp= (char*) malloc(sizeof(char)*MAXI); 
		  char *buffer = (char*) malloc(sizeof(char)*MAXI);
		  
		  FILE *in3;		
		  FILE * fp=  popen("ls", "r");
		  if(fp==NULL)
		    {
		      exit(1);
		    }
			  
		  while(fgets(buffTemp, sizeof(buffTemp), fp) != NULL)
		    {
		      strcat(buffer, buffTemp);
		    
		    }
		  pclose(fp);
		  free (buffTemp);
		  return  buffer;
	  
	}
   /*this method changes the current folder with the folder the client request 
     */
	char *changeDir(char *folder )
	{
                  
	
		  char * finder;
		  char buffercpy[MAXIN];
		  char buffercwd[MAXIN];
		  char buffercat[MAXIN]= "";
		  char buffernew[MAXIN]; 

		  getcwd(buffercwd, sizeof(buffercwd));
		  snprintf(buffercat, 199, "%s/%s", buffercwd, folder); // concata current folder with the new folder
		 
		  int x=chdir(buffercat);
                   printf("%d\n",x);
		  if(x==-1)// checks if the client request an avaliable folder
		    {
		      strcpy(buffercpy,folder);
		     buffercpy[strlen(buffercpy)-1]='\0';
		      finder= strcat(buffercpy,  "  The folder is not available");				
		    } 
		  else 
		  {
			
		    getcwd(buffernew, sizeof(buffernew));
		    finder=strcat(buffernew, " is the new ");
		  }
		  
		  return finder;


	}
/*this method gets  all the availbale folder
*/

	char  *lsDir ()
	{

		  char *buffer;
		  buffer=(char *) malloc (sizeof (char)* MAXI);
		  char * cdbuffer= (char *) malloc (sizeof (char)* MAXI);
	       FILE *  fp=popen ("ls -d */ | cut -f1 -d'/'","r");
           
		  if  (fp==NULL)
		    {
		      error ("error on ls dir");
		      exit(1);
		    }
		  while(fgets(buffer,MAXI, fp)!=NULL)
		    {
		      strcat(cdbuffer,buffer);

		    }

		  pclose(fp);
		  return cdbuffer;

	}
 
     /* this method read the file a client request and it prints all the printable and with th unpritable it replaces with dots */

	char * readFile( char *filein)
	{
		  char *readbuf;
		  char readbuffer[MAXI];
		  readbuf=  (char *) malloc(sizeof(char)*MAXI);
		  char c;
		  FILE *fp;
		  char*holder="File not  available";
		  int y=checkFilePresence(filein);
		  fp = fopen(filein,"r"); 
			  if( fp == NULL )
			    {
			      strcpy(readbuf,holder);//checks if the client request an avaliable file
			    }
			  if(y==0)
			  {
			    
			    strcpy(readbuf,holder);

			 }
			  else{
			  int j=0;
			  while((c = fgetc(fp))!= EOF )
			    {
			  
			      if(isprint(c)) // put to the buufer all the printable to th buffer
				{
				  readbuffer [j]=c;
			    
				}
			      else{        // replaces all the unprintable with dots
			     
				readbuffer [j]= '.'; 
			      
			      }
			      j++;
			    }
			    
			  readbuffer[j]='\0';
			  strcpy( readbuf,readbuffer);

			  fclose(fp);

			}
		  
	return   readbuf;
	  
	}

 /* this method check if a file i plain, directory or a link. it also checks if the file requsted in not available*/
	char *filType(char *fileInput)
	{
		  struct stat statbuffer;
		  char *buffer= (char *) malloc(sizeof(char)*MAXI);
		  char* buffertemp="";
			  if (fileInput == NULL)
			    {
			      fprintf(stderr, "usage: %s file ...\n", fileInput);
			      exit(1);
			    }
			  if (lstat(fileInput, &statbuffer)) 
			    {
			      
                                 buffertemp = "  file not available"; // check if the request is not available
				  strcat(fileInput,buffertemp);
			    } 
			  else 
			    {
			     
			     
			      if (S_ISREG(statbuffer.st_mode)) // checks if a plain file 
				{
				  buffertemp = "  is a plain file ";
				  strcat(fileInput,buffertemp);
				}
			      if (S_ISDIR(statbuffer.st_mode)) // checks if is a directroy
				{
				  buffertemp = "  is a directory file ";
				  strcat(fileInput,buffertemp);
				} 
			      if (S_ISLNK(statbuffer.st_mode)) // check  if is a special file
				{
				  buffertemp = "  is a link ";
				  strcat(fileInput,buffertemp);
				} 
			      /*if(st_rdev) {
				buffertemp= "  its a special file";
				  strcat(fileInput,buffertemp);
				  } */
			 
			    }
	  	strcpy(buffer, fileInput);
	  return buffer;
	}
 

