
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>
#define SIZE 9

typedef enum {  // facil 38 numeros ,medio 32, dificil 28, experto 23 
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


int pintaMenu(Sudoku s)
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






int main(int argc, char *argv[])
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




bool isSafe(char sudoku[9][9], int row, int col, char num) {
    // Verificar si el número ya está presente en la fila
    for (int i = 0; i < 9; i++) {
        if (sudoku[row][i] == num) {
            return false;
        }
    }

    // Verificar si el número ya está presente en la columna
    for (int i = 0; i < 9; i++) {
        if (sudoku[i][col] == num) {
            return false;
        }
    }

    // Verificar si el número ya está presente en el bloque 3x3
    int startRow = row - (row % 3);
    int startCol = col - (col % 3);
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            if (sudoku[startRow + i][startCol + j] == num) {
                return false;
            }
        }
    }

    return true;  // El número es seguro de colocar en esta posición
}

bool fillSudoku(char sudoku[9][9], int row, int col) {
    if (row == 9) {
        return true;  // Se ha completado el Sudoku
    }

    if (col == 9) {
        return fillSudoku(sudoku, row + 1, 0);  // Moverse a la siguiente fila
    }

    if (sudoku[row][col] != ' ') {
        return fillSudoku(sudoku, row, col + 1);  // La celda ya está ocupada, pasar a la siguiente
    }

    // Probar los números del 1 al 9
    for (char num = '1'; num <= '9'; num++) {
        if (isSafe(sudoku, row, col, num)) {
            sudoku[row][col] = num;

            if (fillSudoku(sudoku, row, col + 1)) {
                return true;  // Se encontró una solución
            }

            sudoku[row][col] = ' ';  // No se encontró una solución, restablecer la celda
        }
    }

    return false;  // No se encontró ninguna solución
}

void generateSudoku(Sudoku* sudoku) {
    srand(time(NULL));  // Inicializar la semilla del generador de números aleatorios

    // Inicializar el Sudoku con espacios vacíos
    for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
            sudoku->tablero[i][j] = ' ';
        }
    }

    fillSudoku(sudoku->tablero, 0, 0);

    // Establecer la dificultad y el código del número de Sudoku
    sudoku->dificultad = (Dificultad)(rand() % 3);
    sudoku->codigo = rand() % 100 + 1;
}

void printSudoku(Sudoku sudoku) {
    printf("Dificultad: ");
    switch (sudoku.dificultad) {
        case FACIL:
            printf("Fácil\n");
            break;
        case MEDIO:
            printf("Medio\n");
            break;
        case DIFICIL:
            printf("Difícil\n");
            break;
    }

    printf("Código del número de Sudoku: %d\n", sudoku.codigo);

    printf("Tablero:\n");
    for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
            printf("%c ", sudoku.tablero[i][j]);
        }
        printf("\n");
    }
}


// Function to print the Sudoku grid
void printSudoku(int sudoku[SIZE][SIZE]) {
    for (int i = 0; i < SIZE; i++) {
        printf("%2d ", i + 1); // Line number
        for (int j = 0; j < SIZE; j++) {
            printf("%d ", sudoku[i][j]);
        }
        printf("\n");
    }
}

// Function to check if the given number is already present in the row
int isNumberPresentInRow(int sudoku[SIZE][SIZE], int row, int num) {
    for (int col = 0; col < SIZE; col++) {
        if (sudoku[row][col] == num) {
            return 1; // Number is already present in the row
        }
    }
    return 0; // Number is not present in the row
}

// Function to check if the given number is already present in the column
int isNumberPresentInColumn(int sudoku[SIZE][SIZE], int col, int num) {
    for (int row = 0; row < SIZE; row++) {
        if (sudoku[row][col] == num) {
            return 1; // Number is already present in the column
        }
    }
    return 0; // Number is not present in the column
}

// Function to check if the given number is already present in the 3x3 box
int isNumberPresentInBox(int sudoku[SIZE][SIZE], int startRow, int startCol, int num) {
    for (int row = 0; row < 3; row++) {
        for (int col = 0; col < 3; col++) {
            if (sudoku[row + startRow][col + startCol] == num) {
                return 1; // Number is already present in the box
            }
        }
    }
    return 0; // Number is not present in the box
}

// Function to check if it's safe to place a number at the given position
int isSafe(int sudoku[SIZE][SIZE], int row, int col, int num) {
    return !isNumberPresentInRow(sudoku, row, num) &&
           !isNumberPresentInColumn(sudoku, col, num) &&
           !isNumberPresentInBox(sudoku, row - row % 3, col - col % 3, num);
}

// Function to solve the Sudoku puzzle using backtracking
int solveSudoku(int sudoku[SIZE][SIZE]) {
    for (int row = 0; row < SIZE; row++) {
        for (int col = 0; col < SIZE; col++) {
            if (sudoku[row][col] == 0) {
                for (int num = 1; num <= 9; num++) {
                    if (isSafe(sudoku, row, col, num)) {
                        sudoku[row][col] = num;
                        if (solveSudoku(sudoku)) {
                            return 1; // Puzzle solved
                        }
                        sudoku[row][col] = 0; // Undo the current cell for backtracking
                    }
                }
                return 0; // No solution found
            }
        }
    }
    return 1; // Puzzle solved
}

// Function to generate a random Sudaoku puzzle
void generateRandomSudoku(int sudoku[SIZE][SIZE]) { 
    // Initialize an array to store all the cell positions
    int cellPositions[SIZE * SIZE];
    for (int i = 0; i < SIZE * SIZE; i++) {
        cellPositions[i] = i;
    }

    // Shuffle the cell positions using Fisher-Yates algorithm
    srand(time(NULL));
    for (int i = SIZE * SIZE - 1; i > 0; i--) {
        int j = rand() % (i + 1);
        int temp = cellPositions[i];
        cellPositions[i] = cellPositions[j];
        cellPositions[j] = temp;
    }

    // Fill the cells in a sequential manner using the shuffled cell positions
    for (int i = 0; i < SIZE * SIZE; i++) {
        int cellIndex = cellPositions[i];
        int row = cellIndex / SIZE;
        int col = cellIndex % SIZE;
        int num = rand() % 9 + 1;

        if (isSafe(sudoku, row, col, num)) {
            sudoku[row][col] = num;
        }
    }

    // Solve the partially filled Sudoku grid
    solveSudoku(sudoku);
}

int main() {
    int sudoku[SIZE][SIZE] = {0};

    generateRandomSudoku(sudoku);
    printf("    ");
    for (int i = 1; i <= SIZE; i++) {
        printf("%d ", i); // Column numbers
    }
    printf("\n");
    printf("    ---------------------\n");
    printSudoku(sudoku);

    return 0;
}
