typedef char Cadena[300];
enum Dificultad {FACIL,MEDIO,DIFICIL,EXPERTO};

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

	
	int ObtenerValor(TFC )=4;

	
	bool ComprobarValor(TFCV )=5;

	
	int NumeroHuecos(int )=6;

	
	
	bool Correcto(int )=7;

	
	void Ayuda(TFC)=8;

	
	RCadena GetSudoku(int)=9;

	} = 1;
} = 0x30000001;


