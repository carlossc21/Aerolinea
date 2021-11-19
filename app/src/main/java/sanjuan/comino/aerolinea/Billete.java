package sanjuan.comino.aerolinea;

import android.os.Parcel;
import android.os.Parcelable;

public class Billete implements Parcelable {

    private String nombre, apellidos, email, direccion, fecha, origen, destino;
    private long telefono;
    private int extras;
    private boolean seguro;

    private int precio = 0;


    public Billete(String nombre, String apellidos, String email, String direccion, String fecha, long telefono, int extras, boolean seguro, String origen, String destino) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.direccion = direccion;
        this.fecha = fecha;
        this.telefono = telefono;
        this.extras = extras;
        this.seguro = seguro;
        this.origen = origen;
        this.destino = destino;
    }


    protected Billete(Parcel in) {
        nombre = in.readString();
        apellidos = in.readString();
        email = in.readString();
        direccion = in.readString();
        fecha = in.readString();
        origen = in.readString();
        destino = in.readString();
        telefono = in.readLong();
        extras = in.readInt();
        seguro = in.readByte() != 0;
        precio = in.readInt();
    }

    public static final Creator<Billete> CREATOR = new Creator<Billete>() {
        @Override
        public Billete createFromParcel(Parcel in) {
            return new Billete(in);
        }

        @Override
        public Billete[] newArray(int size) {
            return new Billete[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getEmail() {
        return email;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getFecha() {
        return fecha;
    }

    public long getTelefono() {
        return telefono;
    }

    public int getExtras() {
        return extras;
    }

    public boolean getSeguro(){
        return seguro;
    }

    public void setPrecio(int precio){
        this.precio = precio;
    }

    public int getPrecio() {
        return precio;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nombre);
        parcel.writeString(apellidos);
        parcel.writeString(email);
        parcel.writeString(direccion);
        parcel.writeString(fecha);
        parcel.writeString(origen);
        parcel.writeString(destino);
        parcel.writeLong(telefono);
        parcel.writeInt(extras);
        parcel.writeByte((byte) (seguro ? 1 : 0));
        parcel.writeInt(precio);
    }
}
