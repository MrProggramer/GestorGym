package models.menus;

import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UserType {
    public static class DetectectarTipoUser {
        private final String tipo;
        private final boolean isActive;
        private final String nombre;

        public DetectectarTipoUser(String tipo, boolean isActive, String nombre) {
            this.tipo = tipo;
            this.isActive = isActive;
            this.nombre = nombre;
        }

        public String getType() {
            return tipo;
        }

        public boolean isActive() {
            return isActive;
        }

        public String getNombre() {
            return nombre;
        }

        @Override
        public String toString() {
            return "DetectectarTipoUser{" +
                    "tipo='" + tipo + '\'' +
                    ", isActive=" + isActive +
                    ", nombre='" + nombre + '\'' +
                    '}';
        }
    }

    public static DetectectarTipoUser detectFromFileOrSample(String path) {
        JSONObject json;
        try {
            String content = new String(Files.readAllBytes(Paths.get(path)));
            json = new JSONObject(content);
        } catch (Exception e) {

            json = new JSONObject();
            json.put("tipo", "Cliente");
            json.put("isActive", true);
            json.put("nombre", "Usuario de ejemplo Pepito");
        }
        return fromJson(json);
    }

    public static DetectectarTipoUser fromJson(JSONObject json) {
        String type = json.optString("tipo", "Cliente");
        boolean isActive = json.optBoolean("isActive", true);
        String nombre = json.optString("nombre", "Usuario");
        return new DetectectarTipoUser(type, isActive, nombre);
    }
}
