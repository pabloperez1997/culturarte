/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import logica.Clases.Colaborador;
import logica.Clases.DataImagen;
import logica.Clases.EstadoPropuesta;
import logica.Clases.Proponente;
import logica.Clases.TipoE;
import logica.Clases.TipoRetorno;
import logica.Clases.Usuario;
import logica.Interfaces.IPropCat;
import logica.Controladores.ControladorUsuario;
import logica.Controladores.ControladorPropCat;
import logica.Interfaces.IControladorUsuario;
import logica.Clases.codificador;
import logica.Clases.convertidorDeIMG;
import logica.Controladores.CarpetaImagenes;

/**
 *
 * @author Santiago.S
 */
public class Fabrica {

    codificador a = new codificador();
    convertidorDeIMG convertidor = new convertidorDeIMG();

    //SINGLETON
    private static Fabrica instancia;
    boolean datosCargados = false;

    public static Fabrica getInstance() {
        if (instancia == null) {
            instancia = new Fabrica();
        }
        return instancia;
    }

    private Fabrica() {
        this.cargarDatosPropuesta();
        this.cargardatosColaboracion();
    }

    public boolean limpiarBaseDeDatos() {
        IControladorUsuario ICU = this.getIControladorUsuario();
        boolean ok = ICU.limpiarBaseDeDatos();
        return ok;
    }

    public boolean getdatosCargados() {
        return this.datosCargados;
    }

    private void cargarDatosPropuesta() {
        IPropCat ip = this.getControladorPropCat();

        //ip.CargarPropuestas();
    }

    private void cargardatosColaboracion() {
        IPropCat IP = this.getControladorPropCat();
    }

    public IPropCat getControladorPropCat() {
        IPropCat ControladorPC = ControladorPropCat.getInstance();
        return ControladorPC;
    }

    public IControladorUsuario getIControladorUsuario() {
        IControladorUsuario ControladorU = ControladorUsuario.getInstance();
        return ControladorU; //To change body of generated methods, choose Tools | Templates.
    }

