/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 34667
 */
@MappedSuperclass
@Table(name = "ACTIVIDAD")
@XmlRootElement
public class Actividad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idActividad")
    private String idActividad;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "precioBaseMes")
    private int precioBaseMes;
    @JoinTable(name = "REALIZA", joinColumns = {
        @JoinColumn(name = "idActividad", referencedColumnName = "idActividad")}, inverseJoinColumns = {
        @JoinColumn(name = "numeroSocio", referencedColumnName = "numeroSocio")})
    @ManyToMany
    private Set<Socio> socioSet;
    @JoinColumn(name = "monitorResponsable", referencedColumnName = "codMonitor")
    @ManyToOne
    private Monitor monitorResponsable;

    public Actividad() {
    }

    public Actividad(String idActividad) {
        this.idActividad = idActividad;
    }

    public Actividad(String idActividad, String nombre, int precioBaseMes) {
        this.idActividad = idActividad;
        this.nombre = nombre;
        this.precioBaseMes = precioBaseMes;
    }

    public String getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(String idActividad) {
        this.idActividad = idActividad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecioBaseMes() {
        return precioBaseMes;
    }

    public void setPrecioBaseMes(int precioBaseMes) {
        this.precioBaseMes = precioBaseMes;
    }

    @XmlTransient
    public Set<Socio> getSocioSet() {
        return socioSet;
    }

    public void setSocioSet(Set<Socio> socioSet) {
        this.socioSet = socioSet;
    }

    public Monitor getMonitorResponsable() {
        return monitorResponsable;
    }

    public void setMonitorResponsable(Monitor monitorResponsable) {
        this.monitorResponsable = monitorResponsable;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActividad != null ? idActividad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actividad)) {
            return false;
        }
        Actividad other = (Actividad) object;
        if ((this.idActividad == null && other.idActividad != null) || (this.idActividad != null && !this.idActividad.equals(other.idActividad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Actividad[ idActividad=" + idActividad + " ]";
    }
    
}
