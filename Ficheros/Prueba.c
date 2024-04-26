typedef char Cadena[150];
#include <rpc/types.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

enum CAMPO {
    ISBN = 0,
    TITULO = 1,
    AUTOR = 2,
    AÑO = 3,
    PAIS = 4,
    IDIOMA = 5,
    LIBROSDISPONIBLES = 6,
    LIBROSPRESTADOS = 7,
    LIBROSENESPERA = 8,
    TODOS = 9

};
struct TLibro {
    Cadena Isbn;
    Cadena Titulo;
    Cadena Autor;
    int Anio;
    Cadena Pais;
    Cadena Idioma;
    int NoLibros;
    int NoPrestados;
    int NoListaEspera;
};
typedef struct TLibro TLibro;

struct TConsulta {
    int Ida;
    Cadena Datos;
};
TLibro *Biblioteca = NULL; // Vector dinamico de libros

bool_t EsMenor(int P1, int P2, int Campo) {
    bool_t salida = FALSE;
    TLibro L1 = Biblioteca[P1];
    TLibro L2 = Biblioteca[P2];

    switch (Campo) {
    case 0:
        salida = strcmp(L1.Isbn, L2.Isbn) < 0 ? TRUE : FALSE;
        break;
    case 1:
        salida = strcmp(L1.Titulo, L2.Titulo) < 0 ? TRUE : FALSE;
        break;
    case 2:
        salida = strcmp(L1.Autor, L2.Autor) < 0 ? TRUE : FALSE;
        break;
    case 3:
        salida = L1.Anio < L2.Anio ? TRUE : FALSE;
        break;
    case 4:
        salida = strcmp(L1.Pais, L2.Pais) < 0 ? TRUE : FALSE;
        break;
    case 5:
        salida = strcmp(L1.Idioma, L2.Idioma) < 0 ? TRUE : FALSE;
        break;
    case 6:
        salida = L1.NoLibros < L2.NoLibros ? TRUE : FALSE;
        break;
    case 7:
        salida = L1.NoPrestados < L2.NoPrestados ? TRUE : FALSE;
        break;
    case 8:
        salida = L1.NoListaEspera < L2.NoListaEspera ? TRUE : FALSE;
        break;
    }
    return salida;
}
void Formatea(char *Salida, const char *p, int ancho, char Caracter) {
    Cadena vacia;
    int len = ancho - strlen(p);
    int l = 0, c = 0;

    while (p[l] != '\0') {
        if ((unsigned char)p[l] > 128)
            c++;
        l++;
    }
    len += c / 2;

    if (len < 0)
        len = 0;
    for (int i = 0; i < len; i++)
        vacia[i] = Caracter;
    vacia[len] = '\0';

    sprintf(Salida, "%s%s", p, vacia);
}

void MostrarLibro(TLibro *L, int Pos, bool_t Cabecera) {
    Cadena T, A, B, PI;
    if (Cabecera == TRUE) {
        printf("%-*s%-*s%-*s%*s%*s%*s\n", 5, "POS", 58, "TITULO", 18, "ISBN", 4,
               "DIS", 4, "PRE", 4, "RES");
        printf("     %-*s%-*s%-*s\n", 30, "AUTOR", 28, "PAIS (IDIOMA)", 12,
               "AÑO");
        Formatea(B, "*", 93, '*');
        printf("%s\n", B);
    }
    Formatea(T, L->Titulo, 58, ' ');
    Formatea(A, L->Autor, 30, ' ');
    strcpy(B, L->Pais);
    strcat(B, " (");
    strcat(B, L->Idioma);
    strcat(B, ")");
    Formatea(PI, B, 28, ' ');
    printf("%-5d%s%-*s%*d%*d%*d\n", Pos + 1, T, 18, L->Isbn, 4, L->NoLibros, 4,
           L->NoPrestados, 4, L->NoListaEspera);
    printf("     %s%s%-*d\n", A, PI, 12, L->Anio);
}
void swap(TLibro *xp, TLibro *yp) {
    TLibro temp = *xp;
    *xp = *yp;
    *yp = temp;
}
void bubbleSort(int n) {
    int i, j;
    bool swapped;
    for (i = 0; i < n - 1; i++) {
        swapped = false;
        for (j = 0; j < n - i - 1; j++) {
            if (!EsMenor(i, j, TITULO)) {
                swap(&Biblioteca[j], &Biblioteca[j + 1]);
                swapped = true;
            }
        }

        // If no two elements were swapped by inner loop,
        // then break
        if (swapped == false)
            break;
    }
}

