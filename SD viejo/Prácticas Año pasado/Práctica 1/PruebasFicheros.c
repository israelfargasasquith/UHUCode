#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

struct Sudo{

	int cod;
	int** tablero;

};

int main(){
	char var[] = "Se rellena la posicion Fila[-] Columna[-] con el Valor[-]";
	printf("%c\n",var[55]);
	
/*

	FILE* fichero;
	struct Sudo s, s2, s3;
	struct Sudo lee[3], lee2[2], leeTmp[3];
	int fallo=0;	
	s.cod = 1;
	s.tablero = (int**)malloc(9*sizeof(int*));
	for(int i=0; i<9;i++){
		s.tablero[i] = (int*)malloc(9*sizeof(int));
        memset(s.tablero[i], 0, 9* sizeof(int)); //solamente se rellena con 0s con memset, ya que cambia el valor de los bits
	}

	printf("Visualizamos como hemos rellenado los 3 sudokus distintos\n");

	for(int i=0;i<9;i++){
		for(int j=0;j<9;j++){
			printf("%d ",s.tablero[i][j]);
		}
		printf("\n");
	}
		printf("\n\n\n\n");
	s2.cod = 2;
	s2.tablero = (int**)malloc(9*sizeof(int*));
	for(int i=0; i<9;i++){
		s2.tablero[i] = (int*)malloc(9*sizeof(int));
        for(int j=0; j<9;j++){
			s2.tablero[i][j] = 1;
		}
	}

	for(int i=0;i<9;i++){
		for(int j=0;j<9;j++){
			printf("%d ",s2.tablero[i][j]);
		}
		printf("\n");
	}
	printf("\n\n\n\n");

	s3.cod = 3;
	s3.tablero = (int**)malloc(9*sizeof(int*));
	for(int i=0; i<9;i++){
		s3.tablero[i] = (int*)malloc(9*sizeof(int));
        for(int j=0; j<9;j++){
			s3.tablero[i][j] = 2;
		}
	}

	for(int i=0;i<9;i++){
		for(int j=0;j<9;j++){
			printf("%d ",s3.tablero[i][j]);
		}
		printf("\n");
	}
	printf("\n\n\n\n");
	getchar();

	if((fichero = fopen("pruebafich.bin","wb")) == NULL){
		printf("Error al intentar abrir el fichero\n");
		exit(1);
	}
	
	fallo = fwrite(&s, sizeof(struct Sudo),1,fichero);

	if(!fallo){
		printf("Error en la primera escritura de fichero\n");
	}else{
		printf("Primera escritura correcta\n\n");
	}
	fclose(fichero);

	//ahora en modo lectura
	if((fichero = fopen("pruebafich.bin","rb")) == NULL){
		printf("Error al intentar abrir el fichero\n");
		exit(1);
	}
	
	fallo = fread(&lee, sizeof(struct Sudo),1,fichero);

	if(!fallo){
		printf("Error, nada que leer 1\n");
	}else{
		printf("Todo correcto, el contenido del fichero es: \nCodigo: %d El tablero es el siguiente \n\n",lee[0].cod);
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				printf("%d ",lee[0].tablero[i][j]);
			}
			printf("\n");
		}
	}

	fclose(fichero);

	//Añadimos dos sudokus mas al fichero

	if((fichero = fopen("pruebafich.bin","ab"))== NULL){
		printf("Error al intentar abrir el fichero\n");
		exit(1);
	}

	fallo = fwrite(&s2,sizeof(struct Sudo),1,fichero);

	if(!fallo){
		printf("Error en la escritura de fichero\n");
	}else{
		printf("Todo correcto guardando el segundo juego\n");
	}	

	fallo = fwrite(&s3,sizeof(struct Sudo),1,fichero);

	if(!fallo){
		printf("Error en la escritura de fichero\n");
	}else{
		printf("Todo correcto guardando el tercer juego\n");
	}	
	fclose(fichero);

	//sacamos por pantallas los 3 sudokus guardados

	if((fichero = fopen("pruebafich.bin","rb"))== NULL){
		printf("Error al intentar abrir el fichero\n");
		exit(1);
	}

	fallo = fread(&lee, sizeof(struct Sudo),3,fichero);

	if(!fallo){
		printf("Error, nada que leer 2\n");
	}else{
		printf("\nTodo correcto, el contenido del fichero es:\n\n");
		for(int x=0; x<3;x++){
		 printf("Codigo: %d El tablero es el siguiente \n",lee[x].cod);		
			for(int i=0;i<9;i++){
				for(int j=0;j<9;j++){
					printf("%d ",lee[x].tablero[i][j]);
				}
				printf("\n");
			}
		printf("\n\n\n");
		}
	}
	fclose(fichero);

	//borramos el segundo


	FILE* fichTmp;

	if((fichTmp = fopen("tmp.bin","wb")) == NULL){
		printf("Error al intentar crear el fichero temporal\n");
		exit(1);
	}

	if((fichero = fopen("pruebafich.bin","rb"))== NULL){
		printf("Error al intentar abrir el fichero\n");
		exit(1);
	}

	struct Sudo sudoTmp;
	for(int i=0;i<3;i++){
		fallo = fread(&sudoTmp, sizeof(struct Sudo),1,fichero);					
		if(fallo != 1){
			printf("Error borrando el segundo sudoku\n");
			exit(1);
		}		
		if(i!=1){
			fallo = fwrite(&sudoTmp,sizeof(struct Sudo),1,fichTmp);
			if(fallo != 1){
				printf("Error escribiendo en el fichero tmp\n");
				exit(1);
			}
		}
	}

	fclose(fichero);
	fclose(fichTmp);
	remove("pruebafich.bin");
	rename("tmp.bin","pruebafich.bin");

	//ahora escribimos por pantalla el primero y el tercero

	if((fichero = fopen("pruebafich.bin","rb"))== NULL){
		printf("Error al intentar abrir el fichero\n");
		exit(1);
	}

	fallo = fread(&lee2, sizeof(struct Sudo),2,fichero);

	if(!fallo){
		printf("Error, nada que leer ultimo...\n");
	}else{
		printf("\nTodo correcto, el contenido del fichero es:\n\n");
		for(int x=0; x<2;x++){
		 printf("Codigo: %d El tablero es el siguiente \n",lee2[x].cod);		
			for(int i=0;i<9;i++){
				for(int j=0;j<9;j++){
					printf("%d ",lee2[x].tablero[i][j]);
				}
				printf("\n");
			}
		printf("\n\n\n");
		}
	}
	fclose(fichero);

	
	for(int i=0;i<9;i++){
		
		free(s.tablero[i]);
		free(s2.tablero[i]);
		free(s3.tablero[i]);
	}
*/
	return 0;
}
