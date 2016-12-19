
#include <stdio.h>
#include <stdlib.h>
#include <string.h> 

#define commands(a,b) (0==strcmp(a,b))

void printfil(char *fil);
void encoder(char *encoding, char *encoded);
char characters(char buffer, char adding);
void dencoder(char *decoding, char *decoded);
char remakeChar(char buffer, char adding);


int codebinary[4] = {0b00, 0b01, 0b10, 0b11};
char codes[4]= {' ',':', '@', '\n'};

int main(int argc, char **argv)
{
	
	if(argc<=4)
	{
	
			// prints the filename written in the terminal
		if(commands(argv[1], "p"))
		{
			printfil(argv[2]); 
		}
		// encodes the file
		if(commands(argv[1], "e"))
		{
			encoder(argv[2], argv[3]);
				
		}
		//decodes  the file
		if(commands(argv[1], "d"))
		{
			dencoder(argv[2], argv[3]);
			
			//fprint(stdout,"%s",argv[3]);
				
		}
	}
		
 }



	void printfil(char *fil)
	{
		char c;
		FILE *fp=fopen(fil,"rb");
			if(fp == NULL )
			{
				printf("error in file reading! \n");
				exit(0);
			}
		
			while ((c = fgetc(fp)) != EOF) 
			{
				putchar(c);
			}
			fclose(fp);
	} 
	
/*this methods encode file and write the encoded file to another file
*/
	void encoder(char *encoding, char *encoded)
	{
		char buffer;
		int count = 0;
		FILE *f1= fopen(encoding, "r");
		FILE *f2= fopen(encoded,"w");
			
			if(f1== NULL||f2==NULL)
			{
				printf("Error in reading or creating this file!!!");
				exit(0);
			}
		
			char c;
			while ((c = fgetc(f1)) != EOF)
			{
				if (count < 4 )
				{
					buffer = characters(buffer, c);
				}
				else
				{
					fputc(buffer, f2);
					buffer = 0;
					count= 0;
					buffer = characters(buffer, c);
				}
					count++;
			
			}
				if(count < 4){
					fputc(buffer, f2); // i used this "if" incase the length of the input file is not multiple of 4 or divisble by 4.
				}
				fclose(f1);
				fclose(f2);
		}

	char characters(char buffer, char adding)
	{
		buffer = buffer << 2;
		switch (adding) 
			{
				case ' ' :buffer = buffer| codebinary[0];break;
				case ':' :buffer= buffer | codebinary[1];break;
				case '@' :buffer= buffer | codebinary[2];break;
				case '\n':buffer = buffer| codebinary[3];break;
				default:break;
			}
			
		return buffer;
	}
	//this methods dencodes the file and write the dencoded file to another file

void dencoder(char *decoding, char *decoded){
	char buffer;
	int count = 0;
	FILE *f1, *f2;
	if((f1 = fopen(decoding, "r")) == NULL){
		printf("Error reading this file");
		exit(0);
	}
	if((f2 = fopen(decoded,"w")) == NULL){
		printf("Error creating this file");
		exit(0);
		
	}
	
	char t;
	
	while ((t = fgetc(f1)) != EOF) {
		for(count=1 ; count<=4; count++){
								
		 switch(count)
                        {
                                case 1:buffer = (t >> 6)&3;
                                        putc(codes[buffer], f2);
										printf("%c",codes[buffer]);
                                        break;
                                case 2: buffer = (t >> 4) & 3;
										putc(codes[buffer], f2);
										printf("%c",codes[buffer]);
                                        break;
                                        
                                case 3: buffer= (t >> 2) & 3;
                                        putc(codes[buffer], f2);
										printf("%c",codes[buffer]);
                                        break;
                                case 4: buffer = t & 3;
                                        putc(codes[buffer], f2);
										printf("%c",codes[buffer]);
                                        break;
                        }
								
		}
		buffer=0;
		
	}
	
	
}


	

