/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.Controladores;

import java.util.Calendar;
import java.util.Map;
import logica.Clases.Colaborador;
import logica.Clases.Propuesta;
import logica.Clases.Usuario;
import Persistencia.DBUsuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import static java.nio.file.StandardOpenOption.CREATE;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.Clases.Categoria;
import logica.Clases.Colaboracion;
import logica.Clases.DataImagen;
import logica.Clases.DtColaboraciones;
import logica.Clases.DtNickTitProp;
import logica.Clases.DtSeguidor;
import logica.Clases.DtUsuario;
import logica.Clases.DtinfoColaborador;
import logica.Clases.DtinfoPropuesta;
import logica.Clases.Proponente;
import logica.Fabrica;
import logica.Interfaces.IControladorUsuario;
import logica.Interfaces.IPropCat;
import logica.Clases.codificador;
import logica.Controladores.configuraciones;

/**
 *
 * @author Santiago.S
 */
public class ControladorUsuario implements IControladorUsuario {

    private static ControladorUsuario instancia;
    private Map<String, Usuario> Usuarios;
    private Map<String, Colaboracion> colaboraciones;
    private IPropCat IPC;
    private DBUsuario dbUsuario = null;
    private Colaborador Colaborador;
    codificador a = new codificador();
    configuraciones configuracion = new configuraciones();
    private String carpetaImagenesPerfiles = configuracion.getCarpetaImagenes() + "\\fPerfiles";

    public static ControladorUsuario getInstance() {
        if (instancia == null) {
            instancia = new ControladorUsuario();
        }
        return instancia;
    }

    public ControladorUsuario() {
        this.Usuarios = new HashMap<>();
        this.dbUsuario = new DBUsuario();
        this.IPC = Fabrica.getInstance().getControladorPropCat();
    }

    @Override
    public Map<String, Usuario> getUsuarios() {
        return Usuarios;
    }

    public void setUsuarios(Map<String, Usuario> Usuarios) {
        this.Usuarios = Usuarios;
    }

    @Override
    public boolean seguirUsuario(String nickUsu1, String nickUsu2) throws Exception {

        Usuario aux1 = (Usuario) this.Usuarios.get(nickUsu1);
        Usuario aux2 = (Usuario) this.Usuarios.get(nickUsu2);

        if (aux1 == null) {
            throw new Exception("El Usuario " + nickUsu1 + " NO existe");
        }

        if (aux2 == null) {
            throw new Exception("El Usuario " + nickUsu2 + " NO existe");
        }
        if (aux1.getSeguidos().containsKey(nickUsu2)) {
            throw new Exception("El Usuario " + nickUsu1 + " ya sigue a " + nickUsu2);
        }

        if (aux1 == aux2) {
            throw new Exception("Un Usuario no puede seguirse a si mismo");
        }

        boolean res = this.dbUsuario.seguirUsuario(nickUsu1, nickUsu2);
        if (res) {
            aux1.getSeguidos().put(nickUsu2, aux2);
            aux2.getSeguidores().put(nickUsu1, aux1);
            return true;
        }

        return res;
    }

    @Override
    public List<DtUsuario> ListarProponentes2() {
        List<DtUsuario> listProp = new ArrayList<>();

        Iterator it = this.Usuarios.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry mentry = (Map.Entry) it.next();

            if (mentry.getValue() instanceof Proponente) {
                Proponente aux = (Proponente) mentry.getValue();
                if (aux.getEstaActivo()) {
                    DtUsuario usu = new DtUsuario(aux.getNickname(), aux.getNombre(), aux.getApellido(), aux.getCorreo(), aux.getFechaN(), aux.getImagen(), aux.getPassword(), true);
                    listProp.add(usu);
                }
            }
        }

