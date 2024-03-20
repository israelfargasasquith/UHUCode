#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <stdbool.h>
#include <string.h>
#include <time.h>
#include<unistd.h>
//TODO LO DE FIHCEROS ES OPCIONAL, TENGO QUE PONER MEMORIA DINAMICA, PERO POR AHORA VOY A PONERME A HACER EL RPC
typedef enum {  // facil 43 huecos ,medio 49, dificil 53, experto 58 
    FACIL = 0,
    MEDIO = 1,
    DIFICIL = 2,
    EXPERTO = 3
} Dificultad;

typedef char Cadena[300];

int const TAM = 9;
int const SRTAM = 3;
static int nSudokus =0;

struct RCadena
{
	bool Salida; //TRUE si el campo contenido tiene un sudoku, FALSE si es una cadena vacía.
	Cadena Contenido;
};

struct TFCV //Estructura para indicar una Fila, Columna y Valor
{
	int pCod; //Código del sudoku
	int pFil; //Fila del tablero
	int pCol; //Columna del tablero
	int pVal; //Valor que contiene (del 1 al 9 o vacío '0')
};

struct TFC //Estructura para indicar una Fila y Columna
{
	int pCod;
	int pFil;
	int pCol;
};


struct Sudoku {
    int** tablero;
	int** solucionTablero;
	//struct TFC* casillasDadas;
    int nHuecos;
    Dificultad dificultad;
    int codigo;
};

void inicializarSudoku(struct Sudoku* sudoku, Dificultad dif) {
    sudoku->dificultad = dif;
	switch(sudoku->dificultad){
	case FACIL: sudoku->nHuecos = 43;
	break;
	case MEDIO: sudoku->nHuecos = 49;
	break;
	case DIFICIL: sudoku->nHuecos = 53;
	break;
	case EXPERTO: sudoku->nHuecos = 58;
	break;
	default: printf("ERROR AL SELECCIONAR DIFICULTAD NO SE HA GENERADO MEMORIA \n");
	return ;	
	}
	sudoku->codigo = ++nSudokus;
    sudoku->tablero = (int**)malloc(TAM * sizeof(int*));

    for (int i = 0; i < TAM; i++) {
        sudoku->tablero[i] = (int*)malloc(TAM * sizeof(int));
        //memset(sudoku->tablero[i], 0, TAM * sizeof(int)); No hace falta porque vamos a reescribir todos los valores
    }
	sudoku->solucionTablero = (int**)malloc(TAM * sizeof(int*));
	for(int i=0; i<TAM; i++){
		sudoku->solucionTablero[i] = (int*)malloc(TAM * sizeof(int));
		//memset(sudoku->solucionTablero[i],0,TAM * sizeof(int));
	}

	//sudoku->casillasDadas = (struct TFC*)malloc((81 - sudoku->nHuecos) *sizeof(struct TFC)); da problemas de memoria al guardar las casillas y compararlas

}

int randomGenerator(int num) {
    return (int)floor((float)(rand() / (double)RAND_MAX * num + 1));
}

bool noUsadoFila(struct Sudoku* sudoku, int fila, int num) {
    for (int j = 0; j < TAM; j++) {
        if (sudoku->tablero[fila][j] == num) {
            return false;
        }
    }
    return true;
}

bool noUsadoColumna(struct Sudoku* sudoku, int col, int num) {
    for (int i = 0; i < TAM; i++) {
        if (sudoku->tablero[i][col] == num) {
            return false;
        }
    }
    return true;
}

bool noUsadoCuadrado(struct Sudoku* sudoku, int filaIni, int colIni, int num) {
    for (int i = 0; i < SRTAM; i++) {
        for (int j = 0; j < SRTAM; j++) {
            if (sudoku->tablero[filaIni + i][colIni + j] == num) {
                return false;
            }
        }
    }
    return true;
}

bool numeroSeguro(struct Sudoku* sudoku, int i, int j, int num) {
    return (noUsadoFila(sudoku, i, num) && noUsadoColumna(sudoku, j, num) &&
            noUsadoCuadrado(sudoku, i - i % SRTAM, j - j % SRTAM, num));
}

