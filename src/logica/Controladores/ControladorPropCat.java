/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.Controladores;

import Persistencia.DBColaboracion;
import Persistencia.DBPropuesta;
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
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.Clases.Categoria;
import logica.Clases.Colaboracion;
import logica.Clases.Colaborador;
import logica.Clases.Comentario;
import logica.Clases.DtColaboraciones;
import logica.Clases.DtConsultaPropuesta;
import logica.Clases.DtConsultaPropuesta2;
import logica.Clases.DtinfoColaborador;
import logica.Clases.DtinfoPropuesta;
import logica.Clases.EstadoPropuesta;
import logica.Clases.DtNickTitProp;
import logica.Clases.Proponente;
import logica.Clases.Propuesta;
import logica.Clases.TipoE;
import logica.Clases.TipoRetorno;
import logica.Clases.DtPropuestaWeb;
import logica.Clases.Usuario;
import logica.Fabrica;
import logica.Interfaces.IControladorUsuario;
import logica.Interfaces.IPropCat;
import logica.Clases.DataImagen;
import logica.Controladores.configuraciones;
import logica.Clases.convertidorDeIMG;

/**
 *
 * @author Santiago.S
 */
public class ControladorPropCat implements IPropCat {

    private static ControladorPropCat instancia;
    private Map<String, Propuesta> propuestas;
    private Map<String, Categoria> categorias;
    private IControladorUsuario ICU;
    private DBPropuesta dbPropuesta = null;
    private Categoria catRecordada;
    private Proponente uProponente;
    private Propuesta Propuesta;
    private DBColaboracion dbColaboracion = null;
    private String carpetaImagenesPropuestas = new configuraciones().getCarpetaImagenes();
    convertidorDeIMG convertidor = new convertidorDeIMG();

    public static ControladorPropCat getInstance() {
        if (instancia == null) {
            instancia = new ControladorPropCat();
        }
        return instancia;
    }

    public ControladorPropCat() {
        this.dbPropuesta = new DBPropuesta();
        this.dbColaboracion = new DBColaboracion();
        this.categorias = new HashMap<>();
        this.propuestas = new HashMap<>();
        this.Propuesta = null;
    }

