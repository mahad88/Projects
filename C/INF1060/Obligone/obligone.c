

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <ctype.h>

#define commands(a,b) (0==strcmp(a,b)) //  macro for comparing  arguments
#define MAX 1000

void replacevowels(char * fil);
void removevowels(char *fil);
void readfiler(char *fil);
void printline();
void randomline();

// structure for linklist
 struct linklist 
{
	char * strline ;
	struct linklist* nextline;

};

typedef struct linklist sentence;

FILE *fp;

sentence * first;
int lines;

int main(int argc, char** argv)
{
	if(argc<3)
	{
	printf("ok\n");
		return 1;
	}
	



fp=fopen(argv[2],"r");

readfiler(argv[2]);

	if(fp == NULL)
	{
		printf("error in reading file");
		exit(1);
	}
	// printing out
	if(commands(argv[1],"print"))
	{
	
	 
	sentence * tempLine = first;
			while (tempLine->nextline != NULL) {
				printf("%s", tempLine->strline);
				tempLine = tempLine->nextline;
			}
			
	}
	
			
			//number of characters in file
			
	if(commands(argv[1],"len")) 
	{
		fp=fopen(argv[2],"r");
		char q;
		int t=0;
			while((q=fgetc(fp))!=EOF)
				t++;
				printf("%d",t);
				
	fclose(fp);			
	
	}   

// print file vowel replaced by  a single file
	if (commands(argv[1],"replace"))
	{
		replacevowels(argv[2]);
	}
	
	//prints a randomline
	if (commands(argv[1],"random"))
	{
		randomline();
	}
	//print file which all vowels are removed
	if(commands(argv[1],"remove"))
	{
		removevowels(argv[2]);
	 }
	 

free(first);

return 0;

}


	int hasvowel(char i)
	{
		switch(i)
		{
			case 'a':
			case 'e':
			case 'i':
			case 'o':
			case 'y':
			case 'u':
		   
		    
			
		   return 1;
			default:
		  return 0;
		}
	}
	
	/*  the method goes through the loop as long as their a vowel and replaces all in
	file by a single vowel everytime */
	void replacevowels(char * fil) 
	{
		
       
		int i;
		char vowel [] = {'a','e','i','o','y','u'};
		
			for(i = 0; i  <sizeof(vowel); ++i) 
			{
				printf("\n");
				printf("\n VOWELS  REPLACED BY (%c)\n", (vowel[i]));
				fp = fopen(fil,"r");

			if(fp == NULL)
			{
			  printf("error in reading the file");
			  exit(1);
			}
		
			char c;
			while((c = getc(fp)) != EOF)
			{
				if(hasvowel(c)){
				
				c = vowel[i];
				}
			
				printf("%c",c);
			}
			
	  }
		printf("\n");
		
		fclose(fp);
	}
	
	// the method remove all the vowels in the file
	
	void removevowels(char *fil) 
	{
	printf("\n");
		printf("REMOVING ALL THE VOWELS \n");
		fp = fopen(fil,"r");
	   
		if(fp == NULL)
		{
			printf("error in reading the file \n" );
			exit(1);	
		}
		
		else 
		{
			char c;
			while((c = fgetc(fp)) != EOF) 
			{
		 
				if(!hasvowel(c))
				printf("%c",c);
			}
	  }
	  fclose(fp); 
	}
	/* this method read the file line by line and put each line in  linkedlist
	 */
	
		void readfiler(char*fil)
		{
			
			char bufprint[MAX];
			first = (sentence*)malloc(sizeof(sentence));
			
			if(first==NULL)
			{
				perror("erroe");
				exit(-1);
			}
			sentence * tempLine = first;
	
			while(fgets(bufprint,MAX,fp)!=NULL)
			{
			
			
				tempLine->strline=malloc( sizeof(bufprint));
				strcpy(tempLine->strline ,bufprint);
				
				tempLine->nextline= (sentence*)malloc(sizeof(sentence));
		        
				tempLine=tempLine->nextline;
				tempLine->nextline=NULL;
				lines++;
						
			}
		
			
		}
		//this method read a random line from the file
	void randomline()
	{
		printf("READ A RAONDOM LINE\n");

		srand(time(NULL));  

		 int currentline = 0;
		 int random = rand() % lines;
    
		sentence*templine = first;
 
		while(templine != NULL)
		{
			if(random == currentline)
			{
				printf("%s",templine->strline);
			}
				currentline++;
				templine = templine -> nextline;
		}
			printf("\n");
	}
		