void rellenaCuadrado(struct Sudoku* sudoku, int fila, int col) {
    int num;
    for (int i = 0; i < SRTAM; i++) {
        for (int j = 0; j < SRTAM; j++) {
            do {
                num = randomGenerator(TAM);
            } while (!noUsadoCuadrado(sudoku, fila, col, num));
            sudoku->tablero[fila + i][col + j] = num;
        }
    }
}

void rellenaDiagonal(struct Sudoku* sudoku) {
    for (int i = 0; i < TAM; i += SRTAM) {
        rellenaCuadrado(sudoku, i, i);
    }
}

bool rellenaRestantes(struct Sudoku* sudoku, int i, int j) {
    
	//si aun no estamos en la ultima casilla significa que quedan por rellenar
    if (j >= TAM && i < TAM - 1) {
        i++;
        j = 0;
    } //ultima casilla
    if (i >= TAM && j >= TAM) {
        return true;
    }
	//evitamos la diagonal ya rellena
    if (i < SRTAM) {
        if (j < SRTAM) {
            j = SRTAM;
        }
    } else if (i < TAM - SRTAM) {
        if (j == (int)(i / SRTAM) * SRTAM) {
            j = j + SRTAM;
        }
    } else {
        if (j == TAM - SRTAM) {
            i++;
            j = 0;
            if (i >= TAM) {
                return true;
            }
        }
    }
	//llamada recursiva por si hay sudokus que no tienen solucion que vuelva atras
    for (int num = 1; num <= TAM; num++) {
        if (numeroSeguro(sudoku, i, j, num)) {
            sudoku->tablero[i][j] = num;
            if (rellenaRestantes(sudoku, i, j + 1)) {
                return true;
            }
            sudoku->tablero[i][j] = 0;
        }
    }
    return false;
}

void creaSolucion(struct Sudoku* sudoku){
	for(int i=0; i<TAM;i++){
		for(int j=0;j<TAM;j++){
			sudoku->solucionTablero[i][j] = sudoku->tablero[i][j];
		}
	}
}

void eliminarNHuecos(struct Sudoku* sudoku) {
    int numero = sudoku->nHuecos;
    while (numero != 0) {
        int casilla = randomGenerator(TAM * TAM) - 1;
        int i = casilla / TAM;
        int j = casilla % 9;
        if (j != 0) {
            j = j - 1;
        }
        if (sudoku->tablero[i][j] != 0) {
            numero--;
            sudoku->tablero[i][j] = 0;
        }
    }
}

void pintaSudoku(struct Sudoku* sudoku, char mensaje[]) {

	char *sDificultad;
	bool color = false;

	switch(sudoku->dificultad){
	
		case 0: sDificultad = "FACIL";
		break;

		case 1: sDificultad = "MEDIO";
		break;

		case 2: sDificultad = "DIFICIL";
		break;

		case 3: sDificultad ="EXPERTO";
		break;

		default: printf("Error de dificultad");

	}

	/*printf("Las casillas dadas son:\n");
	for(int x; x < 81 - sudoku->nHuecos;x++){
		printf("Casilla num %d del array: fil-> %d, col-> %d\n",x,sudoku->casillasDadas[x].pFil,sudoku->casillasDadas[x].pCol); para cambiar de color los valores dados
		
	}*/
	printf("Dificultad: %s\t\t Huecos: %d\n", sDificultad,sudoku->nHuecos);
	printf("   1 2 3   4 5 6   7 8 9\n");
    for (int i = 0; i < TAM; i++) {
		if(i % 3 == 0){	
			printf(" +-------+-------+-------+\n");
		}
		printf("%d", i+1);
        for (int j = 0; j < TAM; j++) {
			if(j % 3 == 0){
				printf("| ");
			}
			/*for(int x; x < 81 - sudoku->nHuecos;x++){
				if((sudoku->casillasDadas[x].pFil == i) && (sudoku->casillasDadas[x].pCol == j)){ para cambiar de color los valores dados
					color = true;
				}else{
					color = false;
				}
			}
			if(color == true){	
				printf("\033[0;34m");
				printf("%d ", sudoku->tablero[i][j]); 
				printf("\033[0m");
			}else{
				printf("%d ", sudoku->tablero[i][j]);		
			}*/
			printf("%d ", sudoku->tablero[i][j]);
		
			if(j == TAM -1){
				printf("|");
			}
	    
		}

        printf("\n");
    }
	printf(" +-------+-------+-------+\n");
    printf("\n");
	printf("**Linea de avisos** : %s\n",mensaje);
	//printf("Los numeros de color azul son los generados, no pueden ser borrados para alcanzar la solucion creada\n\n");
	printf("1.- Juego nuevo\n");
	printf("2.- Borrar juego\n");
	printf("3.- Poner valor\n");
	printf("4.- Borrar valor\n");
	printf("5.- Ayuda\n");
	printf("6.- Salir\n");
	printf("Elige opcion: \n\n");
}

