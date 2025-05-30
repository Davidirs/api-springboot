package com.example.apiasistencia.models;

public class Estudiante {
    private String carrera;
    private String imagen;
    private String nombre;
    private String cedula;
    private String correo;
    private String telefono;
    private String periodo;
    private String fechaNacimiento;
    private String estado;

    // Constructor vacío necesario para la deserialización
    public Estudiante() {
    }

    public Estudiante(String cedula,String nombre, String correo, String imagen, String carrera, 
        String periodo, 
        String fechaNacimiento, 
        String estado, 
        String telefono
        ) {
            this.cedula = cedula;
            this.nombre = nombre;
            this.correo = correo;
            this.imagen = imagen;
            this.carrera = carrera;
            this.periodo = periodo;
            this.fechaNacimiento = fechaNacimiento;
            this.estado = estado;
            this.telefono = telefono;
        }

    // Getters y Setters
    public String getcedula() {
        return cedula;
    }

    public void setcedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getcorreo() {
        return correo;
    }

    public void setcorreo(String correo) {
        this.correo = correo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}