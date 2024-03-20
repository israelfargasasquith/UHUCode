
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>
#define SIZE 9

typedef enum {
    FACIL = 0,
    MEDIO = 1,
    DIFICIL = 2
} Dificultad;

typedef struct {
    char tablero[SIZE][SIZE];
    char solucion[SIZE][SIZE];
    Dificultad dificultad;
    int codigo;
} Sudoku;

int numeroSudokus = 0;

int pintaMenuVacio()
{
int opcMenu = -1;
    do{
    printf("Dificultad: \t\t Huecos: \n");
    printf("  123 456 789\n");
    printf("  +---+---+---+\n");
    printf("1|     |    |    |\n");
    printf("2|     |    |    |\n");
    printf("3|     |    |    |\n");
    printf("  +---+---+---+\n");
    printf("4|     |    |    |\n");
    printf("5|     |    |    |\n");
    printf("6|     |    |    |\n");
    printf("  +---+---+---+\n");
    printf("7|     |    |    |\n");
    printf("8|     |    |    |\n");
    printf("9|     |    |    |\n");
    printf("  +---+---+---+\n");
    printf("**Linea de avisos**\n\n");
    printf("1.- Juego nuevo\n");
    printf("6.- Salir\n");
    printf("Elige opcion: ");
scanf("%d",&opcMenu);
}while(opcMenu != 1 || opcMenu != 6);
	return opcMenu;
}


int pintaMenu(Sudoku *s)
{
	char[20] sDificultad;
	int nBlancos = 0;
	int opcMenu = -1;

	switch(s.dificultad){
	case '0': sDificultad = "Facil";
	break;
	case '1': sDificultad = "Medio";
	break;
	case '2': sDificultad = "Dificil";
	break;
	default: printf("Error al poner la dificultad");
	}

	for(i=0; i=SIZE; i++){
		for(j=0; j=SIZE; j++){
			if(s.tablero[i][j] == " ") nBlancos++;		
		}
	}
	printf("Dificultad: %s\t\t Huecos: %d\n",sDificultad, nBlancos);
	printf("  123 456 789\n");
	printf("  +---+---+---+\n");
	printf("1| %c%c%c%c|%c%c%c%c|%c%c%c%c|\n",s.tablero[0][0],s.tablero[0][1],s.tablero[0][2],s.tablero[0][3],s.tablero[0][4],s.tablero[0][5],s.tablero[0][6],s.tablero[0][7],s.tablero[0][8]);
	printf("2| %c%c%c%c|%c%c%c%c|%c%c%c%c|\n",s.tablero[1][0],s.tablero[1][1],s.tablero[1][2],s.tablero[1][3],s.tablero[1][4],s.tablero[1][5],s.tablero[1][6],s.tablero[1][7],s.tablero[1][8]);
	printf("3| %c%c%c%c|%c%c%c%c|%c%c%c%c|\n",s.tablero[2][0],s.tablero[2][1],s.tablero[2][2],s.tablero[2][3],s.tablero[2][4],s.tablero[2][5],s.tablero[2][6],s.tablero[2][7],s.tablero[2][8]);
	printf("  +---+---+---+\n");
	printf("4| %c%c%c%c|%c%c%c%c|%c%c%c%c|\n",s.tablero[3][0],s.tablero[3][1],s.tablero[3][2],s.tablero[3][3],s.tablero[3][4],s.tablero[3][5],s.tablero[3][6],s.tablero[3][7],s.tablero[3][8]);
	printf("5| %c%c%c%c|%c%c%c%c|%c%c%c%c|\n",s.tablero[4][0],s.tablero[4][1],s.tablero[4][2],s.tablero[4][3],s.tablero[4][4],s.tablero[4][5],s.tablero[4][6],s.tablero[4][7],s.tablero[4][8]);
	printf("6| %c%c%c%c|%c%c%c%c|%c%c%c%c|\n",s.tablero[5][0],s.tablero[5][1],s.tablero[5][2],s.tablero[5][3],s.tablero[5][4],s.tablero[5][5],s.tablero[5][6],s.tablero[5][7],s.tablero[5][8]);
	printf("  +---+---+---+\n");
	printf("7| %c%c%c%c|%c%c%c%c|%c%c%c%c|\n",s.tablero[6][0],s.tablero[6][1],s.tablero[6][2],s.tablero[6][3],s.tablero[6][4],s.tablero[6][5],s.tablero[6][6],s.tablero[6][7],s.tablero[6][8]);
	printf("8| %c%c%c%c|%c%c%c%c|%c%c%c%c|\n",s.tablero[7][0],s.tablero[7][1],s.tablero[7][2],s.tablero[7][3],s.tablero[7][4],s.tablero[7][5],s.tablero[7][6],s.tablero[7][7],s.tablero[7][8]);
	printf("9| %c%c%c%c|%c%c%c%c|%c%c%c%c|\n",s.tablero[8][0],s.tablero[8][1],s.tablero[8][2],s.tablero[8][3],s.tablero[8][4],s.tablero[8][5],s.tablero[8][6],s.tablero[8][7],s.tablero[8][8]);
	printf("  +---+---+---+\n");
	printf("**Linea de avisos**\n\n");
	printf("1.- Juego nuevo\n");
	printf("2.- Borrar juego\n");
	printf("3.- Poner valor\n");
	printf("4.- Borrar valor\n");
	printf("5.- Ayuda\n");
	printf("6.- Salir\n");
	printf("Elige opcion: ");
}


