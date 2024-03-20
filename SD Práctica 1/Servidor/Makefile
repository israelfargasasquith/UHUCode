CFLAGS=-Wall
CC=gcc

all: GestorBibliotecad

GestorBiblioteca: GestorBibliotecad.o GestorBiblioteca_svc.o GestorBiblioteca_xdr.o
	$(CC) -o $@ GestorBibliotecad.o GestorBiblioteca_svc.o GestorBiblioteca_xdr.o

GestorBiblioteca.o: GestorBiblioteca.h

clean:
	rm -f *.o GestorBibliotecad