//Sobrecarga cuando no hay ningun sudoku creado
void pintaSudokuVacio(){

	printf("Dificultad: \t\t Huecos: \n");
	printf("   1 2 3   4 5 6   7 8 9\n");
    for (int i = 0; i < TAM; i++) {
	if(i % 3 == 0){	
		printf(" +-------+-------+-------+\n");
	}
	printf("%d", i+1);
        for (int j = 0; j < TAM; j++) {
		if(j % 3 == 0){
			printf("| ");
		}            
		printf("0 ");
		if(j == TAM -1){
			printf("|");
		}
	    
        }

        printf("\n");
    }
	printf(" +-------+-------+-------+\n");
    printf("\n");
	printf("**Linea de avisos**\n\n");
	printf("1.- Juego nuevo\n");
	printf("2.- -------------\n");
	printf("3.- -------------\n");
	printf("4.- -------------\n");
	printf("5.- -------------\n");
	printf("6.- Salir\n");
	printf("Elige opcion: \n\n");


}

/*void guardaCasillasGeneradas(struct Sudoku* sudoku){

	printf("Voy a guardar las casillas generadas\n");

	int pos=0;
	for(int i=0; i<TAM;i++){
		for(int j=0; j<TAM;j++){
			if(sudoku->tablero[i][j] != 0){
				sudoku->casillasDadas[pos].pFil = i;
				sudoku->casillasDadas[pos].pCol = j;
				printf("guardo casilla n-> %d : fil-> %d, col-> %d\n",pos,sudoku->casillasDadas[pos].pFil, sudoku->casillasDadas[pos].pCol);
				pos++;
			}
		}
	}


}*/

/*void creaFichero(FILE** ficheroDatos){ //comprueba si existe un fichero y lo crea en caso de que no exista

	if((*ficheroDatos = fopen("ficheroSudokus.bin","rb")) == NULL){ 
		if((*ficheroDatos = fopen("ficheroSudokus.bin", "wb"))==NULL){
			system("clear");
			printf("No se ha podido abrir ni crear el fichero, saliendo...\n");
			exit(1);		
		}
	}

}*/

bool creaSudoku(struct Sudoku **sudoku){  //Creo que el objetivo de la practica era mas bien realocar la memoria dinamica y tener varios juegos en el servidor y poder cambiar entre ellos, yo lo he hecho con ficheros por la forma en que refiere a guardar el sudoku actual para jugar mas tarde cuando sale de la aplicacion

	nSudokus++;
	if(nSudokus > 1){ //hay que liberar memoria
		for (int i = 0; i < TAM; i++) {
			free((*sudoku)->tablero[i]);
	    }
    	free((*sudoku)->tablero);
		//free(sudoku->casillasDadas);  
		//AQUI PUEDE SER UN ERROR
		for (int i = 0; i < TAM; i++) {
			free((*sudoku)->solucionTablero[i]);
	    }
	    free((*sudoku)->solucionTablero);
    	free((*sudoku));
	}
		*sudoku = (struct Sudoku*)malloc(sizeof(struct Sudoku));
		int dif;		
		printf("De que dificultad quiere el sudoku? (Facil 43 huecos ,Medio 49, Dificil 53, Experto 58)\n");
		printf("Introduce 1 -> Facil | 2 -> Medio | 3 -> Dificil | 4 -> Experto\n");
		scanf("%d",&dif);
		dif--;
		while(dif > 3 || dif < 0){
			printf("Error al elegir dificultad, vuelve a escribir un numero (1 al 4) \nIntroduce numero: ");
			scanf("%d",&dif);
			dif--;	
		}
		inicializarSudoku(*sudoku, dif);

		rellenaDiagonal(*sudoku);

		rellenaRestantes(*sudoku,0,SRTAM);

		creaSolucion(*sudoku);

		eliminarNHuecos(*sudoku);

		//guardaCasillasGeneradas(*sudoku);		
		

}