void generaSolucion(Sudoku *s)
{
	srand(time(NULL));
	for(int i=0;i<SIZE;i++){
		for(int j=0;j<SIZE;j++){
			s.solucion[i][j] = ((rand() % 9)+ 1) + '0';
		}
	}
}

void ayuda(Sudoku *s){

	f
}

void rellenaSudoku(Sudoku *s){

	
	switch(s.dificultad){
	case 0: //damos 38 ayudas
		
	break;

	case 1: //damos 32
	break;

	case 2: //damos 28
	break;

	default: printf("Error con la dificultad");

	}
}





int main(int argc, char *argv[]) //Vamos a pintar primero un solo sudoku que se pinte, se rellene y se compruebe
{
System.cls();
	if(pintaMenuVacio == 1)
	{
		Sudoku s
	}else{
		printf("Adioooos");	
	}
    
exit(0);
}






/*#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>
#define TAM 9
#define SRTAM 3


typedef struct {
	int table[9][9];
	int nBlank;
}Sudoku;

void fillSudoku(Sudoku s){

	rellenaDiagonal(s);
	rellenaRestantes(0,SRTAM);
	removeNDigits(s.nBlank);

}

void rellenaDiagonal(Sudoku s){

	for(int i=0; i < TAM; i+=SRTAM){
	
		rellenaCuadrado(i,i, s);	

	}

}


bool noUsadoCuadrado(int rowStart, int colStart, int num, Sudoku s){
	for(int i=0; i<SRTAM; i++){
		for(int j=0; j<SRTAM; j++){
			if(table[rowStart + i][colStart + j] == num){
				return false;			
			}
		}
	}
	return true;
}
//fill a 3 x 3 box
void rellenaCuadrado(int fila, int col){

	int num;
	for(int i=0; i< SRTAM; i++){
		for(int j=0;j<SRTAM; j++){
			do{
				num = randomGenerator(TAM);
			}while(!noUsadoCuadrado(fila, col, num));
			table[fila + i][col + i] = num;
		}
	}

}

int randomGenerator(int num){
	return rand() % 9 +1;
}
	
bool numeroSeguro(int i, int j, int num){

	return(noUsadoFila(i,num) && noUsadoColumna(j,num) && noUsadoCuadrado(i - i % SRTAM, j - j % SRTAM, num));
}

bool unUsuedInRow(int i, int num){

	for(int j = 0; j < TAM; j++){
		if(table[i][j] == num){
			return false;
		}
	}
	return true;
}

bool noUsadoColumna(int j, int num){
		
	for(int i=0;i<TAM; i++){
		if(table[i][j] == num){
			return false;
		}
	}
	return true;
}

bool rellenaRestantes(int i, int j){
	
	if(j >= TAM && i < TAM - 1){
		i++;
		j=0;
	}
	if(i >= TAM && j >= TAM){
		return true;
	}
	if(i < SRTAM){
		if(j < SRTAM){
			j = SRTAM;
		}
	}else if(i < TAM - SRTAM){
		if(j == (int)(i/SRTAM) * SRTAM){
			j =+ SRTAM;
		}
	}else{
		if(j == TAM - SRTAM){
			i++;
			j=0;
			if(i >= TAM){
				return true;			
			}
		}
	
	}
	for(int num = 1; num <= TAM; num++){
		if(CheckIfSafe(i,j,num)){
			table[i][j] = num;
			if(rellenaRestantes(i,j + 1)){
				return true;
			}
			table[i][j] = 0;
		}
	}
	return false;
}

void eliminarNHuecos(){

	int numero = nHuecos;
	while(numero != 0){
		int casilla = randomGenerator(TAM * TAM) -1;
		int i = (casilla / TAM);
		int j = casilla %9;
		if(j != 0){
			j--;
		}
		if(table[i][j] != 0){
			numero--;
			table[i][j] = 0;		
		}
	}
}

void pintaSudoku(Sudoku s){
	
	for(int i=0; i<TAM;i++){
		for(int j=0;j<TAM;j++){
			printf("%d ",table[i][j]);

		}
		printf("\n");
	}
	printf("\n");
}
	


int main()
{
	Sudoku s;
	
	//sudoku->fillValues();
	//sudoku->pintaSudoku();
	return 0;
}


*/






