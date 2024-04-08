typedef char Cadena[150];
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
struct TLibro
{
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

struct TConsulta
{
    int Ida;
    Cadena Datos;
};
int *Test(int a)
{
    return a + 2;
}
typedef struct TConsulta TConsulta;
int main()
{
    system("clear");
    int numLibros = 0;
    TConsulta consulta;
    consulta.Ida = 5;
    int c, b = 1;
    c = Test(b);
    printf("\nC -> %d\n", c);
    strcpy(consulta.Datos, "hola");
    printf("\nIda : %d", consulta.Ida);
    printf("\nNombre %s\n", consulta.Datos);
    FILE *fdatos = fopen("Biblioteca.cdat", "r");
    TLibro *biblioteca = (TLibro *)malloc(sizeof(TLibro));
    TLibro prueba;
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
    printf("\nIsbn: %s\nTitulo: %s\nAutor: %s\\nAño: %d\nPais: %s\nIdioma: %s\nNumeroLibros: %d", prueba.Isbn, prueba.Titulo, prueba.Autor, prueba.Anio, prueba.Pais, prueba.Idioma, prueba.NoLibros);

    fread(&numLibros, sizeof(int), 1, fdatos);
    fread(biblioteca, sizeof(TLibro), 1, fdatos);
    printf("\nPrimer dato de la biblioteca \n ISBN: %s \tTitulo: %s \tAutor: %s", biblioteca->Isbn, biblioteca->Titulo, biblioteca->Autor);
    fclose(fdatos);
    free(biblioteca);
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