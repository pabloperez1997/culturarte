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
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
import javax.swing.text.html.HTMLDocument;
import logica.Clases.Categoria;
import logica.Clases.Colaboracion;
import logica.Clases.Colaborador;
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
import logica.Fabrica;
import logica.Interfaces.IControladorUsuario;
import logica.Interfaces.IPropCat;

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
        Map<String, Propuesta> prop = this.propuestas;
        Set set = prop.entrySet();
        Iterator iterator = set.iterator();
        List<DtNickTitProp> retorno = new ArrayList();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            Propuesta aux = (Propuesta) mentry.getValue();

            if (aux.getEstadoActual().getEstado() == TipoE.Publicada || aux.getEstadoActual().getEstado() == TipoE.enFinanciacion || aux.getEstadoActual().getEstado() == TipoE.Ingresada) {
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
    public boolean crearPropuesta(String tituloP, String descripcion, String lugar, String imagen, Calendar fecha, float montoE, float montoTot, TipoRetorno retorno) throws Exception {
        String ruta = System.getProperty("user.dir");

        if (this.propuestas.get(tituloP) != null) {
            throw new Exception("Ya existe una propuesta bajo ese Nombre");
        }

        TipoE tipo = TipoE.Ingresada;
        Calendar fechaI = new GregorianCalendar();
        EstadoPropuesta estado = new EstadoPropuesta(tipo, fechaI, true);

        Propuesta nuevaP = new Propuesta(tituloP, descripcion, imagen, lugar, fecha, montoE, montoTot, estado, this.catRecordada, retorno, this.uProponente);

        String fotoLocal = nuevaP.getImagen();
        if (!"".equals(nuevaP.getImagen())) {
            File fLocal = new File(fotoLocal);
            String ex = getFileExtension(fLocal);
            nuevaP.setImagen(tituloP + "." + ex);
        } else {
            nuevaP.setImagen("Culturarte.png");
        }

        boolean agregada = this.dbPropuesta.agregarPropuesta(nuevaP, estado);
        if (agregada) {
            this.propuestas.put(tituloP, nuevaP);
            this.catRecordada.setPropuesta(nuevaP);
            this.uProponente.setPropuesta(nuevaP);

            if (!"".equals(nuevaP.getImagen())) {
                copiarFoto(imagen, tituloP);
            }

        } else {
            this.catRecordada = null;
            this.uProponente = null;
            throw new Exception("La propuesta no pudo ser dada de alta por conflicto del sistema");
        }
        return true;
    }

    @Override
    public List<DtinfoPropuesta> ListarPropuestasDeProponenteX(String nick) {
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
        List<DtNickTitProp> listPropuestas = new ArrayList();

        Iterator it = this.propuestas.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry mentry = (Map.Entry) it.next();
            Propuesta prop = (Propuesta) mentry.getValue();
            DtNickTitProp dtprop = new DtNickTitProp(prop.getTituloP(), prop.getAutor().getNickname());
            listPropuestas.add(dtprop);
        }
        return listPropuestas;
    }

    @Override
    public EstadoPropuesta verEstadoPropuesta(String titulo) {
        EstadoPropuesta estActual = this.propuestas.get(titulo).getEstadoActual();
        return estActual;
    }

    @Override
    public DtinfoPropuesta SeleccionarPropuestaR(String titulo) {
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
    public DtConsultaPropuesta SeleccionarPropuesta(String titulo) throws Exception {

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

            return new DtConsultaPropuesta(prop.getTituloP(), prop.getCategoria().getNombreC(), prop.getLugar(), fechaR, monto, prop.getMontoE(), estado, prop.getDescripcionP(), prop.getImagen(), prop.getMontoTot(), tipoR, nick);
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
        java.util.Date utilDate = Calendar.getInstance().getTime();

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
            this.Propuesta = null;
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
        String ruta = System.getProperty("user.dir");
        File dataInputFile = new File(ruta + "//fotosdp//" + imagen);
        File fileSendPath = new File(ruta + "//fPropuestas//", dataInputFile.getName());
        try {
            Files.copy(Paths.get(dataInputFile.getAbsolutePath()), Paths.get(fileSendPath.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(ControladorPropCat.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dbPropuesta = new DBPropuesta();
        boolean agregada = this.dbPropuesta.agregarPropuestaDatosdePrueba(nuevaP);
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
}
