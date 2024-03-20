#include <stdio.h>
#include <stdlib.h>


int main(){

	int** pInt;
	int** pInt2;
	pInt = (int**)malloc(sizeof(int*)*3);
	for(int i=0; i<3;i++){
		pInt[i] = (int*)malloc(sizeof(int)*3);
	}

	for(int i=0;i<3;i++){
		for(int j=0;j<3;j++){
			pInt[i][j] = i;
		}
	}

	pInt2 = (int**)malloc(sizeof(int*)*3);
	for(int i=0; i<3;i++){
		pInt2[i] = (int*)malloc(sizeof(int)*3);
	}

	for(int i=0;i<3;i++){
		for(int j=0;j<3;j++){
			pInt2[i][j] = i+1;
		}
	}



	printf("\n");
	
	for(int i=0;i<3;i++){
		for(int j=0;j<3;j++){
			printf("%d ",pInt[i][j]);
		}
		printf("\n");
	}

	printf("\n");

	printf("\n");
	
	for(int i=0;i<3;i++){
		for(int j=0;j<3;j++){
			printf("%d ",pInt2[i][j]);
		}
		printf("\n");
	}

	printf("\n");

	for(int i=0; i<3;i++){
		for(int j=0;j<3;j++){
			pInt[i][j] = pInt2[i][j];
		}
	}
	printf("Ahora copiando (dir memoria)\n");
	
	for(int i=0;i<3;i++){
		for(int j=0;j<3;j++){
			printf("%d ",pInt[i][j]);
		}
		printf("\n");
	}

	for(int i=0;i<3;i++){
		for(int j=0;j<3;j++){
			printf("%d ",pInt2[i][j]);
		}
		printf("\n");
	}

	printf("Ahora cambiamos un array\n");
	
	for(int i=0;i<3;i++){
		for(int j=0;j<3;j++){
			pInt[i][j] = 8;
		}
	}


	printf("\n");
	
	for(int i=0;i<3;i++){
		for(int j=0;j<3;j++){
			printf("%d ",pInt[i][j]);
		}
		printf("\n");
	}


	printf("\n");
	for(int i=0;i<3;i++){
		for(int j=0;j<3;j++){
			printf("%d ",pInt2[i][j]);
		}
		printf("\n");
	}


	for(int i=0; i<3;i++){
		free(pInt[i]);
	}
	free(pInt);

	for(int i=0; i<3;i++){
		free(pInt2[i]);
	}
	free(pInt2);

	

	return 0;	
}
