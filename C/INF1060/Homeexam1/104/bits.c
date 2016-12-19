


#include <stdio.h>
#include <stdlib.h>     
#include <string.h>     
#include <unistd.h>
#include <sys/types.h>
#include <sys/errno.h>

#include "safefork.h"




#define MAX_COMMAND 120

#define DEBUG

#define DELIM   " " 


//These are functions protypes 

void promt(void);
void readCommandLine(void);
void splitt_command (char* a);
void execute_command();
void execute_History(char *command);
void printHistory();
void print_Single_History(int coNumber);
 

//These are external variables

char command[MAX_COMMAND];  //array for line to be read.
char *param[21];            // array used when line is divided .
extern char **environ; 
int commandNo=1;                   // counts number of lines to be put 

int n;
int ampersand;                // to check for ambersand


//structure history linklist
struct commandHistory 
{
   char *commandData;       // data for everyline  line
   struct commandHistory *next; // for the next pointer
   
};


typedef struct commandHistory arguments;    //typedef for the struct

arguments *first;
arguments* tempD;



int main()
{
  while(1)
  {
    promt();
    readCommandLine();
    splitt_command (command);
    execute_command();

   
  }
 
  return 0;


}

// this method prompts th username and the number of line inputted
 void promt()
 {

   printf("%s@ifish:% d>", getenv("USER"),commandNo);
   //printf("%d>",args);

 }




 void readCommandLine()
 {
   // read user input
  hh:
 
   if (fgets(command,MAX_COMMAND,stdin) == NULL) // exit with Ctrl+D
   {  
     printf("\n");	
     exit(0);
   }

#ifdef DEBUG  
        fprintf(stderr,"%s\n", command);
	#endif
   // if empty or excess input it should not do anything
   command[strlen(command)-1]=0;


   
   // i no input or excess input
   if(!strlen(command))
   {
       goto hh;
   }

   // call for the history 
   execute_History(command);
   

   // check if command has ampersad
  if (strrchr(command,'&')) 
    
    ampersand= 1; 
 
  }
 
 /* this method split command in words  and copies to char *param
     this first word in the command will be copied to param[0]
 */
  
  void splitt_command (char * command)
  {
     int   ntokens = 0;
     int i ; 
     char*sep=(char*)malloc(MAX_COMMAND*sizeof(char));
     char *kk=sep;
     sep = strtok(command, DELIM);

     while (sep != NULL)
     {
        param[ntokens++]=sep;
        sep = strtok(NULL, DELIM);
     
    }
  
    param[ntokens]=NULL;

    for (i = 0; i < ntokens; i++) 
    {
        #ifdef DEBUG  
        fprintf(stderr,"%s\n", param[i]);
	#endif
    
   }
   free(kk);

  }// end of splitt_command


  void execute_History(char *c)
  {
	
    arguments*tempA ,*tempB,* tempC;
    commandNo++; //  increase the number of command input
 
 
     if(first==NULL)
     { 
       tempA = (arguments *)malloc(sizeof ( arguments)); 


// if the first node is null, do assign the first line read
        tempA->commandData = malloc(sizeof(command)); //allocating memory
        strcpy(tempA->commandData, command);
        tempA->next = NULL;
        first = tempA; // first is now assigned to temp
       
      }

        else
        {
        
         tempB = first;

        while(1)
	{
            if(tempB->next !=NULL) // goes until the next pointer points is a NULL
               tempB=tempB-> next;
            else
               break;
         }

	  tempC = (arguments *)malloc(sizeof (arguments)); // ALLOCATING MEMORY Temp3
        
	  tempC->commandData = malloc(sizeof(command));
            strcpy(tempC->commandData, command);
            tempC->next = NULL;
            tempB->next = tempC;
	    
  	    
         }
     tempD=first;
   }

   void printHistory()
   { 
      int i;
      i=commandNo-1;
      // tempD =first;
       printf("History list  of the last %d commands:\n",i);
	if(tempD == NULL)
       {
	  //   printf("Empty list\n");
        return;
       }
           while(tempD!=NULL)
           {
               printf("%d: %s\n",i, tempD-> commandData); // As as long as temp4 is not NULL the data on each node is printed out.
               tempD=tempD->next;
               i--;
           }

   
   }


  
     void execute_command()
     { 
	  // strtok(param[0],"\n");
        char * p1 = param[0];
	char *p2 = param[1];
	char *p3 = param[2];

       if (param[0] == NULL) {
	 return;
       }	  

	  //  These are the built commands
        hi:; 

	 if(strcmp(param[0],"exit")==0||strcmp(param[0],"quit")==0)
	   {
	     exit(0);
           }

	   else if(strcmp(param[0]," ")==0)
	   goto hi;
	
	 /*  if(strcmp(param[0],"h")==0)
           {
	      printHistory();
	      }*/

	 else if((strcmp(p1, "h") == 0) && p2 != NULL && p3==NULL)
           {
		  int y =commandNo-1;
		   y=atoi(p2);
		    
                 if(commandNo)
                 {
		      print_Single_History(y);
                             
		 }
	   
	  	 else{
			  exit(0);
                    }

	   }
	 
	 else  if(strcmp(param[0],"h")==0)
           {
	      printHistory();
	   }

	 pid_t childpid, pid;
	 int status;
	 char *path;                 //path pointer
	 char allcommand[21];  //array that contain  path
	 int count=0;
	 int execute = 1;          // to check for execusion
	  
	  
	 
	 childpid = safefork();    //create child process
  
	     if(childpid<0)
	     {
		printf("\n ERROR ");
		exit(1);
	     }
  
	      else if (childpid == 0)
              {
		  path = getenv("PATH");     
		  while (*path != '\0') 
                  {    
		     while (*path != ':' && *path != '\0') 
                     {  
			allcommand[count++] = *(path++); 
		      }
			  allcommand[count++] = '/';              
			  allcommand[count] = '\0';               
			  
			  strcat(allcommand, command); 
			
			  execve(allcommand, param, environ); // excuting
			   execute--;
			  
			  if (*path != 0)
			path++;
			count=0;
			
                   }
		   if(execute == 0)
                      {
		         printf("ifish: %s: command not found\n",command);
				      
	              }
		      exit(0);
		}
		  
	      else
              {  
		   if (ampersand==1) //  has   &
		   {
		      printf("int child pid (%d)\n",(int)childpid);
		      ampersand = 0;
		    }
		    else 
                    {   
		       pid = wait(&status); //has no &
			
			  
		    }
	       }


       	}
               /*This method will print a specif history that iits asked    */
	   void print_Single_History(int coNumber)
           {
 	       arguments *current = tempD;
 	       int counter =1;
         	while(current != NULL)
                {
		     if(counter == coNumber)
                    {
		          printf("command %d was  %s :%d\n",counter, current->commandData);
			  
                           execute_History(current->commandData);
			
		
		   } 
		      counter ++;
		      current = current->next;
 	        }
 	
	}

 
