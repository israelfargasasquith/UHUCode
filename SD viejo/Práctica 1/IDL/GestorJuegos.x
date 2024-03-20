typedef char Cadena[300];

enum TDificultad {VACIO, MUY_FACIL, FACIL, MEDIA, DIFICIL, MUY_DIFICIL};

struct RCadena
{
	bool Salida; 
	Cadena Contenido;
};

struct TFCV 
{
	int pCod; 
	int pFil; 
	int pCol; 
	char pVal; 
};

struct TFC 
{
	int pCod;
	int pFil;
	int pCol;
};


program GESTORJUEGOS {
	version GESTORJUEGOS_VER {

	int Nuevo(TDificultad)=1;

	
	bool Borrar(int)=2;

	
	bool PonerValor(TFCV )=3;

	
	char ObtenerValor(TFC )=4;

	
	bool ComprobarValor(TFCV )=5;

	
	int NumeroHuecos(int )=6;

	
	
	bool Correcto(int )=7;

	
	RCadena Ayuda(TFC)=8;

	
	RCadena GetSudoku(int)=9;

	} = 1;
} = 0x30000001;



