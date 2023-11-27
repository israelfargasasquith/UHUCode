package Modelo;

import Modelo.Monitor;
import Modelo.Socio;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-11-23T13:20:24", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Actividad.class)
public class Actividad_ { 

    public static volatile SingularAttribute<Actividad, String> descripcion;
    public static volatile SingularAttribute<Actividad, String> idActividad;
    public static volatile SingularAttribute<Actividad, Monitor> monitorResponsable;
    public static volatile SingularAttribute<Actividad, Integer> precioBaseMes;
    public static volatile SingularAttribute<Actividad, String> nombre;
    public static volatile SetAttribute<Actividad, Socio> socios;

}