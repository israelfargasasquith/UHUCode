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
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 34667
 */
@MappedSuperclass
@Table(name = "MONITOR")
@XmlRootElement
public class Monitor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codMonitor")
    private String codMonitor;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "dni")
    private String dni;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @Column(name = "fechaEntrada")
    private String fechaEntrada;
    @Column(name = "nick")
    private String nick;
    @OneToMany(mappedBy = "monitorResponsable")
    private Set<Actividad> actividadSet;

    public Monitor() {
    }

    public Monitor(String codMonitor) {
        this.codMonitor = codMonitor;
    }

    public Monitor(String codMonitor, String nombre, String dni, String fechaEntrada) {
        this.codMonitor = codMonitor;
        this.nombre = nombre;
        this.dni = dni;
        this.fechaEntrada = fechaEntrada;
    }

    public String getCodMonitor() {
        return codMonitor;
    }

    public void setCodMonitor(String codMonitor) {
        this.codMonitor = codMonitor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    @XmlTransient
    public Set<Actividad> getActividadSet() {
        return actividadSet;
    }

    public void setActividadSet(Set<Actividad> actividadSet) {
        this.actividadSet = actividadSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codMonitor != null ? codMonitor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Monitor)) {
            return false;
        }
        Monitor other = (Monitor) object;
        if ((this.codMonitor == null && other.codMonitor != null) || (this.codMonitor != null && !this.codMonitor.equals(other.codMonitor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Monitor[ codMonitor=" + codMonitor + " ]";
    }
    
}
