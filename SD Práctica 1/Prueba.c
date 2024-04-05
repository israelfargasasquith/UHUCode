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
typedef struct TConsulta TConsulta;
int main()
{
    system("clear");
    int numLibros = 0;
    TConsulta consulta;
    consulta.Ida = 5;
    strcpy(consulta.Datos, "hola");
    printf("\nIda : %d", consulta.Ida);
    printf("\nNombre %s\n", consulta.Datos);
    FILE *fdatos = fopen("Biblioteca.cdat", "r");
    TLibro *biblioteca = (TLibro *)malloc(sizeof(TLibro));
    fread(&numLibros, sizeof(int), 1, fdatos);
    fread(biblioteca, sizeof(TLibro), 1, fdatos);
    printf("Primer dato de la biblioteca \n ISBN: %s \tTitulo: %s \tAutor: %s", biblioteca->Isbn, biblioteca->Titulo, biblioteca->Autor);
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