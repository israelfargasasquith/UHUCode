package Modelo;

import Modelo.Actividad;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-11-23T13:20:24", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Monitor.class)
public class Monitor_ { 

    public static volatile SingularAttribute<Monitor, String> nick;
    public static volatile SingularAttribute<Monitor, String> codMonitor;
    public static volatile SingularAttribute<Monitor, String> correo;
    public static volatile SetAttribute<Monitor, Actividad> actividadesResponsable;
    public static volatile SingularAttribute<Monitor, String> fechaEntrada;
    public static volatile SingularAttribute<Monitor, String> telefono;
    public static volatile SingularAttribute<Monitor, String> nombre;
    public static volatile SingularAttribute<Monitor, String> dni;

}