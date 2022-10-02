package test;

import beans.Mascota;
import connection.DBConnection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

public class OperacionesBD {

    public static void main(String[] args) {
        listarMascota();
        //actualizarMascota(1,"Perro");
        listarMascota();
    }

    public static void actualizarMascota(int id_mascota, String tipo) {
        DBConnection con = new DBConnection();
        String sql = "UPDATE mascota SET tipo=' " + tipo + " 'WHERE id_mascota = " + id_mascota;
        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

    }

    public static void listarMascota() {
        DBConnection con = new DBConnection();
        String sql = "SELECT * FROM mascota ";
        try {
            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id_mascota = rs.getInt("id_mascota");
                String tipo = rs.getString("tipo");
                String nombre_mascota = rs.getString("nombre_mascota");
                String raza = rs.getString("raza");
                int edad = rs.getInt("edad");
                String tamano = rs.getString("tamano");
                String peso = rs.getString("peso");
                String color = rs.getString("color");
                String ciudad = rs.getString("ciudad");
                String ruta_foto = rs.getString("ruta_foto");
                Date fecha_perdido = rs.getDate("fecha_perdido");
                String otros = rs.getString("otros");
            
                Mascota mascotas = new Mascota(id_mascota, tipo, nombre_mascota,raza,edad,tamano,peso,color,ciudad,ruta_foto,fecha_perdido,otros);
                System.out.println(mascotas.toString());
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

    }

}