    @Override
    public List<DtNickTitProp> listarPropuestaC() {
        this.EvaluarEstadosPropuestas();

        Map<String, Propuesta> prop = this.propuestas;
        Set set = prop.entrySet();
        Iterator iterator = set.iterator();
        List<DtNickTitProp> retorno = new ArrayList();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            Propuesta aux = (Propuesta) mentry.getValue();

            if (aux.getEstadoActual().getEstado() == TipoE.Publicada) {
                DtNickTitProp aux2 = new DtNickTitProp(aux);
                retorno.add(aux2);
            }

        }
        return retorno;
    }

    @Override
    public boolean seleccionarUC(String nombreP, String tipoEsp) throws Exception {

        Fabrica fabrica = Fabrica.getInstance();

        this.uProponente = fabrica.getIControladorUsuario().ObtenerProponente(nombreP);
        if (this.uProponente == null) {
            throw new Exception("El usuario no existe en el sistema");
        }

        this.catRecordada = this.categorias.get(tipoEsp);

        return this.catRecordada == null;
    }

    @Override
    public List<String> ListarCategorias() {
        List<String> listCat = new ArrayList();

        Iterator it = this.categorias.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry mentry = (Map.Entry) it.next();
            Categoria aux = (Categoria) mentry.getValue();
            listCat.add(aux.getNombreC());
        }

        return listCat;
    }

    @Override
    public Map<String, Categoria> getCategorias() {
        return this.categorias;

    }

    @Override
    public void altaCategoria(String nombre, String padre) throws Exception {
        this.catRecordada = this.categorias.get(padre);

        if (this.catRecordada == null) {
            throw new Exception("La antecesora no existe");
        }
        if (this.categorias.containsKey(nombre)) {
            throw new Exception("La categoria que quiere cargar ya Existe");
        } else {

            Categoria cat = new Categoria(nombre);

            boolean correcto = this.dbPropuesta.agregarCategoria(nombre, padre);

            if (correcto) {
                this.catRecordada.setCategoriaH(cat);
                this.categorias.put(cat.getNombreC(), cat);
            } else {
                throw new Exception("La operacion no pudo ser realizada con exito");
            }
        }
    }

    @Override
    public boolean crearPropuesta(String tituloP, String descripcion, String lugar, DataImagen imagen, Calendar fecha, float montoE, float montoTot, TipoRetorno retorno) throws Exception {

        if (this.propuestas.get(tituloP) != null) {
            throw new Exception("Ya existe una propuesta bajo ese Nombre");
        }
        TipoE tipo = TipoE.Ingresada;
        Calendar fechaI = new GregorianCalendar();
        EstadoPropuesta estado = new EstadoPropuesta(tipo, fechaI, true);
        String urlImagen;
        if (imagen != null) {
            urlImagen = imagen.getNombreArchivo() + "." + imagen.getExtensionArchivo();
        } else {
            urlImagen = "Culturarte.png";
        }
        Propuesta nuevaP = new Propuesta(tituloP, descripcion, urlImagen, lugar, fecha, montoE, montoTot, estado, this.catRecordada, retorno, this.uProponente);
        boolean agregada = this.dbPropuesta.agregarPropuesta(nuevaP, estado);
        if (agregada) {
            this.propuestas.put(tituloP, nuevaP);
            this.catRecordada.setPropuesta(nuevaP);
            this.uProponente.setPropuesta(nuevaP);
            if (!urlImagen.equals("Culturarte.png")) {
                grabarFotoPropuestas(tituloP, imagen);
            }

        } else {
            this.catRecordada = null;
            this.uProponente = null;
            throw new Exception("La propuesta no pudo ser dada de alta por conflicto del sistema");
        }
        return true;
    }

    public void grabarFotoPropuestas(String titulo, DataImagen imagen) throws IOException {
        String extencion = imagen.getExtensionArchivo();
        String nombreA = imagen.getNombreArchivo();
        byte[] arreglo = imagen.getStream();
        String carpetaImg = carpetaImagenesPropuestas + titulo;
        if (this.carpetaImagenesPropuestas == null) {
            throw new IllegalStateException("La carpeta de imagenes no fue configurada");
        }
        final File fileImagenes = new File(this.carpetaImagenesPropuestas);
        if (!fileImagenes.isDirectory()) {
            throw new IOException("La carpeta de imagenes no fue configurada");
        }//if.
        String pathStr = this.carpetaImagenesPropuestas + "\\fPropuestas" + File.separatorChar + titulo;
        final File dirUsuario = new File(pathStr);
        if (!dirUsuario.isDirectory()) {
            dirUsuario.mkdirs();
        }
        pathStr = pathStr + File.separatorChar + nombreA + "." + extencion;
        final Path path = Paths.get(pathStr);
        Files.write(path, imagen.getStream(), CREATE);
    }

    @Override
    public List<DtinfoPropuesta> ListarPropuestasDeProponenteX(String nick) {
        this.EvaluarEstadosPropuestas();

        List<DtinfoPropuesta> retorno = new ArrayList<>();
        Set set = this.propuestas.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            Propuesta p = (Propuesta) mentry.getValue();
            if (p.getAutor().getNickname().equals(nick)) {
                DtinfoPropuesta dtP = new DtinfoPropuesta(p);
                retorno.add(dtP);
            }
        }
        return retorno;
    }

    @Override
    public List<DtNickTitProp> listarPropuestas() {//commit
        this.EvaluarEstadosPropuestas();

        List<DtNickTitProp> listPropuestas = new ArrayList();

        Iterator it = this.propuestas.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry mentry = (Map.Entry) it.next();
            Propuesta prop = (Propuesta) mentry.getValue();
            DtNickTitProp dtprop = new DtNickTitProp(prop.getTituloP(), prop.getCategoria().getNombreC());
            listPropuestas.add(dtprop);
        }
        return listPropuestas;
    }

    @Override
    public List<DtNickTitProp> listarPropuestasR() {//commit
        this.EvaluarEstadosPropuestas();

        List<DtNickTitProp> listPropuestas = new ArrayList();

        Iterator it = this.propuestas.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry mentry = (Map.Entry) it.next();
            Propuesta prop = (Propuesta) mentry.getValue();
            if (prop.getEstadoActual().getEstado() == TipoE.Publicada || prop.getEstadoActual().getEstado() == TipoE.enFinanciacion) {
                DtNickTitProp dtprop = new DtNickTitProp(prop.getTituloP(), prop.getAutor().getNickname());
                listPropuestas.add(dtprop);
            }
        }
        return listPropuestas;
    }

    @Override
    public EstadoPropuesta verEstadoPropuesta(String titulo) {
        this.EvaluarEstadosPropuestas();

        EstadoPropuesta estActual = this.propuestas.get(titulo).getEstadoActual();
        return estActual;
    }

    @Override
    public DtinfoPropuesta SeleccionarPropuestaR(String titulo) {
        this.EvaluarEstadosPropuestas();

        Map<String, Propuesta> prop = this.propuestas;
        Set set = prop.entrySet();
        Iterator iterator = set.iterator();
        DtinfoPropuesta retorno = null;
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            Propuesta aux = (Propuesta) mentry.getValue();
            if (aux.getTituloP().compareTo(titulo) == 0) {

                retorno = new DtinfoPropuesta(aux.getTituloP(), aux.getDescripcionP(), aux.getImagen(), aux.getCategoria().getNombreC(), aux.getLugar(), aux.getFecha(), aux.getMontoE(), aux.getMontoTot(), aux.getRetorno());
                this.Propuesta = aux;
            }
        }
        return retorno;
    }

    @Override
    public DtinfoPropuesta RetornarPropuestaR(String titulo) {
        this.EvaluarEstadosPropuestas();

        Map<String, Propuesta> prop = this.propuestas;
        Set set = prop.entrySet();
        Iterator iterator = set.iterator();
        DtinfoPropuesta retorno = null;
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            Propuesta aux = (Propuesta) mentry.getValue();
            if (aux.getTituloP().compareTo(titulo) == 0) {
                retorno = new DtinfoPropuesta(aux.getTituloP(), aux.getDescripcionP(), aux.getImagen(), aux.getCategoria().getNombreC(), aux.getLugar(), aux.getFecha(), aux.getMontoE(), aux.getMontoTot(), aux.getRetorno());
            }
        }
        return retorno;
    }

    public float CalcularMontoPropuesta(Propuesta prop) {
        float montoTot = 0;

        List<Colaboracion> lCol = prop.getColaboraciones();

        for (int i = 0; i < lCol.size(); i++) {

            Colaboracion col = (Colaboracion) lCol.get(i);

            montoTot = montoTot + col.getMontoC();
        }

        return montoTot;
    }

    @Override
    public DtConsultaPropuesta SeleccionarPropuesta(String titulo, String proponente) throws Exception {

        Propuesta prop = this.propuestas.get(titulo);
        if (prop != null) {
            String estado;
            estado = prop.getEstadoActual().getEstado().name();
            float monto = this.CalcularMontoPropuesta(prop);
            String nick = prop.getAutor().getNickname();
            String tipoR;

            if (prop.getRetorno() == TipoRetorno.EntGan) {
                tipoR = "Entradas y Porcentaje";
            } else {
                tipoR = prop.getRetorno().name();

            }

            Date fecha = (Date) prop.getFecha().getTime();
            String fechaR = new SimpleDateFormat("dd/MMM/yyyy").format(fecha);
            boolean cancelable = false;
            boolean extendible = false;
            boolean comentable = false;

            if (prop.getAutor().getNickname().equals(proponente) && prop.getEstadoActual().getEstado() == TipoE.Financiada) {
                cancelable = true;
            } else if (prop.getAutor().getNickname().equals(proponente)) {
                if (prop.getEstadoActual().getEstado() == TipoE.Publicada || prop.getEstadoActual().getEstado() == TipoE.enFinanciacion) {
                    extendible = true;
                }
            } else if (prop.EsColaborador(nick) && prop.getEstadoActual().getEstado() == TipoE.Financiada) {
                comentable = this.Comentable(prop, nick);
            }

            return new DtConsultaPropuesta(prop.getTituloP(), prop.getCategoria().getNombreC(), prop.getLugar(), fechaR, monto, prop.getMontoE(), estado, prop.getDescripcionP(), prop.getImagen(), prop.getMontoTot(), tipoR, nick, extendible, cancelable, comentable);

        } else {
            throw new Exception("La propuesta ingresada no esta en el sistema");
        }
    }

    @Override
    public Map<String, Propuesta> getPropuestas() {
        return this.propuestas;
    }

    @Override
    public List<DtinfoPropuesta> DarPropuestasCol(Colaborador c) {
        this.EvaluarEstadosPropuestas();

        List<DtinfoPropuesta> resultado = new ArrayList<DtinfoPropuesta>();
        Iterator it = c.getColaboraciones().iterator();
        while (it.hasNext()) {
            Colaboracion col = (Colaboracion) it.next();
            Set set = this.propuestas.entrySet();
            Iterator it2 = set.iterator();
            while (it2.hasNext()) {
                Map.Entry mentry2 = (Map.Entry) it2.next();
                Propuesta prop = (Propuesta) mentry2.getValue();
                if (prop.getTituloP().equals(col.getTituloP())) {
                    DtinfoPropuesta dtp = new DtinfoPropuesta(prop);
                    resultado.add(dtp);
                    break;
                }
            }

        }
        return resultado;
    }

    @Override
    public void CargarPropuestas() {
        this.dbPropuesta.cargarCategorias();

        Iterator it = this.propuestas.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry mentry = (Map.Entry) it.next();
            Propuesta aux = (Propuesta) mentry.getValue();

            this.dbPropuesta.cargarEstadoPropuesta(aux);
        }

    }

    @Override
    public void CargarColaboraciones() {
        DBColaboracion DBC = new DBColaboracion();
        DBC.CargarColaboraciones();
    }

    @Override
    public boolean agregarColaboracion(boolean Entrada, Float monto) throws Exception {

        IControladorUsuario ICU = Fabrica.getInstance().getIControladorUsuario();
        Calendar calendario = new GregorianCalendar();
        DBColaboracion DBC = new DBColaboracion();

        List<Colaboracion> colaboraciones = this.getPropuesta().getColaboraciones();
        List<Colaboracion> colaboracionesC = ICU.getColaborador().getColaboraciones();
        float TotalColaboracion = 0;

        for (int indice = 0; indice < colaboraciones.size(); indice++) {
            TotalColaboracion = TotalColaboracion + colaboraciones.get(indice).getMontoC();
        }

        for (int indice2 = 0; indice2 < colaboracionesC.size(); indice2++) {
            if (colaboracionesC.get(indice2).getTituloP().compareTo(this.getPropuesta().getTituloP()) == 0) {
                throw new Exception("No puede colaborar en una propuesta mas de una vez");
            }
        }
        if ((TotalColaboracion + monto) <= this.getPropuesta().getMontoTot()) {

            Colaboracion colaboracion = new Colaboracion(ICU.getColaborador(), monto, calendario, Entrada, this.getPropuesta());
            ICU.getColaborador().setColaboraciones(colaboracion);
            this.getPropuesta().setColaboraciones(colaboracion);
            if ((TotalColaboracion + monto) < this.getPropuesta().getMontoTot() && this.getPropuesta().getEstadoActual().getEstado() != TipoE.enFinanciacion) {
                // se crea un estado solo por la primer colaboracion y se mantiene hasta otro evento
                EstadoPropuesta EstadoP = new EstadoPropuesta(TipoE.enFinanciacion, calendario, true);
                // se recupera el estado actual anterior para pasarlo al historial
                EstadoPropuesta EstAnterior = this.getPropuesta().getEstadoActual();
                // se le asigan nul por que ya no es un aestado actual
                EstAnterior.setEsActual(false);
                // se setea en el historial y posterior se carga el nuevo estado actual
                this.getPropuesta().setEstados(EstAnterior);
                this.getPropuesta().setEstadoActual(EstadoP);
                //Se agrega el estado en la base de datos
                DBC.agregarestadocolaboracion();
            } else if ((TotalColaboracion + monto) == this.getPropuesta().getMontoTot()) {
                // igual proceso para otro estado distinto 
                EstadoPropuesta EstadoP = new EstadoPropuesta(TipoE.Financiada, calendario, true);
                EstadoPropuesta EstAnterior = this.getPropuesta().getEstadoActual();
                EstAnterior.setEsActual(false);
                this.getPropuesta().setEstadoActual(EstadoP);
                this.getPropuesta().setEstados(EstAnterior);
                DBC.agregarestadocolaboracion();
            }

            DBC.agregarColaboracion(Entrada, monto);
            return true;
        } else {
            throw new Exception("El monto que ingreso ha superado el limite del monto total, ingrese un monto menor o igual a: $" + (this.getPropuesta().getMontoTot() - TotalColaboracion));
        }
    }

    @Override
    public Propuesta getPropuesta() {
        return Propuesta;
    }

    @Override
    public List<DtinfoColaborador> ListarColaboradores(String titulo) {
        List<DtinfoColaborador> retorno = new ArrayList<>();
        Propuesta a = this.propuestas.get(titulo);
        List<Colaboracion> colaboraciones = a.getColaboraciones();
        Iterator iter = colaboraciones.iterator();
        while (iter.hasNext()) {
            Colaboracion colaboracion = (Colaboracion) iter.next();
            Colaborador colaborador = colaboracion.getColaborador();
            DtinfoColaborador dtCol = new DtinfoColaborador(colaborador);
            retorno.add(dtCol);
        }
        return retorno;
    }

    @Override
    public boolean crearPropuestaDatosdePrueba(String tituloP, String descripcion, Categoria cat, Calendar fecha, String lugar, float montoE, float montoTot, TipoRetorno retorno, Proponente p, String imagen) {
        if (this.getPropuestas().get(tituloP) != null) {
            return false;
        }
        Propuesta nuevaP;
        nuevaP = new Propuesta(tituloP, descripcion, imagen, lugar, fecha, montoE, montoTot, null, cat, retorno, p);
        this.propuestas.put(tituloP, nuevaP);
        String ruta = new configuraciones().getCarpetaImagenes();
        String url = ruta + "\\fotosdp\\" + imagen;
        try {
            DataImagen img = convertidor.convertirStringAImg(url, tituloP);

            this.dbPropuesta = new DBPropuesta();
            boolean agregada = this.dbPropuesta.agregarPropuestaDatosdePrueba(nuevaP);
            if (agregada == true) {
                grabarFotoPropuestas(tituloP, img);
            }
        } catch (IOException ex) {
            Logger.getLogger(ControladorPropCat.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public Categoria ObtenerCategoria(String nomCat) {
        return (Categoria) this.categorias.get(nomCat);
    }

    @Override
    public boolean crearCategoriaDatosdePrueba(String nomCat, String nomPadre) {

        boolean agregada = this.dbPropuesta.agregarCategoria(nomCat, nomPadre);

        if (agregada) {
            Categoria hijo = new Categoria(nomCat);
            Categoria padre = this.categorias.get(nomPadre);
            if (padre != null) {
                padre.getCategoriasH().put(nomCat, hijo);
            }
            this.categorias.put(nomCat, hijo);
            return true;
        }

        return false;
    }

    @Override
    public boolean agregarColaboracionDatosdePrueba(String TituloP, String nickName, float monto, Calendar fechaRealiz, boolean Entrada) {
        Fabrica fabrica = Fabrica.getInstance();
        IControladorUsuario ICU = fabrica.getIControladorUsuario();
        IPropCat IPC = fabrica.getControladorPropCat();

        Propuesta p = (Propuesta) this.propuestas.get(TituloP);
        Colaborador c = (Colaborador) ICU.getUsuarios().get(nickName);

        Colaboracion colaboracion = new Colaboracion(c, monto, fechaRealiz, Entrada, p);
        c.setColaboraciones(colaboracion);
//        colaboracion.setPropuesta(p);
        p.setColaboraciones(colaboracion);

        DBColaboracion DBC = new DBColaboracion();
        DBC.agregarColaboracionDatosdePrueba(TituloP, nickName, monto, fechaRealiz, Entrada);

        return true;

    }

    @Override
    public boolean nuevoEstadoPropuestaDatosdePrueba(String TituloP, TipoE estado, Calendar fecha) {

        EstadoPropuesta estadop = new EstadoPropuesta(estado, fecha, false);
        Propuesta p = (Propuesta) this.propuestas.get(TituloP);
        p.setEstados(estadop);

        DBPropuesta DBP = new DBPropuesta();
        DBP.agregarEstadoPropuestaDatosdePrueba(estadop, TituloP);

        return true;

    }

    @Override
    public void LimpiarPropCat() {
        Iterator it = this.categorias.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry mtry = (Map.Entry) it.next();
            Categoria cat = (Categoria) mtry.getValue();

            if (cat.getPropuestas().size() > 0) {
                Iterator itP = cat.getPropuestas().entrySet().iterator();
                while (itP.hasNext()) {
                    Map.Entry mtryP = (Map.Entry) it.next();
                    Propuesta prop = (Propuesta) mtryP.getValue();
                    this.LimpiarProp(prop);
                    this.getPropuestas().remove(prop.getTituloP(), prop);
                    cat.getPropuestas().remove(prop.getTituloP(), prop);
                }
            }
        }

    }

    public void LimpiarProp(Propuesta prop) {

        for (Colaboracion col : prop.getColaboraciones()) {
            prop.getColaboraciones().remove(col);
            col.setPropuesta(null);
            col.setUColaborador(null);
        }

        for (EstadoPropuesta estP : prop.getHistorialEst()) {
            prop.getHistorialEst().remove(estP);
        }
        prop.setCategoria(null);
        prop.setEstadoActual(null);
        prop.setAutor(null);
    }

    @Override
    public void comprobarBaseCat() {
        this.dbPropuesta.ComprobarBaseCat();
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
    public void copiarFoto(String foto, String tituloP) {

        File origen = new File(foto);
        String extension = getFileExtension(origen);
        String rutaLocal = System.getProperty("user.dir") + "\\fPropuestas\\" + tituloP + "." + extension;
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

    @Override
    public void setEstadoActualDatosDePrueba(String titulo, EstadoPropuesta estadoActual) {

        Propuesta prop = this.getPropuestas().get(titulo);

        boolean ok = this.dbPropuesta.agregarEstadoPropuestaDatosdePrueba(estadoActual, titulo);
        if (ok) {
            prop.setEstadoActual(estadoActual);
        }
    }

    @Override
    public List<DtColaboraciones> listarColaboraciones(String titulo) throws Exception {
        List<DtColaboraciones> retorno = new ArrayList<DtColaboraciones>();
        Propuesta prop = this.propuestas.get(titulo);
        List<Colaboracion> colaboraciones = prop.getColaboraciones();
        Iterator it = colaboraciones.iterator();
        while (it.hasNext()) {
            Colaboracion col = (Colaboracion) it.next();
            DtColaboraciones dtcol = new DtColaboraciones(col);
            retorno.add(dtcol);
        }
        if (retorno.size() < 1) {
            throw new Exception("No existen colaboraciones en la propuesta " + titulo);
        } else {
            return retorno;
        }
    }

    @Override
    public boolean eliminarColaboracion(String titulo, String nick) {
        boolean ok = false;
        Propuesta a = this.propuestas.get(titulo);
        List<Colaboracion> col = a.getColaboraciones();
        for (int i = 0; i < a.getColaboraciones().size(); i++) {
            Colaboracion c = col.get(i);
            if (col.get(i).getNickName().equals(nick)) {
                ok = c.borrarme();
            }
        }
        try {
            ok = this.dbColaboracion.eliminarColaboracion(titulo, nick);
            if (ok) {
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPropCat.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }

    @Override
    public List<DtConsultaPropuesta2> ListaColaboradoresProp(String titulo) {
        List<DtConsultaPropuesta2> listColab = new ArrayList<>();

        Propuesta prop = this.getPropuestas().get(titulo);

        Iterator it = prop.getColaboraciones().iterator();

        while (it.hasNext()) {
            Colaboracion colab = (Colaboracion) it.next();
            Date fecha = (Date) colab.getFechaRealiz().getTime();
            String fechaR = new SimpleDateFormat("dd/MMM/yyyy").format(fecha);
            listColab.add(new DtConsultaPropuesta2(colab.getNickName(), colab.getColaborador().getNombre(), colab.getColaborador().getNombre(), colab.getMontoC(), fechaR));
        }
        return listColab;
    }

    @Override
    public boolean ActualizarDatosPropuesta(DtinfoPropuesta dtp) {
        Set set = this.propuestas.entrySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Map.Entry mentry = (Map.Entry) it.next();
            Propuesta p = (Propuesta) mentry.getValue();
            if (p.getTituloP().equals(dtp.getTitulo())) {
                if (!dtp.getDescripcion().equals("")) {
                    p.setDescripcionP(dtp.getDescripcion());
                }
                if (dtp.getPrecio() != 0) {
                    p.setMontoE(dtp.getPrecio());
                }
                if (!dtp.getImagen().equals("")) {
                    p.setImagen(dtp.getImagen());
                }
                if (!dtp.getLugar().equals("")) {
                    p.setLugar(dtp.getLugar());
                }
                if (dtp.getMonto() != 0) {
                    p.setMontoTot(dtp.getMonto());
                }
                if (dtp.getFechaReal() != null) {
                    p.setFecha(dtp.getFechaReal());
                }
                boolean modifico = this.dbPropuesta.ModificarDatosPropuesta(p);
                if (!modifico) {
                    return false;
                }
                break;
            }
        }
        return true;
    }

    @Override
    public void resetearPropuesta() {
        Propuesta p = new Propuesta("", "", "", "", null, 0, 0, null, null, null, null);
        this.Propuesta = p;
    }

    @Override
    public List<DtNickTitProp> ListaEvaluarPropuesta() {
        List<DtNickTitProp> listProp = new ArrayList<>();

        Iterator it = this.getPropuestas().entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry mtry = (Map.Entry) it.next();

            Propuesta prop = (Propuesta) mtry.getValue();

            if (prop.getEstadoActual().getEstado() == TipoE.Ingresada) {

                DtNickTitProp dtProp = new DtNickTitProp(prop.getTituloP(), prop.getAutor().getNickname());

                listProp.add(dtProp);

            }
        }
        return listProp;
    }

    @Override
    public boolean EvaluarPropuesta(String titulo, TipoE tipo) throws Exception {

        Propuesta prop = this.getPropuestas().get(titulo);

        if (prop.getEstadoActual().getEstado() != TipoE.Ingresada || prop.getEstadoActual().getActual() == false) {
            return false;
        } else {
            EstadoPropuesta estNuevo = new EstadoPropuesta(tipo, new GregorianCalendar(), true);
            EstadoPropuesta estAnterior = prop.getEstadoActual();

            boolean ok = this.dbPropuesta.EvaluarPropuestaBD(estNuevo, estAnterior, titulo);

            if (ok) {
                estAnterior.setEsActual(false);
                prop.setEstados(estAnterior);
                prop.setEstadoActual(estNuevo);
                return true;
            } else {
                throw new Exception("No se pudo alterar el estado en la Base de datos");
            }
        }
    }

    @Override
    public void EvaluarEstadosPropuestas() {

        Iterator it = this.getPropuestas().entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry mtry = (Map.Entry) it.next();

            Propuesta prop = (Propuesta) mtry.getValue();

            if (prop.getEstadoActual().getEstado() == TipoE.Publicada || prop.getEstadoActual().getEstado() == TipoE.enFinanciacion) {

                Calendar fechaDeHoy = new GregorianCalendar();
                Date fechaDeHoy1 = fechaDeHoy.getTime();

                if (!fechaDeHoy1.before(this.FechaCambioEstado(prop.getFechaPublicacion().getTime()))) {
                    if (prop.getMontoTot() <= this.CalcularMontoPropuesta(prop)) {
                        EstadoPropuesta nuevoE = new EstadoPropuesta(TipoE.Financiada, fechaDeHoy, true);
                        EstadoPropuesta EstadoAnt = prop.getEstadoActual();
                        EstadoAnt.setEsActual(false);
                        prop.setEstados(EstadoAnt);
                        prop.setEstadoActual(nuevoE);
                        this.dbPropuesta.EvaluarPropuestaBD(nuevoE, EstadoAnt, prop.getTituloP());
                    } else {
                        EstadoPropuesta nuevoE = new EstadoPropuesta(TipoE.noFinanciada, fechaDeHoy, true);
                        EstadoPropuesta EstadoAnt = prop.getEstadoActual();
                        EstadoAnt.setEsActual(false);
                        prop.setEstados(EstadoAnt);
                        prop.setEstadoActual(nuevoE);
                        this.dbPropuesta.EvaluarPropuestaBD(nuevoE, EstadoAnt, prop.getTituloP());
                    }
                }

            }
        }

    }

    public Date FechaCambioEstado(Date fechaPublicada) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fechaPublicada);
        cal.add(Calendar.DAY_OF_YEAR, 30);
        return cal.getTime();
    }

    @Override
    public Usuario CargarFavoritas(Usuario usu, List<String> usufav) {
        Set set = this.propuestas.entrySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            //recorro las propuestas
            Map.Entry mentry = (Map.Entry) it.next();
            Propuesta p = (Propuesta) mentry.getValue();
            Iterator it2 = usufav.iterator();
            //recorro las propuestas favoritas del usuario
            while (it2.hasNext()) {
                String titulo = (String) it2.next();
                //si el nombre de la propuesta concuerda con la de la favorita del usuario se la agrego.
                if (p.getTituloP().equals(titulo)) {
                    usu.getFavoritas().put(titulo, p);
                    break;
                }
            }
        }
        return usu;
    }

    @Override
    public List<DtinfoPropuesta> ListarPropuestasNoIngresadas(String nick) {
        this.EvaluarEstadosPropuestas();

        List<DtinfoPropuesta> retorno = new ArrayList<>();
        Set set = this.propuestas.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            Propuesta p = (Propuesta) mentry.getValue();
            if (p.getAutor().getNickname().equals(nick)) {
                if (p.getEstadoActual().getEstado() != TipoE.Ingresada) {
                    DtinfoPropuesta dtP = new DtinfoPropuesta(p);
                    retorno.add(dtP);
                }
            }
        }
        return retorno;
    }

    @Override
    public List<DtinfoPropuesta> ListarPropuesta() {
        this.EvaluarEstadosPropuestas();

        List<DtinfoPropuesta> propuestas = new ArrayList<>();
        Set set = this.propuestas.entrySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Map.Entry mentry = (Map.Entry) it.next();
            Propuesta p = (Propuesta) mentry.getValue();
            DtinfoPropuesta dtp = new DtinfoPropuesta(p);
            propuestas.add(dtp);
        }
        return propuestas;
    }

    @Override
    public List<DtinfoPropuesta> ListarPropuestaNOI() {
        this.EvaluarEstadosPropuestas();

        List<DtinfoPropuesta> propuestas = new ArrayList<>();
        Set set = this.propuestas.entrySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Map.Entry mentry = (Map.Entry) it.next();
            Propuesta p = (Propuesta) mentry.getValue();
            if (p.getEstadoActual().getEstado() != TipoE.Ingresada) {
                DtinfoPropuesta dtp = new DtinfoPropuesta(p);
                propuestas.add(dtp);
            }
        }
        return propuestas;
    }

    @Override
    public boolean AgregarFavorita(String titulo, String nick) {
        Boolean exito = this.dbPropuesta.AgregarFavoritas(nick, titulo);
        if (exito) {
            Iterator it = this.propuestas.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry mentry = (Map.Entry) it.next();
                Propuesta p = (Propuesta) mentry.getValue();
                if (p.getTituloP().equals(titulo)) {
                    Fabrica.getInstance().getIControladorUsuario().agregarfavorita(nick, p);
                    break;
                }
            }
        }
        return exito;
    }

    @Override
    public List<DtPropuestaWeb> ListarPropuestasWeb(TipoE estado) {
        this.EvaluarEstadosPropuestas();

        List<DtPropuestaWeb> listProp = new ArrayList<>();

        Iterator it = this.propuestas.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry mtry = (Map.Entry) it.next();
            Propuesta prop = (Propuesta) mtry.getValue();

            if (prop.getEstadoActual().getEstado() == estado) {

                Calendar fechaPub = prop.getFechaPublicacion();
                Date fPubFin = this.FechaCambioEstado(fechaPub.getTime());

                Date fechaActual = new GregorianCalendar().getTime();
                int dias = (int) ((fPubFin.getTime() - fechaActual.getTime()) / 86400000);

                int porcentaje = (int) ((this.CalcularMontoPropuesta(prop) * 100) / prop.getMontoTot());
                DtPropuestaWeb dtpropW;

                if (prop.getEstadoActual().getEstado() == TipoE.Cancelada || prop.getEstadoActual().getEstado() == TipoE.Financiada || prop.getEstadoActual().getEstado() == TipoE.noFinanciada) {
                    dtpropW = new DtPropuestaWeb(prop.getTituloP(), prop.getDescripcionP(), 0, this.CalcularMontoPropuesta(prop), porcentaje, prop.getColaboraciones().size());
                } else {
                    dtpropW = new DtPropuestaWeb(prop.getTituloP(), prop.getDescripcionP(), dias, this.CalcularMontoPropuesta(prop), porcentaje, prop.getColaboraciones().size());
                }
                listProp.add(dtpropW);
            }
        }
        return listProp;
    }

    @Override
    public List<DtinfoPropuesta> ListarPropuestasCategoria(String nombrecat) {
        List<DtinfoPropuesta> propuestas = new ArrayList<>();
        Map<String, Propuesta> prop = this.propuestas;
        Set set = prop.entrySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Map.Entry mentry = (Map.Entry) it.next();
            Propuesta p = (Propuesta) mentry.getValue();
            if (p.getCategoria().getNombreC().equals(nombrecat)) {
                DtinfoPropuesta dtp = new DtinfoPropuesta(p);
                propuestas.add(dtp);
            }
        }
        return propuestas;
    }

    @Override
    public List<DtNickTitProp> listarPropuestasComentar() {

        List<DtNickTitProp> listPropuestas = new ArrayList();

        Iterator it = this.propuestas.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry mentry = (Map.Entry) it.next();
            Propuesta prop = (Propuesta) mentry.getValue();
            if (prop.getEstadoActual().getEstado() == TipoE.Financiada) {
                DtNickTitProp dtprop = new DtNickTitProp(prop.getTituloP(), prop.getAutor().getNickname());
                listPropuestas.add(dtprop);
            }
        }
        return listPropuestas;
    }

    @Override
    public void ComentarPropuesta(String TituloP, String nickColab, String texto) throws Exception {
        Propuesta p = this.propuestas.get(TituloP);
        Comentario c = new Comentario(TituloP, nickColab, texto);

        boolean colaboroenProp = false;

        List<Comentario> comentariosProp = p.getComentarios();
        Iterator it = comentariosProp.iterator();
        while (it.hasNext()) {
            Comentario comen = (Comentario) it.next();
            if (comen.getNickColab().compareTo(nickColab) == 0) {
                throw new Exception("Solo puede comentar una unica vez la propuesta");
            }

        }

        List<Colaboracion> colaboracionesProp = p.getColaboraciones();
        Iterator it1 = colaboracionesProp.iterator();
        while (it1.hasNext()) {
            Colaboracion colab = (Colaboracion) it1.next();
            if (colab.getColaborador().getNickname().compareTo(nickColab) == 0) {
                colaboroenProp = true;
            }
        }

        if (colaboroenProp == false) {
            throw new Exception("Debe colaborar en la propuesta " + TituloP + " para poder Comentarla");
        }

        p.getComentarios().add(c);
        this.dbPropuesta.AgregarComentario(TituloP, nickColab, texto);

    }

    @Override
    public List<DtNickTitProp> ListarPropuestasX_DeProponenteX(String nick) {
        this.EvaluarEstadosPropuestas();
        List<DtNickTitProp> retorno = new ArrayList<>();
        Set set = this.propuestas.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            Propuesta p = (Propuesta) mentry.getValue();
            if (p.getAutor().getNickname().equals(nick)) {
                if (p.getEstadoActual().getEstado() == TipoE.Publicada || p.getEstadoActual().getEstado() == TipoE.enFinanciacion) {
                    DtNickTitProp dtP = new DtNickTitProp(p.getTituloP(), p.getAutor().getNickname());
                    retorno.add(dtP);
                }
            }
        }
        return retorno;
    }

    @Override
    public boolean ExtenderFinanciacion(String Titulo) {
        Iterator it = this.getPropuestas().entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry mtry = (Map.Entry) it.next();
            Propuesta prop = (Propuesta) mtry.getValue();
            if (prop.getTituloP().compareTo(Titulo) == 0) {
                EstadoPropuesta EP = prop.getEstadoPublicado();
                Calendar calendario = new GregorianCalendar();
                calendario.add(Calendar.DAY_OF_YEAR, 30);
                EP.setfechaInicio(calendario);
                this.dbPropuesta.ModificarEstadoPublicadaPropuesta(prop.getTituloP(), calendario);
                return true;
            }
        }
        return false;
    }

    public boolean Comentable(Propuesta prop, String nick) {
        Iterator it = prop.getComentarios().iterator();
        while (it.hasNext()) {
            Comentario com = (Comentario) it.next();
            if (com.getNickColab().equals(nick)) {
                return true;
            }
        }
        return false;
    }
}