typedef struct TConsulta TConsulta;
int main() {
    system("clear");

    char id[10] = "0000";

    for (int i = 0; i < 10; i++) {
        // Convert char array to integer
        int num = atoi(id);

        // Increment the number
        num++;

        // Convert integer back to string
        sprintf(id, "%04d", num);

        // Print the updated id
        printf("ID: %s\n", id);
    }

    int numLibros = 0;
    TConsulta consulta;
    consulta.Ida = 5;
    int c, b = 1;
    // c = Test(b);
    //  printf("\nC -> %d\n", c);
    strcpy(consulta.Datos, "hola");
    printf("\nIda : %d", consulta.Ida);
    printf("\nNombre %s\n", consulta.Datos);
    FILE *fdatos = fopen("Biblioteca.cdat", "r");
    fread(&numLibros, sizeof(int), 1, fdatos);
    int tama = ((numLibros / 4) + 1) * 4;
    printf("\nTama = %d", tama);
    getchar();
    Biblioteca = (TLibro *)malloc(sizeof(TLibro) * tama);
    TLibro nuevo;
    nuevo.Anio = 200;
    strcpy(nuevo.Autor, "pepe");
    strcpy(nuevo.Idioma, "esp");
    strcpy(nuevo.Isbn, id);
    nuevo.NoLibros = 0;
    nuevo.NoListaEspera = 420;
    nuevo.NoPrestados = 69;
    strcpy(nuevo.Pais, "Marocco");
    strcpy(nuevo.Titulo, "Nijas in paris");
    fread(Biblioteca, sizeof(TLibro), numLibros, fdatos);
    fclose(fdatos);
    printf("\nVamos a meter el libro %s en la posicion %d", nuevo.Titulo,
           numLibros);
    Biblioteca[numLibros++] = nuevo;
    printf("\nVamos a ordenar por isbn");

    /*for (int i = 0; i < numLibros - 1; ++i) {
        for (int j = i + 1; j < numLibros; ++j) {
            if (!EsMenor(i, j, ISBN)) { // swap
                tmp = Biblioteca[i];
                Biblioteca[i] = Biblioteca[j];
                Biblioteca[j] = Biblioteca[i];
            }
        }
    }*/
    bubbleSort(numLibros);
    MostrarLibro(Biblioteca, 0, TRUE);
    for (int i = 1; i < numLibros; i++) {
        MostrarLibro(&Biblioteca[i], i, FALSE);
    }

    // fdatos = fopen("Biblioteca.cdat", "w");
    // fwrite(&numLibros, sizeof(int), 1, fdatos);
    // fwrite(biblioteca, sizeof(TLibro) * numLibros, 1, fdatos);
    // fclose(fdatos);
    free(Biblioteca);
    /*
    FILE *fdatos2 = fopen("Biblioteca.cdat", "r");
    fread(&numLibros, sizeof(int), 1, fdatos2);
    TLibro *biblioteca2 = (TLibro *)malloc(sizeof(TLibro) * numLibros);
    fread(biblioteca2, sizeof(TLibro), numLibros, fdatos2);
    for (int i = 0; i < numLibros; i++)
    {
        printf("\nLibro[%d] \n ISBN: %s \tTitulo: %s \tAutor: %s", i,
    biblioteca2[i].Isbn, biblioteca2[i].Titulo, biblioteca2[i].Autor);
        printf("\n****************************************************************************\n");
    }
    fclose(fdatos2);
    free(biblioteca2);*/
    // printf("\nPrimer dato de la biblioteca \n ISBN: %s \tTitulo: %s \tAutor:
    // %s", biblioteca->Isbn, biblioteca->Titulo, biblioteca->Autor);

    /*TLibro prueba;
    printf("\nIntroduce el Isbn: ");
    //__fpurge(stdin);
    scanf("%s", prueba.Isbn);
    printf("\nIntroduce el Autor: ");
    //__fpurge(stdin);
    scanf("%s", prueba.Autor);
    printf("\nIntroduce el Titulo: ");
    //__fpurge(stdin);
    scanf("%s", prueba.Titulo);
    printf("\nIntroduce el año: ");
    scanf("%d", &prueba.Anio);
    printf("\nIntroduce el Pais: ");
    //__fpurge(stdin);
    scanf("%s", prueba.Pais);
    printf("\nIntroduce el Idioma: ");
    //__fpurge(stdin);
    scanf("%s", prueba.Idioma);
    printf("\nIntroduce el Numero de libros inicial: ");
    scanf("%d", &prueba.NoLibros);
    prueba.NoListaEspera = 0;
    prueba.NoPrestados = 0;
    printf("\nEl nuevo libro ->\n");
    printf("\nIsbn: %s\nTitulo: %s\nAutor: %s\\nAño: %d\nPais: %s\nIdioma:
    %s\nNumeroLibros: %d", prueba.Isbn, prueba.Titulo, prueba.Autor,
    prueba.Anio, prueba.Pais, prueba.Idioma, prueba.NoLibros);
*/

    /*Cadena prueba = {1, 2, 3, 4};
    Cadena prueba2 = {'1', '2', '3', '4', '\0'};
    Cadena prueba3 = "prueba3";
     printf("\nprueba: %s", prueba);
     printf("\nprueba2: %s", prueba2);
     printf("\nprueba3: %s", prueba3);
    if (strcmp(prueba, prueba2) == 0)
    {
        printf("\nSi");
    }
    else
    {
        printf("\nNo");
    }*/
    return 0;
}