    public void cargarDatosdePrueba() throws IOException {
        IControladorUsuario ICU = this.getIControladorUsuario();
        IPropCat IPC = this.getControladorPropCat();
        String ruta = CarpetaImagenes.getInstance().carpetafDatosDP;
//    
//    DBUsuario d=new DBUsuario();
//    d.limpiarBD();
//           
        DataImagen foto;
        //-----------------------------------------------------Proponentes------------------------------------//
        foto = convertidor.convertirStringAImg(ruta + "hrubino.jpg", "hrubino");
        ICU.AgregarUsuarioProponente("hrubino", "Horacio", "Rubino", "horacio.rubino@guambia.com.uy", new GregorianCalendar(1962, 01, 25), foto, "18 de Julio 1234", "Horacio Rubino Torres nace el 25 de " + "febrero de 1962, es conductor, actor y libretista.  Debuta en "
                + "1982 en carnaval en Los Klaper´s, donde estuvo cuatro" + " años, actuando y libretando. Luego para \"Gaby´s\" (6 años),"
                + "escribió en categoría revistas y humoristas y desde el comienzo y hasta el presente"
                + " en su propio conjunto Momosapiens. ", "https://twitter.com/horaciorubino", a.sha1("pass1"));

        foto = convertidor.convertirStringAImg(ruta + "mbusca.jpg", "mbusca");
        ICU.AgregarUsuarioProponente("mbusca", "Martín", "Buscaglia", "Martin.bus@agadu.org.uy", new GregorianCalendar(1972, 05, 14), foto, "Colonia 4321", "Martín Buscaglia (Montevideo, 1972) es un artista, músico, compositor y productor uruguayo."
                + " Tanto con su banda (“Los Bochamakers”) como en su formato “Hombre orquesta”, o solo con su guitarra, "
                + "ha recorrido el mundo tocando entre otros países en España, Estados Unidos, Inglaterra, Francia, Australia, "
                + "Brasil, Colombia, Argentina, Chile, Paraguay, México y Uruguay. (Actualmente los Bochamakers son Matías Rada,"
                + " Martín Ibarburu, Mateo Moreno, Herman Klang) Paralelamente, tiene proyectos a dúo con el español Kiko Veneno, "
                + "la cubana Yusa, el argentino Lisandro Aristimuño, su compatriota Antolín, y a trío junto a los brasileros Os "
                + "Mulheres Negras.", "http://www.martinbuscaglia.com/", a.sha1("pass2"));

        foto = convertidor.convertirStringAImg(ruta + "hectorg.jpg", "hectorg");
        ICU.AgregarUsuarioProponente("hectorg", "Héctor", "Guido", "hector.gui@elgalpon.org.uy", new GregorianCalendar(1954, 00, 07), foto, "Gral. Flores 5645", "En 1972 ingresó a la Escuela de Arte Dramático del teatro El Galpón. Participó en más de treinta "
                + "obras teatrales y varios largometrajes. Integró el elenco estable de Radioteatro del Sodre, y en 2006 fue asesor "
                + "de su Consejo Directivo. Como actor recibió múltiples reconocimientos: cuatro premios Florencio, premio al mejor actor"
                + " extranjero del Festival de Miami y premio Mejor Actor de Cine 2008. Durante varios períodos fue directivo del teatro"
                + " El Galpón y dirigente de la Sociedad Uruguaya de Actores (SUA); integró también la Federación Uruguaya de Teatros "
                + "Independientes (FUTI). Formó parte del equipo de gestión de la refacción de los teatros La Máscara, Astral y El Galpón, "
                + "y del equipo de gestión en la construcción del teatro De la Candela y de la sala Atahualpa de El Galpón.", "", a.sha1("pass3"));

        foto = convertidor.convertirStringAImg(ruta + "tabarec.jpg", "tabarec");
        ICU.AgregarUsuarioProponente("tabarec", "Tabaré", "Cardozo", "tabare.car@agadu.org.uy", new GregorianCalendar(1971, 06, 24), foto, "Santiago Rivas 1212", "Tabaré Cardozo (Montevideo, 24 de julio de 1971) es un cantante, compositor y murguista uruguayo; "
                + "conocido por su participación en la murga Agarrate Catalina, conjunto que fundó junto a su hermano Yamandú y Carlos "
                + "Tanco en el año 2001.", "https://www.facebook.com/Tabar%C3%A9-Cardozo-55179094281/?ref=br_rs", a.sha1("pass4"));

        foto = convertidor.convertirStringAImg(ruta + "cachilas.jpg", "cachilas");
        ICU.AgregarUsuarioProponente("cachilas", "Waldemar “Cachila”", "Silva", "Cachila.sil@c1080.org.uy", new GregorianCalendar(1947, 00, 01), foto, "Br. Artigas 4567", "Nace en el año 1947 en el conventillo \"Medio Mundo\" ubicado en pleno Barrio Sur. Es heredero parcialmente-junto"
                + " al resto de sus hermanos- de la Comparsa \"Morenada\" (inactiva desde el fallecimiento de Juan Ángel Silva), en 1999 forma su "
                + "propia Comparsa de negros y lubolos \"Cuareim 1080\". Director responsable, compositor y cantante de la misma. ",
                "https://www.facebook.com/C1080?ref=br_rs", a.sha1("pass5"));

        ICU.AgregarUsuarioProponente("juliob", "Julio", "Bocca", "juliobocca@sodre.com.uy", new GregorianCalendar(1967, 02, 16), null,
                "Benito Blanco 4321", "", "", a.sha1("pass6"));

        ICU.AgregarUsuarioProponente("diegop", "Diego", "Parodi", "diego@efectocine.com", new GregorianCalendar(1975, 00, 01), null, "Emilio Frugoni 1138 Ap. 02", "", "http://www.efectocine.com", a.sha1("pass7"));

        foto = convertidor.convertirStringAImg(ruta + "kairoh.jpg", "kairoh");
        ICU.AgregarUsuarioProponente("kairoh", "Kairo", "Herrera", "kairoher@pilsenrock.com.uy", new GregorianCalendar(1840, 03, 25), foto, "Paraguay 1423", "", "", a.sha1("pass8"));

        foto = convertidor.convertirStringAImg(ruta + "durazno.png", "durazno.png");
        ICU.AgregarUsuarioProponente("durazno", "Itendencia", "Durazno", "comunicacion@durazno.gub.uy", new GregorianCalendar(1980, 9, 31), foto, "8 de Octubre 1429", "Nuestros festivales que son orgullo del Uruguay, como cada enero con lo mejor del tango o el festival de la música "
                + "tropical. La fiesta de los jóvenes, donde miles de ellos disfrutan de variados espectáculos al aire libre y se confunden para vivir "
                + "tres días inolvidables, convocados por diversos géneros, como rock, tropical, murga y tango.", "http://durazno.gub.uy/portal/index.php", a.sha1("pass9"));

////-----------------------------------------------------Colaboradores------------------------------------//
        ICU.AgregarUsuarioColaborador("robinh", "Robin", "Henderson", "Robin.h@tinglesa.com.uy", new GregorianCalendar(1940, 07, 03), null, a.sha1("pass1"));

        foto = convertidor.convertirStringAImg(ruta + "marcelot.jpg", "marcelot");
        ICU.AgregarUsuarioColaborador("marcelot", "Marcelo", "Tinelli", "marcelot@ideasdelsur.com.ar", new GregorianCalendar(1960, 03, 01), foto, a.sha1("pass2"));

        foto = convertidor.convertirStringAImg(ruta + "novick.jpg", "novick");
        ICU.AgregarUsuarioColaborador("novick", "Edgardo", "Novick", "edgardo@novick.com.uy", new GregorianCalendar(1960, 03, 01), foto, a.sha1("pass3"));

        foto = convertidor.convertirStringAImg(ruta + "sergiop.jpg", "sergiop");
        ICU.AgregarUsuarioColaborador("sergiop", "Sergio", "Puglia", "puglia@alpanpan.com.uy", new GregorianCalendar(1950, 00, 28), foto, a.sha1("pass4"));

        ICU.AgregarUsuarioColaborador("chino", "Alvaro", "Recoba", "chino@trico.org.uy", new GregorianCalendar(1976, 02, 17), null, a.sha1("pass5"));

        ICU.AgregarUsuarioColaborador("tonyp", "Antonio", "Pacheco", "eltony@manya.org.uy", new GregorianCalendar(1995, 01, 14), null, a.sha1("pass6"));

        foto = convertidor.convertirStringAImg(ruta + "nicoJ.jpg", "nicoJ");
        ICU.AgregarUsuarioColaborador("nicoJ", "Nicolás", "Jodal", "jodal@artech.com.uy", new GregorianCalendar(1960, 07, 9), foto, a.sha1("pass7"));

        ICU.AgregarUsuarioColaborador("juanP", "Juan", "Perez", "juanp@elpueblo.com", new GregorianCalendar(1970, 00, 01), null, a.sha1("pass8"));

        ICU.AgregarUsuarioColaborador("Mengano", "Mengano", "Gómez", "menganog@elpueblo.com", new GregorianCalendar(1982, 01, 02), null, a.sha1("pass9"));

        ICU.AgregarUsuarioColaborador("Perengano", "Perengano", "López", "pere@elpueblo.com", new GregorianCalendar(1985, 02, 03), null, a.sha1("pass10"));

        ICU.AgregarUsuarioColaborador("Tiajaci", "Tía", "Jacinta", "jacinta@elpueblo.com", new GregorianCalendar(1990, 03, 04), null, a.sha1("pass11"));

//-----------------------------------------------------Categorias------------------------------------//
        IPC.crearCategoriaDatosdePrueba("Categoria", null);
        IPC.crearCategoriaDatosdePrueba("Carnaval", "Categoria");
        IPC.crearCategoriaDatosdePrueba("Cine", "Categoria");
        IPC.crearCategoriaDatosdePrueba("Danza", "Categoria");
        IPC.crearCategoriaDatosdePrueba("Literatura", "Categoria");
        IPC.crearCategoriaDatosdePrueba("Musica", "Categoria");
        IPC.crearCategoriaDatosdePrueba("Teatro", "Categoria");
        IPC.crearCategoriaDatosdePrueba("Humoristas", "Carnaval");
        IPC.crearCategoriaDatosdePrueba("Lubolos", "Carnaval");
        IPC.crearCategoriaDatosdePrueba("Murga", "Carnaval");
        IPC.crearCategoriaDatosdePrueba("Parodistas", "Carnaval");
        IPC.crearCategoriaDatosdePrueba("Revista", "Carnaval");
        IPC.crearCategoriaDatosdePrueba("Cine a Pedal", "Cine");
        IPC.crearCategoriaDatosdePrueba("Cine al Aire Libre", "Cine");
        IPC.crearCategoriaDatosdePrueba("Ballet", "Danza");
        IPC.crearCategoriaDatosdePrueba("Flamenco", "Danza");
        IPC.crearCategoriaDatosdePrueba("Concierto", "Musica");
        IPC.crearCategoriaDatosdePrueba("Festival", "Musica");
        IPC.crearCategoriaDatosdePrueba("Comedia", "Teatro");
        IPC.crearCategoriaDatosdePrueba("Teatro Dramático", "Teatro");
        IPC.crearCategoriaDatosdePrueba("Teatro Musical", "Teatro");
        IPC.crearCategoriaDatosdePrueba("Stand-up", "Comedia");

//-----------------------------------------------------Propuestas------------------------------------//
        IPC.crearPropuestaDatosdePrueba("Cine en el Botánico", "El 16 de Diciembre a la hora 20 se proyectará la película \"Clever\", en el Jardín Botánico (Av. 19 de Abril 1181) "
                + "en el marco de las actividades realizadas por el ciclo Cultura al Aire Libre. El largometraje uruguayo de ficción"
                + "Clever es dirigido por Federico Borgia y Guillermo Madeiro. Es apto para mayores de 15 años.", IPC.ObtenerCategoria("Cine al Aire Libre"), new GregorianCalendar(2018, 4, 16),
                "Jardín Botánico ", 200, 150000, TipoRetorno.porGanancias, ICU.ObtenerProponente("diegop"), "Culturarte.png");

        IPC.crearPropuestaDatosdePrueba("Durazno Rock", "Una vez mas EspectaculosBA dice presente en DURAZNO ROCK! Mas de 400 personas nos acompañaron el año pasado y "
                + "este año se repite!! Este año con la presencia destacada de La Beriso!!", IPC.ObtenerCategoria("Festival"), new GregorianCalendar(2018, 11, 10),
                "Durazno", 0, 100000, TipoRetorno.porGanancias, ICU.ObtenerProponente("durazno"), "Culturarte.png");

        IPC.crearPropuestaDatosdePrueba("El Lazarillo de Tormes", "Vuelve unas de las producciones de El Galpón más aclamadas de los últimos tiempos. Esta obra se ha presentado "
                + "en Miami, Nueva York, Washington, México, Guadalajara, Río de Janeiro y La Habana. En nuestro país, El Lazarillo de Tormes fue nominado en los rubros mejor espectáculo "
                + "y mejor dirección a los Premios Florencio 1995, obteniendo su protagonista Héctor Guido el Florencio a Mejor actor de ese año.",
                IPC.ObtenerCategoria("Teatro Dramático"), new GregorianCalendar(2018, 11, 03), "Teatro el Galpón", 350, 1750000, TipoRetorno.Entradas, ICU.ObtenerProponente("hectorg"), "Culturarte.png");

        IPC.crearPropuestaDatosdePrueba("El Pimiento Indomable", "El Pimiento Indomable, formación compuesta por Kiko Veneno y el uruguayo Martín Buscaglia, presentará este 19 de Octubre, "
                + "su primer trabajo. Bajo un título homónimo al del grupo, es un disco que según los propios protagonistas “no se parece al de ninguno de los dos por separado. Entre los "
                + "títulos que se podrán escuchar se encuentran “Nadador salvador”, “América es más grande”, “Pescaito Enroscado” o “La reina del placer", IPC.ObtenerCategoria("Concierto"), new GregorianCalendar(2018, 06, 26),
                "Teatro Solís", 400, 400000, TipoRetorno.porGanancias, ICU.ObtenerProponente("mbusca"), "El Pimiento Indomable.jpg");

        IPC.crearPropuestaDatosdePrueba("Pilsen Rock", "La edición 2018 del Pilsen Rock se celebrará el 21 de Octubre en la Rural del Prado y contará con la participación de "
                + "más de 15 bandas nacionales. Quienes no puedan trasladarse al lugar, "
                + "tendrán la posibilidad de disfrutar los shows a través de Internet, así como entrevistas en vivo a los músicos una vez finalizados los conciertos.",
                IPC.ObtenerCategoria("Festival"), new GregorianCalendar(2018, 10, 15), "Rural de Prado", 1000, 900000, TipoRetorno.EntGan, ICU.ObtenerProponente("kairoh"), "Pilsen Rock.jpg");

        IPC.crearPropuestaDatosdePrueba("Religiosamente", "MOMOSAPIENS presenta Religiosamente. Mediante dos parodias y un hilo conductor que aborda la temática de la religión "
                + "Momosapiens, mediante el humor y la reflexión, hilvana una historia que muestra al hombre inmerso en el tema religioso. El libreto está escrito utilizando diferentes"
                + " lenguajes de humor, dando una visión satírica y reflexiva desde distintos puntos de vista, logrando mediante situaciones paródicas armar una propuesta plena de"
                + "arte carnavalero. ", IPC.ObtenerCategoria("Parodistas"), new GregorianCalendar(2018, 5, 18), "Teatro de Verano", 300, 300000, TipoRetorno.EntGan, ICU.ObtenerProponente("hrubino"), "Religiosamente.jpg");

        IPC.crearPropuestaDatosdePrueba("Romeo y Julieta", "Romeo y Julieta de Kenneth MacMillan, uno de los ballets favoritos del director artístico Julio Bocca, se presentará nuevamente "
                + "el 5 de Noviembre en el Auditorio Nacional del Sodre. Basada en la obra homónima de William Shakespeare, Romeo y Julieta es considerada la coreografía maestra del MacMillan. "
                + "La producción de vestuario y escenografía se realizó en los Talleres del Auditorio Adela Reta, sobre los diseños originales.", IPC.ObtenerCategoria("Ballet"), new GregorianCalendar(2018, 10, 05),
                "Auditorio Nacional del Sodre", 800, 750000, TipoRetorno.porGanancias, ICU.ObtenerProponente("juliob"), "Romeo y Julieta.jpg");

        IPC.crearPropuestaDatosdePrueba("Un día de Julio", "La Catalina presenta el espectáculo Un Día de Julio en Landia. Un hombre misterioso y solitario vive encerrado entre las cuatro paredes "
                + "de su casa. Intenta, con sus teorías extravagantes, cambiar el mundo exterior que le resulta inhabitable. Un día de Julio sucederá algo que cambiará su vida y la de su entorno "
                + "para siempre.", IPC.ObtenerCategoria("Murga"), new GregorianCalendar(2018, 10, 16), "Landia", 650, 300000, TipoRetorno.EntGan, ICU.ObtenerProponente("tabarec"), "Un día de Julio.jpg");

        //------------------------------------------Seguidores--------------------------------------------------//
        try {
            ICU.seguirUsuario("chino", "tonyp");
            ICU.seguirUsuario("diegop", "durazno");
            ICU.seguirUsuario("diegop", "hrubino");
            ICU.seguirUsuario("durazno", "hrubino");
            ICU.seguirUsuario("durazno", "nicoJ");
            ICU.seguirUsuario("hectorg", "juliob");
            ICU.seguirUsuario("hectorg", "mbusca");
            ICU.seguirUsuario("hrubino", "diegop");
            ICU.seguirUsuario("hrubino", "durazno");
            ICU.seguirUsuario("hrubino", "hectorg");
            ICU.seguirUsuario("juliob", "diegop");
            ICU.seguirUsuario("juliob", "mbusca");
            ICU.seguirUsuario("kairoh", "sergiop");
            ICU.seguirUsuario("marcelot", "cachilas");
            ICU.seguirUsuario("marcelot", "juliob");
            ICU.seguirUsuario("marcelot", "kairoh");
            ICU.seguirUsuario("mbusca", "cachilas");
            ICU.seguirUsuario("mbusca", "kairoh");
            ICU.seguirUsuario("mbusca", "tabarec");
            ICU.seguirUsuario("Mengano", "hectorg");
            ICU.seguirUsuario("Mengano", "juliob");
            ICU.seguirUsuario("Mengano", "sergiop");
            ICU.seguirUsuario("nicoJ", "cachilas");
            ICU.seguirUsuario("nicoJ", "kairoh");
            ICU.seguirUsuario("nicoJ", "tabarec");
            ICU.seguirUsuario("novick", "cachilas");
            ICU.seguirUsuario("novick", "hrubino");
            ICU.seguirUsuario("novick", "tabarec");
            ICU.seguirUsuario("Perengano", "diegop");
            ICU.seguirUsuario("Perengano", "tonyp");
            ICU.seguirUsuario("robinh", "diegop");
            ICU.seguirUsuario("robinh", "hectorg");
            ICU.seguirUsuario("robinh", "juliob");
            ICU.seguirUsuario("sergiop", "diegop");
            ICU.seguirUsuario("sergiop", "juliob");
            ICU.seguirUsuario("sergiop", "mbusca");
            ICU.seguirUsuario("tabarec", "cachilas");
            ICU.seguirUsuario("tabarec", "hrubino");
            ICU.seguirUsuario("Tiajaci", "juliob");
            ICU.seguirUsuario("Tiajaci", "kairoh");
            ICU.seguirUsuario("Tiajaci", "sergiop");
            ICU.seguirUsuario("cachilas", "hrubino");
        } catch (Exception ex) {
            Logger.getLogger(Fabrica.class.getName()).log(Level.SEVERE, null, ex);
        }

//------------------------------------------Colaboraciones--------------------------------------------------//
        IPC.agregarColaboracionDatosdePrueba("Cine en el Botánico", "novick", 50000, new GregorianCalendar(2018, 04, 20, 14, 30, 0), false);
        IPC.agregarColaboracionDatosdePrueba("Cine en el Botánico", "robinh", 50000, new GregorianCalendar(2018, 04, 24, 17, 25, 0), false);
        IPC.agregarColaboracionDatosdePrueba("Cine en el Botánico", "nicoJ", 50000, new GregorianCalendar(2018, 04, 30, 18, 30, 0), false);

        IPC.agregarColaboracionDatosdePrueba("Religiosamente", "marcelot", 200000, new GregorianCalendar(2018, 05, 30, 14, 25, 0), false);
        IPC.agregarColaboracionDatosdePrueba("Religiosamente", "Tiajaci", 500, new GregorianCalendar(2018, 06, 01, 18, 05, 0), true);
        IPC.agregarColaboracionDatosdePrueba("Religiosamente", "Mengano", 600, new GregorianCalendar(2018, 06, 07, 17, 45, 0), true);
        IPC.agregarColaboracionDatosdePrueba("Religiosamente", "novick", 50000, new GregorianCalendar(2018, 06, 10, 14, 35, 0), false);
        IPC.agregarColaboracionDatosdePrueba("Religiosamente", "sergiop", 50000, new GregorianCalendar(2018, 06, 15, 9, 45, 0), false);

        IPC.agregarColaboracionDatosdePrueba("El Pimiento Indomable", "marcelot", 200000, new GregorianCalendar(2018, 7, 01, 07, 40, 0), false);
        IPC.agregarColaboracionDatosdePrueba("El Pimiento Indomable", "sergiop", 80000, new GregorianCalendar(2018, 7, 03, 9, 25, 0), false);

        IPC.agregarColaboracionDatosdePrueba("Pilsen Rock", "chino", 50000, new GregorianCalendar(2018, 10, 25, 16, 50, 0), true);
        IPC.agregarColaboracionDatosdePrueba("Pilsen Rock", "novick", 120000, new GregorianCalendar(2018, 10, 30, 15, 50, 0), false);
        IPC.agregarColaboracionDatosdePrueba("Pilsen Rock", "tonyp", 120000, new GregorianCalendar(2018, 11, 05, 19, 30, 0), true);

        IPC.agregarColaboracionDatosdePrueba("Romeo y Julieta", "sergiop", 100000, new GregorianCalendar(2018, 10, 8, 04, 58, 0), false);
        IPC.agregarColaboracionDatosdePrueba("Romeo y Julieta", "marcelot", 200000, new GregorianCalendar(2018, 10, 9, 11, 25, 0), false);

        IPC.agregarColaboracionDatosdePrueba("Un día de Julio", "tonyp", 30000, new GregorianCalendar(2018, 10, 8, 04, 48, 0), true);
        IPC.agregarColaboracionDatosdePrueba("Un día de Julio", "marcelot", 150000, new GregorianCalendar(2018, 10, 9, 15, 30, 0), false);
//---------------------------------------------- Pagos --------------------------------------------------------//

        IPC.CargarPagosTarjetaDP("novick", "Cine en el Botánico", "Oca", "1234567845672017", new GregorianCalendar(2018, 06, 30), 123, "Edgardo Novick");
        IPC.CargarPagosTarjetaDP("robinh", "Cine en el Botánico", "Oca", "1234567823452017", new GregorianCalendar(2018, 07, 30), 123, "Robin Henderson");
        IPC.CargarPagosTarjetaDP("nicoj", "Cine en el Botánico", "Visa", "1234567834562017", new GregorianCalendar(2018, 8, 30), 123, "Nicolás Jodal");
        IPC.CargarPagosTarjetaDP("marcelot", "Religiosamente", "Visa", "1234567845672017", new GregorianCalendar(2018, 9, 30), 123, "Marcelo Tinelli");
        IPC.CargarPagosTarjetaDP("tiajaci", "Religiosamente", "Master", "1234567856782017", new GregorianCalendar(2018, 10, 30), 123, "Tía Jacinta");
        IPC.CargarPagosTarjetaDP("Mengano", "Religiosamente", "Master", "1234567867892017", new GregorianCalendar(2018, 11, 30), 123, "Mengano Gómez");

        IPC.CargarPagosTransfPayDP("novick", "Religiosamente", "BROU", "1234567890", "Edgardo Novick");
        IPC.CargarPagosTransfPayDP("sergiop", "Religiosamente", "BROU", "2345678901", "Sergio Puglia");
        IPC.CargarPagosTransfPayDP("marcelot", "El Pimiento Indomable", "Santander", "3456789012", "Marcelo Tinelli");
        IPC.CargarPagosTransfPayDP("sergiop", "El Pimiento Indomable", "Santander", "4567890123", "Sergio Puglia");
        IPC.CargarPagosTransfPayDP("chino", "Pilsen Rock", "HSBC", "5678901234", "Alvaro Recoba");
        IPC.CargarPagosTransfPayDP("novick", "Pilsen Rock", "HSBC", "6789012345", "Edgardo Novick");
        IPC.CargarPagosTransfPayDP("tonyp", "Pilsen Rock", null, "1234567890", "Antonio Pacheco");
        IPC.CargarPagosTransfPayDP("tonyp", "Un día de Julio", null, "4567890123", "Antonio Pacheco");
        IPC.CargarPagosTransfPayDP("marcelot", "Un día de Julio", null, "5678901234", "Marcelo Tinelli");

//------------------------------------------Estado Propuesta--------------------------------------------------//
        IPC.nuevoEstadoPropuestaDatosdePrueba("Cine en el Botánico", TipoE.Ingresada, new GregorianCalendar(2018, 04, 16, 15, 30, 0));
        IPC.nuevoEstadoPropuestaDatosdePrueba("Cine en el Botánico", TipoE.Publicada, new GregorianCalendar(2018, 04, 17, 8, 30, 0));
        IPC.nuevoEstadoPropuestaDatosdePrueba("Cine en el Botánico", TipoE.enFinanciacion, new GregorianCalendar(2018, 04, 20, 14, 30, 0));
        IPC.nuevoEstadoPropuestaDatosdePrueba("Cine en el Botánico", TipoE.Financiada, new GregorianCalendar(2018, 05, 16, 18, 30, 0));
        IPC.setEstadoActualDatosDePrueba("Cine en el Botánico", new EstadoPropuesta(TipoE.Cancelada, new GregorianCalendar(2018, 05, 20, 14, 50, 0), true));

        IPC.nuevoEstadoPropuestaDatosdePrueba("Religiosamente", TipoE.Ingresada, new GregorianCalendar(2018, 05, 18, 4, 28, 0));
        IPC.nuevoEstadoPropuestaDatosdePrueba("Religiosamente", TipoE.Publicada, new GregorianCalendar(2018, 05, 20, 4, 56, 0));
        IPC.nuevoEstadoPropuestaDatosdePrueba("Religiosamente", TipoE.enFinanciacion, new GregorianCalendar(2018, 05, 30, 14, 25, 0));
        IPC.setEstadoActualDatosDePrueba("Religiosamente", new EstadoPropuesta(TipoE.Financiada, new GregorianCalendar(2018, 06, 20, 9, 45, 0), true));

        IPC.nuevoEstadoPropuestaDatosdePrueba("El Pimiento Indomable", TipoE.Ingresada, new GregorianCalendar(2018, 06, 26, 15, 30, 0));
        IPC.nuevoEstadoPropuestaDatosdePrueba("El Pimiento Indomable", TipoE.Publicada, new GregorianCalendar(2018, 06, 31, 8, 30, 0));
        IPC.nuevoEstadoPropuestaDatosdePrueba("El Pimiento Indomable", TipoE.enFinanciacion, new GregorianCalendar(2018, 7, 01, 7, 40, 0));
        IPC.setEstadoActualDatosDePrueba("El Pimiento Indomable", new EstadoPropuesta(TipoE.noFinanciada, new GregorianCalendar(2018, 7, 30, 8, 30), true));

        IPC.nuevoEstadoPropuestaDatosdePrueba("Pilsen Rock", TipoE.Ingresada, new GregorianCalendar(2018, 10, 15, 15, 40, 0));
        IPC.nuevoEstadoPropuestaDatosdePrueba("Pilsen Rock", TipoE.Publicada, new GregorianCalendar(2018, 10, 20, 14, 30, 0));
        IPC.setEstadoActualDatosDePrueba("Pilsen Rock", new EstadoPropuesta(TipoE.enFinanciacion, new GregorianCalendar(2018, 10, 20, 16, 50, 0), true));

        IPC.nuevoEstadoPropuestaDatosdePrueba("Romeo y Julieta", TipoE.Ingresada, new GregorianCalendar(2018, 10, 05, 12, 20, 0));
        IPC.nuevoEstadoPropuestaDatosdePrueba("Romeo y Julieta", TipoE.Publicada, new GregorianCalendar(2018, 10, 06, 10, 25, 0));
        IPC.setEstadoActualDatosDePrueba("Romeo y Julieta", new EstadoPropuesta(TipoE.enFinanciacion, new GregorianCalendar(2018, 10, 8, 04, 58, 0), true));

        IPC.nuevoEstadoPropuestaDatosdePrueba("Un día de Julio", TipoE.Ingresada, new GregorianCalendar(2018, 10, 16, 02, 00, 0));
        IPC.nuevoEstadoPropuestaDatosdePrueba("Un día de Julio", TipoE.Publicada, new GregorianCalendar(2018, 10, 17, 04, 50, 0));
        IPC.setEstadoActualDatosDePrueba("Un día de Julio", new EstadoPropuesta(TipoE.enFinanciacion, new GregorianCalendar(2018, 10, 18, 04, 48, 0), true));

        IPC.nuevoEstadoPropuestaDatosdePrueba("El Lazarillo de Tormes", TipoE.Ingresada, new GregorianCalendar(2018, 11, 03, 02, 40, 0));
        IPC.setEstadoActualDatosDePrueba("El Lazarillo de Tormes", new EstadoPropuesta(TipoE.Publicada, new GregorianCalendar(2018, 11, 9, 02, 40, 0), true));

        IPC.setEstadoActualDatosDePrueba("Durazno Rock", new EstadoPropuesta(TipoE.Ingresada, new GregorianCalendar(2018, 11, 10, 2, 12, 0), true));
        //------------------------------------------Comentarios Propuesta--------------------------------------------------//

        try {
            IPC.ComentarPropuesta("Cine en el Botánico", "novick", "Muy buena propuesta.");
            IPC.ComentarPropuesta("Cine en el Botánico", "robinh", "Realmente una pena que la propuesta haya sido cancelada.");
            IPC.ComentarPropuesta("Cine en el Botánico", "nicoJ", "No se lo pueden perder!");

            IPC.ComentarPropuesta("Religiosamente", "marcelot", "Todos al teatro de verano este 7 de Octubre!");
            IPC.ComentarPropuesta("Religiosamente", "Mengano", "Arriba Momosapiens!!!");
            IPC.ComentarPropuesta("Religiosamente", "sergiop", "Los conmino a todos a ir!");
            IPC.ComentarPropuesta("Religiosamente", "novick", "Excelente propuesta. Ahí estaremos.");
        } catch (Exception ex) {
            Logger.getLogger(Fabrica.class.getName()).log(Level.SEVERE, null, ex);
        }
        IPC.AgregarFavorita("Religiosamente", "hrubino");
        IPC.AgregarFavorita("El Pimiento Indomable", "hrubino");
        IPC.AgregarFavorita("Un día de Julio", "hrubino");
        IPC.AgregarFavorita("Cine en el Botánico", "mbusca");
        IPC.AgregarFavorita("El Pimiento Indomable", "mbusca");
        IPC.AgregarFavorita("Pilsen Rock", "mbusca");
        IPC.AgregarFavorita("Romeo y Julieta", "hectorg");
        IPC.AgregarFavorita("El Lazarillo de Tormes", "hectorg");
        IPC.AgregarFavorita("Religiosamente", "tabarec");
        IPC.AgregarFavorita("Un día de Julio", "tabarec");
        IPC.AgregarFavorita("Religiosamente", "cachilas");
        IPC.AgregarFavorita("Romeo y Julieta", "juliob");
        IPC.AgregarFavorita("El Lazarillo de Tormes", "juliob");
        IPC.AgregarFavorita("Cine en el Botánico", "diegop");
        IPC.AgregarFavorita("El Lazarillo de Tormes", "diegop");
        IPC.AgregarFavorita("Religiosamente", "kairoh");
        IPC.AgregarFavorita("Pilsen Rock", "kairoh");
        IPC.AgregarFavorita("Durazno Rock", "durazno");
        IPC.AgregarFavorita("Cine en el Botánico", "robinh");
        IPC.AgregarFavorita("Religiosamente", "marcelot");
        IPC.AgregarFavorita("El Pimiento Indomable", "marcelot");
        IPC.AgregarFavorita("Religiosamente", "novick");
        IPC.AgregarFavorita("Pilsen Rock", "novick");
        IPC.AgregarFavorita("El Pimiento Indomable", "sergiop");
        IPC.AgregarFavorita("Romeo y Julieta", "sergiop");
        IPC.AgregarFavorita("Pilsen Rock", "chino");
        IPC.AgregarFavorita("Pilsen Rock", "tonyp");
        IPC.AgregarFavorita("Un día de Julio", "tonyp");
        IPC.AgregarFavorita("Cine en el Botánico", "nicoJ");
        IPC.AgregarFavorita("Pilsen Rock", "juanP");
        IPC.AgregarFavorita("Religiosamente", "Mengano");
        IPC.AgregarFavorita("Un día de Julio", "Mengano");
        IPC.AgregarFavorita("Pilsen Rock", "Perengano");
        IPC.AgregarFavorita("Un día de Julio", "Perengano");
        IPC.AgregarFavorita("Religiosamente", "Tiajaci");
        IPC.AgregarFavorita("El Lazarillo de Tormes", "Tiajaci");
//    
        this.datosCargados = true;

    }

    public void LimpiarLogica() {

        IControladorUsuario ICU = this.getIControladorUsuario();
        IPropCat IPC = this.getControladorPropCat();

        Map<String, Usuario> usuarios = ICU.getUsuarios();
        Set set = usuarios.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            if (mentry.getValue() instanceof Proponente) {
                Proponente p = (Proponente) mentry.getValue();
                ICU.borrarProponente(p.getNickname());
                iterator.remove();//aca se van borrando los usuarios proponentes
            }

            if (mentry.getValue() instanceof Colaborador) {
                Colaborador c = (Colaborador) mentry.getValue();
                ICU.borrarColaborador(c.getNickname());
                iterator.remove();//aca se van borrando los usuarios colaboradores
            }

        }
        ICU.getUsuarios().clear();
        IPC.getPropuestas().clear();//para limpiar el map dsp que borre todo
        ICU.eliminarCategorias();

    }

}
