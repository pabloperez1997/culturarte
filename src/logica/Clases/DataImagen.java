package logica.Clases;

import java.util.Arrays;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author Martin
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DataImagen {

    private byte[] stream;
    private String nombreArchivo;
    private String extensionArchivo;

    public DataImagen(final byte[] stream, final String nombreArchivo, final String extensionArchivo) {
        this.stream = stream;
        this.nombreArchivo = nombreArchivo;
        this.extensionArchivo = extensionArchivo;
    }

    public byte[] getStream() {
        byte[] result = Arrays.copyOf(this.stream, this.stream.length);
        return result;
    }

    public void setStream(final byte[] stream) {
        this.stream = stream;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(final String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getExtensionArchivo() {
        return extensionArchivo;
    }

    public void setExtensionArchivo(final String extensionArchivo) {
        this.extensionArchivo = extensionArchivo;
    }

}