        return listProp;
    }

    @Override
    public boolean dejarseguirUsuario(String nickUsu1, String nickUsu2) throws Exception {

        Usuario aux1 = (Usuario) this.Usuarios.get(nickUsu1);
        Usuario aux2 = (Usuario) this.Usuarios.get(nickUsu2);

        if (aux1 == null) {
            throw new Exception("El Usuario " + nickUsu1 + " NO existe");
        }

        if (aux2 == null) {
            throw new Exception("El Usuario " + nickUsu2 + " NO existe");
        }

        if (aux1.getSeguidos().containsKey(nickUsu2) == false) {
            throw new Exception("El Usuario " + nickUsu1 + " NO sigue a " + nickUsu2);
        }

        boolean res = this.dbUsuario.dejarseguirUsuario(nickUsu1, nickUsu2);
        if (res) {
            aux1.getSeguidos().remove(nickUsu2, aux2);
            aux2.getSeguidores().remove(nickUsu1, aux1);
            return true;
        }

        return res;
    }

    @Override
    public boolean AgregarUsuarioColaborador(String nickName, String nombre, String apellido, String correo, Calendar fechaN, DataImagen imagen, String password) {
        Colaborador c = null;
        if (this.Usuarios.get(nickName) != null) {
            return false;
        } else {
            if (imagen == null) {
                c = new Colaborador(nickName, nombre, apellido, correo, fechaN, "nadie.png", password);
            } else {
                c = new Colaborador(nickName, nombre, apellido, correo, fechaN, imagen.getNombreArchivo() + "." + imagen.getExtensionArchivo(), password);
                try {
                    grabarFoto(correo, imagen);
                } catch (IOException ex) {
                    Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.Usuarios.put(nickName, c);
            boolean res = this.dbUsuario.agregarColaborador(c);
            return res;
        }
    }

    public void grabarFoto(String correo, DataImagen imagen) throws IOException {
        String extencion = imagen.getExtensionArchivo();
        String nombreA = imagen.getNombreArchivo();
        byte[] arreglo = imagen.getStream();
        String carpetaImg = carpetaImagenesPerfiles + correo;
        if (this.carpetaImagenesPerfiles == null) {
            throw new IllegalStateException("La carpeta de imagenes no fue configurada");
        }
        final File fileImagenes = new File(this.carpetaImagenesPerfiles);

        String pathStr = this.carpetaImagenesPerfiles + File.separatorChar + correo;
        final File dirUsuario = new File(pathStr);
        if (!dirUsuario.isDirectory()) {
            dirUsuario.mkdirs();
        }
        pathStr = pathStr + File.separatorChar + nombreA + "." + extencion;
        final Path path = Paths.get(pathStr);
        Files.write(path, imagen.getStream(), CREATE);
    }

    @Override
    public boolean AgregarUsuarioProponente(String nickName, String nombre, String apellido, String correo, Calendar fechaN, DataImagen imagen, String direccion, String biografia, String sitioWeb, String password) {
        Proponente c = null;

        if (this.Usuarios.get(nickName) != null) {
            return false;
        } else {
            if (imagen == null) {
                c = new Proponente(biografia, direccion, sitioWeb, nickName, nombre, apellido, correo, fechaN, "nadie.png", password, true, null);
            } else {
                c = new Proponente(biografia, direccion, sitioWeb, nickName, nombre, apellido, correo, fechaN, imagen.getNombreArchivo() + "." + imagen.getExtensionArchivo(), password, true, null);
                try {
                    grabarFoto(correo, imagen);
                } catch (IOException ex) {
                    Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.Usuarios.put(nickName, c);
            boolean res = this.dbUsuario.agregarProponente(c);
            return res;
        }
    }

    @Override
    public void copiarFoto(String foto, String nick) {

        File origen = new File(foto);
        String ex = getFileExtension(origen);
        String rutaLocal = System.getProperty("user.dir") + "\\fPerfiles\\" + nick + "." + ex;
        File destino = new File(rutaLocal);

        try {
            InputStream in = new FileInputStream(origen);
            OutputStream out = new FileOutputStream(destino);

            byte[] buf = new byte[1024];
            int len;

            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }

            in.close();
            out.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }

    @Override
    public ArrayList<DtUsuario> ListarProponentes() {
        ControladorUsuario CU = new ControladorUsuario();
        Set set = Usuarios.entrySet();
        Iterator iterator = set.iterator();
        ArrayList<DtUsuario> retorno = new ArrayList();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            if (mentry.getValue() instanceof Proponente) {
                Proponente aux = (Proponente) mentry.getValue();
                if (aux.getEstaActivo()) {
                    DtUsuario aux2 = new DtUsuario(aux.getNickname(), aux.getNombre(), aux.getApellido(), aux.getCorreo(), aux.getFechaN(), aux.getImagen(), aux.getPassword(), true, aux.getBiografia(), aux.getSitioweb(), aux.getDireccion());
                    retorno.add(aux2);
                }
            }
        }
        return retorno;
    }

    @Override
    public Proponente ObtenerProponente(String nombreP) {
        return (Proponente) this.Usuarios.get(nombreP);
    }

    @Override
    public ArrayList<DtinfoColaborador> ListarColaboradores() {
        IControladorUsuario CU = Fabrica.getInstance().getIControladorUsuario();
        Set set = Usuarios.entrySet();
        Iterator iterator = set.iterator();
        ArrayList<DtinfoColaborador> retorno = new ArrayList();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            if (mentry.getValue() instanceof Colaborador) {
                Colaborador aux = (Colaborador) mentry.getValue();
                DtinfoColaborador aux2 = new DtinfoColaborador(aux.getNickname(), aux.getNombre(), aux.getApellido(), aux.getCorreo(), aux.getFechaN());
                retorno.add(aux2);
            }
        }
        return retorno;
    }

    @Override
    public ArrayList<DtinfoColaborador> BuscarColaborador(String nick) {
        ArrayList<DtinfoColaborador> colEncontrados = new ArrayList<>();
        Set set = Usuarios.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            if (mentry.getValue() instanceof Colaborador) {
                Colaborador aux = (Colaborador) mentry.getValue();
                if (aux.getNickname().contains(nick)) {
                    DtinfoColaborador aux2 = new DtinfoColaborador(aux);
                    colEncontrados.add(aux2);
                }
            }
        }
        return colEncontrados;
    }

    @Override
    public List<DtinfoPropuesta> verPropuestas(String nick) {
        List<DtinfoPropuesta> dtpropuestas = null;
        Set set = this.Usuarios.entrySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Map.Entry mentry = (Map.Entry) it.next();
            if (mentry.getValue() instanceof Colaborador) {
                Colaborador c2 = (Colaborador) mentry.getValue();
                if (c2.getNickname().equals(nick)) {

                    dtpropuestas = this.IPC.DarPropuestasCol(c2);
                    break;
                }
            }
        }

        return dtpropuestas;
    }

    @Override
    public DtinfoColaborador verPerfil(String nick) {
        DtinfoColaborador dtc = null;
        Set set = Usuarios.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {

            Map.Entry mentry = (Map.Entry) iterator.next();
            if (mentry.getValue() instanceof Colaborador) {
                Colaborador aux = (Colaborador) mentry.getValue();

                if (aux.getNickname().equals(nick)) {
                    dtc = new DtinfoColaborador(aux);
                    this.Colaborador = aux;
                    break;
                }
            }
        }
        return dtc;
    }

    @Override
    public void CargarUsuarios() {
        this.Usuarios = dbUsuario.cargarUsuarios();

        Iterator it = this.Usuarios.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry mtry = (Map.Entry) it.next();
            Usuario usu = (Usuario) mtry.getValue();
            dbUsuario.Cargarseguidos(usu);
        }

    }

    @Override
    public Map<String, Colaboracion> getColaboraciones() {
        return this.colaboraciones;
    }

    @Override
    public DtinfoColaborador SeleccionarColaborador(String nickName) {
        this.getUsuarios();
        Set set = Usuarios.entrySet();
        Iterator iterator = set.iterator();
        ArrayList<DtinfoColaborador> retorno = new ArrayList();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            if (mentry.getValue() instanceof Colaborador) {
                Colaborador aux = (Colaborador) mentry.getValue();
                if (aux.getNickname().equals(nickName)) {
                    this.setColaborador(aux);
                    return new DtinfoColaborador(aux.getNickname(), aux.getNombre(), aux.getApellido(), aux.getCorreo(), aux.getFechaN());
                }
            }
        }
        return null;
    }

    @Override
    public Colaborador getColaborador() {
        return this.Colaborador;
    }

    @Override
    public DtinfoColaborador getDtColaborador(String nick) {
        DtinfoColaborador dtc = null;
        Set set = Usuarios.entrySet();
        Iterator iterator = set.iterator();
        ArrayList<DtinfoColaborador> retorno = new ArrayList();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            if (mentry.getValue() instanceof Colaborador) {
                Colaborador c = (Colaborador) mentry.getValue();
                if (c.getNickname().equals(nick)) {
                    dtc = new DtinfoColaborador(c);
                    break;
                }
            }
        }
        return dtc;
    }

    public void setColaborador(Colaborador colaborador) {
        this.Colaborador = colaborador;
    }

    @Override
    public List<DtColaboraciones> ListarColaboraciones(String nickName) {
        Fabrica fabrica = Fabrica.getInstance();
        IControladorUsuario ICU = fabrica.getIControladorUsuario();
        Map<String, Usuario> Usuarios = ICU.getUsuarios();
        Set set = Usuarios.entrySet();
        Iterator iterator = set.iterator();
        List<DtColaboraciones> retorno = new ArrayList<>();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            if (mentry.getValue() instanceof Colaborador) {
                if ((((Usuario) mentry.getValue()).getNickname().compareTo(nickName) == 0)) {
                    List<Colaboracion> col = ((Colaborador) mentry.getValue()).getColaboraciones();
                    for (int i = 0; i < col.size(); i++) {
                        if (col.get(i).getPropuesta().getEstaActiva()) {
                            retorno.add(new DtColaboraciones(col.get(i).getNickName(), col.get(i).getMontoC(), col.get(i).getFechaRealiz(), col.get(i).getEntradas(), col.get(i).getTituloP()));
                        }
                    }
                    return retorno;
                }
            }
        }
        return null;
    }

    @Override
    public void LimpiarUsuarios() { // Limpiado de la logica en proceso 
        Iterator it = this.Usuarios.entrySet().iterator();
        boolean borrado;
        while (it.hasNext()) {
            Map.Entry mtry = (Map.Entry) it.next();

            if (mtry.getValue() instanceof Colaborador) {
                Colaborador tempC = (Colaborador) mtry.getValue();
                if (tempC.getColaboraciones().size() > 0) {
                    this.LimpiarColaboracionesU(tempC);
                }
                borrado = this.Usuarios.remove(tempC.getNickname(), tempC);

            } else {
                Proponente tempP = (Proponente) mtry.getValue();
                if (tempP.getPropuestas().size() > 0) {
                    this.LimpiarPropuestasP(tempP);
                }
                borrado = this.Usuarios.remove(tempP.getNickname(), tempP);
            }

        }
        System.gc();

    }

    public void LimpiarColaboracionesU(Colaborador usu) { // limpiado de la logica en proceso

        for (Colaboracion colab : usu.getColaboraciones()) {
            usu.getColaboraciones().remove(colab);
        }

    }

    public void LimpiarPropuestasP(Proponente usu) { // limpiado de la logica en proceso

        for (Map.Entry mty : usu.getPropuestas().entrySet()) {
            Propuesta prop = (Propuesta) mty.getValue();
            usu.getPropuestas().remove(prop);
        }
    }

    @Override
    public ArrayList<DtUsuario> ListarUsuarios() {
        Set set = Usuarios.entrySet();
        Iterator iterator = set.iterator();
        ArrayList<DtUsuario> retorno = new ArrayList();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            if (mentry.getValue() instanceof Usuario) {
                Usuario aux = (Usuario) mentry.getValue();
                if (aux instanceof Colaborador) {
                    DtUsuario aux2 = new DtUsuario(aux.getNickname(), aux.getNombre(), aux.getApellido(), aux.getCorreo(), aux.getFechaN(), aux.getImagen(), aux.getPassword(), false);
                    retorno.add(aux2);
                } else {
                    Proponente prop = (Proponente) aux;
                    if (prop.getEstaActivo()) {
                        DtUsuario aux2 = new DtUsuario(aux.getNickname(), aux.getNombre(), aux.getApellido(), aux.getCorreo(), aux.getFechaN(), aux.getImagen(), aux.getPassword(), true);
                        retorno.add(aux2);
                    }
                }

            }
        }
        return retorno;
    }

    @Override
    public boolean limpiarBaseDeDatos() {
        DBUsuario d = new DBUsuario();
        boolean ok = d.limpiarBD();
        return ok;
    }

    @Override
    public void borrarProponente(String nickProp) {
        Fabrica fabrica = Fabrica.getInstance();
        IPropCat iContPro = fabrica.getControladorPropCat();
        IControladorUsuario ControladorU = fabrica.getIControladorUsuario();

        Proponente usr = ControladorU.ObtenerProponente(nickProp);
        if (usr != null) {

            //se elimina el usuario como seguido de sus seguidores
            Map<String, Usuario> eliminarseg = usr.getSeguidos();
            List<Usuario> listaSeguidores = new ArrayList<Usuario>(eliminarseg.values());
            Iterator<Usuario> iter = listaSeguidores.iterator();
            while (iter.hasNext()) {
                Usuario sigue = iter.next();
                sigue.getSeguidos().remove(nickProp);
            }
            usr.getSeguidos().clear();

            //se eliminan las propuestas en las que participa el proponente
            Map<String, Propuesta> eliminarprop = usr.getPropuestas();
            Set set = eliminarprop.entrySet();
            Iterator iterProp = set.iterator();

            while (iterProp.hasNext()) {
                Map.Entry mentry = (Map.Entry) iterProp.next();
                if (mentry.getValue() instanceof Propuesta) {
                    Propuesta propActual = (Propuesta) mentry.getValue();
                    iContPro.getPropuestas().remove(propActual.getTituloP());
                    iterProp.remove();
                }
            }
            usr.getPropuestas().clear();
            usr.getPropuestasRealizadas().clear();
        }

    }

    @Override
    public Colaborador ObtenerColaborador(String nombreC) {
        return (Colaborador) this.Usuarios.get(nombreC);
    }

    @Override
    public void borrarColaborador(String nickColab) {
        Fabrica fabrica = Fabrica.getInstance();
        IPropCat iContPro = fabrica.getControladorPropCat();
        IControladorUsuario ControladorU = fabrica.getIControladorUsuario();

        Colaborador usr = ControladorU.ObtenerColaborador(nickColab);
        if (usr != null) {

            //se eliminan las colaboraciones para ese colaborador
            List<Colaboracion> lCol = usr.getColaboraciones();
            Iterator it = lCol.iterator();
            while (it.hasNext()) {
                Colaboracion col = (Colaboracion) it.next();
                it.remove();
            }
            usr.getColaboraciones().clear();

        }

    }

    @Override
    public void eliminarCategorias() {
        Fabrica fabrica = Fabrica.getInstance();
        IPropCat IPC = fabrica.getControladorPropCat();

        Map<String, Categoria> categorias = IPC.getCategorias();
        Set set = categorias.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            if (mentry.getValue() instanceof Categoria) {
                Categoria c = (Categoria) mentry.getValue();
                iterator.remove();//aca se van borrando todas las categorias
            }

        }
        IPC.getCategorias().clear();//limpia todo el map por si querdo algo

    }

    @Override
    public boolean AgregarUsuarioColaboradorDatosdePrueba(String nickName, String nombre, String apellido, String correo, Calendar fechaN, String imagen, String password) {
        String ruta = System.getProperty("user.dir");
        if (this.Usuarios.get(nickName) != null) {
            return false;

        } else {
            Colaborador c = new Colaborador(nickName, nombre, apellido, correo, fechaN, imagen, password);

            String fotoLocal = c.getImagen();
            if (!"".equals(c.getImagen())) {
                File fLocal = new File(fotoLocal);
                String ex = getFileExtension(fLocal);
                File dataInputFile = new File(ruta + "\\fotosdp\\" + imagen);
                File fileSendPath = new File(ruta + "\\fPerfiles\\", dataInputFile.getName());
                try {
                    Files.copy(Paths.get(dataInputFile.getAbsolutePath()), Paths.get(fileSendPath.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException exep) {
                    Logger.getLogger(ControladorPropCat.class.getName()).log(Level.SEVERE, null, exep);
                }
                c.setImagen(nickName + "." + ex);
            } else {
                File dataInputFile = new File(ruta + "\\fotosdp\\" + imagen);
                File fileSendPath = new File(ruta + "\\fPerfiles\\", "nadie.png");
                try {
                    Files.copy(Paths.get(dataInputFile.getAbsolutePath()), Paths.get(fileSendPath.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException ex) {
                    Logger.getLogger(ControladorPropCat.class.getName()).log(Level.SEVERE, null, ex);
                }
                dataInputFile.renameTo(fileSendPath);
                c.setImagen("nadie.png");
            }
            boolean res = this.dbUsuario.agregarColaborador(c);

            if (res) {
                this.Usuarios.put(nickName, c);
                if (!"".equals(c.getImagen())) {
                    copiarFoto(fotoLocal, nickName);
                }
            }
            return res;
        }
    }

    @Override
    public boolean AgregarUsuarioProponenteDatosdePrueba(String nickName, String nombre, String apellido, String correo, Calendar fechaN, String imagen, String direccion, String biografia, String sitioWeb, String password) {
        String ruta = System.getProperty("user.dir");
        if (this.Usuarios.get(nickName) != null) {
            return false;
        } else {
            Proponente c = new Proponente(biografia, direccion, sitioWeb, nickName, nombre, apellido, correo, fechaN, imagen, password, true, null);
            String fotoLocal = c.getImagen();
            if (!"".equals(c.getImagen())) {
                File fLocal = new File(fotoLocal);
                String ex = getFileExtension(fLocal);
                File dataInputFile = new File(ruta + "//fotosdp//" + imagen);
                File fileSendPath = new File(ruta + "//fPerfiles//", dataInputFile.getName());
                try {
                    Files.copy(Paths.get(dataInputFile.getAbsolutePath()), Paths.get(fileSendPath.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException exep) {
                    Logger.getLogger(ControladorPropCat.class.getName()).log(Level.SEVERE, null, exep);
                }
                c.setImagen(nickName + "." + ex);
            } else {
                File dataInputFile = new File(ruta + "//fotosdp//" + imagen);
                File fileSendPath = new File(ruta + "//fPerfiles//", "nadie.png");
                try {
                    Files.copy(Paths.get(dataInputFile.getAbsolutePath()), Paths.get(fileSendPath.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException ex) {
                    Logger.getLogger(ControladorPropCat.class.getName()).log(Level.SEVERE, null, ex);
                }
                dataInputFile.renameTo(fileSendPath);
                c.setImagen("nadie.png");
            }
            boolean res = this.dbUsuario.agregarProponente(c);
            if (res) {
                this.Usuarios.put(nickName, c);
                if (!"".equals(c.getImagen())) {
                    copiarFoto(fotoLocal, nickName);
                }
            }
            return res;
        }
    }

    @Override
    public void resetearColaborador() {
        Colaborador c = new Colaborador("", "", "", "", null, "", "");
        this.Colaborador = c;
    }

    @Override
    public DtinfoColaborador getDtColaborador() {
        DtinfoColaborador dtc = new DtinfoColaborador(this.Colaborador);
        return dtc;
    }

    @Override
    public List<DtSeguidor> MostrarUsuarios() {
        List<DtSeguidor> listU = new ArrayList<>();

        Iterator it = this.Usuarios.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry mtry = (Map.Entry) it.next();

            Usuario usu = (Usuario) mtry.getValue();

            if (usu instanceof Proponente) {
                Proponente prop = (Proponente) usu;
                if (prop.getEstaActivo()) {
                    listU.add(new DtSeguidor(usu.getNickname(), usu.getNombre(), usu.getApellido(), "Proponente"));
                }
            } else {
                listU.add(new DtSeguidor(usu.getNickname(), usu.getNombre(), usu.getApellido(), "Colaborador"));
            }

        }
        return listU;
    }

    @Override
    public List<DtSeguidor> MostrarMisSeguidos(String nick) {
        List<DtSeguidor> listU = new ArrayList<>();

        Iterator it = this.Usuarios.get(nick).getSeguidos().entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry mtry = (Map.Entry) it.next();

            Usuario usu = (Usuario) mtry.getValue();

            if (usu instanceof Proponente) {
                Proponente prop = (Proponente) usu;
                if (prop.getEstaActivo()) {
                    listU.add(new DtSeguidor(usu.getNickname(), usu.getNombre(), usu.getApellido(), "Proponente"));
                }
            } else {
                listU.add(new DtSeguidor(usu.getNickname(), usu.getNombre(), usu.getApellido(), "Colaborador"));
            }

        }

        return listU;
    }

    @Override
    public DtNickTitProp obtenerNombreApellido(String nick) throws Exception {

        Usuario usu = this.Usuarios.get(nick);

        if (usu instanceof Proponente) {
            Proponente prop = (Proponente) usu;
            if (prop.getEstaActivo()) {
                return new DtNickTitProp(usu.getNombre(), usu.getApellido());
            }
        } else {
            throw new Exception("El usuario no es un proponente");
        }
        return null;
    }

    @Override
    public DtUsuario ObtenerDTUsuario(String nombreU) {
        DtUsuario dtc = null;
        Set set = Usuarios.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {

            Map.Entry mentry = (Map.Entry) iterator.next();
            Usuario aux = (Usuario) mentry.getValue();

            if (aux.getNickname().equals(nombreU)) {
                if (aux instanceof Colaborador) {
                    dtc = new DtUsuario(aux.getNickname(), aux.getNombre(), aux.getApellido(), aux.getCorreo(), aux.getFechaN(), aux.getImagen(), aux.getPassword(), false);
                } else {
                    Proponente p = (Proponente) aux;
                    if (p.getEstaActivo()) {
                        dtc = new DtUsuario(aux.getNickname(), aux.getNombre(), aux.getApellido(), aux.getCorreo(), aux.getFechaN(), aux.getImagen(), aux.getPassword(), true, p.getBiografia(), p.getSitioweb(), p.getDireccion());
                    } else {
                        return null;
                    }
                }
                break;
            }
        }

        return dtc;

    }

    @Override
    public List<DtUsuario> ObtenerSeguidos(String nick) {
        List<DtUsuario> seguidores = new ArrayList<>();
        //busco el usuario
        Set set = this.Usuarios.entrySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Map.Entry mentry = (Map.Entry) it.next();
            Usuario aux = (Usuario) mentry.getValue();
            if (aux.getNickname().equals(nick)) {
                //obtengo los seguidos
                Map<String, Usuario> seguidos = aux.getSeguidos();
                Set set2 = seguidos.entrySet();
                Iterator it2 = set2.iterator();
                while (it2.hasNext()) {
                    //recorro los seguidores y los transformo a dtusuario
                    Map.Entry mentry2 = (Map.Entry) it2.next();
                    Usuario aux2 = (Usuario) mentry2.getValue();
                    if (aux2 instanceof Colaborador) {
                        DtUsuario dtu = new DtUsuario(aux2.getNickname(), aux2.getNombre(), aux2.getApellido(), aux2.getCorreo(), aux2.getFechaN(), aux2.getImagen(), aux2.getPassword(), false);
                        seguidores.add(dtu);
                    } else {
                        Proponente prop = (Proponente) aux2;
                        if (prop.getEstaActivo()) {
                            DtUsuario dtu = new DtUsuario(aux2.getNickname(), aux2.getNombre(), aux2.getApellido(), aux2.getCorreo(), aux2.getFechaN(), aux2.getImagen(), aux2.getPassword(), true);
                            seguidores.add(dtu);
                        }
                    }
                }
            }
        }
        return seguidores;
    }

    @Override
    public List<DtUsuario> ObtenerSeguidores(String nick) {
        List<DtUsuario> seguidores = new ArrayList<>();
        Set set = this.Usuarios.entrySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            //recorro los usuarios
            Map.Entry mentry = (Map.Entry) it.next();
            Usuario aux = (Usuario) mentry.getValue();
            Set set2 = aux.getSeguidos().entrySet();
            Iterator it2 = set2.iterator();
            while (it2.hasNext()) {
                //recorro sus seguidos
                Map.Entry mentry2 = (Map.Entry) it2.next();
                Usuario aux2 = (Usuario) mentry2.getValue();
                if (aux2.getNickname().equals(nick)) {
                    //si el usuario sigue al que pase por parametro lo agrego a la lista
                    if (aux instanceof Colaborador) {
                        DtUsuario dtu = new DtUsuario(aux.getNickname(), aux.getNombre(), aux.getApellido(), aux.getCorreo(), aux.getFechaN(), aux.getImagen(), aux.getPassword(), false);
                        seguidores.add(dtu);
                    } else {
                        Proponente prop = (Proponente) aux;
                        if (prop.getEstaActivo()) {
                            DtUsuario dtu = new DtUsuario(aux.getNickname(), aux.getNombre(), aux.getApellido(), aux.getCorreo(), aux.getFechaN(), aux.getImagen(), aux.getPassword(), true);
                            seguidores.add(dtu);
                        }
                    }
                }
            }
        }
        return seguidores;
    }

    @Override
    public List<DtinfoPropuesta> obtenerfavoritas(String nick) {
        List<DtinfoPropuesta> favoritas = new ArrayList<>();
        Set set = this.Usuarios.entrySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            //recorro los usuarios
            Map.Entry mentry = (Map.Entry) it.next();
            Usuario aux = (Usuario) mentry.getValue();
            if (aux.getNickname().equals(nick)) {
                Map<String, Propuesta> fav = aux.getFavoritas();
                Set set2 = fav.entrySet();
                Iterator it2 = set2.iterator();
                //recorro sus propuestas favoritas
                while (it2.hasNext()) {
                    Map.Entry mentry2 = (Map.Entry) it2.next();
                    Propuesta p = (Propuesta) mentry2.getValue();
                    if (p.getEstaActiva()) {
                        //la transformo a dt y la agrego al resultado
                        DtinfoPropuesta dtp = new DtinfoPropuesta(p);
                        favoritas.add(dtp);
                    }
                }
            }
        }
        return favoritas;
    }

    @Override
    public void CargarFavoritas() {
        Set set = this.Usuarios.entrySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            //recorro los usuarios
            Map.Entry mentry = (Map.Entry) it.next();
            Usuario aux = (Usuario) mentry.getValue();
            List<String> favoritas = this.dbUsuario.CargarFavoritas(aux.getNickname());
            //le agrego las propuestas favoritas
            aux = this.IPC.CargarFavoritas(aux, favoritas);
        }
    }

    @Override
    public DtUsuario ObtenerDTUsuario_Correo(String correoU) {

        DtUsuario dtc = null;
        Set set = Usuarios.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {

            Map.Entry mentry = (Map.Entry) iterator.next();
            if (mentry.getValue() instanceof Usuario) {
                Usuario aux = (Usuario) mentry.getValue();

                if (aux.getCorreo().equals(correoU)) {
                    if (aux instanceof Colaborador) {
                        dtc = new DtUsuario(aux.getNickname(), aux.getNombre(), aux.getApellido(), aux.getCorreo(), aux.getFechaN(), aux.getImagen(), aux.getPassword(), false);
                    } else {
                        Proponente prop = (Proponente) aux;
                        if (prop.getEstaActivo()) {
                            dtc = new DtUsuario(aux.getNickname(), aux.getNombre(), aux.getApellido(), aux.getCorreo(), aux.getFechaN(), aux.getImagen(), aux.getPassword(), true);
                        }
                    }
                    break;
                }
            }
        }
        return dtc;

    }

    @Override
    public void agregarfavorita(String nick, Propuesta p) {
        Set set = Usuarios.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            Usuario u = (Usuario) mentry.getValue();
            if (u.getNickname().equals(nick)) {
                u.getFavoritas().put(p.getTituloP(), p);
                break;
            }
        }
    }

    @Override
    public List<DtColaboraciones> getMontoColaboracion(String colaborador) {

        Colaborador colab = Fabrica.getInstance().getIControladorUsuario().ObtenerColaborador(colaborador);
        List<Colaboracion> col = colab.getColaboraciones();
        List<DtColaboraciones> col2 = new ArrayList<>();
        Iterator it = col.iterator();
        while (it.hasNext()) {
            Colaboracion c = (Colaboracion) it.next();
            DtColaboraciones dtc2 = new DtColaboraciones(c);
            col2.add(dtc2);
        }
        return col2;
    }

    @Override
    public boolean DesactivarProponente(String nickname) throws Exception {

        Proponente prop = this.ObtenerProponente(nickname);
        if (prop != null) {
            if (!prop.getEstaActivo()) {
                throw new Exception("El proponente ya fue Desactivado");
            } else {
                prop.setEstaActiva(false);
                prop.setFDesactivacion(new GregorianCalendar());
                boolean ok = this.dbUsuario.setNuevoEstadoProponente(nickname, false, prop.getFDesactivacion());
                Iterator it = prop.getPropuestas().entrySet().iterator();

                while (it.hasNext()) {
                    Map.Entry mtry = (Map.Entry) it.next();
                    Propuesta propuesta = (Propuesta) mtry.getValue();

                    if (propuesta.getEstaActiva()) { // deberian estar todas activas (true)
                        propuesta.setEstaActiva(false);
                        Fabrica.getInstance().getControladorPropCat().DesactivarPropuesta(propuesta);

                    }
                }
            }
            return true;
        } else {
            throw new Exception("El Proponente que desea desactivar no existe");
        }
    }
    
    
    @Override
    public ArrayList<DtUsuario> ListarUsuariosRanking() {
        Set set = Usuarios.entrySet();
        Iterator iterator = set.iterator();
        ArrayList<DtUsuario> retorno = new ArrayList();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            if (mentry.getValue() instanceof Usuario) {
                Usuario aux = (Usuario) mentry.getValue();
                if (aux instanceof Colaborador) {
                    DtUsuario aux2 = new DtUsuario(aux.getNickname(), aux.getNombre(), aux.getApellido(), aux.getCorreo(), aux.getFechaN(), aux.getImagen(), aux.getPassword(), false);
                    aux2.getSeguidores().addAll(aux.getSeguidores().keySet());
                    if(aux2.getSeguidores().size()>0){
                    retorno.add(aux2);}
                } else {
                    Proponente prop = (Proponente) aux;
                    if (prop.getEstaActivo()) {
                        DtUsuario aux2 = new DtUsuario(aux.getNickname(), aux.getNombre(), aux.getApellido(), aux.getCorreo(), aux.getFechaN(), aux.getImagen(), aux.getPassword(), true);
                        aux2.getSeguidores().addAll(prop.getSeguidores().keySet());
                        if(aux2.getSeguidores().size()>0){
                        retorno.add(aux2);}
                    }
                }

            }
        }

        Iterator<DtUsuario> actual;
        DtUsuario usr,usr1;
        int siguiente;

        actual = retorno.iterator();
        for(int i=0; i<retorno.size();i++)
        {
             siguiente = i;
             siguiente++;
             while(siguiente<retorno.size()){
                  if(retorno.get(i).getSeguidores().size() < retorno.get(siguiente).getSeguidores().size()){
                    usr= retorno.set(siguiente, retorno.get(i));
                    usr1= retorno.set(i,usr);      
                  }
                  siguiente++;                    
             }        
        }

        return retorno;
    }

}
