CC = gcc
CFLAGS += -g -I/usr/include/tirpc
LDLIBS += -ltirpc -lnsl

all: GestorBibliotecad

GestorBiblioteca: GestorBibliotecad.o GestorBiblioteca_svc.o GestorBiblioteca_xdr.o
	$(CC) -o $@ $^ $(LDLIBS)

GestorBibliotecad.o: GestorBibliotecad.c GestorBiblioteca.h
	$(CC) -c $(CFLAGS) $< -o $@

GestorBiblioteca_svc.o: GestorBiblioteca_svc.c GestorBiblioteca.h
	$(CC) -c $(CFLAGS) $< -o $@

GestorBiblioteca_xdr.o: GestorBiblioteca_xdr.c GestorBiblioteca.h
	$(CC) -c $(CFLAGS) $< -o $@

clean:
	rm -f *.o GestorBibliotecad