bool comprobarSudoku(struct Sudoku* sudo){

	bool bien=true;

	for(int i=0; i<TAM && bien;i++){
		for(int j=0;j<TAM && bien;j++){
			if(sudo->tablero[i][j] != sudo->solucionTablero[i][j]){
				bien=false;
			}
		}
	}
	return bien;
}

bool menuSudokuVacio(){
	int opcMenuV = -1;
	do{
		system("clear");
		if(opcMenuV != 6  && opcMenuV != 1){
		printf("ELIGE EL NUMERO 1 O 6, POR FAVOR\n");	
	 }
		pintaSudokuVacio();	
		scanf("%d",&opcMenuV);
	}while(opcMenuV != 6 && opcMenuV != 1);

	if(opcMenuV == 6){
		return false;
	}
	return true;
}

void liberaSudokus(struct Sudoku *sudoku){//, FILE* ficheroDatos){

	for (int i = 0; i < TAM; i++) {
		free(sudoku->tablero[i]);
	    }
    free(sudoku->tablero);
	//free(sudoku->casillasDadas);
		
	for (int i = 0; i < TAM; i++) {
		free(sudoku->solucionTablero[i]);
	    }
	free(sudoku->solucionTablero);
    free(sudoku);
	//cierra fichero si esta abierto
	/*if((fopen("ficheroDatos.bin","rb")) != NULL){
		fclose(ficheroDatos);
	}*/
}

