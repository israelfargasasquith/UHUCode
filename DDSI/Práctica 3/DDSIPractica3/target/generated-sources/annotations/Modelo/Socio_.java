package Modelo;

import Modelo.Actividad;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-11-04T13:57:02", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Socio.class)
public class Socio_ { 

    public static volatile SingularAttribute<Socio, String> numeroSocio;
    public static volatile SingularAttribute<Socio, String> fechaNacimiento;
    public static volatile SingularAttribute<Socio, String> correo;
    public static volatile SingularAttribute<Socio, Character> categoria;
    public static volatile SingularAttribute<Socio, String> fechaEntrada;
    public static volatile SingularAttribute<Socio, String> telefono;
    public static volatile SetAttribute<Socio, Actividad> actividades;
    public static volatile SingularAttribute<Socio, String> nombre;
    public static volatile SingularAttribute<Socio, String> dni;

}