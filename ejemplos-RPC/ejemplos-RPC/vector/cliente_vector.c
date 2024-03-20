#include "vector.h"

#include <stdlib.h>
#include <stdio.h>
#include <malloc.h>


/* prototipos */
float * crear_vector_aleatorio(int);
void volcar_vector(t_vector);

int main(int argc, char ** argv) {
   char *host;
   
   if (argc != 2) {	
      fprintf(stderr, "ERROR: formato incorrecto \ncliente nombre_servidor\n");
      exit(1);
   }
   
   host = argv[1];
   
   CLIENT *clnt;
   t_vector *resultado1;
   entrada1 args1;
   entrada2 args2;
   
   
   clnt = clnt_create (host, OPER_VECTOR, OPER_VECTORVER, "tcp");
   if (clnt == NULL) {
      clnt_pcreateerror (host);
      exit (1);
   }
   
   /* Crear vectores aleatorios */
   t_vector vector1;
   vector1.t_vector_len = 5;
   vector1.t_vector_val = crear_vector_aleatorio(5);
   printf("VECTOR 1:");
   volcar_vector(vector1);
   printf("\n");
   
   t_vector vector2;
   vector2.t_vector_len = 5;
   vector2.t_vector_val = crear_vector_aleatorio(5);
   printf("VECTOR 2:");
   volcar_vector(vector2);
   printf("\n");
   
   /* llamada a escalado_vector */
   args1.v = vector1;
   args1.c = 100.0;
   resultado1 = escalado_vector_1(&args1, clnt);
   if (resultado1 == (t_vector *) NULL) {
      clnt_perror (clnt, "call failed");
   }
   printf("ESCALADO DE VECTOR (*100)\n vector resultado:");
   volcar_vector(*resultado1);
   printf("\n");
   
   
   /* llamada a suma vectorial */
   args2.v1 = vector1;
   args2.v2 = vector2;   
   resultado1 = suma_vectorial_1(&args2, clnt);
   if (resultado1 == (t_vector *) NULL) {
      clnt_perror (clnt, "call failed");
   }
   printf("SUMA VECTORIAL DE 2 VECTORES\n vector resultado:");
   volcar_vector(*resultado1);   
   printf("\n");
   
   
   clnt_destroy (clnt);
}


float * crear_vector_aleatorio(int tamano) {
   float * aux = (float *) malloc(tamano * sizeof(float));
   int i;
   
   for (i=0; i < tamano; i++){
      aux[i] = 10.0 * (random()/(float) RAND_MAX);
//      aux[i] = i;
   }
   return(aux);
}


void volcar_vector(t_vector aux){
   int i;
   
   printf("( ");
   for (i = 0; i < aux.t_vector_len; i++)   {
       printf("%6.2f ", aux.t_vector_val[i]);
   }
   printf(")");
   
}