int main() {

   
    srand(time(NULL));
    struct Sudoku* sudokus = NULL;
	//FILE* ficheroDatos = NULL;
	int errorFich =0;
	int opcMenu;
	struct TFCV ponerQuitar;
	char opcGuardar;
	char opcBorrarActual;
	char mensaje[100];
	strcpy(mensaje,"");
	opcMenu = 6;

	do{

		if(sudokus == NULL){
			if(menuSudokuVacio()){
				creaSudoku(&sudokus);
				//creaFichero(&ficheroDatos);
			}else{
				exit(0);			
			}
		}
		opcMenu = -1;
		system("clear");
		if(comprobarSudoku(sudokus)){
			strcpy(mensaje,"FELICIDADES, HAS GANADO");		
		}
		pintaSudoku(sudokus,mensaje);
		strcpy(mensaje,"");
		scanf("%d",&opcMenu);
		switch(opcMenu){

			case 1:  //Crea un nuevo Sudoku y guarda los datos del actual HACER CON MEMORIA DINAMICA
					if(nSudokus == 0){ //simplemente crea uno y no guarda nada
						creaSudoku(&sudokus);	
					}/*else{ //si hay que guardar el juego
						if((ficheroDatos = fopen("ficheroSudokus.bin","ab")) == NULL){ 
							if((ficheroDatos = fopen("ficheroSudokus.bin","wb")) == NULL){
								printf("Error, no se ha podido crear el fichero con la opcion 1");
								sleep(2);
								exit(1);							
							}
							
						}
						errorFich = fwrite(&sudokus,sizeof(struct Sudoku),1,ficheroDatos);
						if(errorFich != 1){
							printf("Error,no se ha podido guardar en fichero el sudoku opc 1");
							sleep(2);
							exit(1);
						}
						fclose(ficheroDatos);
					}*/
			break;

			case 2:  //Borra el juego actual, preguntando antes
					printf("Quiere borrar la partida actual? Todo el progreso se perdera y no es revertible (S/N)\n");
						scanf(" %c",&opcBorrarActual);
					if((strcmp(&opcBorrarActual,"S") == true) || (strcmp(&opcBorrarActual,"s") == true)){
						liberaSudokus(sudokus);//,ficheroDatos);
						sudokus=NULL;
					}
			break;

			case 3: //Pone un valor en el tablero, cada vez que pone un valor comprueba (si no hay huecos libres, que esta bien el sudoku entero)
					do{
						printf("\nIntroduce el valor que quiere meter en el sudoku(1-9): ");
						scanf("%d",&ponerQuitar.pVal);
						printf("Introduce la columna a insertar el valor(1-9): ");
						scanf("%d",&ponerQuitar.pCol);
						printf("Introduce la fila a insertar el valor(1-9): ");
						scanf("%d", &ponerQuitar.pFil);
					}while(ponerQuitar.pVal > 9 || ponerQuitar.pVal < 1 || ponerQuitar.pCol > 9 || ponerQuitar.pCol < 1 || ponerQuitar.pFil > 9 || ponerQuitar.pFil < 1);
					ponerQuitar.pCol--;
					ponerQuitar.pFil--;
					if(sudokus->tablero[ponerQuitar.pFil][ponerQuitar.pCol] == 0){ //Se puede colocar el valor, si quiere reescribirlo debe borrarlo primero
						sudokus->tablero[ponerQuitar.pFil][ponerQuitar.pCol] = ponerQuitar.pVal;
						if(sudokus->nHuecos >0){
							sudokus->nHuecos--;
						}					
					}else{
						strcpy(mensaje,"Error, el valor debe ser borrado antes de introducir uno nuevo");
					}
					
			
			break;

			case 4: //Borra un valor del tablero
					
					printf("Introduce la columna a borrar el valor: ");
					scanf("%d",&ponerQuitar.pCol);
					printf("Introduce la fila a borrar el valor: ");
					scanf("%d", &ponerQuitar.pFil);
					
					if(!(ponerQuitar.pCol > 9 || ponerQuitar.pCol < 1 || ponerQuitar.pFil > 9 || ponerQuitar.pFil < 1)){
						ponerQuitar.pCol--;
						ponerQuitar.pFil--;
						if(sudokus->tablero[ponerQuitar.pFil][ponerQuitar.pCol] != 0){ //El valor vuelve a 0 si no es 0 ya
							sudokus->tablero[ponerQuitar.pFil][ponerQuitar.pCol] = 0;
								sudokus->nHuecos++;				// que no aumente el numero maximo de huecos de los dados	
						}else{
							strcpy(mensaje,"Error, el valor ya esta borrado");
						}
					}else{
						strcpy(mensaje,"ERROR: La posición indicada es erronea, por favor valores entre el 1 y el 9");
					}

			break;

			case 5: //Rellena un hueco vacio como ayuda
					;bool vacio = false;
					for(int i=0;i<TAM && !vacio;i++){
						for(int j=0;j<TAM && !vacio;j++){
							if(sudokus->tablero[i][j] == 0){
								sudokus->tablero[i][j] = sudokus->solucionTablero[i][j];
								vacio = true;
								strcpy(mensaje,"Se rellena la posicion Fila[-] Columna[-] con el Valor: -");
								mensaje[28] = (i+1)+'0';
								mensaje[39] = (j+1)+'0';
								mensaje[56] = sudokus->tablero[i][j]+ '0';
								//goto salto; todo el mundo me dice que los gotos son feos :c
							}
						}
					}				
					//salto: ;
					if(sudokus->nHuecos > 0){
						sudokus->nHuecos--;
					}
			break;

			case 6: //Sale de la app guardando el juego
					if(nSudokus > 0){
						printf("Antes de salir quiere guardar la partida actual? (S/N)\n");
						scanf(" %c",&opcGuardar);
						if((strcmp(&opcGuardar,"S") == true) || (strcmp(&opcGuardar,"s") == true)){
							/*if((ficheroDatos = fopen("ficheroSudokus.bin","ab")) == NULL){ 
								if((ficheroDatos = fopen("ficheroSudokus.bin","wb")) == NULL){
									printf("Error, no se ha podido crear el fichero con la opcion 6\n");
									exit(1);							
								}
								
							}
							errorFich = fwrite(&sudokus,sizeof(struct Sudoku),1,ficheroDatos);
							if(errorFich != 1){
								printf("Error,no se ha podido guardar en fichero el sudoku opc 6\n");
								exit(1);
							}*/
							//fclose(ficheroDatos);
						}
					}
			break;

			case 7: //carga un juego, muestra primero el numero de juegos que hay y la dificultad de cada uno YA LO HARE CUANDO IMPLEMENTE MEMORIA DINAMICA

			break;

			default:
				printf(" *******ELIJA UNA OPCION DEL 1 AL 6 POR, FAVOR*******\n");//Vuelve a preguntar por la opcion
				sleep(2);

		}

	}while(opcMenu != 6);

		//liberaSudokus(sudokus,ficheroDatos);
		liberaSudokus(sudokus);
    

    return 0;
}


