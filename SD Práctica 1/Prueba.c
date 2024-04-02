typedef char Cadena[150];
#include<stdlib.h>
int main(){
    Cadena prueba = {1,2,3,4};
    Cadena prueba2 = {1,2,3,4};

    if(strcmp(prueba,prueba2) == 0){
        printf("Si");
    }else{
        printf("No");
    }

    return 0;
}