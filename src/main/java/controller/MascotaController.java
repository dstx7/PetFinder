package controller;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
//import java.util.Date;
import java.sql.Date;
import java.util.List;

import com.google.gson.Gson;
import beans.Mascota;
import connection.DBConnection;

public class MascotaController implements IMascotaController {

    @Override
    public String listar(boolean ordenar, String orden) {

        Gson gson = new Gson();

        DBConnection con = new DBConnection();
        String sql = "Select * from mascota";

        if (ordenar == true) {
            sql += " order by tipo " + orden;
        }

        List<String> mascota = new ArrayList<String>();

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
              

                Mascota mascotas = new Mascota(id_mascota, tipo, nombre_mascota, raza, edad, tamano,peso,color,ciudad,ruta_foto, fecha_perdido,otros );

                mascota.add(gson.toJson(mascotas));

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return gson.toJson(mascota);

    }
}